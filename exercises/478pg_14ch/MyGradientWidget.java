import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyGradientWidget extends JPanel {
	public void paintComponent(Graphics g) {
		if (!(g instanceof Graphics2D)) {
			return;
		}

		Graphics2D g2d = (Graphics2D) g;
		Random random = new Random();

		// color 1
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		Color startColour = new Color(red, green, blue);

		// color 2
		red = random.nextInt(256);
		green = random.nextInt(256);
		blue = random.nextInt(256);
		Color endColour = new Color(red, green, blue);

		GradientPaint gradient = new GradientPaint(70, 70, startColour, 150, 150, endColour);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}