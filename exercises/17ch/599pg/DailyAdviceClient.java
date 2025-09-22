import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient {
  public void go() {
    InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8080);
    try (SocketChannel socketChannel = SocketChannel.open(serverAddress)) {
      Reader channelReader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(channelReader);

      String advice = reader.readLine();
      System.out.println("Advice of the day: \"" + advice + "\"");

      reader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    new DailyAdviceClient().go();
  }
}
