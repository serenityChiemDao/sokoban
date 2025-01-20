
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class InvalidFirstElementException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public InvalidFirstElementException(){}
   public InvalidFirstElementException(final String message){super(message);}
}

