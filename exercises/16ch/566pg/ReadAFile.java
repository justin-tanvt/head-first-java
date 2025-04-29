import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class ReadAFile {

	private static final String FILE_PATH = "MyText.txt";

	public static void main(String[] args) {
		// option 1 - old java.io
		File myFile = new File(FILE_PATH);
		try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to read file!", e);
		}

		// option 2 - Java 8 and Streams API
		try (Stream<String> lines = Files.lines(Path.of(FILE_PATH))) {
			lines.forEach(l -> System.out.println(l));
		} catch (IOException e) {
			throw new RuntimeException("Failed to read file!", e);
		}
	}

}