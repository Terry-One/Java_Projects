import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Lab 5: Java Collection Framework and Skip List <br />
 * The {@code SkipList} class
 * @param <K>           {@code K} key of each skip list node
 * @param <V>           {@code V} value of each skip list node
 */
public class SkipList<K extends Comparable<K>, V> {

    /**
     * The {@code Node} class for {@code SkipList}
     */
    private class Node {
        public K key;
        public V value;
        public ArrayList<Node> forwards = new ArrayList<Node>();
        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            for (int i = 0; i < level; i++)
                forwards.add(null);
        }
        public String toString() {
            return String.format("%s(%s,%d)", value, key, forwards.size());
        }
    }

    private Node basenode = new Node(null, null, 1);

    /**
     * Level of the skip list. An empty skip list has a level of 1
     */
    private int level = 1;

    /**
     * Size of the skip list
     */
    private int size = 0;

    private final Random rand = new Random();

    /**
     * Get a random level
     * @return {@code int} random integer
     */
    private int random_Level() {
        int level = 0;
        level = rand.nextInt(10) + 1;
        return level;
    }

    /**
     * Insert an new element into the skip list
     * @param key       {@code K} key of the new element
     * @param value     {@code V} value of the new element
     */
    public void insert(K key, V value) {
        // TODO: Lab 5 Part 1-1 -- skip list insertion

        Node current_node = basenode;
        ArrayList<Node> table = new ArrayList<Node>(Collections.nCopies(level, null));

        // iterate for each level of the skip list, check for the key
        for (int i = level - 1; i >= 0; i --) {

            Node next_node = current_node.forwards.get(i);

            while (next_node != null && key.compareTo(next_node.key) > 0) {
                current_node = next_node;
                next_node = next_node.forwards.get(i);
            }
            // set the table for each level
            table.set(i, current_node);
        }
        current_node = current_node.forwards.get(0);

        // if the found the key then updatae the value, else then insert a new one
        if (current_node != null && current_node.key.compareTo(key) == 0) {
            current_node.value = value;
        }
        else
        {
            int new_level = random_Level();
            Node new_node = new Node(key, value, new_level);


            // build new node in the table
            for (int i = 0; i < Integer.min(level, new_level); i++) 
            {
                new_node.forwards.set(i, table.get(i).forwards.get(i));
                table.get(i).forwards.set(i, new_node);
            }

            if (new_level > level) {
                for (int i = level; i < new_level; ++i) {
                    basenode.forwards.add(new_node);
                }
                level = new_level;
            }
            size+= 1;
        }
    }

    /**
     * Remove an element by the key
     * @param key       {@code K} key of the element
     * @return          {@code V} value of the removed element
     */
    public V remove(K key) {
        // TODO: Lab 5 Part 1-2 -- skip list deletion

        Node current_node = basenode;
        ArrayList<Node> table = new ArrayList<Node>(Collections.nCopies(level, null));
        // iterate the entire list to find the node with this key 
        for (int i = level - 1; i >= 0; i --) {

            Node next_node = current_node.forwards.get(i);

            while (next_node != null && key.compareTo(next_node.key) > 0) {

                current_node = next_node;
                next_node = next_node.forwards.get(i);
            }
            table.set(i, current_node);
        }
        current_node = current_node.forwards.get(0);

        // if not found return null, else then remove the found key 
        if (current_node == null || current_node.key.compareTo(key) != 0){

            return null;
        }
        else
        {
            for (int i = 0; i < level; i ++) {

                Node table_get = table.get(i);
                Node next = table_get.forwards.get(i);

                if (next != null && next.key.compareTo(key) == 0){
                    table_get.forwards.set(i, current_node.forwards.get(i));
                }
            }
            // delete the node if the basenode has nothing in front
            for (int i = level - 1; i >= 1; i --) {
                if (basenode.forwards.get(i) == null) {
                    basenode.forwards.remove(i);
                    level -= 1;
                }
            }
            size -= 1;
            return current_node.value;
        }

    }

    public Node find_node(K key){
        Node current_node = basenode;
        // iterate for every level of the list to find the key
        for (int i = level - 1; i >= 0; i --) {
            Node next_node = current_node.forwards.get(i);

            // find the node in each level
            while (next_node != null && key.compareTo(next_node.key) > 0) {
                current_node = next_node;
                next_node = next_node.forwards.get(i);
            }
        }
        current_node = current_node.forwards.get(0);
        return current_node;
    }


    /**
     * Search for an element by the key
     * @param key       {@code K} key of the element
     * @return          {@code V} value of the target element
     */
    public V search(K key) {
        // TODO: Lab 5 Part 1-3 -- skip list node search
        Node current_node = basenode;
        //search the node first
        current_node = find_node(key);
        // if found value just return it, else them return null
        if (current_node != null && current_node.key.compareTo(key) == 0)
            return current_node.value;
        else
            return null;
    }

    /**
     * Get the level of the skip list
     * @return          {@code int} level of the skip list
     */
    public int level() {
        return level;
    }

    /**
     * Get the size of the skip list
     * @return          {@code int} size of the skip list
     */
    public int size() {
        return size;
    }

    /**
     * Print the skip list
     * @return          {@code String} the string format of the skip list
     */
    public String toString() {
        
        String result = "SkipList is shown below with size: "+ size +" and level: "+ level + "\n";
        for (int i = level - 1; i >= 0; i --) {
            Node current_node = basenode.forwards.get(i);
            result += ">> ";
            while (current_node != null) {
                result += String.valueOf(current_node.key) + " ===> ";
                current_node = current_node.forwards.get(i);
            }
            result += "null\n";
        }
        return result;
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        SkipList<Integer, String> list = new SkipList<Integer, String>();
        int[] keys = new int[10];
        for (int i = 0; i < 10; i++) {                          // Insert elements
            keys[i] = (int) (Math.random() * 200);
            list.insert(keys[i], "\"" + keys[i] + "\"");
        }

        System.out.println(list);

        for (int i = 0; i < 10; i += 3) {
            int key = keys[i];
            // Search elements
            System.out.println(String.format("Find element             %3d: value=%s", key, list.search(key)));
            // Remove some elements
            System.out.println(String.format("Remove element           %3d: value=%s", key, list.remove(key)));
            // Search the removed elements
            System.out.println(String.format("Find the removed element %3d: value=%s", key, list.search(key)));
        }
        System.out.println(list);
    }
}
