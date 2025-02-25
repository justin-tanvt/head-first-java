import java.io.FileWriter;

public class WriteAFile {
	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("Foo.txt");
			writer.write("hello foo!\n");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}