package application.core.entities;

import java.util.Date;

public class Cell {

	public String brand, type;
	public int id, capacity, packID;
	public Date testDate;

	public Cell(String brand, String type, int capacity, int packID, Date date) {
		this.brand = brand;
		this.type = type;
		this.id = -1;
		this.capacity = capacity;
		this.packID = packID;
		this.testDate = date;
	}

	public Cell(String brand, String type, int id, int capacity, int packID, Date date) {
		this.brand = brand;
		this.type = type;
		this.id = id;
		this.capacity = capacity;
		this.packID = packID;
		this.testDate = date;
	}

	public Cell(String brand, String type, int id, int capacity, int packID) {
		this.brand = brand;
		this.type = type;
		this.id = id;
		this.capacity = capacity;
		this.packID = packID;
		this.testDate = null;
	}

	public String toCSV() {
		String result = "";

		result += id + ";";
		result += brand + ";";
		result += type + ";";
		result += capacity + ";";
		result += packID + ";";
		result += testDate;
		return result;
	}
}
