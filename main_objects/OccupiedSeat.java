package main_objects;

public class OccupiedSeat {

	private int room;
	private int row;
	private int column;
	
	public OccupiedSeat() { }
	
	public OccupiedSeat(int room, int row, int column) {
		this.room = room;
		this.row = row;
		this.column = column;
	}
	
	public int getRoom() {
		return room;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return "[" + room + "|" + row + "|" + column + "]";
	}

}
