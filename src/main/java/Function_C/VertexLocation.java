package Function_C;

public class VertexLocation {
	  public  int x;
	  public  int y;

	  public VertexLocation(int x, int y) {
	    this.x = x; 
	    this.y = y; 
	  } 
	  public void UpdateLocation(int x, int y){
		    this.x = x; 
		    this.y = y; 
	  }

	  public boolean isSame(VertexLocation a, VertexLocation b){
		  return (a.getX() == b.getX()) && (a.getY() == b.getY());
	  }
	  public int getX(){
		  return x;
	  }
	  public int getY(){
		  return y;
	  }

		  
} 