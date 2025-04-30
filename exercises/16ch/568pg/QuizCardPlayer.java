import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardPlayer {

	// configurable settings
	private static final String WINDOW_NAME = "Quiz Card Player";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 600;
	private static final int TEXT_AREA_HEIGHT = 20;
	private static final int TEXT_AREA_WIDTH = 20;
	private static final String FONT_NAME = "sanserif";
	private static final int FONT_SIZE = 24;

	private ArrayList<QuizCard> cardList = new ArrayList<>();
	private JFrame frame;
	private JTextArea textArea;

	public static void main(String[] args) {
		new QuizCardPlayer().go();
	}

	public void go() {
		frame = new JFrame(WINDOW_NAME);

		// create and set menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.addActionListener(e -> open());
		fileMenu.add(openMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		// create text area

		// create button

		// arrange main GUI elements

		// configure and display frame
		frame.setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void nextCard() {}

	private void open() {}

	private void loadFile(File file) {}

	private void makeCard(String lineToParse) {}

}