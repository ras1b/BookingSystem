package RoomBookingSystem.functions;

import java.util.Scanner;

import RoomBookingSystem.Menu;
import RoomBookingSystem.rooms.Room;

public class cancelRoom {

	
	public static void cancelRooms(Scanner scan) {
		System.out.println("- - Cancel Reservation - -");
		System.out.println(" ");
		System.out.println(" To get started, please enter the email used for booking");
		
		String email = scan.nextLine();
		if(!email.contains("@")) { cancelRooms(scan); return;}
		
		for(Room rooms : Menu.roomList.values()) {
			if(rooms.eMail.equalsIgnoreCase(email)) {
				System.out.println(" ");
				System.out.println(" Email found!");
				System.out.println("Please confirm the room number you want to cancel");
				confirmRoomNumber(scan, email);
				return;
			}
		}		
		System.out.println(" ");
		System.out.println(" Email could not be found, please try again");
		cancelRooms(scan);
	}
	
	private static void confirmRoomNumber(Scanner scan, String email) {

		String roomnumber = scan.nextLine();
		Room room;
		try {
			room = Menu.roomList.get(Integer.parseInt(roomnumber));
		}catch(Exception e) {
			System.out.println("Invalid option, please try again");
			confirmRoomNumber(scan, email);
			return;
		}
		
		if(room == null) {
			System.out.println("Invalid option, please try again");
			confirmRoomNumber(scan, email);
			return;
		}
		
		
		if(room.geteMail().equalsIgnoreCase(email)) {
			System.out.println("Room found! Reservation has been cancelled thank you.");
			System.out.println(" ");
			System.out.println("Press Enter to return to the menu..");
			room.seteMail("free");
			scan.nextLine();
			Menu.menu(scan);
		}
	
	}
	
}
