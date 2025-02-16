import javax.swing.*;
import java.awt.*;

public class Button1 {

	private JFrame frame;

	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showFrame() {
		frame.setSize(200, 200);
		frame.setVisible(true);
	}

	public void showFrame(int width, int height) {
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	public void go515() {
		JButton button = new JButton("click like you mean it");
		frame.getContentPane().add(BorderLayout.EAST, button);
		showFrame();
	}

	public void go516a() {
		JButton button = new JButton("There is no spoon...");
		frame.getContentPane().add(BorderLayout.NORTH, button);
		showFrame();
	}

	public void go516b() {
		JButton button = new JButton("Click This!");
		Font bigFont = new Font("serif", Font.BOLD, 28);
		button.setFont(bigFont);
		frame.getContentPane().add(BorderLayout.NORTH, button);
		showFrame();
	}

	public void go517() {
		JButton east = new JButton("East");                                                                                                                                              
		JButton west = new JButton("West");                                                                                                                                              
		JButton north = new JButton("North");                                                                                                                                            
		JButton south = new JButton("South");                                                                                                                                            
		JButton center = new JButton("Center");                                                                                                                                          
		                                                                                                                                                                                 
		frame.getContentPane().add(BorderLayout.EAST, east);                                                                                                                             
		frame.getContentPane().add(BorderLayout.WEST, west);                                                                                                                             
		frame.getContentPane().add(BorderLayout.NORTH, north);                                                                                                                           
		frame.getContentPane().add(BorderLayout.SOUTH, south);                                                                                                                           
		frame.getContentPane().add(BorderLayout.CENTER, center);

		showFrame(300, 300);
	}
	
	public static void main(String[] args) {
		Button1 gui = new Button1();
		gui.go517();
	}

}