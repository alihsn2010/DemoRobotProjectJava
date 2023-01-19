package Main;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoRobot {

	// Global Variable Initialized
	public static Pattern roomPattern = Pattern.compile("(?<x>[0-9]+) (?<y>[0-9]+)");
	public static Pattern PostionPattern = Pattern.compile("(?<x>[0-9]+) (?<y>[0-9]+) (?<z>[NESW])");
	public static Pattern DirectionPattern = Pattern.compile("^[RFL]*$");

	// Main Function
	public static void main(String[] args) {

		System.out.println("Robot Demo");
		RobotModelHelper ModelHelper = new RobotModelHelper();

		// Step 1 : Get Room Size (Wired Room)
		// -----------------------------------
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Width Height of Room eg: 5 5");
			String strRoomSizeValue = input.nextLine();
			Matcher roomPatternMatcher = roomPattern.matcher(strRoomSizeValue);
			String[] strRoom = strRoomSizeValue.split(" ");
			
			//Checks the lengh of inputs
			if(strRoom.length >2 || strRoom.length == 1) {
				throw new RobotValidate("Only two inputs are allowed");
			}
			
			
			int x = parseInt(strRoom[0]);
			int y = parseInt(strRoom[1]);
			setParamRoom setRoom = new setParamRoom(x, y);
			System.out.println(format("Room Size is x:[%s] y:[%s] ", x, y));

			// Step 2 Input Robot Position
			// -----------------------------------
			System.out.println("Enter Robot Program Instruction eg: 1 2 N ");
			String strPosition = input.nextLine();
			Matcher CheckInputPostionMatcher = PostionPattern.matcher(strPosition);
			String[] Position = strPosition.split(" ");
			int PositionX = parseInt(Position[0]);
			int PositionY = parseInt(Position[1]);
			String directionName = ModelHelper.getDirectionFullName(Position[2]);
			
			// Validate Position pattern
			if(CheckInputPostionMatcher.find()==false) {
				throw new RobotValidate("Invalid pattern or patterns found");
			}

			// Validate the robot position in room limit
			if (x < PositionX || y < PositionY || x < -1 || y < -1 || x <=0 || y <=0 ) 
			{
				throw new RobotValidate(format("Room Size is x:[%s] y:[%s] and given position is PositionX:[%s] PositionY:[%s]", x,y,PositionX, PositionY));
			}
			if (PositionX <=0 || PositionY <=0 ) 
			{
				throw new RobotValidate(format("Room Size is x:[%s] y:[%s] and given position is PositionX:[%s] PositionY:[%s] : Robot is Out of Position", x,y,PositionX, PositionY));
			}

			// Step 3 Input Robot Direction Input
			// -----------------------------------
			System.out.println("Enter Robot Moving Direction eg: RFRFFRFRF");
			String setRobotCommandList = input.nextLine();
			Matcher directionMatcher = DirectionPattern.matcher(setRobotCommandList);
			
			// If any invalid character(s) are found
			if(directionMatcher.find()==false) {
				throw new RobotValidate("Invalid pattern or patterns found");
			}
			String[] listMove = setRobotCommandList.split("");

			// Step 4 Robot Command Execution
			// ------------------------------
			// Test Cases
			// Test Case 1
			// 5 5
			// 1 2 N
			// R-F-R-F-F-R-F-R-F
			// 1 3 N

			// Test Case 2
			// 5 5
			// 1 1 E
			// RFLFFLRF
			// 4 2 E
			// ------------------------------

			RobotModelCommand objModel = new RobotModelCommand();
			objModel.setvalueX(PositionX);
			objModel.setvalueY(PositionY);
			objModel.setOldDirection(directionName);
			for (String strCommand : listMove) {

				String getTempDirection = "";
				if (strCommand.equals("R")) {
					System.out.println(format("Command :[%s] ", strCommand));
					System.out.println(format("Old Direction :[%s] ", objModel.getOldDirection()));
					getTempDirection = Right(objModel.getOldDirection());
					objModel.setOldDirection(getTempDirection);
					System.out.println(format("New Direction is :[%s] ", objModel.getOldDirection()));
					System.out.println(format("x is :[%s] ", objModel.getvalX()));
					System.out.println(format("y is :[%s] ", objModel.getvalY()));
					System.out.println(format("-------------------------------------"));
				}

				if (strCommand.equals("L")) {
					System.out.println(format("Command :[%s] ", strCommand));
					System.out.println(format("Old Direction :[%s] ", objModel.getOldDirection()));
					getTempDirection = Left(objModel.getOldDirection());
					objModel.setOldDirection(getTempDirection);
					System.out.println(format("New Direction is :[%s] ", objModel.getOldDirection()));
					System.out.println(format("x is :[%s] ", objModel.getvalX()));
					System.out.println(format("y is :[%s] ", objModel.getvalY()));
					System.out.println(format("-------------------------------------"));
				}

				if (strCommand.equals("F")) {
					// objForward myObj1 = new objForward();
					System.out.println(format("Command :[%s] ", strCommand));
					objModel = DirectionForward(objModel.getOldDirection(), objModel.getvalX(), objModel.getvalY());
					System.out.println(format(" New Direction is :[%s] ", objModel.getOldDirection()));
					System.out.println(format(" X is :[%s] ", objModel.getvalX()));
					System.out.println(format(" Y is :[%s] ", objModel.getvalY()));
					System.out.println(format("-------------------------------------"));
				}
			}
			String outName = ModelHelper.getDirectionShortName(objModel.getOldDirection());
			// setDirection direction1 = direction(objModel.getOldDirection());
			System.out.println(format("-------------------------------------"));
			System.out.println(
					format(" Robot Final Destination Result :[%s]  [%s]  [%s] ", objModel.getvalX(), objModel.getvalY(), outName));
			System.out.println("--------------");
			System.out.println("End Demo Robot");
		} catch (RobotValidate robotValidate) {
			robotValidate.printStackTrace();
		}
	}

	// Right forward

	public static RobotModelCommand DirectionForward(String direction, int x, int y) {

		RobotModelCommand myObj = new RobotModelCommand();
		if (direction.equals("NORTH")) {
			y--;
			myObj.setvalueY(y);
			myObj.setOldDirection(direction);
			myObj.setvalueX(x);
			return (myObj);
		}
		if (direction.equals("SOUTH")) {
			y++;
			myObj.setvalueY(y);
			myObj.setOldDirection(direction);
			myObj.setvalueX(x);
			return (myObj);
		}
		if (direction.equals("EAST")) {
			x++;
			myObj.setvalueX(x);
			myObj.setOldDirection(direction);
			myObj.setvalueY(y);
			return (myObj);
		}
		if (direction.equals("WEST")) {
			x--;
			myObj.setvalueX(x);
			myObj.setOldDirection(direction);
			myObj.setvalueY(y);
			return (myObj);
		} else {
			return (myObj);
		}
	}

	// Right Direction

	public static String Right(String direction) {
		if (direction.equals("NORTH")) {
			return "EAST";
		}

		if (direction.equals("EAST")) {
			return "SOUTH";
		}
		if (direction.equals("SOUTH")) {
			return "WEST";
		}
		if (direction.equals("WEST")) {
			return "NORTH";
		} else {
			return "\"Error in string\"";
		}
	}

	// Left Direction

	public static String Left(String name) {
		if (name.equals("NORTH")) {
			return "WEST";
		}
		if (name.equals("EAST")) {
			return "NORTH";
		}
		if (name.equals("SOUTH")) {
			return "EAST";
		}
		if (name.equals("WEST")) {
			return "SOUTH";
		} else {
			return "\"Error in string\"";
		}
	}

}
