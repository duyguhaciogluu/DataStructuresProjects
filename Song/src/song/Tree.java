
package song;

import java.util.ArrayList;

public class Tree {

    Node root;

    public Tree() {
        this.root = null;
    }

    int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node min(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    void preOrderInt(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderInt(node.left);
            preOrderInt(node.right);
        }
    }

    void preOrderString(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderString(node.left);
            preOrderString(node.right);
        }
    }

    Node insertInt(Node node, int key, int index) {

        if (node == null) {
            return (new Node(key));
        }

        if (key < node.key) {
            node.left = insertInt(node.left, key, index);
        } else if (key > node.key) {
            node.right = insertInt(node.right, key, index);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node insertString(Node node, String data, int index) {

        if (node == null) {
            return (new Node(data));
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertString(node.left, data, index);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertString(node.right, data, index);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void deleteKey(Node focus, int key) {
        root = deleteNode(root, key);
    }

    Node deleteNode(Node focus, int key) {

        if (focus == null) {
            return focus;
        }

        if (key < focus.key) {
            focus.left = deleteNode(focus.left, key);
        } else if (key > focus.key) {
            focus.right = deleteNode(focus.right, key);
        } else {

            if ((focus.left == null) || (focus.right == null)) {
                Node temp = null;
                if (null == focus.left) {
                    temp = focus.right;
                } else {
                    temp = focus.left;
                }

                if (temp == null) {
                    temp = focus;
                    focus = null;
                } else {
                    focus = temp;
                }
            } else {
                Node temp = min(focus.right);

                focus.key = temp.key;

                focus.right = deleteNode(focus.right, temp.key);
            }
        }

        if (focus == null) {
            return focus;
        }

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;

        int balance = getBalance(focus);

        if (balance > 1 && getBalance(focus.left) >= 0) {
            return rightRotate(focus);
        }

        if (balance > 1 && getBalance(focus.left) < 0) {
            focus.left = leftRotate(focus.left);
            return rightRotate(focus);
        }

        if (balance < -1 && getBalance(focus.right) <= 0) {
            return leftRotate(focus);
        }

        if (balance < -1 && getBalance(focus.right) > 0) {
            focus.right = rightRotate(focus.right);
            return leftRotate(focus);
        }

        return focus;
    }

    void deleteString(Node focus, String data) {
        root = deleteNodeString(root, data);
    }

    Node deleteNodeString(Node focus, String data) {
        if (focus == null) {
            return focus;
        }

        if (data.compareTo(focus.data) < 0) {
            focus.left = deleteNodeString(focus.left, data);
        } else if (data.compareTo(focus.data) > 0) {
            focus.right = deleteNodeString(focus.right, data);
        } else {

            if ((focus.left == null) || (focus.right == null)) {
                Node temp = null;
                if (temp == focus.left) {
                    temp = focus.right;
                } else {
                    temp = focus.left;
                }

                if (temp == null) {
                    temp = focus;
                    focus = null;
                } else {
                    focus = temp;
                }
            } else {

                Node temp = min(focus.right);

                focus.data = temp.data;

                focus.right = deleteNodeString(focus.right, temp.data);
            }
        }

        if (focus == null) {
            return focus;
        }

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;

        int balance = getBalance(focus);

        if (balance > 1 && getBalance(focus.left) >= 0) {
            return rightRotate(focus);
        }
        if (balance > 1 && getBalance(focus.left) < 0) {
            focus.left = leftRotate(focus.left);
            return rightRotate(focus);
        }

        if (balance < -1 && getBalance(focus.right) <= 0) {
            return leftRotate(focus);
        }

        if (balance < -1 && getBalance(focus.right) > 0) {
            focus.right = rightRotate(focus.right);
            return leftRotate(focus);
        }

        return focus;
    }

}

