package RoomBookingSystem.functions;

import java.util.Scanner;

import RoomBookingSystem.Menu;
import RoomBookingSystem.rooms.Room;

public class reserveRoom {
	
	
	// first called method when selecting reserve a room
	public static void reserveRoomMain(Scanner scan) {		
		System.out.println("- - You have selected to reserve a room! - -");
		System.out.println(" ");
		System.out.println("We would firstly like to ask a few questions to find your perfect match.");
		System.out.println(" ");
		
		// call the reserve room type method to get options
		String roomType = reserveRoomType(scan);
		Double roomprice = reserveRoomPrice(scan);
		boolean balcony = reserveRoomBalcony(scan);
		boolean longue = reserveRoomLongue(scan);
		
		
		
		if(checkMatchs(scan, roomType, roomprice, balcony, longue) != 0) {	
			System.out.println(" ");
			System.out.println(" We have found a match!");
			int roomnumber = checkMatchs(scan, roomType, roomprice, balcony, longue);
			bookRoom(scan, roomnumber);
		}else {
			roomfullBestOptions(scan, roomType, roomprice, balcony, longue);
		}
		
	}
	
	
	private static String reserveRoomType(Scanner scan) {
		System.out.println("Enter the Room Type (Single, Double, Suite)");
		String roomType = scan.nextLine();
	
		//Switch case for roomType	
		switch(roomType.toLowerCase()) {
			case "single":
				System.out.println("- - You have selected Room Type: Single - -");
				System.out.println(" ");
			    
			    return roomType;
			case "double":
				System.out.println("You have selected Room Type: Double");
				System.out.println(" ");

				return roomType;
			case "suite":
				System.out.println("You have selected Room Type: Suite");
				System.out.println(" ");
				return roomType;	
			default:
				System.out.println("Incorrect input, please try again...");
				reserveRoomType(scan);	
		}
		return roomType;
	}
	
	private static double reserveRoomPrice(Scanner scan) {
		System.out.println("Enter a budget for the room");
		Double roomPrice = 0.00;
		
		try {
		    roomPrice= Double.valueOf(scan.nextLine());	
		}catch(Exception e) {
			System.out.println("Incorrect input, please try again...");
			reserveRoomPrice(scan);
		}

		return roomPrice;
				
	}
	
	private static boolean reserveRoomBalcony(Scanner scan) {
		System.out.println("Would you like a Balcony for the room");
		String balcony = scan.nextLine();
		switch(balcony.toLowerCase()) {
		case "yes":			
			return true;
		case "no":
			return false;
		default:
			System.out.println("Incorrect input, please try again...");
			reserveRoomBalcony(scan);	
		}	
		return false;
	}
	
	private static boolean reserveRoomLongue(Scanner scan) {
		System.out.println("Would you like a longue for the room");
		String longue = scan.nextLine();
		switch(longue.toLowerCase()){
		case "yes":			
			return true;
		case "no":
			return false;
		default:
			System.out.println("Incorrect input, please try again...");
			reserveRoomLongue(scan);	
		}	
		return false;
	}
	
	private static int checkMatchs(Scanner scan, String Type, double Price, Boolean Balcony, Boolean Longue) {
		for(Room rooms : Menu.roomList.values()) {
			if(!rooms.getRoomType().equalsIgnoreCase(Type)) continue;
			if(rooms.getRoomPrice() > Price) continue;
			if(rooms.hasBalcony != Balcony) continue;
			if(rooms.hasLounge != Longue) continue;
			if(!rooms.geteMail().equalsIgnoreCase("free")) continue;
			return rooms.roomNum;
		}
		
		return 0;
	}
	
	private static void bookRoom(Scanner scan, int roomnumber) {
		System.out.println("To finish off booking the room " + roomnumber + " please enter a valid email address");
		String email = scan.nextLine();
		for(Room rooms : Menu.roomList.values()) {
			if(rooms.geteMail().equalsIgnoreCase(email)) {
				System.out.println(" ");
				System.out.println("Your email is linked to another booking please cancel that first to continue!");
				System.out.println(" ");
				scan.nextLine();
				Menu.menu(scan);
				return;
			}
		}
		
		if(!email.contains("@")) {
			System.out.println("Invalid mail please try again!");
			bookRoom(scan, roomnumber);	
		}
		
		
		Menu.roomList.get(roomnumber).seteMail(email);
		System.out.println("You have successfully booked a room! Thank you.");
		System.out.println("Press Enter to return to the menu..");
		scan.nextLine();
		Menu.menu(scan);
		
	}
	
	private static  void roomfullBestOptions(Scanner scan, String roomType, double roomPrice, Boolean roomBalcony, Boolean roomLongue) {
		System.out.println("");
		System.out.println(" We are unable to find you a room with these condidtions, but we have the next best alternative.");
		int bestMatch = 0;
		// best match room number
		int bestMatchNumber = 0;
		for(Room room : Menu.roomList.values()) {
			if(!room.geteMail().equalsIgnoreCase("free")) continue;
			int match = 0;
			if(room.getRoomType().equalsIgnoreCase(roomType)) {
				match++;
			}
			if(room.getRoomPrice() < roomPrice ) {
				match++;
			}
			if(room.getHasBalcony() == roomBalcony) {
				match++;
			}
			if(room.getHasLounge() == roomLongue) {
				match++;
			}
			if(match > bestMatch) {
			bestMatch = match;
			bestMatchNumber = room.getRoomNum();
			}
		}
		Room bestMatchRoom = Menu.roomList.get(bestMatchNumber);
		
		System.out.println("Your best option is room " + bestMatchRoom.getRoomNum() + " and has " + bestMatch + "/4 condidtions correct");
		System.out.println(" Would you like to continue to book room " + bestMatchRoom.getRoomNum() + "?");
		System.out.println(" Room Type: " + bestMatchRoom.getRoomType());
		System.out.println(" Price: £" + bestMatchRoom.getRoomPrice());
		System.out.println(" Balcony: " + bestMatchRoom.getHasBalcony());
		System.out.println(" Longue: " + bestMatchRoom.getHasLounge());
		System.out.println(" ");
		
		String ans = scan.nextLine().toLowerCase();
		
		switch(ans) {
		case "yes":
			bookRoom(scan, bestMatchRoom.getRoomNum());
			return;
		case "no":
			System.out.println("You have opt out!  Press Enter to return to the menu..");
			scan.nextLine();
			Menu.menu(scan);
			return;
		default:
			System.out.println("Invalid option, please try again");
			roomfullBestOptions(scan, roomType, roomPrice, roomBalcony, roomLongue);
		}
				

	}
	
	
}
