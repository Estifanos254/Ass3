import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javafx.scene.Node;

/**
 * A basic implementation of a doubly linked list with various methods to add, remove, and retrieve elements.
 * @param <T> the type of the elements in the list
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

    /**
     * The head of the list
     */
    protected Node head;

    /**
     * The tail of the list
     */
    protected Node tail;

    /**
     * The size of the list
     */
    protected int size;

    /**
     * Initializes an empty list.
     */
    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Gets the size of the list.
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds an element to the end of the list.
     * @param data the element to add
     */
    public void addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    /**
     * Adds an element to the front of the list.
     * @param data the element to add
     */
    public void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    /**
     * Gets the first element in the list.
     * @return the first element in the list, or null if the list is empty
     */
    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    /**
     * Gets the last element in the list.
     * @return the last element in the list, or null if the list is empty
     */
    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    /**
     * Returns an iterator over the elements in the list.
     * @return an iterator over the elements in the list
     */
    public ListIterator<T> iterator() {
        return new ListIterator<T>();
    }

    /**
     * Removes the first occurrence of an element in the list using the specified comparator.
     * @param targetData the element to remove
     * @param comparator the comparator used to compare elements
     * @return the removed node, or null if the element is not found
     */

    /**
     * Removes and returns the first element in the list.
     * @return the first element in the list, or null if the list is
     */
    
        public Node remove(T targetData, Comparator<T> comparator) {
            Node currentNode = head;

            while (currentNode != null) {
                if (comparator.compare(currentNode.data, targetData) == 0) {
                    if (currentNode == head) {
                        head = currentNode.next;

                        if (head != null) {
                            head.prev = null;
                        }

                        size--;
                        return currentNode;
                    } else if (currentNode == tail) {
                        tail = currentNode.prev;
                        tail.next = null;
                        size--;
                        return currentNode;
                    } else {
                        currentNode.prev.next = currentNode.next;
                        currentNode.next.prev = currentNode.prev;
                        size--;
                        return currentNode;
                    }
                }

                currentNode = currentNode.next;
            }

            return null;
        }

        public T retrieveFirstElement() {
            if (head == null) {
                return null;
            }

            T data = head.data;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }

            size--;

            return data;
        }

        public T retrieveLastElement() {
            if (tail == null) {
                return null;
            }

            T data = tail.data;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }

            size--;

            return data;
        }

        public ArrayList<T> toArrayList() {
            ArrayList<T> list = new ArrayList<T>();
            Node currentNode = head;

            while (currentNode != null) {
                list.add(currentNode.data);
                currentNode = currentNode.next;
            }

            return list;
        }

        protected class Node {
            protected T data;
            protected Node prev;
            protected Node next;

            protected Node(T data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }

            
            
            
            
            protected class DoubleLinkedListIterator implements ListIterator<T> {
                private Node nextNode;
                private int nextIndex;
                private Node lastReturnedNode;
                
                public DoubleLinkedListIterator() {
                    nextNode = head;
                    nextIndex = 0;
                    lastReturnedNode = null;
                }
                
                public DoubleLinkedListIterator(int index) {
                    if (index < 0 || index > size) {
                        throw new IndexOutOfBoundsException();
                    }
                    if (index == size) {
                        nextNode = null;
                    } else {
                        nextNode = getNodeAtIndex(index);
                    }
                    nextIndex = index;
                    lastReturnedNode = null;
                }
                
                private BasicDoubleLinkedList<T>.Node getNodeAtIndex(int index) {
					// TODO Auto-generated method stub
					return null;
				}

				public boolean hasNext() {
                    return nextIndex < size;
                }
                
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    lastReturnedNode = nextNode;
                    nextNode = nextNode.next;
                    nextIndex++;
                    return lastReturnedNode.data;
                }
                
                public boolean hasPrevious() {
                    return nextIndex > 0;
                }
                
                public T previous() {
                    if (!hasPrevious()) {
                        throw new NoSuchElementException();
                    }
                    if (nextNode == null) {
                        lastReturnedNode = tail;
                        nextNode = tail;
                    } else {
                        lastReturnedNode = nextNode.prev;
                        nextNode = nextNode.prev;
                    }
                    nextIndex--;
                    return lastReturnedNode.data;
                }
                
                public int nextIndex() {
                    return nextIndex;
                }
                
                public int previousIndex() {
                    return nextIndex - 1;
                }
                
                public void remove() {
                    if (lastReturnedNode == null) {
                        throw new IllegalStateException();
                    }
                    Node nodeToRemove = lastReturnedNode;
                    lastReturnedNode = null;
                    removeNode(nodeToRemove);
                    nextIndex--;
                }
                
                public void set(T data) {
                    if (lastReturnedNode == null) {
                        throw new IllegalStateException();
                    }
                    lastReturnedNode.data = data;
                }
                
                public void add(T data) {
                    Node newNode = new Node(data);
                    if (head == null) {
                        head = newNode;
                        tail = newNode;
                    } else if (nextNode == null) {
                        newNode.prev = tail;
                        tail.next = newNode;
                        tail = newNode;
                    } else {
                        newNode.prev = nextNode.prev;
                        newNode.next = nextNode;
                        nextNode.prev.next = newNode;
                        nextNode.prev = newNode;
                    }
                    size++;
                    nextIndex++;
                    lastReturnedNode = null;
                }
                
                public void unsupportedOperation() {
                    throw new UnsupportedOperationException();
                }
            }
            
        }
    

