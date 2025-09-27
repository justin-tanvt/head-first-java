import javax.swing.JTextField;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClientA {

	private JTextField outgoing;
	private PrintWriter writer;

	public static void main(String[] args) {
		new SimpleChatClientA().go();
	}

	public void go() {
		System.out.println("in go() method!");
		setUpNetworking();
	}

	private void setUpNetworking() {
		System.out.println("in setUpNetworking() method!");
		InetSocketAddress serverAddress = new InetSocketAddress(
				SimpleChatConstants.IP_ADDRESS, SimpleChatConstants.PORT_NUMBER);
		try (SocketChannel channel = SocketChannel.open(serverAddress)) {
			writer = new PrintWriter(Channels.newWriter(channel, UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() {

	}
}