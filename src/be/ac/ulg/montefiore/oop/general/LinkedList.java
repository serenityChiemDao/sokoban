
package be.ac.ulg.montefiore.oop.general;

public class LinkedList<Type> implements Stack<Type>
{
   private LinkedListElement<Type> first = null;

   public LinkedListElement<Type> first()
   {
      return first;
   }

   public void push(final Type data)
   {
      first = new LinkedListElement<Type>(data, first);
   }

   public Type pop()
   {
      final Type oldFirstData = first.data();
      first = first.next();
      return oldFirstData;
   }
}

