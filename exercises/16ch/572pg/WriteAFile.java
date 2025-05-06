import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriteAFile {
  public static void main(String[] args) {
    Path myPath = Paths.get("MyFile.txt");
    try {
      BufferedWriter writer = Files.newBufferedWriter(myPath);
      writer.write("Hello World!\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
