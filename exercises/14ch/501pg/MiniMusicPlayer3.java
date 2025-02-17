import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.sound.midi.ShortMessage.NOTE_ON;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.CONTROL_CHANGE;

public class MiniMusicPlayer3 {

	private static final int PANEL_WIDTH = 300;
	private static final int PANEL_HEIGHT = 300;

	private static final int SEQUENCER_TEMPO_BPM = 120;
	private static final int NUMBER_OF_NOTES = 16;
	private static final int NOTE_LENGTH = 4;
	private static final int TICKS_BETWEEN_NOTES = 2;
	private static final int MIDI_INSTRUMENT = 1;
	private static final int NOTE_VOLUME = 100;
	private static final int CONTROLLER_EVENT_NOTE = 127;

	private JFrame frame;
	private MyDrawPanel panel;
	private Random random = new Random();

	public static void main(String[] args) {
		MiniMusicPlayer3 mini = new MiniMusicPlayer3();
		mini.go();
	}

	public void setUpGui() {
		frame = new JFrame("My First Music Video");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		panel = new MyDrawPanel();
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void go() {
		setUpGui();

		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addControllerEventListener(panel, new int[]{CONTROLLER_EVENT_NOTE});
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			int note;
			int ticksBetweenNoteOns = NOTE_LENGTH + TICKS_BETWEEN_NOTES;
			for (int i = 0; i < NUMBER_OF_NOTES * ticksBetweenNoteOns; i += ticksBetweenNoteOns) {
				note = random.nextInt(50) + 20;
				track.add(makeEvent(NOTE_ON, MIDI_INSTRUMENT, note, NOTE_VOLUME, i));
				track.add(makeEvent(CONTROL_CHANGE, 2, CONTROLLER_EVENT_NOTE, 0, i));	
				track.add(makeEvent(NOTE_OFF, MIDI_INSTRUMENT, note, NOTE_VOLUME, i + NOTE_LENGTH));
			}

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(SEQUENCER_TEMPO_BPM);
			sequencer.start();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
		return event;
	}

	class MyDrawPanel extends JPanel implements ControllerEventListener {
		private static final int WIDTH_PADDING = 10;
		private static final int HEIGHT_PADDING = 10;
		private static final int RECTANGLE_MIN_WIDTH = 10;
		private static final int RECTANGLE_MIN_HEIGHT = 10;

		private static final int XPOS_MAX = PANEL_WIDTH - WIDTH_PADDING - RECTANGLE_MIN_WIDTH;
		private static final int YPOS_MAX = PANEL_HEIGHT - HEIGHT_PADDING - RECTANGLE_MIN_HEIGHT;
		private static final int XPOS_VARIABLE = XPOS_MAX - WIDTH_PADDING;
		private static final int YPOS_VARIABLE = YPOS_MAX - HEIGHT_PADDING;

		private boolean msg = false;

		public MyDrawPanel() {
			this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		}

		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();	
		}	

		public void paintComponent(Graphics g) {
			if (msg) {
				int r = random.nextInt(250);
				int gr = random.nextInt(250);
				int b = random.nextInt(250);
				g.setColor(new Color(r, gr, b));

				int xPos = WIDTH_PADDING + random.nextInt(XPOS_VARIABLE);
				int yPos = HEIGHT_PADDING + random.nextInt(YPOS_VARIABLE);

				int widthVariable = PANEL_WIDTH - xPos - RECTANGLE_MIN_WIDTH - WIDTH_PADDING;
				int heightVariable = PANEL_HEIGHT - yPos - RECTANGLE_MIN_HEIGHT - HEIGHT_PADDING;
				int width = RECTANGLE_MIN_WIDTH + random.nextInt(widthVariable);
				int height = RECTANGLE_MIN_HEIGHT + random.nextInt(heightVariable);

				g.fillRect(xPos, yPos, width, height);
				msg = false;
			}
		}
	}

}