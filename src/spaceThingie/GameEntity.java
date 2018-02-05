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

import processing.core.PImage;

public abstract class GameEntity {

  protected long hp;
  protected long maxHp;
  protected Position hitbox;
  protected PImage texture;
  
  
  public void setTexture(PImage texture) {
    this.texture = texture;
  }
  
  public void setHitbox(Position hitbox) {
    this.hitbox = hitbox;
  }
  
  public Position getHitbox() {
    return hitbox;
  }
  
  public PImage getTexture() {
    return this.texture;
  }
  
  
  
  
  public abstract boolean takeDamage(long damage);
}
