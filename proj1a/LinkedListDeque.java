


public class LinkedListDeque<T> {

    private class IntNode {
        private T item;
        private IntNode next;
        private IntNode pre;
        public IntNode(T i, IntNode a, IntNode b) {
            item = i;
            next = a;
            pre = b;
        }
    }
    private final IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        IntNode first = sentinel.next;
        IntNode newFirst = new IntNode(item, first, sentinel);
        sentinel.next = newFirst;
        first.pre = newFirst;

        size += 1;
    }

    public void addLast(T item) {
        IntNode last = sentinel.pre;
        IntNode newLast = new IntNode(item, sentinel, last);
        last.next = newLast;
        sentinel.pre = newLast;

        size += 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        IntNode front = sentinel.next;
        IntNode newFirst = sentinel.next.next;
        sentinel.next = newFirst;
        newFirst.pre = sentinel;
        size -= 1;

        return front.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        IntNode last = sentinel.pre;
        IntNode newLast = sentinel.pre.pre;
        newLast.next = sentinel;
        sentinel.pre = newLast;
        size -= 1;
        return last.item;
    }

    public T get(int index) {

        if (index >= size || index < 0) {
            return  null;
        }

        IntNode node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return recursiveHelper(index, sentinel.next);

    }

    private T recursiveHelper(int index, IntNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return recursiveHelper(index - 1, node.next);
        }
    }
}
