package Entity;

/**
 * DataBase Manager (DBManager) enables us to add new ways of saving data
 * such as to a CSV file by extending from this interfacc=e
 * @author vince
 *
 */
public interface DBmanager {
	abstract MOBLIMA loadData(String filename);
	abstract void saveData(String filename, MOBLIMA app);
	
}
