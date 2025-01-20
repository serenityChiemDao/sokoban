
package be.ac.ulg.montefiore.oop.sokoban;

public class ImmobileCell
{
   protected final ImmobileCellType type;

   public ImmobileCell(final ImmobileCellType type){this.type = type;}

   public final ImmobileCellType type(){return type;}
}

