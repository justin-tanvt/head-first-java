import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyAnimatedPanel {

	private int x = 20;
	private int y = 50;
	private MyDrawPanel drawPanel = new MyDrawPanel();

	public void changePosition(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
	}

	public MyDrawPanel getDrawPanel() {
		return drawPanel;
	}

	public class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.orange);
			g.fillOval(x, y, 100, 100);
		}
	}
}
