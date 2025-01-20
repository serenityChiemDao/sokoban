
package be.ac.ulg.montefiore.oop.sokoban;

import be.ac.ulg.montefiore.oop.general.Direction;

public enum Move
{
   GO_BACK_UP(Direction.UP),
   GO_BACK_DOWN(Direction.DOWN),
   GO_BACK_LEFT(Direction.LEFT),
   GO_BACK_RIGHT(Direction.RIGHT),
   PULL_UP(Direction.UP),
   PULL_DOWN(Direction.DOWN),
   PULL_LEFT(Direction.LEFT),
   PULL_RIGHT(Direction.RIGHT);

   final Direction direction;

   private Move(final Direction direction){this.direction = direction;}

   public final Direction direction(){return direction;}
}

