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

public class Area {

  private Position anker, size;
  
  public Area(float x, float y, float width, float height) {
    anker = new Position(x, y);
    size = new Position(width, height);
  }
  
  public void resize(float newWidth, float newHeight) {
    size.updatePositionFixed(newWidth, newHeight);
  }
  
  public void move(Position delta) {
    anker.updatePosition(delta);
  }
  
  public void moveLimitAtZero(Position delta) {
    anker.updatePositionKeepPositive(delta);
  }
  
  public Position getSize() {
    return size;
  }
  
  public Position getPosition() {
    return anker;
  }
  
  public Position getMiddle() {
    return Position.plus(Position.multSkalar(size, 0.5), anker);
  }
  
  public boolean isInArea(Position p) {
    Position deltaTL = Position.minus(p, anker);
    if(deltaTL.isPositiveAnd()) {
      return Position.minus(size, deltaTL).isPositiveAnd();
    }
    return false;
  }
}
