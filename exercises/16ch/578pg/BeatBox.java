import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.ShortMessage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class BeatBox {

  // music settings
  private static final int TRACK_TEMPO_IN_BPM = 120;
  private static final int TRACK_LENGTH_IN_BEATS = 16;
  private static final int BEAT_LENGTH_IN_TICKS = 1;
  private static final int NOTE_VELOCITY = 100;
  private static final int TEMPO_ADJUSTMENT_PERCENTAGE = 3;

  // display settings
  private static final int MAIN_DISPLAY_PANEL_PADDING = 10;
  private static final int HORIZONTAL_SPACING_IN_GRID = 2;
  private static final int VERTICAL_SPACING_IN_GRID = 1;
  private static final int HORIZONTAL_PADDING_INSTRUMENT_LABELS = 1;
  private static final int VERTICAL_SPACING_INSTRUMENT_LABELS = VERTICAL_SPACING_IN_GRID + 3; // do not modify

  // music constants
  private static final int TRACK_MIDI_CHANNEL = 9;
  private static final int CONTROLLER_EVENT_MIDI_CHANNEL = 1;
  private static final int CONTROLLER_EVENT_NOTE = 127;
  private static final int NOTE_OFF_CONST = 0;

  // savefile constants
  private static final String SAVEFILE_NAME = "Checkbox.ser";

  private Sequencer sequencer;
  private Sequence sequence;
  private Track track;
  private ArrayList<JCheckBox> checkboxList;
  private final Instrument[] instruments = Instrument.values();
  private final int instrumentsCount = instruments.length;
  private final int checkboxCount = TRACK_LENGTH_IN_BEATS * instrumentsCount;

  public static void main(String[] args) {
    new BeatBox().buildGUI();
  }

  public void buildGUI() {
    // initialise main GUI elements
    // - frame
    JFrame frame = new JFrame("Cyber BeatBox");
    // -- main display panel
    JPanel display = new JPanel(new BorderLayout());
    // --- instrument names
    Box nameBox = new Box(BoxLayout.Y_AXIS);
    // --- instrument checkboxes grid
    GridLayout grid = new GridLayout(instrumentsCount, TRACK_LENGTH_IN_BEATS);
    grid.setVgap(VERTICAL_SPACING_IN_GRID);
    grid.setHgap(HORIZONTAL_SPACING_IN_GRID);
    JPanel checkboxPanel = new JPanel(grid);
    // --- control panel
    Box buttonBox = new Box(BoxLayout.Y_AXIS);

    // link and arrange main GUI elements
    display.add(BorderLayout.WEST, nameBox);
    display.add(BorderLayout.CENTER, checkboxPanel);
    display.add(BorderLayout.EAST, buttonBox);
    frame.getContentPane().add(display);

    // configure main GUI elements
    // - frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(50, 50, 300, 300);
    // -- main display panel
    display.setBorder(BorderFactory.createEmptyBorder(
        MAIN_DISPLAY_PANEL_PADDING,
        MAIN_DISPLAY_PANEL_PADDING,
        MAIN_DISPLAY_PANEL_PADDING,
        MAIN_DISPLAY_PANEL_PADDING));
    // --- instrument names
    for (Instrument instrument : instruments) {
      JLabel nameLabel = new JLabel(instrument.getName());
      nameLabel.setBorder((BorderFactory.createEmptyBorder(
          VERTICAL_SPACING_INSTRUMENT_LABELS,
          HORIZONTAL_PADDING_INSTRUMENT_LABELS,
          VERTICAL_SPACING_INSTRUMENT_LABELS,
          HORIZONTAL_PADDING_INSTRUMENT_LABELS
      )));
      nameBox.add(nameLabel);
    }
    // --- instrument checkboxes
    checkboxList = new ArrayList<>();
    for (int i = 0; i < checkboxCount; i++) {
      JCheckBox c = new JCheckBox();
      c.setSelected(false);
      checkboxList.add(c);
      checkboxPanel.add(c);
    }
    // --- control panel
    JButton start = new JButton("Start");
    start.addActionListener(e -> buildTrackAndStart());
    JButton stop = new JButton("Stop");
    stop.addActionListener(e -> sequencer.stop());
    JButton upTempo = new JButton("Tempo Up");
    upTempo.addActionListener(e -> changeTempo(1.0f * (100 + TEMPO_ADJUSTMENT_PERCENTAGE) / 100));
    JButton downTempo = new JButton("Tempo Down");
    downTempo.addActionListener(e -> changeTempo(1.0f * (100 - TEMPO_ADJUSTMENT_PERCENTAGE) / 100));
    JButton writeFile = new JButton("serializeIt");
    writeFile.addActionListener(e -> writeFile());
    JButton readFile = new JButton("restore");
    readFile.addActionListener(e -> readFile());
    buttonBox.add(start);
    buttonBox.add(stop);
    buttonBox.add(upTempo);
    buttonBox.add(downTempo);
    buttonBox.add(writeFile);
    buttonBox.add(readFile);

    setUpMidi();

    // display GUI
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);
  }

  private void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(TRACK_TEMPO_IN_BPM);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private void changeTempo(float tempoMultiplier) {
    sequencer.setTempoFactor(sequencer.getTempoFactor() * tempoMultiplier);
  }

  private void buildTrackAndStart() {
    // reset to fresh track
    sequence.deleteTrack(track);
    track = sequence.createTrack();

    // loop through each instrument to collect ON/OFF for each beat from checkboxes
    int[] noteOnOffList;
    for (int i = 0; i < instrumentsCount; i++) {
      int instrumentMidiChannel = instruments[i].getMidiChannel();

      noteOnOffList = new int[TRACK_LENGTH_IN_BEATS];
      int checkboxListRowOffset = i * TRACK_LENGTH_IN_BEATS;
      for (int j = 0; j < TRACK_LENGTH_IN_BEATS; j++) {
        JCheckBox c = checkboxList.get(checkboxListRowOffset + j);
        if (c.isSelected()) {
          noteOnOffList[j] = instrumentMidiChannel;
        } else {
          noteOnOffList[j] = NOTE_OFF_CONST;
        }
      }
      addInstrumentToTrack(noteOnOffList);
    }

    // add empty CONTROL_CHANGE event on final beat
    // else sequencer might loop before full track length if no notes selected at the end
    track.add(makeEvent(CONTROL_CHANGE, CONTROLLER_EVENT_MIDI_CHANNEL, CONTROLLER_EVENT_NOTE, 0, TRACK_LENGTH_IN_BEATS));

    try {
      sequencer.setSequence(sequence);
      sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
      sequencer.setTempoInBPM(TRACK_TEMPO_IN_BPM);
      sequencer.start();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  private void addInstrumentToTrack(int[] noteOnOffList) {
    for (int i = 0; i < TRACK_LENGTH_IN_BEATS; i++) {
      int status = noteOnOffList[i];

      if (status != NOTE_OFF_CONST) {
        track.add(makeEvent(NOTE_ON, TRACK_MIDI_CHANNEL, status, NOTE_VELOCITY, i * BEAT_LENGTH_IN_TICKS));
        track.add(makeEvent(NOTE_OFF, TRACK_MIDI_CHANNEL, status, NOTE_VELOCITY, (i + 1) * BEAT_LENGTH_IN_TICKS));
      }
    }
  }

  public static MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage msg = new ShortMessage();
      msg.setMessage(command, channel, one, two);
      event = new MidiEvent(msg, tick);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
    return event;
  }

  private void writeFile() {
    boolean[] checkboxState = new boolean[checkboxCount];

    for (int i = 0; i < checkboxCount; i++) {
      if (checkboxList.get(i).isSelected()) {
        checkboxState[i] = true;
      }
    }

    try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(SAVEFILE_NAME))) {
      os.writeObject(checkboxState);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void readFile() {
    boolean[] checkboxState = null;

    try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(SAVEFILE_NAME))) {
      checkboxState = (boolean[]) is.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (int i = 0; i < checkboxCount; i++) {
      checkboxList.get(i).setSelected(checkboxState[i]);
    }

    sequencer.stop();
    buildTrackAndStart();
  }
}
