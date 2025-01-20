
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class EmptyLineException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public EmptyLineException(){}
   public EmptyLineException(final String message){super(message);}
}

