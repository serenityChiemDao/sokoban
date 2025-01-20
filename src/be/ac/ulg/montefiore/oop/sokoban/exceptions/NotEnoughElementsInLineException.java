
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class NotEnoughElementsInLineException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public NotEnoughElementsInLineException(){}
   public NotEnoughElementsInLineException(final String message)
   {
      super(message);
   }
}

