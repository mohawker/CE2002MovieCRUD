package controller;

import entity.MOBLIMA;

/**
 * DataBase Manager (DBManager) enables us to add new ways of saving data such as to a CSV file by extending from this interface
 */
public interface DBManager {
	
	/**
	 * Used to load data into the app
	 * @param filename - File from which data is to be loaded from
	 * @return MOBLIMA app with the data loaded
	 */
	abstract MOBLIMA loadData(String filename);
	
	/**
	 * Used to store data into a file
	 * @param filename - File for the data to be saved to
	 * @param app - MOBLIMA app with the data needed to be stored
	 */
	abstract void saveData(String filename, MOBLIMA app);
}
