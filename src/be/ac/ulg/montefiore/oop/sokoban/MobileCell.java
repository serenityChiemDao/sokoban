
package be.ac.ulg.montefiore.oop.sokoban;

import be.ac.ulg.montefiore.oop.general.Direction;

public class MobileCell
{
   protected final int initL, initC;
   protected int l, c;
   protected final Board board;

   public MobileCell(final int initL, final int initC, final Board board)
   {
      l = this.initL = initL;
      c = this.initC = initC;
      this.board = board;
   }

   public final int initL(){return initL;}
   public final int initC(){return initC;}
   public final int l(){return l;}
   public final int c(){return c;}
   public final Board board(){return board;}

   public final boolean canMove(final Direction direction)
   {
      switch(direction)
      {
         case UP: return canMoveUp();
         case DOWN: return canMoveDown();
         case LEFT: return canMoveLeft();
         default: return canMoveRight();
      }
   }

   protected boolean canMoveUp()
   {
      return
      (
         board.mLayer[l - 1][c] == null && board.iLayer[l - 1][c].type !=
         ImmobileCellType.WALL
      );
   }

   protected boolean canMoveDown()
   {
      return
      (
         board.mLayer[l + 1][c] == null && board.iLayer[l + 1][c].type !=
         ImmobileCellType.WALL
      );
   }

   protected boolean canMoveLeft()
   {
      return
      (
         board.mLayer[l][c - 1] == null && board.iLayer[l][c - 1].type !=
         ImmobileCellType.WALL
      );
   }

   protected boolean canMoveRight()
   {
      return
      (
         board.mLayer[l][c + 1] == null && board.iLayer[l][c + 1].type !=
         ImmobileCellType.WALL
      );
   }

   public final void move(final Direction direction)
   {
      switch(direction)
      {
         case UP: moveUp(); break;
         case DOWN: moveDown(); break;
         case LEFT: moveLeft(); break;
         case RIGHT: moveRight();
      }
   }

   protected void moveUp()
   {
      final MobileCell mLayer[][] = board.mLayer;
      mLayer[l][c] = null;
      mLayer[--l][c] = this;
   }

   protected void moveDown()
   {
      final MobileCell mLayer[][] = board.mLayer;
      mLayer[l][c] = null;
      mLayer[++l][c] = this;
   }


   protected void moveLeft()
   {
      final MobileCell mLayer[][] = board.mLayer;
      mLayer[l][c] = null;
      mLayer[l][--c] = this;
   }

   protected void moveRight()
   {
      final MobileCell mLayer[][] = board.mLayer;
      mLayer[l][c] = null;
      mLayer[l][++c] = this;
   }
}

