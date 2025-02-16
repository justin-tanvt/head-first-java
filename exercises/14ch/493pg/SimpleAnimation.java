import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {
	public static void main(String[] args) {
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MyAnimatedPanel animPanel = new MyAnimatedPanel();
		frame.getContentPane().add(BorderLayout.CENTER, animPanel.getDrawPanel());

		frame.setSize(500, 500);
		frame.setVisible(true);

		for (int t = 0; t < 100; t++) {
			animPanel.changePosition(5, 5);
			frame.repaint();
			try {
			  Thread.sleep(100);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}			
		}
	}
}