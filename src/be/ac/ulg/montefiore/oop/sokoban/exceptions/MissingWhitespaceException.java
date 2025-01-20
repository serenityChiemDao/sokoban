
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class MissingWhitespaceException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public MissingWhitespaceException(){}
   public MissingWhitespaceException(final String message){super(message);}
}

