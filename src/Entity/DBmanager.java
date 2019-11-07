package Entity;

public interface DBmanager {
	abstract MOBLIMA loadData(String filename);
	abstract void saveData(String filename, MOBLIMA app);
	
}
