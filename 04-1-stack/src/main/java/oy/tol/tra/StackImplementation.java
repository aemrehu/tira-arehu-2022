package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   // TODO: Add member variables needed.
   // Do not use constant values in code, e.g. 10. Instead, define a constant for that. For example:
   // private static final int MY_CONSTANT_VARIABLE = 10;

   private Object [] itemArray;
   private int arrayCapacity;
   private int currentIndex;

   private static final int DEFAULT_CAPACITY = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size (see member variable!).
      try {
         arrayCapacity = DEFAULT_CAPACITY;
         currentIndex = -1;
         itemArray = new Object[arrayCapacity];
      } catch (Exception e) {
         throw new StackAllocationException(e.getMessage());
      }
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("Stack size should be greater than 1.");
      }
      try {
         arrayCapacity = capacity;
         currentIndex = -1;
         itemArray = new Object[arrayCapacity];
      } catch (Exception e) {
         throw new StackAllocationException(e.getMessage());
      }
   }

   @Override
   public int capacity() {
      return arrayCapacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if (element == null) {
         throw new NullPointerException();
      }
      if (currentIndex == arrayCapacity - 1) {
         arrayCapacity *= 2; //double the stack size
         final Object [] newArray = new Object[arrayCapacity]; //new stack object
         for (int i=0; i<currentIndex+1; i++) {
            newArray[i] = itemArray[i]; //transfer elements to new stack
         }
         itemArray = newArray; //oldstack is newstack
      }
      currentIndex++;
      itemArray[currentIndex] = element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if (currentIndex == -1) {
         throw new StackIsEmptyException("Stack is empty.");
      } else {
         currentIndex--;
         return (E) itemArray[currentIndex+1];
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if (currentIndex == -1) {
         throw new StackIsEmptyException("Stack is empty.");
      } else {
         return (E) itemArray[currentIndex];
      }
   }

   @Override
   public int size() {
      return currentIndex + 1;
   }

   @Override
   public void clear() {
      final Object [] newArray = new Object[arrayCapacity];
      itemArray = newArray;
      currentIndex = -1;
   }

   @Override
   public boolean isEmpty() {
      if (currentIndex == -1) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      
      if (isEmpty()) {
         return "[]";
      }
      String stack = "[";
      for (int i=0; i<currentIndex+1; i++) {
         if (i==0) {
            stack = stack + itemArray[i];
         } else if (i == currentIndex) {
            stack = stack + ", " + itemArray[i] + "]";
         } else {
            stack = stack + ", " + itemArray[i];
         }
      }
      return stack;
   }
}
