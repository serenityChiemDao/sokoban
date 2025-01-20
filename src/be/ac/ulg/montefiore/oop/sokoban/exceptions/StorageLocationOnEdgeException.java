
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class StorageLocationOnEdgeException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public StorageLocationOnEdgeException(){}
   public StorageLocationOnEdgeException(final String message){super(message);}
}

