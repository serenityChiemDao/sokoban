
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class SupernumeraryLineException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public SupernumeraryLineException(){}
   public SupernumeraryLineException(final String message){super(message);}
}

