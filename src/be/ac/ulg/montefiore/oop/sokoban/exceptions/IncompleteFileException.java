
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class IncompleteFileException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public IncompleteFileException(){}
   public IncompleteFileException(final String message){super(message);}
}

