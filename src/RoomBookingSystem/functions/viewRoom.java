package RoomBookingSystem.functions;

import java.util.Scanner;

import RoomBookingSystem.Menu;
import RoomBookingSystem.rooms.Room;

public class viewRoom {

	public static void viewRoomMain(Scanner scan) {
		
		System.out.println("You have selected to view room reservations!");
		System.out.println(" ");
		//iterating the rooms.txt to see reserved rooms
		findroom(scan);
		
	}
	
	private static void findroom(Scanner scan) {
		Room room = null;
		System.out.println("Please enter email that is linked to the room");
		System.out.println(" ");
		String email = scan.nextLine();
		
		//prevents user from continuing when input has incorrect email (Missing the "@")
		if(!email.contains("@")) { findroom(scan); return;}
		//validating the email and room are correct by being compared it's data from getEmail to email and room to rooms
		for(Room rooms : Menu.roomList.values()) {
			if(rooms.geteMail().equalsIgnoreCase(email)) {
				System.out.println("Email found");
				System.out.println(" Please enter room number linked to email");
				room = rooms;
				confirmRoomNumber(scan, room);
				return;
			}
		}
		System.out.println(" ");
		System.out.println(" Email could not be found please try again");
		findroom(scan);			
		return;
	}
	
	
	private static void confirmRoomNumber(Scanner scan, Room room) {
		//converts the roomnumber string to integer value using parseInt and checks using scanner if it's correct ("Confirming room number")
		String roomnumber = scan.nextLine();
		try {
			room = Menu.roomList.get(Integer.parseInt(roomnumber));
		}catch(Exception e) {
			System.out.println("Invalid option please try again");
			confirmRoomNumber(scan, room);
			return;
		}
		
		if(room == null) {
			System.out.println("Invalid option please try again");
			confirmRoomNumber(scan, room);
			return;
		}
			//once data is correct end result will display room information of the email and its room number
		
		if(room.geteMail().equalsIgnoreCase(room.geteMail())) {
			System.out.println(" ");
			System.out.println("Room Infomation: ");
			System.out.println(" ");
			System.out.println("Room Number: " + room.getRoomNum());
			System.out.println("Room Type: " + room.getRoomType());
			System.out.println("Room Price : £" + room.getRoomPrice() + "/per night");
			System.out.println("Room Balcony:  " + room.getHasBalcony());
			System.out.println("Room Longue: " + room.getHasLounge());
			System.out.println(" ");
			System.out.println("Press Enter to return to the menu..");
			scan.nextLine();
			Menu.menu(scan);
		}
	
	}
	
}
