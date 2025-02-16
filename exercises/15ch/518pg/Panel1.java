import javax.swing.*;
import java.awt.*;

public class Panel1 {

	private JFrame frame;
	private JPanel panel;

	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void tearDown() {
		frame.setSize(250, 200);
		frame.setVisible(true);
	}

	public void go() {
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		pg518Decorator();
		pg520DecoratorA();
		// pg520DecoratorB();
		pg521Decorator();
		frame.getContentPane().add(BorderLayout.EAST, panel);
		tearDown();
	}

	public void pg518Decorator() {
		JButton button = new JButton("shock me");
		panel.add(button);
	}

	public void pg520DecoratorA() {
		JButton button2 = new JButton("bliss");
		panel.add(button2);
	}

	public void pg520DecoratorB() {
		JButton button3 = new JButton("huh?");
		panel.add(button3);
	}

	public void pg521Decorator() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	public static void main(String[] args) {
		Panel1 gui = new Panel1();
		gui.go();
	}

}