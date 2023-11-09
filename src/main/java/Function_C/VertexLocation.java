package Function_C;

public class VertexLocation {
	  public  int x;
	  public  int y;

	  public VertexLocation(int x, int y) {
	    this.x = x; 
	    this.y = y; 
	  } 
	  public void updateLocation(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }

	  public boolean isSame(VertexLocation other){
		  return (this.x == other.getX()) && (this.y == other.getY());
	  }
	  public int getX(){
		  return x;
	  }
	  public int getY(){
		  return y;
	  }

		  
} 