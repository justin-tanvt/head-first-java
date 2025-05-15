public enum Instrument {

	BASS_DRUM("Bass Drum", 35),
	CLOSED_HIHAT("Closed Hi-Hat", 42),
	OPEN_HIHAT("Open Hi-Hat", 46),
	ACOUSTIC_SNARE("Acoustic Snare", 38),
	CRASH_CYMBAL("Crash Cymbal", 49),
	HAND_CLAP("Hand Clap", 39),
  HIGH_TOM("High Tom", 50),
  HI_BONGO("Hi Bongo", 60),
  MARACAS("Maracas", 70),
  WHISTLE("Whistle", 72),
  LOW_CONGA("Low Conga", 64),
  COWBELL("Cowbell", 56),
  VIBRASLAP("Vibraslap", 58),
  LOWMID_TOM("Low-mid Tom", 47),
  HIGH_AGOGO("High Agogo", 67),
  OPEN_HI_CONGA("Open Hi Conga", 63);

	private final String name;
	private final int midiChannel;

	Instrument(String name, int midiChannel) {
		this.name = name;
		this.midiChannel = midiChannel;
	}

	public String getName() {
		return this.name;
	}

	public int getMidiChannel() {
		return this.midiChannel;
	}
}
