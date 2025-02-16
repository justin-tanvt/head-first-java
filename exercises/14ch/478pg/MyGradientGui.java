import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MyGradientGui implements ActionListener {

	private JFrame frame;

	public static void main(String[] args) {
		MyGradientGui gui = new MyGradientGui();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel widget = new MyGradientWidget();
		frame.getContentPane().add(BorderLayout.CENTER, widget);

		JButton button = new JButton("Change colors");
		button.addActionListener(this);
		frame.getContentPane().add(BorderLayout.SOUTH, button);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		frame.repaint();
	}

}