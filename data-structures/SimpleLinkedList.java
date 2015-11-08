/**
 * Simple Linked List with append method
 */
class Node {
    int value;
    Node next;

    public Node(int v) {
        value = v;
    }

    public void append(int v) {
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = new Node(v);
    }
}
