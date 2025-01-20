
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class EmptyFileException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public EmptyFileException(){}
   public EmptyFileException(final String message){super(message);}
}

