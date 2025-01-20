
package be.ac.ulg.montefiore.oop.sokoban;

import be.ac.ulg.montefiore.oop.general.*;

import be.ac.ulg.montefiore.oop.graphics.SokobanView;
import be.ac.ulg.montefiore.oop.graphics.SokobanEventsHandler;
import be.ac.ulg.montefiore.oop.graphics.NegativeNumberException;
import be.ac.ulg.montefiore.oop.graphics.UnknownDirectionException;
import be.ac.ulg.montefiore.oop.graphics.NullArrayException;
import be.ac.ulg.montefiore.oop.graphics.BadHeightException;
import be.ac.ulg.montefiore.oop.graphics.BadWidthException;
import be.ac.ulg.montefiore.oop.graphics.BadCellConstantException;

public class EventsHandler implements SokobanEventsHandler
{
   protected final Game game;

   public EventsHandler(final Game game){this.game = game;}

   public final Game game(){return game;}

   protected Direction direction(final int directionConstant)
   {
      switch(directionConstant)
      {
         case UP: return Direction.UP;
         case DOWN: return Direction.DOWN;
         case LEFT: return Direction.LEFT;
         case RIGHT: return Direction.RIGHT;
         default: return null;
      }
   }

   protected Move reverseMove(final boolean isPush, final Direction direction)
   {
      if(isPush)
         switch(direction)
         {
            case UP: return Move.PULL_DOWN;
            case DOWN: return Move.PULL_UP;
            case LEFT: return Move.PULL_RIGHT;
            default: return Move.PULL_LEFT;
         }
      else
         switch(direction)
         {
            case UP: return Move.GO_BACK_DOWN;
            case DOWN: return Move.GO_BACK_UP;
            case LEFT: return Move.GO_BACK_RIGHT;
            default: return Move.GO_BACK_LEFT;
         }
   }

   public int[][] sokobanViewBoard()
   {
      final Board board = game.board;
      final int height = board.height, width = board.width;

      final int sokobanViewBoard[][] = new int[height][width];

      final ImmobileCell iLayer[][] = board.iLayer;
      final int OUTSIDE = SokobanView.OUTSIDE, WALL = SokobanView.WALL,
      S_FLOOR = SokobanView.FLOOR, S_LOCATION = SokobanView.S_LOCATION;

      for(int l = 0; l < height; ++l)
         for(int c = 0; c < width; ++c)
            switch(iLayer[l][c].type)
            {
               case OUTSIDE: sokobanViewBoard[l][c] = OUTSIDE; break;
               case WALL: sokobanViewBoard[l][c] = WALL; break;
               case FLOOR: sokobanViewBoard[l][c] = S_FLOOR; break;
               default: sokobanViewBoard[l][c] = S_LOCATION;
            }

      final Player player = game.player;
      final int player_L = player.l, player_C = player.c;
      final ImmobileCellType I_FLOOR = ImmobileCellType.FLOOR;

      sokobanViewBoard[player_L][player_C] =
      (
         iLayer[player_L][player_C].type == I_FLOOR ? SokobanView.PLAYER :
         SokobanView.S_LOCATION_W_PLAYER
      );

      final int S_BOX = SokobanView.BOX, BOX_OK = SokobanView.BOX_OK;

      for
      (
         LinkedListElement<MobileCell> boxElement = game.boxList.first();
         boxElement != null;
         boxElement = boxElement.next()
      )
      {
         final MobileCell box = boxElement.data();
         final int boxL = box.l, boxC = box.c;
         sokobanViewBoard[boxL][boxC] =
         (
            iLayer[boxL][boxC].type == I_FLOOR ? S_BOX : BOX_OK
         );
      }

      return sokobanViewBoard;
   }

   protected boolean gameWon()
   {
      final ImmobileCell iLayer[][] = game.board.iLayer;
      final ImmobileCellType S_LOCATION = ImmobileCellType.S_LOCATION;
      for
      (
         LinkedListElement<MobileCell> boxElement = game.boxList.first();
         boxElement != null;
         boxElement = boxElement.next()
      )
      {
         final MobileCell box = boxElement.data();
         if(iLayer[box.l][box.c].type != S_LOCATION)
            return false;
      }
      return true;
   }

   protected int directionConstant(final Direction direction)
   {
      switch(direction)
      {
         case UP: return SokobanView.UP;
         case DOWN: return SokobanView.DOWN;
         case LEFT: return SokobanView.LEFT;
         default: return SokobanView.RIGHT;
      }
   }

   public void move(final int directionConstant)
   {
      final Direction direction = direction(directionConstant);
      if(direction != null)
      {
         try
         {
            final Player player = game.player;
            final SokobanView view = game.view;

            if(player.canPush(direction))
            {
               player.push(direction);
               view.updateMovements(++game.moveNumber);
               view.updatePushes(++game.pushNumber);
               game.moveStack.push(reverseMove(true, direction));
            }
            else if(player.canMove(direction))
            {
               player.move(direction);
               view.updateMovements(++game.moveNumber);
               game.moveStack.push(reverseMove(false, direction));
            }
            else
               player.direction = direction;
            
            view.updatePlayerDirection(directionConstant(player.direction));
            view.updateBoard(sokobanViewBoard());
            if(gameWon())
               view.win();
            view.refreshWindow();
         }
         catch
         (
            final NegativeNumberException | UnknownDirectionException |
            NullArrayException | BadHeightException | BadWidthException |
            BadCellConstantException e
         )
         {
            System.err.println
            (
               "This exception should never have been triggered.\n"
            );
            e.printStackTrace();
            System.exit(-5);
         }
      }
   }

   public void undo()
   {
      try
      {
         final Move lastMove = (game.moveStack.pop());
         final SokobanView view = game.view;
         switch(lastMove)
         {
            case GO_BACK_UP:
            case GO_BACK_DOWN:
            case GO_BACK_LEFT:
            case GO_BACK_RIGHT:
               game.player.goBack(lastMove.direction);
            break;
            default:
               game.player.pull(lastMove.direction);
               view.updatePushes(--game.pushNumber);
            break;
         }

         view.updateMovements(--game.moveNumber);
         view.updatePlayerDirection(directionConstant(game.player.direction));
         view.updateBoard(sokobanViewBoard());
         view.refreshWindow();
      }
      catch
      (
         final NegativeNumberException | UnknownDirectionException |
         NullArrayException | BadHeightException | BadWidthException |
         BadCellConstantException e
      )
      {
         System.err.println
         (
            "This exception should never have been triggered.\n"
         );
         e.printStackTrace();
         System.exit(-6);
      }
   }

   public void restart()
   {
      LinkedListElement<MobileCell> firstBoxElement = game.boxList.first();
      final MobileCell[][] mLayer = game.board.mLayer;
      for
      (
         LinkedListElement<MobileCell> boxElement = firstBoxElement;
         boxElement != null;
         boxElement = boxElement.next()
      )
      {
         final MobileCell box = boxElement.data();
         mLayer[box.l][box.c] = null;
      }
      final Player player = game.player;
      mLayer[player.l][player.c] = null;

      mLayer[player.l = player.initL][player.c = player.initC] = player;
      for
      (
         LinkedListElement<MobileCell> boxElement = firstBoxElement;
         boxElement != null;
         boxElement = boxElement.next()
      )
      {
         final MobileCell box = boxElement.data();
         mLayer[box.l = box.initL][box.c = box.initC] = box;
      }

      game.moveStack = new LinkedList<Move>();
      try
      {
         final SokobanView view = game.view;
         view.updateMovements(game.moveNumber = 0);
         view.updatePushes(game.pushNumber = 0);
         view.updatePlayerDirection
         (
            directionConstant(player.direction = Direction.UP)
         );
         view.updateBoard(sokobanViewBoard());
         view.refreshWindow();
      }
      catch
      (
         final NegativeNumberException | UnknownDirectionException |
         NullArrayException | BadHeightException | BadWidthException |
         BadCellConstantException e
      )
      {
         System.err.println
         (
            "This exception should never have been triggered.\n"
         );
         e.printStackTrace();
         System.exit(-7);
      }
   }
}

