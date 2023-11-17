package Function_C;

public class VertexLocation {
	  public  int x=-1;
	  public  int y=-1;

	  public VertexLocation(int x, int y) {
	    this.x = x; 
	    this.y = y; 
	  }

	  public VertexLocation(VertexLocation other){
		  this.x = other.x;
		  this.y = other.y;
	  }

	  public void updateLocation(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }

	  public boolean isSame(VertexLocation other){
		  return (this.x == other.x) && (this.y == other.y);
	  }
		  
} 