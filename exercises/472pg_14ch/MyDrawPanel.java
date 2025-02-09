import javax.swing.*;
import java.awt.*;
import java.util.Random;

class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		// draw rectangle
		// g.setColor(Color.orange);
		// g.fillRect(20,50,100,100);

		// display a JPEG
		// Image image = new ImageIcon("catzilla.jpg").getImage();
		// g.drawImage(image, 3, 4, this);

		// draw randomly coloured circle on black background
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Random random = new Random();
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		Color randomColour = new Color(red, green, blue);
		g.setColor(randomColour);
		g.fillOval(70, 70, 100, 100);

		if (g instanceof Graphics2D) {
			System.out.println("Graphics upcasted to Graphics2D!");
			Graphics2D g2d = (Graphics2D) g;
			g2d.rotate(90);
		}
	}
}