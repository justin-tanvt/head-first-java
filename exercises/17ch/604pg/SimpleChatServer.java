import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatServer {
  private final List<PrintWriter> clientWriters = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(LocalDateTime.now() + " > " + "SimpleChatServer" + "[" + Thread.currentThread().getName() + "]" + " -- " + "main()");
    new SimpleChatServer().go();
  }

  private void go() {
    debug("in go()");
    ExecutorService threadPool = Executors.newCachedThreadPool();
    debug("created cached thread pool");
    try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
      debug("opened server socket channel");
      serverSocketChannel.bind(new InetSocketAddress(SimpleChatConstants.PORT_NUMBER));
      debug("bound server socket channel to port");

      while (serverSocketChannel.isOpen()) {
        debug("waiting to accept client socket");
        SocketChannel clientSocket = serverSocketChannel.accept();
        debug("accepted client socket");
        clientWriters.add(new PrintWriter(Channels.newWriter(clientSocket, UTF_8)));
        debug("created client writer");
        threadPool.submit(new ClientHandler(clientSocket));
        debug("created client handler and added to thread pool");
        debug("got a connection");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void tellEveryone(String message) {
    debug("in tellEveryone()");
    for (PrintWriter writer : clientWriters) {
      writer.println(message);
      writer.flush();
    }
  }

  private void debug(String message) {
    System.out.println(LocalDateTime.now() + " > " + this.getClass().getName() + "[" + Thread.currentThread().getName() + "]" + " -- " + message);
  }

  public class ClientHandler implements Runnable {
    BufferedReader reader;
    SocketChannel socket;

    public ClientHandler(SocketChannel clientSocket) {
      debug("in constructor");
      socket = clientSocket;
      reader = new BufferedReader(Channels.newReader(socket, UTF_8));
    }

    public void run() {
      debug("in run()");
      String message;
      debug("going to start reading messages from reader in a while-loop");
      try {
        while (true) {
          message = reader.readLine();
          if (message == null) {
            debug("received null message from reader, exiting reader while-loop");
            break;
          }
          debug("received non-null message from reader");
          System.out.println("received from client : " + message);
          tellEveryone(message);
          debug("broadcasted message to clients");
        }
        debug("exited reader while-loop");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void debug(String message) {
      System.out.println(LocalDateTime.now() + " > " + this.getClass().getName() + "[" + Thread.currentThread().getName() + "]" + " -- " + message);
    }
  }
}