package RoomBookingSystem.rooms;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import RoomBookingSystem.Menu;

public class RoomManager {

	public void loadRooms() {
		//Create a scanner, fileName called hotelrooms.txt which will read the data inside it.
		Scanner scan = new Scanner(System.in);
		String fileName = "hotelrooms.txt";
		FileReader file;
		// Using trycatch to debug error if the file hotelrooms.txt does not exist 
		try {
			file = new FileReader(fileName);
			Scanner read = new Scanner(file);
			// Create a while loop to iterate through hotelrooms.txt  
			while(read.hasNextLine()) {
				// Reads next line and stores it into the String line
				String line = read.nextLine();
				//Reads the String line and stores each break into the String[] arr
				String[] arr = line.split(" ");
				// We have an ArrayList that can hold objects of type Room
				// We call this ArrayList currentRooms
				// We iterate through our file and add the objects of type room to our list with the .add method
				Menu.roomList.put(Integer.parseInt(arr[0]), new Room(Integer.parseInt(arr[0]), arr[1], Double.parseDouble(arr[2]), Boolean.parseBoolean(arr[3]), Boolean.parseBoolean(arr[4]), arr[5]));			
			}
			read.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//To save room reservations and cancellations in .txt file. Overwrites existing data with email (free or email)
	//use trycatch method in case .txt file does not exist, and can highlight the issue
	public void saveRooms() {
		try {
			PrintStream out = new PrintStream(new FileOutputStream("hotelrooms.txt"));		
			
			System.setOut(out);
			
			for(Room rooms : Menu.roomList.values()) {
				out.println(rooms.getRoomNum() + " " + 
						String.valueOf(rooms.getRoomType()) + " " + 
						String.valueOf(rooms.getRoomPrice()) + " " + 
								String.valueOf(rooms.getHasBalcony()) + " " +
										String.valueOf(rooms.getHasLounge()) + " " + 
												String.valueOf(rooms.geteMail()));		
			}
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
		
	}

}
