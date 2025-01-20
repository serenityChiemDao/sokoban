
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class InvalidSecondElementException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public InvalidSecondElementException(){}
   public InvalidSecondElementException(final String message){super(message);}
}

