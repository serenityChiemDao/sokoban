
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class IncompleteLineException extends IncompleteFileException
{
   private static final long serialVersionUID = 1L;

   public IncompleteLineException(){}
   public IncompleteLineException(final String message){super(message);}
}

