package spaceThingie;

import processing.core.*;

public class Player {
  
  PApplet pA;
  
  Position pos, movement;
  
  public Player(PApplet parent, float xInitial, float yInitial) {
    this.pA = parent;
    this.pos = new Position(xInitial, yInitial);
    movement = new Position(0,0);
  }
  
  public void drawPlayer() {
    pA.stroke(0xFFFFFFFF);
    pA.line(pos.x - 20, pos.y - 20, pos.x + 20, pos.y + 20);
    pA.line(pos.x + 20, pos.y - 20, pos.x - 20, pos.y + 20);
    pA.text("Player", pos.x, pos.y);
    
	}
  
  public Position movePlayer() {
    pos.updatePosition(movement);
    movement.nullPosition();
    return pos;
  }
  
  public void setPlayerMovement(float deltaX, float deltaY) {
    movement.updatePosition(deltaX, deltaY);
  }
	
}
