import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size; //РєРѕР»-РІРѕ СѓР·Р»РѕРІ РІ РїРѕРґРґРµСЂРµРІРµ СЃ РєРѕСЂРЅРµРј РІ СѓР·Р»Рµ Node
        private int depth;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.depth = 1;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else return node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("РќРµ РјРѕР¶РµС‚ Р±С‹С‚СЊ РєР»СЋС‡Р° СЃ Р·РЅР°С‡РµРЅРёРµРј nul");
        }
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("РќРµ РјРѕР¶РµС‚ Р±С‹С‚СЊ РєР»СЋС‡Р° СЃ Р·РЅР°С‡РµРЅРёРµРј nul");
        }
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.size = size(node.left) + size(node.right) + 1;
        reDepth(node);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        reDepth(node);
        return node;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("РќРµ РјРѕР¶РµС‚ Р±С‹С‚СЊ РєР»СЋС‡Р° СЃ Р·РЅР°С‡РµРЅРёРµРј nul");
        }
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node tmp = node;
            node = min(node.right);
            node.right = deleteMin(node.right);
            node.left = tmp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        reDepth(node);
        return node;
    }

    public int depth() {
        return depth(root);
    }

    private int depth(Node node) {
        if (node == null) {
            return 0;
        } else return node.depth;
    }

    private void reDepth(Node node) {
        node.depth = Math.max(depth(node.left), depth(node.right)) + 1;
    }

    public boolean isBalance(){
        return depth(root.left) == depth(root.right);
    }
}
