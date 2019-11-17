package boundary;

/**
 * Interface for other potential account views to be easily implemented: To easily add more boundaries/views to the application
 */
public interface View{
	
	/**
	 * Prints out the view depending on the type of user
	 * @return
	 */
	public abstract int printView();
}
