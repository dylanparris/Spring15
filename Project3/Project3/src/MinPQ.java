import java.util.Arrays;

public class MinPQ<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    public T[] array;
    public int size;
    
    /**
     * Constructs a new min-heap.
     */
    @SuppressWarnings("unchecked")
	public MinPQ () {
        array = (T[])new Comparable[DEFAULT_CAPACITY];  
        size = 0;
    }
    
    /**
     * Adds element to min-heap.
     * @param element new element to be added
     */
    public void add(T element) {
        if (size >= array.length - 1) {
            array = this.resize();
        }
        size++;
        int index = size;
        array[index] = element;
        
        moveUp();
    }
    
    /**
     * @return true if the heap is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    
    /**
     * @return the minimum element in the heap.
     */
    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return array[1];
    }

    
    /**
     * @return the element that was removed
     */
    public T remove() {
    	T result = peek();
    	array[1] = array[size];
    	array[size] = null;
    	size--;
    	moveDown();
    	return result;
    }
    
    
    /**
     * @return string representation of min heap
     */
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * heapify's after a remove operation
     */
    public void moveDown() {
        int index = 1;
        
        while (hasLeftChild(index)) {
            int smallerChild = leftIndex(index);
            if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
                smallerChild = rightIndex(index);
            } 
            
            if (array[index].compareTo(array[smallerChild]) > 0) {
                swap(index, smallerChild);
            } else {
                break;
            }
            
            index = smallerChild;
        }        
    }
    
    /**
     * heapify's after add operation
     */
    public void moveUp() {
        int index = this.size;
        
        while (hasParent(index)
                && (parent(index).compareTo(array[index]) > 0)) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }
    
    /**
     * check to see if there is a parent
     * @param i - index of current element
     * @return - true if parent element exists
     */
    public boolean hasParent(int i) {
        return i > 1;
    }
    
    /**
     * Index of Left Child
     * @param i - index of current element
     * @return index of left child
     */
    public int leftIndex(int i) {
        return i * 2;
    }
    
    /**
     * Index of Right Child
     * @param i - index of current element
     * @return index of right child
     */
    public int rightIndex(int i) {
        return i * 2 + 1;
    }
    
    /**
     * Does current element have left child
     * @param i - index of current element
     * @return true if left child exists
     */
    public boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }
    
    /**
     * Does current element have right child
     * @param i - index of current element
     * @return true if right child exists
     */
    public boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }
    
    /**
     * Parent element
     * @param i - index of current element
     * @return parent element
     */
    public T parent(int i) {
        return array[parentIndex(i)];
    }
    
    /**
     * index of parent element
     * @param i - index of current element
     * @return index of parent element
     */
    public int parentIndex(int i) {
        return i / 2;
    }
    
    /**
     * resizes array
     * @return new array of double the size
     */
    public T[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    
    /**
     * swap 2 elements in the array
     * @param index1 - index of first element to be swapped 
     * @param index2 - index of second element to be swapped
     */
    public void swap(int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;        
    }
}