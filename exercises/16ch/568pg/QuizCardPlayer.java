import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardPlayer {

	// configurable settings
	private static final String WINDOW_NAME = "Quiz Card Player";
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 400;
	private static final int TEXT_AREA_HEIGHT = 10;
	private static final int TEXT_AREA_WIDTH = 20;
	private static final String FONT_NAME = "sanserif";
	private static final int FONT_SIZE = 24;

	// constants
	private static final String BUTTON_LABEL_SHOW_ANSWER = "Show Answer";
	private static final String BUTTON_LABEL_NEXT_QUESTION = "Next Question";

	private ArrayList<QuizCard> cardList = new ArrayList<>();
	private int currentCardIndex;
	private QuizCard currentCard;
	private boolean isShowQuestion = false;
	private JFrame frame;
	private JTextArea textArea;
	private JButton nextButton;

	public static void main(String[] args) {
		QuizCardPlayer p = new QuizCardPlayer();
		p.cardList.add(new QuizCard("foo1", "bar1"));
		p.cardList.add(new QuizCard("foo2", "bar2"));
		p.go();
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
		Font bigFont = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		textArea = new JTextArea(TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(bigFont);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// create button
		nextButton = new JButton(BUTTON_LABEL_SHOW_ANSWER);
		nextButton.setEnabled(false);
		nextButton.addActionListener(e -> nextCard());

		// arrange main GUI elements
		JPanel panel = new JPanel();
		panel.add(scroller);
		panel.add(nextButton);
		frame.getContentPane().add(BorderLayout.CENTER, panel);

		// configure and display frame
		frame.setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void nextCard() {
		// if not showing question, load new quizcard and show question
		if (!isShowQuestion) {
			currentCardIndex++;
			if (currentCardIndex < cardList.size()) {
				currentCard = cardList.get(currentCardIndex);
				textArea.setText(currentCard.getQuestion());
				nextButton.setText(BUTTON_LABEL_SHOW_ANSWER);
				nextButton.setEnabled(true);
				isShowQuestion = true;
			// no cards to show, disable button
			} else {
				nextButton.setEnabled(false);	
			}

		// if already showing question, show answer
		} else {
			textArea.setText(currentCard.getAnswer());
			nextButton.setText(BUTTON_LABEL_NEXT_QUESTION);
			isShowQuestion = false;

			// disable button if no more cards left to show
			if (currentCardIndex == cardList.size() - 1) {
				nextButton.setEnabled(false);
			}
		}
	}

	private void open() {
		// prompt user to select save file
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(frame);
		// load from file
		// loadFile(fileChooser.getSelectedFile());
		// show card
		currentCardIndex = -1;
		nextCard();
	}

	private void loadFile(File file) {
		// read file line by line
		// for each line, make card
	}

	private void makeCard(String lineToParse) {
		// split line by separator
		// if two string parts, create card and save to cardlist 
	}

}