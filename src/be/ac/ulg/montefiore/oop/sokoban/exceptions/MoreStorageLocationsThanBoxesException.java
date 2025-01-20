
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class MoreStorageLocationsThanBoxesException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public MoreStorageLocationsThanBoxesException(){}
   public MoreStorageLocationsThanBoxesException(final String message)
   {
      super(message);
   }
}

