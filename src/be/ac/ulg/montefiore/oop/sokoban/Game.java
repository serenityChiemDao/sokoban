
package be.ac.ulg.montefiore.oop.sokoban;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import be.ac.ulg.montefiore.oop.general.Stack;
import be.ac.ulg.montefiore.oop.general.LinkedList;
import be.ac.ulg.montefiore.oop.graphics.SokobanView;
import be.ac.ulg.montefiore.oop.graphics.SokobanSwingView;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import be.ac.ulg.montefiore.oop.sokoban.exceptions.*;
import be.ac.ulg.montefiore.oop.graphics.BadHeightException;
import be.ac.ulg.montefiore.oop.graphics.BadWidthException;
import be.ac.ulg.montefiore.oop.graphics.NullHandlerException;
import be.ac.ulg.montefiore.oop.graphics.NullArrayException;
import be.ac.ulg.montefiore.oop.graphics.BadCellConstantException;

public class Game
{
   protected final Board board;
   protected final LinkedList<MobileCell> boxList;
   protected final Player player;

   protected Stack<Move> moveStack;
   protected int moveNumber;
   protected int pushNumber;

   protected final SokobanView view;
   protected final EventsHandler eventsHandler;

   public Game(final String gameFileName)
   throws FileNotFoundException, GameFileException, NullHandlerException
   {
      try(final Scanner gameFileScanner = new Scanner(new File(gameFileName)))
      {
         final String firstLine;
         try{firstLine = gameFileScanner.nextLine();}
         catch(final NoSuchElementException e)
         {
            throw new EmptyFileException
            (
               GameFileExceptionMessages.emptyFileExceptionMessage
               (
                  gameFileName
               )
            );
         }
         if(firstLine.length() == 0)
            throw new EmptyLineException
               (
                  GameFileExceptionMessages.emptyLineExceptionMessage
                     (gameFileName, 1)
               );

         StringTokenizer stringTokenizer = new StringTokenizer(firstLine);

         final int height;
         try{height = Integer.parseInt(stringTokenizer.nextToken());}
         catch(final NumberFormatException e)
         {
            throw new InvalidFirstElementException
            (
               GameFileExceptionMessages.invalidFirstElementExceptionMessage
               (
                  gameFileName
               )
            );
         }

         final int width;
         try{width = Integer.parseInt(stringTokenizer.nextToken());}
         catch(final NoSuchElementException e)
         {
            throw new IncompleteLineException
            (
               GameFileExceptionMessages.incompleteLineExceptionMessage
               (
                  gameFileName, 1
               )
            );
         }
         catch(final NumberFormatException e)
         {
            throw new InvalidSecondElementException
            (
               GameFileExceptionMessages.invalidSecondElementExceptionMessage
               (
                  gameFileName
               )
            );
         }

         if(stringTokenizer.hasMoreTokens())
            throw new TooManyElementsInLineException
            (
               GameFileExceptionMessages.tooManyElementsInLineExceptionMessage
               (
                  gameFileName, 1
               )
            );

         eventsHandler = new EventsHandler(this);
         try
         {
            view = new SokobanSwingView(height, width, eventsHandler);
         }
         catch(final BadHeightException | BadWidthException e)
         {
            throw new GameFileException(e.getMessage());
         }
         catch(final NullHandlerException e)
         {
            System.out.println("This exception should never have been triggered.\n");
            throw e;
         }
 
         board = new Board(height, width);

         boolean playerRead = false;
         int boxNumber = 0, storageLocationNumber = 0;
         Player player = null;
         boxList = new LinkedList<MobileCell>();

         for(int l = 0; l < height; ++l)
         {
            final String line;
            try{line = gameFileScanner.nextLine();}
            catch(final NoSuchElementException e)
            {
               throw new IncompleteFileException
               (
                  GameFileExceptionMessages.incompleteFileExceptionMessage
                  (
                     gameFileName
                  )
               );
            }
            if(line.length() == 0)
               throw new EmptyLineException
                  (
                     GameFileExceptionMessages.emptyLineExceptionMessage
                        (gameFileName, l + 2)
                  );

            stringTokenizer = new StringTokenizer(line);

            final int tokenNumber = stringTokenizer.countTokens();
            if(tokenNumber < width)
               throw new NotEnoughElementsInLineException
               (
                  GameFileExceptionMessages.
                  notEnoughElementsInLineExceptionMessage
                  (
                     gameFileName, l + 2
                  )
               );
            else if(tokenNumber > width)
               throw new TooManyElementsInLineException
               (
                  GameFileExceptionMessages.
                  tooManyElementsInLineExceptionMessage
                  (
                     gameFileName, l + 2
                  )
               );

            final ImmobileCellType
            OUTSIDE = ImmobileCellType.OUTSIDE,
            WALL = ImmobileCellType.WALL,
            S_LOCATION = ImmobileCellType.S_LOCATION,
            FLOOR = ImmobileCellType.FLOOR;

            for(int c = 0; c < width; ++c)
            {
               final String token = stringTokenizer.nextToken();
               if(token.length() > 1)
                  throw new MissingWhitespaceException
                  (
                     GameFileExceptionMessages.missingWhitespaceExceptionMessage
                     (
                        gameFileName, l + 2
                     )
                  );

               switch(token.charAt(0))
               {
                  default:
                     throw new InvalidCharacterException
                     (
                        GameFileExceptionMessages.
                        invalidCharacterExceptionMessage
                        (
                           gameFileName, l + 2
                        )
                     );

                  case 'O':
                     if(l != 0)
                        switch(board.iLayer[l - 1][c].type)
                        {
                           case FLOOR: case S_LOCATION:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }
                     if(c != 0)
                        switch(board.iLayer[l][c - 1].type)
                        {
                           case FLOOR: case S_LOCATION:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }
                     board.iLayer[l][c] = new ImmobileCell(OUTSIDE);
                  break;

                  case 'W':
                     board.iLayer[l][c] = new ImmobileCell(WALL);
                  break;

                  case 'F':
                     if(l == 0 || l == height - 1 || c == 0 || c == width - 1)
                        throw new FloorOnEdgeException
                        (
                           GameFileExceptionMessages.floorOnEdgeExceptionMessage
                           (
                              gameFileName
                           )
                        );
                     if(l != 0)
                        switch(board.iLayer[l - 1][c].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 1
                                 )
                              );
                           default:;
                        }
                     if(c != 0)
                        switch(board.iLayer[l][c - 1].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }

                     board.iLayer[l][c] = new ImmobileCell(FLOOR);
                  break;

                  case 'S':
                     if(l == 0 || l == height - 1 || c == 0 || c == width - 1)
                        throw new StorageLocationOnEdgeException
                        (
                           GameFileExceptionMessages.
                           storageLocationOnEdgeExceptionMessage
                           (
                              gameFileName
                           )
                        );
                     if(l != 0)
                        switch(board.iLayer[l - 1][c].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 1
                                 )
                              );
                           default:;
                        }
                     if(c != 0)
                        switch(board.iLayer[l][c - 1].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }

                     ++storageLocationNumber; 
                     board.iLayer[l][c] = new ImmobileCell(S_LOCATION);
                  break;

                  case 'B':
                     if(l == 0 || l == height - 1 || c == 0 || c == width - 1)
                        throw new BoxOnEdgeException
                        (
                           GameFileExceptionMessages.boxOnEdgeExceptionMessage
                           (
                              gameFileName
                           )
                        );
                     if(l != 0)
                        switch(board.iLayer[l - 1][c].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 1
                                 )
                              );
                           default:;
                        }
                     if(c != 0)
                        switch(board.iLayer[l][c - 1].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }

                     ++boxNumber;
                     boxList.push
                     (
                        board.mLayer[l][c] =  new MobileCell(l, c, board)
                     );
                     board.iLayer[l][c] = new ImmobileCell(FLOOR);
                  break;

                  case 'P':
                     if(playerRead)
                        throw new SupernumeraryPlayerException
                        (
                           GameFileExceptionMessages.supernumeraryPlayerExceptionMessage
                           (
                              gameFileName, l + 2
                           )
                        );
                     if
                     (
                        l == 0 || l == height - 1 || c == 0 || c == width - 1
                     )
                        throw new PlayerOnEdgeException
                        (
                           GameFileExceptionMessages.
                           playerOnEdgeExceptionMessage
                           (
                              gameFileName
                           )
                        );
                     if(l != 0)
                        switch(board.iLayer[l - 1][c].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 1
                                 )
                              );
                           default:;
                        }
                     if(c != 0)
                        switch(board.iLayer[l][c - 1].type)
                        {
                           case OUTSIDE:
                              throw new MisplacedOutsideException
                              (
                                 GameFileExceptionMessages.
                                 misplacedOutsideExceptionMessage
                                 (
                                    gameFileName, l + 2
                                 )
                              );
                           default:;
                        }

                     playerRead = true;
                     player = new Player(l, c, board);
                     board.mLayer[l][c] = player;
                     board.iLayer[l][c] = new ImmobileCell(FLOOR);
                  break;
               }
            }
         }

         if(gameFileScanner.hasNextLine())
            throw new SupernumeraryLineException
            (
               GameFileExceptionMessages.supernumeraryLineExceptionMessage
               (
                  gameFileName
               )
            );
         else if(!playerRead)
            throw new NoPlayerException
            (
               GameFileExceptionMessages.noPlayerExceptionMessage
               (
                  gameFileName
               )
            );
         else if(boxNumber == 0)
            throw new NoBoxException
            (
               GameFileExceptionMessages.noBoxExceptionMessage
               (
                  gameFileName
               )
            );
         else if(boxNumber > storageLocationNumber)
            throw new MoreBoxesThanStorageLocationsException
            (
               GameFileExceptionMessages.
               moreBoxesThanStorageLocationsExceptionMessage
               (
                  gameFileName
               )
            );
         else if(storageLocationNumber > boxNumber)
            throw new MoreStorageLocationsThanBoxesException
            (
               GameFileExceptionMessages.
               moreStorageLocationsThanBoxesExceptionMessage
               (
                  gameFileName
               )
            );


         this.player = player;

         moveNumber = 0;
         pushNumber = 0;
         moveStack = new LinkedList<Move>();
      }
   }

   public void play()
   throws
   NullArrayException, BadHeightException, BadWidthException,
   BadCellConstantException 
   {
      try
      {
         view.updateBoard(eventsHandler.sokobanViewBoard());
         view.refreshWindow();
      }
      catch
      (
         final NullArrayException | BadHeightException | BadWidthException |
         BadCellConstantException e
      )
      {
         System.err.println
         (
            "This exception should never have been triggered.\n"
         );
         throw e;
      }
   }
}

