package RoomBookingSystem.rooms;

public class Room {

	// Properties
	public int roomNum;
	public String roomType;
	public Double roomPrice;
	public Boolean hasBalcony;
	public Boolean hasLounge;
	public String eMail;
	
	public Room(int num, String type, Double price, Boolean balcony, Boolean lounge, String email) {
		this.roomNum = num;
		this.roomType = type;
		this.roomPrice = price;
		this.hasBalcony = balcony;
		this.hasLounge = lounge;
		this.eMail = email;
	}

	// Methods/Functions
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Boolean getHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(Boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public Boolean getHasLounge() {
		return hasLounge;
	}

	public void setHasLounge(Boolean hasLounge) {
		this.hasLounge = hasLounge;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
}