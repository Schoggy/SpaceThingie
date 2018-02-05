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

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class GameLevel {

  public float width, height;
  public int quadWidth, quadHeight;
  public int quadCountX, quadCountY;
  
  
  public GameLevelQuad[][] quads;

  public GameLevel(int quadWidth, int quadHeight, int quadCountW, int quadCountH) {
    this.quadWidth = quadWidth;
    this.quadHeight = quadHeight;
    
    this.width = quadCountW * quadWidth;
    this.height = quadCountH * quadHeight;
    
    this.quadCountX = quadCountW;
    this.quadCountY = quadCountH;
    
    quads = new GameLevelQuad[quadCountW][quadCountH];
    
    initQuads(quadCountW, quadCountH);
    
    generateStars(0.00015);
  }
  
  private void initQuads(int quadsX, int quadsY) {
    for(int x = 0; x < quadsX; x++) {
      for(int y = 0; y < quadsY; y++) {
        quads[x][y] = new GameLevelQuad(new Position((float) x * quadWidth, (float) y * quadHeight));
      }
    }
    
    System.out.println("Initialized " + (quadsX * quadsY) + " quads.");
    System.out.println("Level is " + width + " by " + height);
  }
  
  public int loadLevel(String filename) {
    // TODO implementation
    return 0;
  }
  
  public void addNPC(Player npc) {
    getQuadAtPos(npc.getPosition()).npcs.add(npc);
  }
  
  public Vector<GameLevelQuad> getQuadsInArea(Area a){
    Vector<GameLevelQuad> qOut = new Vector<GameLevelQuad>();
    Position anker = getQuadIndexForPos(a.getPosition());
    Position high = new Position(a.getPositionBL().x, a.getPositionBL().y);
    
    high.x += quadWidth - (high.x % quadWidth);
    high.y += quadHeight - (high.y % quadHeight);
    
    high = getQuadIndexForPos(high);
    
    for(int x = (int) anker.x; x < (int) high.x; x++) {
      for(int y = (int) anker.y; y < (int) high.y; y++) {
        qOut.add(quads[x][y]);
      }
    }
    
    return qOut;
  }
  
  public GameLevelQuad getQuadAtPos(Position pos) {
    int quadX = (int) Math.floor(pos.x / quadWidth);
    if(quadX < 0) {
      quadX = 0;
    } else if(quadX >= quadCountX) {
      quadX = quadCountX - 1;
    }
    int quadY = (int) Math.floor(pos.y / quadHeight);
    if(quadY < 0) {
      quadY = 0;
    } else if(quadY >= quadCountY) {
      quadY = quadCountY - 1;
    }
    
    return quads[quadX][quadY];
  }
  
  public Position getQuadIndexForPos(Position pos) {
    return new Position((float) Math.floor(pos.x / quadWidth), (float) Math.floor(pos.y / quadHeight));
  }
  
  public void tick(Position offset) {
    for(GameLevelQuad quadA[] : quads) {
      for(GameLevelQuad quad : quadA) {
        quad.tick(offset);
      }
    }
  }
  
  public void addProjectile(Projectile p) {
    getQuadAtPos(p.pos).projectiles.add(p);
  }
  
  private void generateStars(double density) {
    double levelspace = width * height;
    long starcount = 0;
    density = 1 / density;
    Position newP;
    GameLevelQuad quad;
    for(double count = 0; count < levelspace; count += density) {
      newP = new Position((float) (Math.random() * (width - 0.1)), (float) (Math.random() * (height - 0.1)));
      quad = getQuadAtPos(newP);
      quad.stars.add(newP);
      starcount++;
    }
    System.out.println("Generated " + starcount + " stars!");
  }
}
