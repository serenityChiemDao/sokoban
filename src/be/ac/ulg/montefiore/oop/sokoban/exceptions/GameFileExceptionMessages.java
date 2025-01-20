
package be.ac.ulg.montefiore.oop.sokoban.exceptions;

public class GameFileExceptionMessages
{
   public static String emptyFileExceptionMessage(final String gameFileName)
   {
      return "The file " + gameFileName + " is empty.";
   }

   public static String emptyLineExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return 
         "Line " + lineNumber + " in the file " + gameFileName + " is empty.";
   }

   public static String invalidFirstElementExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "The first element of the file " + gameFileName + 
         " should be an integer.";
   }

   public static String incompleteLineExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "Line " + lineNumber + " in the file " + gameFileName +
         " is incomplete.";
   }

   public static String invalidSecondElementExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "The second element of the file " + gameFileName + 
         " should be an integer.";
   }

   public static String notEnoughElementsInLineExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "There aren't enough elements in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String tooManyElementsInLineExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "There are too many elements in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String incompleteFileExceptionMessage(final String gameFileName)
   {
      return "The file " + gameFileName + " is incomplete.";
   }

   public static String missingWhitespaceExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "A whitespace character is missing in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String invalidCharacterExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "There is an invalid character in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String misplacedOutsideExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "There is a misplaced outside cell in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String floorOnEdgeExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There is a a floor cell on the edge of the board in the file "
         + gameFileName + ".";
   }

   public static String storageLocationOnEdgeExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There is a a storage location cell on the edge of the board in " +
         " the file " + gameFileName + ".";
   }

   public static String boxOnEdgeExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There is a a box cell on the edge of the board in the file "
         + gameFileName + ".";
   }

   public static String playerOnEdgeExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There is a a player cell on the edge of the board in the file "
         + gameFileName + ".";
   }

   public static String supernumeraryPlayerExceptionMessage
   (
      final String gameFileName, final int lineNumber
   )
   {
      return
         "There is a supernumerary player in the line " + lineNumber +
         " in the file " + gameFileName + ".";
   }

   public static String supernumeraryLineExceptionMessage
   (
      final String gameFileName
   )
   {
      return "There is a supernumerary line in the file " + gameFileName + ".";
   }

   public static String noPlayerExceptionMessage(final String gameFileName)
   {
      return "There is no player in the file " + gameFileName + ".";
   }

   public static String noBoxExceptionMessage(final String gameFileName)
   {
      return "There is no box in the file " + gameFileName + ".";
   }

   public static String moreBoxesThanStorageLocationsExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There are more boxes than storage locations in the file " +
         gameFileName + ".";
   }

   public static String moreStorageLocationsThanBoxesExceptionMessage
   (
      final String gameFileName
   )
   {
      return
         "There are more storage locations than boxes in the file " +
         gameFileName + ".";
   }
}

