package model.entities;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date CheckOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if(!checkOut.after(checkIn)) {
			throw new DomainException(" check-out date must be after check-in date: ");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		CheckOut = checkOut;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public Date getCheckOut() {
		return CheckOut;
	}
	
	public long duration(){
		long diff = CheckOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDate(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		
		if(checkIn.before(now)|| checkOut.before(now)) {
			throw new DomainException(" dates for update must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			throw new DomainException(" check-out date must be after check-in date: ");
		}
		
		this.checkIn = checkIn;
		this.CheckOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room: "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(CheckOut)
				+", "
				+ duration()
				+ " noites ";
	}
	
	
	
	
}
