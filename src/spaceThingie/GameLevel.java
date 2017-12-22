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

public class GameLevel {

  public float width, height;
  
  public Vector<Object> content;
  public Vector<Position> stars;
  public Vector<Projectile> projectiles; 
  public Vector<Player> npcs;
  
  public GameLevel(float width, float height) {
    this.width = width;
    this.height = height;
    projectiles = new Vector<Projectile>();
    content = new Vector<Object>();
    stars = new Vector<Position>();
    npcs = new Vector<Player>();
    generateStars(0.0001);
  }
  
  public int loadLevel(String filename) {
    // TODO implementation
    return 0;
  }
  
  public void addNPC(Player npc) {
    npcs.add(npc);
  }
  
  public void scrub() {
    cleanProjectiles();
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
  
  public void addProjectile(Projectile p) {
    projectiles.add(p);
  }
  
  private void generateStars(double density) {
    double levelspace = width * height;
    long starcount = 0;
    density = 1 / density;
    for(double count = 0; count < levelspace; count += density) {
      stars.add(new Position((float) Math.random() * width, (float) Math.random() * height));
      starcount++;
    }
    System.out.println("Generated " + starcount + " stars!");
  }
  
  private void cleanProjectiles() {
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
