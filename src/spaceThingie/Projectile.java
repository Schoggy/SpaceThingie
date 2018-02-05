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

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

public abstract class Projectile {

  protected Position pos, movement;
  protected double heading;
  protected PImage texture;
  protected String id;
  protected PApplet pA;
  protected GameLevel level;
  private boolean isDespawned;
  protected GameEntity parent;
  protected GameLevelQuad crtQuad;
  
  public Projectile() {
    pos = new Position(0,0);
    movement = new Position(0,0);
    isDespawned = false;
  }
  
  public void setParents(PApplet p, GameLevel level, GameEntity gE) {
    pA = p;
    this.level = level;
    parent = gE;
  }
  
  protected void drawProjectile(Position offset) {
    if(!isDespawned) {
      PShape ps = pA.createShape();
      
      ps.setFill(0xFFFF0000);
      ps.setStroke(0xFF000000);
      ps.translate(pos.x - offset.x, pos.y - offset.y);
      ps.beginShape();
      ps.vertex(0, (float) -3);
      ps.vertex(2, (float) 3);
      ps.vertex(-2, (float) 3);
      ps.endShape(PConstants.CLOSE);
      ps.rotate((float) heading);
      ps.draw(pA.g);
    }
    
    /*
    if(texture.isLoaded()) {
      
    } else {
      System.err.println("Texture for projectile " + id + " has not been loaded!");
    }*/
    
  }
  
  protected void despawn() {
    isDespawned = true;
  }
  public boolean isDespawned() {
    return isDespawned;
  }
  
  protected void checkBoundaries() {
    if(pos.x < 0 || pos.x > level.width || pos.y < 0 || pos.y > level.height) {
      isDespawned = true;
    }
  }
  
  public boolean canHitEntity(GameEntity gE) {
    if(gE == parent) {
      return false;
    }
    return true;
  }
  
  public abstract void hitEffect(GameEntity gE);
  public abstract int getHitDamage();
  public abstract void updateProjectile(Position offset);
  protected abstract void orientProjectile();
  protected abstract void moveProjectile();
  public abstract void despawnAction();
  
  
  
}
