import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;


public class LinkedListDeque<BleepBlorp> {

    public class IntNode {
        public BleepBlorp item;
        public IntNode next;
        public IntNode pre;
        public IntNode(BleepBlorp i, IntNode a, IntNode b) {
            item = i;
            next = a;
            pre = b;
        }
    }
    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null,null,null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(BleepBlorp item){
        IntNode first = sentinel.next;
        IntNode newFirst = new IntNode(item,first,sentinel);
        sentinel.next = newFirst;
        first.pre = newFirst;

        size += 1;
    }

    public void addLast(BleepBlorp item) {
        IntNode last = sentinel.pre;
        IntNode newLast = new IntNode(item, sentinel, last);
        last.next = newLast;
        sentinel.pre = newLast;

        size += 1;

    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }else{
            return false;
        }
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

    public BleepBlorp removeFirst() {
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

    public BleepBlorp removeLast() {
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

    public BleepBlorp get(int index) {
        int i = 0;
        IntNode first = sentinel.next;
        while (i < index) {
            if (first == sentinel) {
                return null;
            }
            first = first.next;
        }
        return first.item;
    }

    public BleepBlorp getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return recursiveHelper(index, sentinel.next);

    }

    public BleepBlorp recursiveHelper(int index, IntNode node) {
        if (index == 0) {
            return node.item;
        }else {
            return recursiveHelper(index-1, node.next);
        }
    }
}
