import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

class DFS {
    /**
     * Tree traversal using dfs algorithm.
     */
    static void dfs(int[] tree) {
        Set<Integer> investigated = new HashSet<Integer>();
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int root = findNode(tree, investigated, 0); // get root node
        investigated.add(root);
        stack.push(root);
        System.out.println(root);
        while (stack.size() != 0) {
            int v = stack.peek();
            int u = findNode(tree, investigated, v);
            if (u == -1) {
                stack.pop();
                continue;
            }
            if (!investigated.contains(u)) {
                System.out.println(u);
                investigated.add(u);
                stack.push(u);
            }
        }
    }

    /**
     * Helper class to find new child node for specified parent.
     */
    static int findNode(int[] tree, Set<Integer> investigated, int parent) {
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == parent && !investigated.contains(i + 1)) {
                return i + 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        /* Array of integers represents tree.
         * Number of each node is its index in the array plus 1.
         * The value of each element in the array is the number of the parent node.
         */
        dfs(new int[]{0, 1, 1, 2, 2, 5, 5, 5, 3, 3});
    }
}
