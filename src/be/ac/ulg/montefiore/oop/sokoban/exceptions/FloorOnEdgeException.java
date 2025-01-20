
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class FloorOnEdgeException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public FloorOnEdgeException(){}
   public FloorOnEdgeException(final String message){super(message);}
}

