package main_objects;

import java.util.Date;

import main_objects.Utility.Type;

import java.text.ParseException;

public class Movie {
	
	private String name;
	private Type type;
	private Date start_time;
	private Date end_time;
	
	public Movie(String name) {
		this.name = name;
	}
	
	public Movie(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public Movie(String name, String start_time, String end_time) {
		this.name = name;
		try {
			this.start_time = Utility.hour_format.parse(start_time);
			this.end_time = Utility.hour_format.parse(end_time);
		} catch (ParseException e) {
			System.out.println("Invalid date format!");
		} catch (NullPointerException e) {
			this.start_time = null;
			this.end_time = null;
		}
	}
	
	public Movie(String name, Type type, String start_time, String end_time) {
		this.name = name;
		this.type = type;
		
		try {
			this.start_time = Utility.hour_format.parse(start_time);
			this.end_time = Utility.hour_format.parse(end_time);
		} catch (ParseException e) {
			System.out.println("Invalid date format!");
		}
	}

	@Override
	public String toString() {
		return String.format("%s: %s - %s", name, getStart_time(), getEnd_time());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getStart_time() {
		try {
			String aux = Utility.hour_format.format(start_time);
			return aux;
		} catch (NullPointerException e) {
			return " ";
		}
	}

	public void setStart_time(String start_time) {
		try {
			this.start_time = Utility.hour_format.parse(start_time);
		} catch (ParseException e) {
			System.out.println("Invalid date format!");
		}
	}

	public String getEnd_time() {
		try {
			String aux = Utility.hour_format.format(end_time);
			return aux;
		} catch (NullPointerException e) {
			return " ";
		}
	}

	public void setEnd_time(String end_time) {
		try {
			this.end_time = Utility.hour_format.parse(end_time);
		} catch (ParseException e) {
			System.out.println("Invalid date format!");
		}
	}
	
}
