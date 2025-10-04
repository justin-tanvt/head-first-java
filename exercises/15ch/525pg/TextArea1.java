import javax.swing.*;
import java.awt.*;

public class TextArea1 {

	public static void main(String[] args) {
		TextArea1 gui = new TextArea1();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Text Panel
    JPanel panel = new JPanel();
    JTextArea text = new JTextArea(10, 20);
    JScrollPane scroller = new JScrollPane(text);
    text.setLineWrap(true);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    panel.add(scroller);
    frame.getContentPane().add(BorderLayout.CENTER, panel);

    // Button
    JButton button = new JButton("Just Click It");
    frame.getContentPane().add(BorderLayout.SOUTH, button);
    button.addActionListener(event -> {
      text.append("button clicked\n");
    });

    frame.setSize(350, 300);
    frame.setVisible(true);
	}
}