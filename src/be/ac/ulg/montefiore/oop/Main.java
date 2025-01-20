
package be.ac.ulg.montefiore.oop;

import be.ac.ulg.montefiore.oop.sokoban.Game;

import java.io.FileNotFoundException;
import be.ac.ulg.montefiore.oop.sokoban.exceptions.GameFileException;
import be.ac.ulg.montefiore.oop.graphics.NullHandlerException;
import be.ac.ulg.montefiore.oop.graphics.NullArrayException;
import be.ac.ulg.montefiore.oop.graphics.BadHeightException;
import be.ac.ulg.montefiore.oop.graphics.BadWidthException;
import be.ac.ulg.montefiore.oop.graphics.BadCellConstantException;

public class Main
{
   public static void main (String[] args)
   {
      if(args.length != 1)
      {
         System.err.println("Usage : java -cp \"graphics.jar\" Main board.txt");
         System.exit(-1);
      }
      try
      {
         final Game game = new Game(args[0]);
         game.play();
      }
      catch(final FileNotFoundException e)
      {
         System.err.println(args[0] + " was not found.");
         System.exit(-2);
      }
      catch(final GameFileException e)
      {
         System.err.println(e.getMessage());
         System.exit(-3);
      }
      catch
      (
         final NullHandlerException | NullArrayException | BadHeightException |
         BadWidthException | BadCellConstantException e
      )
      {
         e.printStackTrace();
         System.exit(-4);
      }
}
}

