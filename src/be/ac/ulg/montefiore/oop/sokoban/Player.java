
package be.ac.ulg.montefiore.oop.sokoban;

import be.ac.ulg.montefiore.oop.general.Direction;

public class Player extends MobileCell
{
   protected Direction direction;

   public Player(final int initL, final int initC, final Board board)
   {
      super(initL, initC, board);
      direction = Direction.UP;
   }

   public final Direction direction(){return direction;}

   protected void moveUp()
   {
      direction = Direction.UP;
      super.moveUp();
   }

   protected void moveDown()
   {
      direction = Direction.DOWN;
      super.moveDown();
   }

   protected void moveLeft()
   {
      direction = Direction.LEFT;
      super.moveLeft();
   }

   protected void moveRight()
   {
      direction = Direction.RIGHT;
      super.moveRight();
   }

   public final void goBack(final Direction direction)
   {
      switch(direction)
      {
         case UP: goBackUp(); break;
         case DOWN: goBackDown(); break;
         case LEFT: goBackLeft(); break;
         case RIGHT: goBackRight();
      }
   }

   protected void goBackUp()
   {
      direction = Direction.DOWN;
      super.moveUp();
   }

   protected void goBackDown()
   {
      direction = Direction.UP;
      super.moveDown();
   }

   protected void goBackLeft()
   {
      direction = Direction.RIGHT;
      super.moveLeft();
   }

   protected void goBackRight()
   {
      direction = Direction.LEFT;
      super.moveRight();
   }

   public final boolean canPush(final Direction direction)
   {
      switch(direction)
      {
         case UP: return canPushUp();
         case DOWN: return canPushDown();
         case LEFT: return canPushLeft();
         default: return canPushRight();
      }
   }

   protected boolean canPushUp()
   {
      final MobileCell mLayer[][] = board.mLayer;
      return(mLayer[l - 1][c] != null && mLayer[l - 1][c].canMoveUp());
   }
   
   protected boolean canPushDown()
   {
      final MobileCell mLayer[][] = board.mLayer;
      return(mLayer[l + 1][c] != null && mLayer[l + 1][c].canMoveDown());
   }

   protected boolean canPushLeft()
   {
      final MobileCell mLayer[][] = board.mLayer;
      return(mLayer[l][c - 1] != null && mLayer[l][c - 1].canMoveLeft());
   }

   protected boolean canPushRight()
   {
      final MobileCell mLayer[][] = board.mLayer;
      return(mLayer[l][c + 1] != null && mLayer[l][c + 1].canMoveRight());
   }
   
   public final void push(final Direction direction)
   {
      switch(direction)
      {
         case UP: pushUp(); break;
         case DOWN: pushDown(); break;
         case LEFT: pushLeft(); break;
         case RIGHT: pushRight();
      }
   }

   protected void pushUp()
   {
      direction = Direction.UP;
      board.mLayer[l - 1][c].moveUp();
      moveUp();
   }

   protected void pushDown()
   {
      direction = Direction.DOWN;
      board.mLayer[l + 1][c].moveDown();
      moveDown();
   }

   protected void pushLeft()
   {
      direction = Direction.LEFT;
      board.mLayer[l][c - 1].moveLeft();
      moveLeft();
   }

   protected void pushRight()
   {
      direction = Direction.RIGHT;
      board.mLayer[l][c + 1].moveRight();
      moveRight();
   }

   public final void pull(final Direction direction)
   {
      switch(direction)
      {
         case UP: pullUp(); break;
         case DOWN: pullDown(); break;
         case LEFT: pullLeft(); break;
         case RIGHT: pullRight();
      }
   }

   protected void pullUp()
   {
      goBackUp();
      board.mLayer[l + 2][c].moveUp();
   }

   protected void pullDown()
   {
      goBackDown();
      board.mLayer[l - 2][c].moveDown();
   }

   protected void pullLeft()
   {
      goBackLeft();
      board.mLayer[l][c + 2].moveLeft();
   }

   protected void pullRight()
   {
      goBackRight();
      board.mLayer[l][c - 2].moveRight();
   }
}

