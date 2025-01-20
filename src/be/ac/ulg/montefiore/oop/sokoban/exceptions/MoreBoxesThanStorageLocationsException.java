
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class MoreBoxesThanStorageLocationsException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public MoreBoxesThanStorageLocationsException(){}
   public MoreBoxesThanStorageLocationsException(final String message)
   {
      super(message);
   }
}

