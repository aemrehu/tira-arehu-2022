package oy.tol.tra;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private static int DEFAULT_CAPACITY = 20;
    private static double LOAD_FACTOR = 0.65;
    private Pair<K,V> [] itemArray;
    private int count;
    private int capacity;

    //Statistics
    int collisionCount;
    int maxProbingCount;
    int reallocationCount;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        if (count == 0 )  {
            itemArray = new Pair[size];
            capacity = size;
        } else {
            reallocate(size);
        }
    }

    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        String toReturn = "";
        toReturn += "Capacity      : " + capacity;
        toReturn += "\nCount         : " + count;
        toReturn += "\nFilled        : " + (double)((double)count/(double)capacity)*100.0 + "%";
        toReturn += "\nCollisions    : " + collisionCount;
        toReturn += "\nMax Probing   : " + maxProbingCount;
        toReturn += "\nReallocations : " + reallocationCount;
        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }
        int index = 0;
        int hashModifier = 0;
        int currentProbingCount = 0;
        boolean added = false;

        if (count > capacity*LOAD_FACTOR) {
            reallocate((int)(capacity * (1.0 / LOAD_FACTOR)));
        }

        do {
            index = indexFor(key, hashModifier);
            if (itemArray[index] == null) {
                //add new pair
                itemArray[index] = new Pair<K,V>(key, value);
                count++;
                added = true;
            } else if (!itemArray[index].getKey().equals(key)) {
                //collision
                hashModifier++;
                collisionCount++;
                currentProbingCount++;
            } else {
                //shouldn't get here!!!
                added = true;
            }
        } while (!added);

        if (maxProbingCount < currentProbingCount)
            maxProbingCount = currentProbingCount;

        if (added)
            return true;
        else
            return false;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        boolean finished = false;
        V result = null;
        int hashModifier = 0;
        int index;
        do {
            index = indexFor(key, hashModifier);
            if (itemArray[index] != null) {
                if (itemArray[index].getKey().equals(key)) {
                    //item found
                    result = itemArray[index].getValue();
                    finished = true;
                } else {
                    //collision
                    hashModifier++;
                }
            } else {
                //key not found -> return null
                finished = true;
            }
        } while (!finished);

        return result;
    }

    @Override
    @java.lang.SuppressWarnings({"unchecked"})
    public Pair<K,V> [] toSortedArray() {
        Pair<K,V> [] toReturn = new Pair[count];
        int addIndex = 0;
        for (int i=0; i<capacity; i++) {
            if (itemArray[i] != null) {
                toReturn[addIndex++] = itemArray[i];
            }
        }
        Algorithms.fastSort(toReturn);
        return toReturn;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int newCapacity = (int)(count * (1.0 / LOAD_FACTOR));
        if (newCapacity < capacity) {
            reallocate(newCapacity);
        }
    }

    private int indexFor(K key, int hashModifier) {
        return ((key.hashCode() + hashModifier) & 0x7fffffff) % capacity;
    }

    @java.lang.SuppressWarnings({"unchecked"})
    private void reallocate(int newCapacity) {
        Pair<K,V> [] oldArray = itemArray;
        itemArray = new Pair[newCapacity];
        int oldCapacity = capacity;
        capacity = newCapacity;
        count=0;
        collisionCount=0;
        maxProbingCount=0;

        for (int i=0; i<oldCapacity; i++) {
            if (oldArray[i] != null) {
                add(oldArray[i].getKey(), oldArray[i].getValue());
            }
        }
        reallocationCount++;
    }

}
