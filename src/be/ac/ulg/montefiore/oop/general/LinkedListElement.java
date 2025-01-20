
package be.ac.ulg.montefiore.oop.general;

public class LinkedListElement<Type>
{
   private final Type data;
   private final LinkedListElement<Type> next;

   public LinkedListElement(final Type data, final LinkedListElement<Type> next)
   {
      this.data = data;
      this.next = next;
   }

   public final Type data()
   {
      return data;
   }

   public final LinkedListElement<Type> next()
   {
      return next;
   }
}

