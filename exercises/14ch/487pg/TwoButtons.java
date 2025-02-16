import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		changeColorButton.addActionListener(new ColorListener());
		frame.getContentPane().add(BorderLayout.SOUTH, changeColorButton);

		// label
		label = new JLabel("I'm a label");
		frame.getContentPane().add(BorderLayout.WEST, label);

		// label button
		JButton changeLabelButton = new JButton("Change Label");
		changeLabelButton.addActionListener(new LabelListener());
		frame.getContentPane().add(BorderLayout.EAST, changeLabelButton);

		frame.setSize(500,400);
		frame.setVisible(true);
	}

	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Ouch!");
		}
	}

	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	}

}