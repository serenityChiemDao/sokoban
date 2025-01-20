
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class BoxOnEdgeException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public BoxOnEdgeException(){}
   public BoxOnEdgeException(final String message){super(message);}
}

