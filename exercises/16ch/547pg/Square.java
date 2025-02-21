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
		mySquare = Square.load(filename);
		System.out.println(mySquare);
		System.out.println(mySquare.width + ", " + mySquare.height);
	}

	private boolean save(String filename) {
    boolean success = false;
    ObjectOutputStream os = null;
		try {
			FileOutputStream fs = new FileOutputStream(filename);
			os = new ObjectOutputStream(fs);
			os.writeObject(this);
      success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
      if (os != null) {
        try {
          os.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
		return success;
	}

	private static Square load(String filename) {
		Square s = null;
    ObjectInputStream is = null;
		try {
			FileInputStream fs = new FileInputStream(filename);
			is = new ObjectInputStream(fs);
			Object o = is.readObject();
			if (o instanceof Square) {
				s = (Square) o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
		return s;
	}

}
