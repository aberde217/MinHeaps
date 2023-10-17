public class MinPQ <Item extends Comparable<Item>> {
    private Item[] arr;
    private int size = 0;
    private static final int INITIAL_SIZE = 10;

    public MinPQ() {
        arr = (Item[]) new Comparable[INITIAL_SIZE];
        arr[0] = null;
    }

    private void swap(int child, int parent) {
        Item copy = arr[parent];
        arr[parent] = arr[child];
        arr[child] = copy;
    }
    private int parentIndex(int index) {
        if (index/2 == 0) return 1;
        return index/2;
    }

    private int rightChildIndex(int index) {
        return (index * 2) + 1;
    }

    private int leftChildIndex(int index) {
        return (index * 2);
    }
    private void swimUp(int index) {
        if (index == 1);
        else if (arr[index].compareTo(arr[parentIndex(index)]) <= 0) {
            swap(index, parentIndex(index));
            swimUp(parentIndex(index));
        }
    }

    private void swimDown(int index) {
        if (arr[leftChildIndex(index)] == null && arr[rightChildIndex(index)] == null);
        else if (arr[leftChildIndex(index)] != null && arr[index].compareTo(arr[leftChildIndex(index)]) >= 0) {
            swap(leftChildIndex(index),index);
            swimDown(leftChildIndex(index));
        } else if (arr[rightChildIndex(index)] != null && arr[index].compareTo(arr[rightChildIndex(index)]) >= 0) {
            swap(rightChildIndex(index),index);
            swimDown(rightChildIndex(index));
        }
    }

    private void resizeUp() {
        Item[] newArr = (Item[]) new Comparable[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, size + 1);
        arr = newArr;
    }
    private void resizeDown() {
        Item[] newArr = (Item[]) new Comparable[arr.length/2];
        System.arraycopy(arr, 0, newArr, 0, size + 1);
        arr = newArr;
    }
    public void add(Item x) {
        if ((double)size/arr.length >= ((double)4/5)) {
            resizeUp();
        }
        arr[size + 1] = x;
        size += 1;
        int index = size;
        swimUp(index);
    }

    public Item getSmallest() {
        return arr[1];
    }

    public Item removeSmallest() {
        Item smallest = getSmallest();
        swap(size, 1);
        arr[size] = null;
        swimDown(1);
        size--;
        if ((double)size/arr.length <= ((double)1/6)) resizeDown();
        return smallest;
    }

    public int size() {
        return size;
    }
}
