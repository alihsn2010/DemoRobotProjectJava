package Main;

import static java.lang.String.format;

public  class RobotModelHelper {
	
	
	 public static String getDirectionFullName(String Strvalue) {
		    switch (Strvalue) {
		      case "N":
		        return "NORTH";
		      case "E":
		        return "EAST";
		      case "S":
		        return "SOUTH";
		      case "W":
		        return "WEST";
		      default:
		        throw new IllegalArgumentException(format("Unknown direction [%s]", Strvalue));
		    }
		  }	 
	 
	 public  static String getDirectionShortName(String Strvalue) {
		    switch (Strvalue) {
		      case "NORTH":
		        return "N";
		      case "EAST":
		        return "E";
		      case "SOUTH":
		        return "S";
		      case "WEST":
		        return "W";
		      default:
		        throw new IllegalArgumentException(format("Unknown direction [%s]", Strvalue));
		    }
		  }	
		  

}
