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

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

public class GameLevelQuad {

  public Position anker;
  public Vector<Position> stars;
  public LinkedList<Projectile> projectiles; 
  public LinkedList<Player> npcs;
  
  public boolean hasProjectiles;
  public boolean hasGameEntity;
  
  public GameLevelQuad(Position anker) {
    this.anker = anker;
    
    projectiles = new LinkedList<Projectile>();
    stars = new Vector<Position>();
    npcs = new LinkedList<Player>();
  }
  
  public void tick(Position offset) {
    for(Projectile p : projectiles) {
      if(!p.isDespawned())
        p.updateProjectile(offset);
    }
    for(Player p : npcs) {
      p.updatePlayer(new Position(0,0), offset);
    }
  }
  public void cleanup() {
    cleanProjectiles();
    cleanPlayers();
  }
  
  private void cleanPlayers() {
    ListIterator<Player> iter = npcs.listIterator();
    Player p;
    while(iter.hasNext()) {
      p = iter.next();
      if(!p.alive) {
        p.die();
        iter.remove();
      }
    }
  }
  
  public void cleanProjectiles() {
    ListIterator<Projectile> iter = projectiles.listIterator();
    Projectile p;
    while(iter.hasNext()) {
      p = iter.next();
      if(p.isDespawned()) {
        iter.remove();
        continue;
      }
      if(p.crtQuad != this) {
        p.crtQuad.projectiles.add(p);

        iter.remove();
      }
    }
  }
  
  public GameLevelQuad handOverProjectile(GameLevelQuad oldQuad, Projectile p) {
    if(oldQuad != this) {
      projectiles.add(p);
      oldQuad.projectiles.remove(p);
    }
    return this;
  }
  
}
