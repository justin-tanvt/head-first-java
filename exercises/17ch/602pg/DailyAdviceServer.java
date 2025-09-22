import java.net.InetSocketAddress;
import java.util.Random;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Channels;
import java.io.IOException;
import java.io.PrintWriter;

public class DailyAdviceServer {

	final private String[] ADVICE_LIST = {
		"Take smaller bites",
		"Go for the tight jeans. No they do NOT make you look fat.",
		"One word: inappropriate",
		"Just for today, be honest. Tell your boss what you *really* think",
		"You might want to rethink that haircut."
	};

	private final Random random = new Random();

	public void go() {
		System.out.println("DailyAdviceServer started!");

		try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
			serverChannel.bind(new InetSocketAddress(8080));

			while (serverChannel.isOpen()) {
				SocketChannel clientChannel = serverChannel.accept();
				PrintWriter writer = new PrintWriter(Channels.newOutputStream(clientChannel));

				String advice = getAdvice();
				writer.println(advice);
				System.out.println("Just gave this advice: \"" + advice + "\"");
				writer.close();
				clientChannel.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private String getAdvice() {
		int nextAdvice = random.nextInt(ADVICE_LIST.length);
		return ADVICE_LIST[nextAdvice];
	}

	public static void main(String[] args) {
		new DailyAdviceServer().go();
	}
}