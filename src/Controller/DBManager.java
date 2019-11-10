package Controller;

import Entity.MOBLIMA;

/**
 * DataBase Manager (DBManager) enables us to add new ways of saving data
 * such as to a CSV file by extending from this interface
 * @author vince
 *
 */
public interface DBManager {
	abstract MOBLIMA loadData(String filename);
	abstract void saveData(String filename, MOBLIMA app);
	
}
