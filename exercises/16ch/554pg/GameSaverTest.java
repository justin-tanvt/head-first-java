import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class GameSaverTest {
	public static void main(String[] args) {
		// initialise characters	
		GameCharacter[] characters = new GameCharacter[3];
		characters[0] = new GameCharacter(50, "Elf", 
			new String[]{"bow", "sword", "dust"});
		characters[1] = new GameCharacter(200, "Troll", 
			new String[]{"bare hands", "big ax"});
		characters[2] = new GameCharacter(120, "Magician", 
			new String[]{"spells", "invisibility"});

		// filename
		final String filename = "Game.ser";

		// save characters into file
		int successfulSaves = 0;
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			for (GameCharacter c : characters) {
				oos.writeObject(c);
				successfulSaves++;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// load characters from file
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			for (int i = 1; i <= successfulSaves; i++) {
				GameCharacter c = (GameCharacter) ois.readObject();
				System.out.println(" Character #" + i + " type: " + c.getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}	
}