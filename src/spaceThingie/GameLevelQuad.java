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

import java.util.ListIterator;
import java.util.Vector;

public class GameLevelQuad {

  public Position anker;
  public Vector<Position> stars;
  public Vector<Projectile> projectiles; 
  public Vector<Player> npcs;
  
  public GameLevelQuad(Position anker) {
    this.anker = anker;
    
    projectiles = new Vector<Projectile>();
    stars = new Vector<Position>();
    npcs = new Vector<Player>();
  }
  
  public void tick(Position offset) {
    for(Projectile p : projectiles) {
      if(!p.isDespawned())
        p.updateProjectile(offset);
    }
    for(Player p : npcs) {
      p.updatePlayer(new Position(0,0), offset);
    }
    cleanProjectiles();
  }
  
  public void cleanProjectiles() {
    ListIterator<Projectile> iter = projectiles.listIterator();
    Projectile p;
    while(iter.hasNext()) {
      p = iter.next();
      if(p.isDespawned()) {
        iter.remove();
      }
    }
  }
  
}
