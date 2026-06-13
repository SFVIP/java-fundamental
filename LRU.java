import java.util.HashMap;
import java.util.Map;

interface iLRU<K, V> {
    void update(K key, V value);

    V get(K key);
}

public class LRU<K, V> implements iLRU<K, V> {

    private int capacity;
    private int length;
    private Node<V> head;
    private Node<V> tail;

    private Map<K, Node<V>> lookup = new HashMap<>();
    private Map<Node<V>, K> reverseLookup = new HashMap<>();

    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>(3);
        assert lru.get("foo") == null; // [null, null, null]
        lru.update("foo", 69); // ["foo", null, null]
        assert lru.get("foo") == 69; // [69, null, null]

        lru.update("bar", 420); // ["bar", "foo", null]
        assert lru.get("bar") == 420; // [420, 69, null]

        lru.update("baz", 1337); // ["baz", "bar", "foo"]
        assert lru.get("baz") == 1337; // [1337, 420, 69]

        lru.update("ball", 69420); // ["ball", "baz", "bar"]
        assert lru.get("ball") == 69420; // [69420, 1337, 420]

        assert lru.get("foo") == null; // ["ball", "baz", "bar"]
        assert lru.get("bar") == 420; // [69420, 1337, 420]
        // -> ["bar", "ball", "baz"]
        // -> [420, 69420, 1337]
        lru.update("foo", 69); // ["foo", "bar", "ball"]
        // -> [69, 420, 69420]
        assert lru.get("bar") == 420; // ["bar", "foo", "ball"]
        // -> [420, 69, 69420]
        assert lru.get("foo") == 69; // ["foo", "bar", "ball"]
        // -> [69, 420, 69420]
        assert lru.get("baz") == null;

        System.out.println("All good");
    }

    @SuppressWarnings("unused")
    private LRU() {
    }

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void update(K key, V value) {
        // does it exist?

        Node<V> node = this.lookup.get(key);
        // if it doesn't we need to insert
        if (node == null) {
            // check capacity and evict it over
            node = this.createNode(value);
            this.length++;
            this.prepend(node);
            this.trimCache();

            this.lookup.put(key, node);
            this.reverseLookup.put(node, key);
        } else {
            // if it does, we need to update to the front of the list and update the value
            this.detach(node);
            node.value = value;
            this.prepend(node);
        }
    }

    @Override
    public V get(K key) {
        // check the cache for existence
        Node<V> node = this.lookup.get(key);
        if (node == null) {
            return null;
        }
        // update the value we found and move it to the front
        this.detach(node);
        this.prepend(node);
        // return out the value found or undefined if not exist
        return node.value;
    }

    private Node<V> createNode(V v) {
        return new Node<V>(v);
    }

    private void detach(Node<V> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (this.head == node) {
            this.head = this.head.next;
        }

        if (this.tail == node) {
            this.tail = this.tail.prev;
        }

        node.next = node.prev = null;
    }

    private void prepend(Node<V> node) {
        if (this.head == null) {
            this.head = this.tail = node;
            return;
        }

        node.next = this.head;
        this.head.prev = node;
        this.head = node;
    }

    private void trimCache() {
        if (this.length <= this.capacity) {
            return;
        }

        Node<V> tail = this.tail;
        this.detach(this.tail);

        K key = this.reverseLookup.get(tail);
        this.lookup.remove(key);
        this.reverseLookup.remove(tail);
        this.length--;
    }
}

class Node<V> {
    public V value;
    public Node<V> next;
    public Node<V> prev;

    public Node(V v) {
        this.value = v;
    }
}
