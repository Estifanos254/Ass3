import java.util.Comparator;
import java.util.ListIterator;
import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    private Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = tail = newNode;
        } else if (comparator.compare(data, head.data) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (comparator.compare(data, tail.data) >= 0) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node currentNode = head;
            while (comparator.compare(data, currentNode.data) > 0) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode;
            newNode.prev = currentNode.prev;
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
        }
        size++;
    }

    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    public ListIterator<T> iterator() {
        return super.iterator();
    }

   
}


@Override
public void addToEnd(T data) {
    throw new UnsupportedOperationException("Invalid operation for sorted list.");
}

@Override
public void addToFront(T data) {
    throw new UnsupportedOperationException("Invalid operation for sorted list.");
}

public ListIterator<T> iterator() {
    return new SortedListIterator();
}

public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) {
    Node currentNode = head;
    Node prevNode = null;
    while (currentNode != null && comparator.compare(data, currentNode.data) != 0) {
        prevNode = currentNode;
        currentNode = currentNode.next;
    }
    if (currentNode == null) {
        return null;
    }
    if (prevNode == null) {
        head = head.next;
    } else {
        prevNode.next = currentNode.next;
    }
    if (currentNode.next == null) {
        tail = prevNode;
    } else {
        currentNode.next.prev = prevNode;
    }
    size--;
    return currentNode;
}

private class SortedListIterator implements ListIterator<T> {
    
    private Node current;
    private boolean forward;
    
    public SortedListIterator() {
        current = head;
        forward = true;
    }
    
    public void add(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }
    
    public boolean hasNext() {
        return current != null;
    }
    
    public boolean hasPrevious() {
        return current != null;
    }
    
    public T next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        T data = current.data;
        current = current.next;
        forward = true;
        return data;
    }
    
    public int nextIndex() {
        throw new UnsupportedOperationException();
    }
    
    public T previous() {
        if (!hasPrevious()) {
            throw new java.util.NoSuchElementException();
        }
        T data = current.data;
        current = current.prev;
        forward = false;
        return data;
    }
    
    public int previousIndex() {
        throw new UnsupportedOperationException();
    }
    
    public void remove() {
        if (current == null) {
            throw new IllegalStateException();
        }
        if (forward) {
            current = current.prev;
            SortedDoubleLinkedList.this.remove(current.next.data, comparator);
        } else {
            current = current.next;
            SortedDoubleLinkedList.this.remove(current.prev.data, comparator);
        }
    }
    
    public void set(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }
}
}







