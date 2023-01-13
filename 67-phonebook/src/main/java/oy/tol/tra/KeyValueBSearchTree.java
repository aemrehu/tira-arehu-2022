package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table implementation

    private TreeNode<K,V> root;
    private int count;

    //Statistics
    int maxDepth;
    int maxCollisions;

    public KeyValueBSearchTree() {
        root = null;
        count = 0;
        maxDepth = 0;
        maxCollisions = 0;
    }

    @Override
    public Type getType() {
       return Type.BST;
    }
 
    @Override
    public int size() {
        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter value here. 
     * You will then see if you have too many collisions. It will tell you that your hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        String toReturn = "BST statistics:";
        toReturn += "\n Count          : " + count;
        toReturn += "\n Max depth      : " + maxDepth;
        toReturn += "\n Max collisions : " + maxCollisions;

        return toReturn;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        } else {
            if (root == null) {
                root = new TreeNode<K,V>(key, value);
                count++;
                maxDepth = 1;
                maxCollisions = 0;
                return true;
            } else {
                TreeNode.addDepth = 1;
                TreeNode.collisions = 0;
                int added = root.insert(key, value, key.hashCode());
                if (added > 0) {
                    count++;
                    if (TreeNode.addDepth > maxDepth) {
                        maxDepth = TreeNode.addDepth;
                    }
                    if (TreeNode.collisions > maxCollisions) {
                        maxCollisions = TreeNode.collisions;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException();
        } else {
            if (root == null) {
                return null;
            } else {
                return root.find(key, key.hashCode());
            }
        }
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // not needed
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K,V> [] toSortedArray() {
        if (root == null) {
            return null;
        }
        Pair<K,V> [] returnArray = (Pair<K,V>[])new Pair[count];
        int [] toAddIndex = {0};
        root.toSortedArray(returnArray, toAddIndex);
        Algorithms.fastSort(returnArray);
        return returnArray;
    }
    
    @Override
    public void compress() throws OutOfMemoryError {
        // not needed
    }

}
