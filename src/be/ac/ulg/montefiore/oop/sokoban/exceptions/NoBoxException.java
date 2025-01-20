
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class NoBoxException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public NoBoxException(){}
   public NoBoxException(final String message){super(message);}
}

