package coding_problems.cracking_the_coding_interview;

import java.util.HashSet;

/**
 * Simple Linked List with append method
 */
class Node {
    int value;
    Node next;

    public Node(int v) {
        value = v;
    }

    public static void main(String[] args) {
        Node n = new Node(1);
        n.append(2);
        n.append(3);
        n.append(4);
        n.append(5);
        n.append(6);
        n.append(5);
        n.append(7);
        n.append(8);
        n.append(9);

        n.removeDuplicates2();

        Node tmp = n;
        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    public void append(int v) {
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = new Node(v);
    }

    void removeDuplicates1() {
        Node n = this;
        HashSet<Integer> hs = new HashSet<Integer>();
        hs.add(n.value);
        while (n.next != null) {
            if (hs.contains(n.next.value)) {
                n.next = n.next.next;
                continue;
            }
            hs.add(n.next.value);
            n = n.next;
        }
    }

    void removeDuplicates2() {
        Node n = this;
        while (n != null) {
            Node runner = n;
            while (runner.next != null) {
                if (runner.next.value == n.value) {
                    runner.next = runner.next.next;
                    continue;
                }
                runner = runner.next;
            }
            n = n.next;
        }
    }
}
