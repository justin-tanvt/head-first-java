import javax.swing.*;
import java.awt.*;

public class TwoButtons {

	private JFrame frame;
	private JLabel label;

	public static void main(String[] args) {
		TwoButtons gui = new TwoButtons();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// panel
		MyDrawPanel panel = new MyDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, panel);

		// panel button
		JButton changeColorButton = new JButton("Change Circle");
		changeColorButton.addActionListener(e -> frame.repaint());
		frame.getContentPane().add(BorderLayout.SOUTH, changeColorButton);

		// label
		label = new JLabel("I'm a label");
		frame.getContentPane().add(BorderLayout.WEST, label);

		// label button
		JButton changeLabelButton = new JButton("Change Label");
		changeLabelButton.addActionListener(event -> label.setText("Ouch!"));
		frame.getContentPane().add(BorderLayout.EAST, changeLabelButton);

		frame.setSize(500,400);
		frame.setVisible(true);
	}

}