package Main;


public class RobotModelCommand {
	
	  private int valueX; // private = restricted access

	  // Get
	  public int getvalX() {
	    return valueX;
	  }

	  // Set
	  public void setvalueX(int newvalueX) {
	    this.valueX = newvalueX;
	  }
	  
	  
	  private int valueY; // private = restricted access

	  // Get
	  public int getvalY() {
	    return valueY;
	  }

	  // Set
	  public void setvalueY(int newvalueY) {
	    this.valueY = newvalueY;
	  }
	  
	  private String valueOldDirection; // private = restricted access

	  // Get
	  public String getOldDirection() {
	    return valueOldDirection;
	  }

	  // Set
	  public void setOldDirection(String newDirection) {
	    this.valueOldDirection = newDirection;
	  }
	}