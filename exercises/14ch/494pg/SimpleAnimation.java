import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SimpleAnimation {
	private int xPos = 70;
	private int yPos = 70;

	public static void main(String[] args) {
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		MyDrawPanel panel = new MyDrawPanel();
		frame.getContentPane().add(panel);
		frame.setSize(300, 300);
		frame.setVisible(true);

		for (int i = 0; i < 130; i++) {
			xPos++;
			yPos++;

			panel.repaint();

			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}

	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.green);
			g.fillOval(xPos, yPos, 40, 40);
		}
	}
}