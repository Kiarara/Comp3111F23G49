package Function_C;

/**
 * The VertexLocation is a class that indicates the location on a grid
 *
 * @author PENG Xinyin(Kiara)
 */
public class VertexLocation {

	/**
	 * the row of location
	 */
	public  int x;

	/**
	 * the coloum of location
	 */
	public  int y;

	/**
	 * Constructs a new VertexLocation with the specified x and y coordinates.
	 *
	 * @param x The x-coordinate of the location.
	 * @param y The y-coordinate of the location.
	 */
	  public VertexLocation(int x, int y) {
	    this.x = x; 
	    this.y = y; 
	  }

	/**
	 * Constructs a new VertexLocation by copying the coordinates from another VertexLocation object.
	 *
	 * @param other The VertexLocation object to copy the coordinates from.
	 */
	  public VertexLocation(VertexLocation other){
		  this.x = other.x;
		  this.y = other.y;
	  }

	/**
	 * Updates the location with new x and y coordinates
	 *
	 * @param x The new x-coordinate.
	 * @param y The new y-coordinate.
	 */
	  public void updateLocation(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }

	/**
	 * Checks whether the current location is the same as another VertexLocation object.
	 *
	 * @param other The VertexLocation object to compare against.
	 * @return true if the locations have the same x and y coordinates, false otherwise.
	 */
	  public boolean isSame(VertexLocation other){
		  return (this.x == other.x) && (this.y == other.y);
	  }
		  
} 