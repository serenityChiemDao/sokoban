
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class TooManyElementsInLineException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public TooManyElementsInLineException(){}
   public TooManyElementsInLineException(final String message){super(message);}
}

