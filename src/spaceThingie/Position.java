package spaceThingie;

public class Position {

	public float x;
	public float y;
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void updatePosition(float deltaX, float deltaY) {
		x += deltaX;
		y += deltaY;
	}
	
	public void updatePosition(Position delta) {
	  x += delta.x;
	  y += delta.y;
	}
	
	public void updatePositionFixed(float x, float y) {
	  this.x = x;
	  this.y = y;
	}
	
	public void nullPosition() {
	  this.x = 0;
	  this.y = 0;
	}
	
}
