
package song;


public class Node {

    int key, height;
    String data;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }

    public Node(String data) {
        this.data = data;
        height=1;
    }
    
}

