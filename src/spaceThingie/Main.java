package spaceThingie;

import processing.core.*;

public class Main extends PApplet{

	int color;
	Player p;
	
	public static void main(String[] args) {
		processing.core.PApplet.main("spaceThingie.Main");
	}
	
	public void settings() {
		size(1600,900);
	}
	
	public void setup() {
		p = new Player(this, 800, 450);
		this.keyRepeatEnabled = true;
	}
	
	public void draw() {
		background(0x777777);
		if(keyPressed) {
		  handleMovement();
		}
		
		p.movePlayer();
		p.drawPlayer();
	}
	
	
	
	public void handleMovement() {
	  switch(key) {
	    case 'w':{
	      p.setPlayerMovement(0, -1);
	      break;
	    }
	    case 'a':{
        p.setPlayerMovement(-1, 0);
        break;
      }
	    case 's':{
        p.setPlayerMovement(0, 1);
        break;
      }
      case 'd':{
        p.setPlayerMovement(1, 0);
        break;
      }
	  }
	}
	

}
