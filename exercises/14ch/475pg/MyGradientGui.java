import javax.swing.*;

public class MyGradientGui {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel widget = new MyGradientWidget();
		frame.getContentPane().add(widget);

		frame.setVisible(true);
	}
}