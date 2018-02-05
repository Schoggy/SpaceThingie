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

import java.util.Vector;

import processing.core.*;

public class Player {

  private PApplet pA;

  private GameEntity vehicle;
  private boolean isNPC;
  private Position pos, movement;
  private GameLevel level;
  private double heading;
  private boolean fire;

  public Player(PApplet parent, float xInitial, float yInitial) {
    this.pA = parent;
    this.pos = new Position(xInitial, yInitial);
    movement = new Position(0, 0);
    isNPC = true;
  }
  
  public Player(PApplet parent, Position initial) {
    this.pA = parent;
    this.pos = initial;
    movement = new Position(0, 0);
  }

  public void setNPC(boolean isNPC) {
    this.isNPC = isNPC;
  }
  
  public void setGameEntity(GameEntity vehicle) {
    this.vehicle = vehicle;
  }
  
  public void setLevel(GameLevel level) {
    this.level = level;
  }
  
  public void drawPlayer(Position offset) {
    // Draw player (temporary)
    PShape ps = pA.createShape();
    ps.setFill(0xFFFFFFFF);
    ps.setStroke(0xFF000000);
    ps.translate(pos.x - offset.x, pos.y - offset.y);
    ps.beginShape();
    ps.vertex(0, (float) -12.5);
    ps.vertex(10, (float) 12.5);
    ps.vertex(-10, (float) 12.5);
    ps.endShape(PConstants.CLOSE);
    ps.rotate((float) heading);
    ps.draw(pA.g);
    pA.text("x: " + pos.x + " y: " + pos.y + " HP: " + vehicle.hp, pos.x - offset.x, pos.y - offset.y);
  }

  public void setPositionFixed(Position newPos) {
    movement.updatePosition(Position.minus(Position.plus(pos, movement), newPos));
  }
  
  public void asyncForcePosition(Position newPos) {
    pos.updatePositionFixed(newPos.x, newPos.y);
  }
  
  public void orientPlayer(Position mousePos) {
    Position deltaMouse = Position.minus(mousePos, pos);
    double hyp = Math.sqrt((deltaMouse.x * deltaMouse.x) + (deltaMouse.y * deltaMouse.y));
    if(hyp == 0) {
      heading = 0;
    } else {
      heading = PApplet
          .radians((float) (Math.asin(deltaMouse.y / hyp) * (180 / Math.PI) + 90) * (deltaMouse.x < 0 ? -1 : 1));
    }
    if(Double.isNaN(heading)) {
      heading = 0;
    }
   }

  private void movePlayer() {
    movement.rotatePositonUpdate(heading);
    pos.updatePosition(movement);
  }

  public Position updatePlayer(Position mousePos, Position offset) {
    orientPlayer(mousePos);
    movePlayer();
    Position out = new Position(movement.x, movement.y);
    drawPlayer(offset);
    if(fire) {
      fire = false;
      shoot();
    }
    hitDetection();
    movement.nullPosition();
    return out;
  }
  
  private void hitDetection() {
    Area hitbox = new Area(pos.x, pos.y, vehicle.getHitbox().x, vehicle.getHitbox().y);
    Vector<GameLevelQuad> quads = level.getQuadsInArea(hitbox);
    
    for(GameLevelQuad quad : quads) {
      for(Projectile p : quad.projectiles) {
        if(p.canHitEntity(vehicle)) {
          if(hitbox.isInArea(p.pos)) {
            vehicle.takeDamage(p.getHitDamage());
            p.despawn();
          }
        }
      }
    }
    
  }
  
  private void shoot() {
    level.addProjectile(new Bullet(pA, level, pos.x, pos.y, (float) heading, (float) 5.0, 1, vehicle));
  }
  
  public void setShoot() {
    fire = true;
  }

  public void setPlayerMovement(float deltaX, float deltaY) {
    movement.updatePosition(deltaX, deltaY);
  }
  
  public double getHeading() {
    return heading;
  }
  
  public Position getPosition() {
    return pos;
  }
  
}
