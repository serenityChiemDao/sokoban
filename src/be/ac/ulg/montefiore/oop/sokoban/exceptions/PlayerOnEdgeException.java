
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class PlayerOnEdgeException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public PlayerOnEdgeException(){}
   public PlayerOnEdgeException(final String message){super(message);}
}

