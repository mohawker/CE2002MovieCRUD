package Entity;


import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.DBManager;

/**
 * Implements DBManager, enables loading and saving of data
 */
public class SerializeDB implements DBManager{
	
	/**
	 * Implements the loadData abstract method in the DBManager interface
	 */
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

	/**
	 * Implements the saveData abstract method in the DBManager interface
	 */
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
