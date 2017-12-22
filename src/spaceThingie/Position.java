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

public class Position {

  public float x;
  public float y;

  public Position(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public static Position plus(Position p1, Position p2) {
    return new Position(p1.x + p2.x, p1.y + p2.y);
  }

  public static Position minus(Position p1, Position p2) {
    return new Position(p1.x - p2.x, p1.y - p2.y);
  }

  public static Position multSkalar(Position p, double factor) {
    return new Position((float) (p.x * factor), (float) (p.y * factor));
  }

  public void updatePosition(float deltaX, float deltaY) {
    x += deltaX;
    y += deltaY;
  }

  public void updatePosition(Position delta) {
    x += delta.x;
    y += delta.y;
  }

  public void updatePositionKeepPositive(Position delta) {
    x += (delta.x + x < 0) ? 0 : delta.x;
    y += (delta.y + y < 0) ? 0 : delta.y;
  }

  public void updatePositionFixed(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public static Position limitPositionToPositive(Position pos) {
    return new Position((pos.x >= 0) ? pos.x : 0, (pos.y >= 0) ? pos.y : 0);
  }

  public boolean isPositiveAnd() {
    if(this.x >= 0 && this.y >= 0){
      return true;
    }
    return false;
  }

  public void rotatePositonUpdate(double alpha) {
    float rotX = (float) (x * Math.cos(alpha + Math.PI) + y * Math.sin(alpha));
    float rotY = (float) (y * Math.cos(alpha) - x * Math.sin(alpha + Math.PI));

    x = -rotX;
    y = rotY;

  }

  public void nullPosition() {
    this.x = 0;
    this.y = 0;
  }

}
