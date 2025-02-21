import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Square implements Serializable {

	private int width;
	private int height;

	public Square(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static void main(String[] args) {
		String filename = "Square.ser";

		Square mySquare = new Square(50, 20);
		System.out.println(mySquare);
		System.out.println(mySquare.width + ", " + mySquare.height);
		mySquare.save(filename);

		mySquare = null;
		mySquare = Square.open(filename);
		System.out.println(mySquare);
		System.out.println(mySquare.width + ", " + mySquare.height);
	}

	private boolean save(String filename) {
		try {
			FileOutputStream fs = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(this);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static Square open(String filename) {
		Square s = null;
		try {
			FileInputStream fs = new FileInputStream(filename);
			ObjectInputStream os = new ObjectInputStream(fs);
			Object o = os.readObject();
			if (o instanceof Square) {
				s = (Square) o;
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}