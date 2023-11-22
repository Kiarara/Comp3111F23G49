package Function_C;

/**
 * The VertexLocation is a class that indicates the location on a grid
 */
public class VertexLocation {
	  public  int x;
	  public  int y;

	  // constructor
	  public VertexLocation(int x, int y) {
	    this.x = x; 
	    this.y = y; 
	  }

	  // copy constructor
	  public VertexLocation(VertexLocation other){
		  this.x = other.x;
		  this.y = other.y;
	  }

	  // update the location with given new x and y
	  public void updateLocation(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }

	  // check whether the current location is the same as another one
	  public boolean isSame(VertexLocation other){
		  return (this.x == other.x) && (this.y == other.y);
	  }
		  
} 