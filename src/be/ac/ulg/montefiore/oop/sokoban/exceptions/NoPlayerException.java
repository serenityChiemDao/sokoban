
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class NoPlayerException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public NoPlayerException(){}
   public NoPlayerException(final String message){super(message);}
}

