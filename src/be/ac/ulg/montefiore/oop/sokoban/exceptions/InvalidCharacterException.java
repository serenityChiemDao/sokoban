
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class InvalidCharacterException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public InvalidCharacterException(){}
   public InvalidCharacterException(final String message){super(message);}
}

