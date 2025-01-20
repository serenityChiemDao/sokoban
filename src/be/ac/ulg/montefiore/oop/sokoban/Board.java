
package be.ac.ulg.montefiore.oop.sokoban;

public class Board
{
   protected final int height, width;
   protected final ImmobileCell[][] iLayer;
   protected final MobileCell[][] mLayer;

   public Board(final int height, final int width)
   {
      this.height = height;
      this.width = width;
      iLayer = new ImmobileCell[height][width];
      mLayer = new MobileCell[height][width];
   }

   public final int height(){return height;}
   public final int width(){return width;}
   public final ImmobileCell[][] iLayer(){return iLayer;}
   public final MobileCell[][] mLayer(){return mLayer;}
}

