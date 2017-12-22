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

public class Bullet extends Projectile {

  private int damage;
  private Position speed;
  
  public Bullet(PApplet p, GameLevel level, float x, float y, double heading, float speed) {
    this.speed = new Position(0,0);
    id = "Bullet";
    pos.x = x;
    pos.y = y;
    this.speed.y = -speed;
    this.heading = heading;
    setParents(p, level);
  }
  
  
  @Override
  public void updateProjectile(Position offset) {
    orientProjectile();
    moveProjectile();
    checkBoundaries();
    drawProjectile(offset);
  }

  @Override
  public void despawnAction() {
    
  }


  @Override
  protected void orientProjectile() {
    movement.nullPosition();
    movement.updatePosition(speed);
    movement.rotatePositonUpdate(heading);
  }


  @Override
  protected void moveProjectile() {
    pos.updatePosition(movement);
  }

}
