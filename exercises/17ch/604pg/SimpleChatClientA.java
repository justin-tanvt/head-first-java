import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClientA {

  private JTextField outgoing;
  private SocketChannel channel;
  private PrintWriter writer;

  private static final String GUI_WINDOW_NAME = "Ludicrously Simple Chat Client";
  private static final int GUI_WINDOW_LENGTH = 400;
  private static final int GUI_WINDOW_HEIGHT = 100;
  private static final int GUI_TEXTFIELD_LENGTH = 20;
  private static final String GUI_BUTTON_TEXT = "Send";

  public static void main(String[] args) {
    System.out.println(LocalDateTime.now() + " > " + "SimpleChatClientA" + "[" + Thread.currentThread().getName() + "]" + " -- " + "main()");
    new SimpleChatClientA().go();
  }

  public void go() {
    debug("go()");
    setUpNetworking();
    setUpGui();
  }

  private void setUpNetworking() {
    debug("setUpNetworking()");
    InetSocketAddress serverAddress = new InetSocketAddress(
        SimpleChatConstants.IP_ADDRESS, SimpleChatConstants.PORT_NUMBER);
    debug("trying to open socket channel next");
    try {
      channel = SocketChannel.open(serverAddress);
      debug("opened socket channel");
      writer = new PrintWriter(Channels.newWriter(channel, UTF_8));
      debug("created writer");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("failed to set up networking!");
    }
  }

  private void setUpGui() {
    debug("setUpGui()");
    outgoing = new JTextField(GUI_TEXTFIELD_LENGTH);

    JButton sendButton = new JButton(GUI_BUTTON_TEXT);
    sendButton.addActionListener(e -> sendMessage());

    JPanel panel = new JPanel();
    panel.add(outgoing);
    panel.add(sendButton);

    JFrame frame = new JFrame(GUI_WINDOW_NAME);
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setSize(GUI_WINDOW_LENGTH, GUI_WINDOW_HEIGHT);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private void sendMessage() {
    debug("sendMessage()");
    writer.println(outgoing.getText());
    debug("sent message to server");
    writer.flush();
    outgoing.setText("");
    debug("reset text field");
    outgoing.requestFocus();
  }

  private void debug(String message) {
    System.out.println(LocalDateTime.now() + " > " + this.getClass().getName() + "[" + Thread.currentThread().getName() + "]" + " -- " + message);
  }
}