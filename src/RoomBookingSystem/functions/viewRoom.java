package RoomBookingSystem.functions;

import java.util.Scanner;

import RoomBookingSystem.Menu;
import RoomBookingSystem.rooms.Room;

public class viewRoom {

	public static void viewRoomMain(Scanner scan) {
		
		System.out.println("You have selected to view room reservations!");
		System.out.println(" ");
		findroom(scan);
		
	}
	
	private static void findroom(Scanner scan) {
		Room room = null;
		System.out.println("Please enter email that is linked to the room");
		System.out.println(" ");
		String email = scan.nextLine();
		
		if(!email.contains("@")) { findroom(scan); return;}
		
		for(Room rooms : Menu.roomList.values()) {
			if(rooms.geteMail().equalsIgnoreCase(email)) {
				System.out.println("Email found");
				System.out.println(" Please enter room number linked to email");
				room = rooms;
				conformRoomNumber(scan, room);
				return;
			}
		}
		System.out.println(" ");
		System.out.println(" Email could not be found please try again");
		findroom(scan);			
		return;
	}
	
	
	private static void conformRoomNumber(Scanner scan, Room room) {

		String roomnumber = scan.nextLine();
		try {
			room = Menu.roomList.get(Integer.parseInt(roomnumber));
		}catch(Exception e) {
			System.out.println("Invalid option please try again");
			conformRoomNumber(scan, room);
			return;
		}
		
		if(room == null) {
			System.out.println("Invalid option please try again");
			conformRoomNumber(scan, room);
			return;
		}
		
		
		if(room.geteMail().equalsIgnoreCase(room.geteMail())) {
			System.out.println(" ");
			System.out.println("Room Infomation: ");
			System.out.println(" Room Number: " + room.getRoomNum());
			System.out.println("Room Type: " + room.getRoomType());
			System.out.println("Room Price : £" + room.getRoomPrice() + "/per night");
			System.out.println("Room Balcony:  " + room.getHasBalcony());
			System.out.println("Room Longue: " + room.getHasLounge());
			scan.nextLine();
			Menu.menu(scan);
		}
	
	}
	
}
