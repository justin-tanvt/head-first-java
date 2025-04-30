import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardBuilder {

	// configurable settings
	private static final String WINDOW_NAME = "Quiz Card Builder";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 600;
	private static final int TEXT_AREA_HEIGHT = 6;
	private static final int TEXT_AREA_WIDTH = 20;
	private static final String FONT_NAME = "sanserif";
	private static final int FONT_SIZE = 24;

	private ArrayList<QuizCard> cardList = new ArrayList<>();
	private JFrame frame;
	private JTextArea question;
	private JTextArea answer;

	public static void main(String[] args) {
		new QuizCardBuilder().go();
	}

	public void go() {
		frame = new JFrame(WINDOW_NAME);

		// create question and answer text area
		Font bigFont = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		question = createTextArea(bigFont);
		JScrollPane qScroller = createScroller(question);
		answer = createTextArea(bigFont);
		JScrollPane aScroller = createScroller(answer);

		// create button
		JButton nextButton = new JButton("Next Card");
		nextButton.addActionListener(e -> nextCard());

		// create and set menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(e -> clearAll());
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(e -> saveCard());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		// arrange main GUI elements
		JPanel mainPanel = new JPanel();
		mainPanel.add(new JLabel("Question:"));
		mainPanel.add(qScroller);
		mainPanel.add(new JLabel("Answer:"));
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

		// configure and display frame
		frame.setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private JScrollPane createScroller(JTextArea textArea) {
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return scroller;
	}

	private JTextArea createTextArea(Font font) {
		JTextArea textArea = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(font);
		return textArea;
	}

	private void nextCard() {
		QuizCard card = new QuizCard(question.getText(), answer.getText());
		cardList.add(card);
		clearCard();
	}

	private void saveCard() {
		QuizCard card = new QuizCard(question.getText(), answer.getText());
		cardList.add(card);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(frame);
		saveFile(fileChooser.getSelectedFile());
	}

	private void clearAll() {
		cardList.clear();
		clearCard();
	}

	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}

	private void saveFile(File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (QuizCard card : cardList) {
				bw.write(card.getQuestion() + QuizCardConstants.SAVEFILE_QUESTION_ANSWER_SEPARATOR);
				bw.write(card.getAnswer() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Couldn't write the cardList out: " + e.getMessage());
		}
	}

}