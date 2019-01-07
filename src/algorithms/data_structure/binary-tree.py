class BinaryTree(object):
    """
    Binary Tree class.
    """
    root = None

    class Node(object):
        """
        Tree Node class.
        """
        value = None
        parent = None
        left = None
        right = None

        def __init__(self, value):
            self.value = value

    def insert(self, z):
        """
        Method to insert node with z value to tree.
        :param z: value of new node.
        """
        z = BinaryTree.Node(z)  # create new Node with z value
        y = None  # last successful node reference
        x = self.root
        while x is not None:
            y = x
            if z.value <= x.value:
                x = x.left
            else:
                x = x.right

        z.parent = y
        if y is None:
            self.root = z
        elif z.value <= y.value:
            y.left = z
        else:
            y.right = z

    def search(self, value, node):
        """
        Method to search node by value.
        :param value: search value.
        :param node: root node for subtree.
        :return: node object with searched value.
        """
        if node is None or value == node.value:
            return node
        if value <= node.value:
            return self.search(value, node.left)
        else:
            return self.search(value, node.right)

    def iterative_search(self, value, node=None):
        """
        Method to search node by value. Realization using
        iterations instead of recursion.
        :param value: search value.
        :param node: root node for subtree.
        :return: node object with searched value.
        """
        if node is None:
            node = self.root
        while node is not None and node.value != value:
            if value <= node.value:
                node = node.left
            else:
                node = node.right

        return node

    def min(self, node=None):
        """
        Method to get node with minimum value.
        :param node: root node for subtree.
        :return: node object with minimum value.
        """
        if node is None:
            node = self.root
        while node.left is not None:
            node = node.left

        return node

    def max(self, node=None):
        """
        Method to get node with maximum value.
        :param node: root node for subtree.
        :return: node object with maximum value.
        """
        if node is None:
            node = self.root
        while node.right is not None:
            node = node.right

        return node

    def successor(self, node):
        """
        Method gets next successor for specified node.
        :param node: specified node object.
        :return: node object of the successor.
        """
        if node.right is not None:
            return self.min(node.right)
        parent = node.parent
        while parent is not None and parent.right == node:
            node = parent
            parent = node.parent

        return parent

    def delete1(self, node):
        """
        Delete node child.
        :param node: node to delete.
        :return: node deleted from tree.
        """
        parent = node.parent
        if parent is not None:
            if parent.left == node:
                parent.left = None
            else:
                parent.right = None

        return node

    def delete2(self, node):
        """
        Delete node with one child.
        :param node: node to delete.
        :return: node deleted from tree.
        """
        if node.left is not None:
            child = node.left
        else:
            child = node.right

        parent = node.parent
        if parent is not None:
            if parent.right == node:
                parent.right = child
            else:
                parent.left = child
            child.parent = parent
        else:
            self.root = child

        return node

    def delete3(self, node):
        """
        Delete node with two children.
        :param node: node to delete.
        :return: successor of node which is deleted from tree.
        """
        successor = self.successor(node)
        if successor.right is not None:
            successor.right.parent = successor.parent
            successor.parent.left = successor.right
        elif node.right == successor:
            node.right = None

        node.value = successor.value
        return successor

    def delete(self, node):
        """
        Delete node from tree.
        :param node: node to delete.
        :return: deleted node.
        """
        if node.right is not None and node.left is not None:
            return self.delete3(node)
        elif node.left is not None or node.right is not None:
            return self.delete2(node)
        else:
            return self.delete1(node)

    def in_order_tree_walk(self, node):
        """
        Method prints tree's nodes value while in-order walk.
        :param node: root node for subtree.
        """
        if node is not None:
            self.in_order_tree_walk(node.left)
            print node.value
            self.in_order_tree_walk(node.right)

    def detail_output(self, node):
        """
        Method prints detail output of tree hierarchy.
        :param node: root node for subtree.
        """
        if node is not None:
            print 'node:', node.value
            if node.left is not None:
                print 'l:', node.left.value
            if node.right is not None:
                print 'r:', node.right.value
            if node.left is not None:
                self.detail_output(node.left)

            if node.right is not None:
                self.detail_output(node.right)


if __name__ == '__main__':
    tree = BinaryTree()
    tree.insert(5)
    tree.insert(4)
    tree.insert(2)
    tree.insert(1)
    tree.insert(6)
    tree.insert(3)
    tree.in_order_tree_walk(tree.root)
    tree.detail_output(tree.root)
    print tree.search(2, tree.root)
    print tree.successor(tree.search(1, tree.root)).value

    print 'Deleting'
    print tree.delete(tree.search(2, tree.root)).value
    tree.detail_output(tree.root)
