
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class MisplacedOutsideException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public MisplacedOutsideException(){}
   public MisplacedOutsideException(final String message){super(message);}
}

