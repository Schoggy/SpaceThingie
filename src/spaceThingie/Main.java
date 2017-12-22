/* 
This file is part of SpaceThingie.

SpaceThingie is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SpaceThingie is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SpaceThingie.  If not, see <http://www.gnu.org/licenses/>.
*/

package spaceThingie;

import processing.core.*;

public class Main extends PApplet {

  public enum HotKeys{    
    KeyW(0), KeyA(1), KeyS(2), KeyD(3), Space(4);
    public int index;
    private HotKeys(int index) {
      this.index = index;
    }
  }
  
  private static int singleStep = 4;
  
  public boolean[] HKeys;
  
  private Player p;
  private ViewFrame frame;
  private GameLevel level;
  private Mouse mouse;
  
  private Player npc;

  public static void main(String[] args) {
    processing.core.PApplet.main("spaceThingie.Main");
  }

  public void settings() {
    size(1600, 900);
  }

  public void setup() {
    // Player's player
    p = new Player(this, 0, 0);
    p.setGameEntity(new Ship(100, 100, new Position(10, 10)));
    
    // Testing NPC's player
    npc = new Player(this, 2000, 2000);
    npc.setGameEntity(new Ship(100, 100, new Position(10, 10)));
    
    
    HKeys = new boolean[4];
    mouse = new Mouse(mouseX, mouseY);
    frame = new ViewFrame(this, p, (float) 1600, (float) 900, (float) displayWidth, (float) displayHeight);
    level = new GameLevel(4000, 4000);
    level.addNPC(npc);
    frame.useLevel(level);
  }

  public void draw() {
    //background(0x777777);
    if(keyPressed){
      handleMovement();
    }
    
    frame.draw(mouse);
    level.tick(frame.getOffset());
    
    if(frameCount % 120 == 0) {
      level.scrub();
    }
  }
  
  public void mouseMoved() {
    mouse.updateFixed(mouseX, mouseY);
  }
  
  public void keyPressed() {
    if(key == 'w') {
      HKeys[HotKeys.KeyW.index] = true;
    }
    if(key == 'a') {
      HKeys[HotKeys.KeyA.index] = true;
    }
    if(key == 's') {
      HKeys[HotKeys.KeyS.index] = true;
    }
    if(key == 'd') {
      HKeys[HotKeys.KeyD.index] = true;
    }
    if(key == ' ') {
      p.setShoot();
    }
    if(key == 'q') {
      exit();
    }
  }  
  
  public void keyReleased() {
    if(key == 'w') {
      HKeys[HotKeys.KeyW.index] = false;
    }
    if(key == 'a') {
      HKeys[HotKeys.KeyA.index] = false;
    }
    if(key == 's') {
      HKeys[HotKeys.KeyS.index] = false;
    }
    if(key == 'd') {
      HKeys[HotKeys.KeyD.index] = false;
    }
  }
  
  public void handleMovement() {
    if(HKeys[HotKeys.KeyW.index]) {
      p.setPlayerMovement(0, -1 * singleStep);
    }
    if(HKeys[HotKeys.KeyA.index]) {
      p.setPlayerMovement(-1 * singleStep, 0);
    }
    if(HKeys[HotKeys.KeyS.index]) {
      p.setPlayerMovement(0, singleStep);
    }
    if(HKeys[HotKeys.KeyD.index]) {
      p.setPlayerMovement(singleStep, 0);
    }
  }
  
}
