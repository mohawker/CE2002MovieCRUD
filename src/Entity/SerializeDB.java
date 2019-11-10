package Entity;


import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.DBManager;

/**
 * Implements DBManager, enables loading and saving of data
 * @author vince
 *
 */
public class SerializeDB implements DBManager{
	
	public MOBLIMA loadData(String filename) {
		MOBLIMA app = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			app = (MOBLIMA) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return app;
	}

	public void saveData(String filename, MOBLIMA app) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(app);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
