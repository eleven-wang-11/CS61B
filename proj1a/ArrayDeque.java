public class ArrayDeque<T> {
    private T[] items;
    private int CAPACITY = 8;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T []) new Object[CAPACITY];
        size = 0;
        nextFirst = items.length / 2;
        nextLast = plusOne(nextFirst);
    }
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    public void printDeque() {
        int iterator = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[iterator] + " ");
            iterator = plusOne(iterator);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        resize();
        return returnItem;

    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        resize();
        return returnItem;
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int iterator = plusOne(nextFirst);
        for (int i = 0; i < index; i++) {
            iterator = plusOne(iterator);
        }
        return items[iterator];
    }

    private void resize() {
        if (size == items.length) {
            expand();
        }

        if (size < items.length * 0.25 && size > CAPACITY) {
            reduce();
        }
    }

    private void expand() {
        changeCapacity(items.length * 2);
    }
    private void reduce() {
        changeCapacity(items.length / 2);
    }
    private void changeCapacity(int capacity) {
        T[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne((nextLast));
        items = (T []) new Object[capacity];
        nextFirst = items.length / 2;
        nextLast = plusOne(nextFirst);
        //nextFirst does not change, only change nextLast
        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            index = 0;
        } else {
            index += 1;
        }
        return index;
    }

    private int plusOne(int index, int length) {
        if (index == length - 1) {
            index = 0;
        } else {
            index += 1;
        }
        return index;
    }

    private int minusOne(int index) {
        if (index == 0) {
            index = items.length - 1;
        } else {
            index -= 1;
        }
        return index;
    }


    private static void main(String[] args) {
        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            ald1.addFirst(i);
        }
        ald1.printDeque();
        for (int i = 0; i < 100; i++) {
            ald1.removeFirst();
        }
        System.out.println(ald1.size());

    }



}
