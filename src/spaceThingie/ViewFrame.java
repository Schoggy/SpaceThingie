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

import processing.core.PApplet;

public class ViewFrame {

  private GameLevel level;
  private Area frame;
  private Position movement;
  private Player player;
  private PApplet pA;
  
  public ViewFrame(PApplet parent, Player player, float width, float height, float x, float y) {
    pA = parent;
    frame = new Area(x, y, width, height);
    this.player = player;
    player.asyncForcePosition(frame.getMiddle());
  }
  
  public void move(Position movement) {
    Position decideHigh = Position.minus(Position.minus(new Position(level.width, level.height),
                            Position.multSkalar(frame.getSize(), 0.5)), player.getPosition());
    Position decideLow = Position.minus(player.getPosition(), Position.plus(Position.multSkalar(frame.getSize(), 0.5), movement));

    movement.x = ((decideLow.x < 0) || (decideHigh.x < 0)) ? 0 : movement.x;
    movement.y = ((decideLow.y < 0) || (decideHigh.y < 0)) ? 0 : movement.y;
   
    frame.moveLimitAtZero(movement);
  }
  
  public void changeZoom(double factor) {
    frame.resize((float) (frame.getSize().x * factor), (float) (frame.getSize().y * factor));
  }
  
  public int useLevel(GameLevel level) {
    this.level = level;
    player.setLevel(level);
    
    // TODO Level checks, initializations
    
    return 0;
  }
  
  public void draw(Mouse mouse) {
    pA.background(0x80000000);
    Position offset = Position.limitPositionToPositive(frame.getPosition());
    
    drawStars(offset);
    movement = player.updatePlayer(Position.plus(offset, mouse.getPos()), offset);
    move(movement);
  }
  
  public Position getOffset() {
    return Position.limitPositionToPositive(frame.getPosition());
  }
  
  private void drawStars(Position offset) {
    pA.fill(0xFFFFFFFF);
    pA.stroke(0xFFFFFFFF);
    for(Position star : level.stars) {
      if(frame.isInArea(star)) {
        pA.rect(star.x - offset.x, star.y - offset.y, 1, 1);
      }
    }
  }
  
  
}
