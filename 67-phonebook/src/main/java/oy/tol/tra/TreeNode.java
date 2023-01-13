package oy.tol.tra;

public class TreeNode<K extends Comparable<K>, V> {

    private Pair<K,V> keyValue;
    private TreeNode<K,V> leftChild;
    private TreeNode<K,V> rightChild;
    private int thisHash;

    public static int addDepth = 0;
    public static int collisions = 0;

    public TreeNode(K key, V value) {
        keyValue = new Pair<K,V>(key, value);
        leftChild = null;
        rightChild = null;
        thisHash = key.hashCode();
    }

    public int insert(K key, V value, int toInsertHash) {
        int added = 0;
        int hashModifier = 1;
        if (toInsertHash < thisHash) {
            //new object belongs to left child
            if (leftChild == null) {
                //left child doesn't exist
                leftChild = new TreeNode<K,V>(key, value);
                added = 1;
                TreeNode.addDepth++;
            } else {
                //left child exists
                added = leftChild.insert(key, value, toInsertHash);
                TreeNode.addDepth++;
            }
        } else if (toInsertHash > thisHash) {
            //new object belongs to right child
            if (rightChild == null) {
                //right child doesn't exist
                rightChild = new TreeNode<K,V>(key, value);
                added = 1;
                TreeNode.addDepth++;
            } else {
                //right child exists
                added = rightChild.insert(key, value, toInsertHash);
                TreeNode.addDepth++;
            }
        } else {
            //collision, same hash
            if (keyValue.getKey().equals(key)) {
                //just the same key, new value
                keyValue.setvalue(value);
            } else {
                //real collision
                TreeNode.collisions++;
                added = this.insert(key, value, toInsertHash+hashModifier);
            }
        }
        
        return added;
    }

    public V find(K key, int toFindHash) {
        V result = null;
        int hashModifier = 1;
        if (toFindHash < thisHash) {
            //toFind should be in left child
            if (leftChild != null) {
                result = leftChild.find(key, toFindHash);
            }
        } else if (toFindHash > thisHash) {
            //toFind should be in right child
            if (rightChild != null) {
                result = rightChild.find(key, toFindHash);
            }
        } else {
            //hashes are equal
            if (keyValue.getKey().equals(key)) {
                //correct node found
                result = keyValue.getValue();
            } else {
                //collision, two different keys with same hash
                result = this.find(key, toFindHash+hashModifier);
            }
        }
        return result;
    }

    public void toSortedArray(Pair<K,V> [] array, int [] index) {
        //check left child first
        if (leftChild != null) {
            leftChild.toSortedArray(array, index);
        }
        //next this node:
        array[index[0]++] = new Pair<K,V>(keyValue.getKey(), keyValue.getValue());

        //last check right child
        if (rightChild != null) {
            rightChild.toSortedArray(array, index);
        }
    }
}
