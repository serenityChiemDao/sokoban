
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class SupernumeraryPlayerException extends GameFileException
{
   private static final long serialVersionUID = 1L;

   public SupernumeraryPlayerException(){}
   public SupernumeraryPlayerException(final String message){super(message);}
}

