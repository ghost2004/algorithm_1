
public class RandomizedQueue<Item> implements Iterable<Item> {

    private final int initialSize = 10;
    private Object[] elementData;
    private int arraySize;
    private int objSize;
   
    // construct an empty randomized queue
    
    public RandomizedQueue() {
        this.arraySize = initialSize;
        this.objSize = 0;
        this.elementData = new Object [initialSize];
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return objSize == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return objSize;
    }
    
}
