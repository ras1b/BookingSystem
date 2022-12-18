package RoomBookingSystem;

import java.util.HashMap;
import java.util.Scanner;

import RoomBookingSystem.functions.cancelRoom;
import RoomBookingSystem.functions.reserveRoom;
import RoomBookingSystem.functions.viewRoom;
import RoomBookingSystem.rooms.Room;
import RoomBookingSystem.rooms.RoomManager;

public class Menu {

	public static HashMap<Integer, Room> roomList;
	
	public static String menu(Scanner scan) {
		//Create a scanner
		// Menu GUI
		System.out.println("- - Room Booking System - -");
		System.out.println(" ");
		System.out.println("1 - Reserve Room");
		System.out.println("2 - Cancel Room");
		System.out.println("3 - View Room Reservations");
		System.out.println("Q - Quit ");
		System.out.println("Pick:");
		String pick = scan.nextLine();
	
	//Case 1 - Reserving a room	(Switch case)
		switch(pick.toLowerCase()) {
			case "1":
				reserveRoom.reserveRoomMain(scan);
			return pick;
	
	//Case 2 - Cancelling a room (Switch Case)			
			case "2":
				cancelRoom.cancelRoom(scan);
				return pick;
				
	//Case 3 - View room reservations (Switch Case)				
			case "3":
				viewRoom.viewRoomMain(scan);
				return pick;
			case "q":
				System.out.println("Successfully closed!");
				RoomManager roomManager = new RoomManager();
				roomManager.saveRooms();
				scan.close();
				return pick;	
			default:
				System.out.println("Incorrect input, please try again...");
				menu(scan);
		}
		scan.close();
		return pick;
	}

	public static void main(String[] args) {

		//initialized roomList variable so can use
		roomList = new HashMap<>();
		
		RoomManager roomManager = new RoomManager();
		roomManager.loadRooms();
		
		
		//Create a scanner, fileName called hotelrooms.txt which will read the data inside it.
		Scanner scan = new Scanner(System.in);
		

		menu(scan);						

		
		scan.close();
	}

	
}