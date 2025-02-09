import javax.swing.*;
import java.awt.*;

public class TestDriveMyDrawPanel {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MyDrawPanel myPanel = new MyDrawPanel();
		frame.getContentPane().add(myPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}