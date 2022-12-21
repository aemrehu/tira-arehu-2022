package oy.tol.tra;

import java.util.Arrays;
import java.util.List;

public class LinkedListImplementation<E> implements LinkedListInterface<E> {

   private class Node<T> {
      Node(T data) {
         element = data;
         next = null;
      }
      T element;
      Node<T> next;
      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head = null;
   private int size = 0;

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      if (element == null) {
         throw new NullPointerException("Element cannot be null");
      }
      Node<E> newnode = new Node<E>(element);
      if (head == null) {
         head = newnode;
         size++;
      } else if (head != null) {
         newnode.element = element;
         Node<E> currentnode = head;
         while (currentnode.next != null) {
            currentnode = currentnode.next;
         }
         currentnode.next = newnode;
         size++;
      } else {
         throw new LinkedListAllocationException("Failed to allocate new list element.");
      }
   }

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      if (index < 0 || (index >= size && index != 0)) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      } else if (element == null) {
         throw new NullPointerException("Element cannot be null");
      }
      Node<E> newnode = new Node<E>(element);
      if (index == 0) {
         newnode.next = head;
         head = newnode;
         size++;
      } else if (head != null) {
         int counter = 0;
         Node<E> current = head;
         Node<E> previous = null;
         while (current != null && counter != index) {
            previous = current;
            current = current.next;
            counter++;
         }
         newnode.next = current;
         previous.next = newnode;
         size++;
      } else {
         throw new LinkedListAllocationException("Failed to allocate new list element.");
      }
   }

   @Override
   public boolean remove(E element) throws NullPointerException {
      if (element == null) {
         throw new NullPointerException("Element cannot be null");
      } else if (head == null) {
         return false;
      } else if (element.equals(head.element)) {
         head = head.next;
         size--;
         return true;
      }
      Node<E> current = head;
      Node<E> previous = null;
      while (current.next != null) {
         if (!element.equals(current.element)) {
            previous = current;
            current = current.next;
            continue;
         } else {
            previous.next = current.next;
            size--;
            return true;
         }
      }
      return false;
   }

   @Override
   public E remove(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      } else if (head != null) {
         int counter = 0;
         Node<E> current = head;
         Node<E> previous = null;
         while (current != null && counter != index) {
            previous = current;
            current = current.next;
            counter++;
         }
         if (previous == null) {
            head = current.next;
            size--;
         } else {
            previous.next = current.next;
            size--;
         }
         return current.element;
      } else {
         return null;
      }
   }

   @Override
   public E get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("Index out of bounds");
      } else if (head == null) {
         return null;
      } else {
         int counter = 0;
         Node<E> current = head;
         while (current.next != null && counter != index) {
            current = current.next;
            counter++;
         }
         return current.element;
      }
   }

   @Override
   public int indexOf(E element) throws NullPointerException {
      if (element == null) {
         throw new NullPointerException("Element cannot be null");
      } else if (head == null) {
         return -1;
      }
      int counter = 0;
      if (element.equals(head.element)) {
         return counter;
      }
      Node<E> current = head;
      while (current.next != null) {
         if (!element.equals(current.element)) {
            current = current.next;
            counter++;
            continue;
         } else {
         return counter;
         }
      }
      if (element.equals(current.element)) {
         return counter;
      } else {
         return -1;
      }
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void clear() {
      size = 0;
      head = null;
   }

   @Override
   public void reverse() {
      if (head == null) {
         ;
      } else {
         Node<E> current = head;
         Node<E> previous = null;
         Node<E> tmp = null;
         for (int i=0; i < size; i++) {
            if (current.next == null) {
               current.next = previous;
               head = current;
               break;
            } else {
               tmp = current.next;
               current.next = previous;
               previous = current;
               current = tmp;
            }
         }
      }
   }

   @Override
   public String toString() {
      if (size == 0) {
         return "[]";
      }
      Node<E> current = head;
      String result = "[";
      while (current.next != null) {
         result = result + current.toString() + ", ";
         current = current.next;
      }
      result = result + current.toString() + "]";
      return result;
   }
}
