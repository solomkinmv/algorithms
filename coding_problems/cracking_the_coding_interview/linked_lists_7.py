class LinkedList(object):
    def __init__(self, value):
        self.value = value
        self.next = None

    def append(self, value):
        node = LinkedList(value)
        runner = self
        while runner.next != None:
            runner = runner.next
        runner.next = node

    def show(self):
        runner = self
        while runner:
            print(runner.value)
            runner = runner.next


def rpal_check(node, length):
    if length < 2:
        return node.next
    returned = rpal_check(node.next, length - 2)
    if type(returned) is bool:
        return False
    print("{} vs {}".format(node.value, returned.value))
    if node.value == returned.value:
        if returned.next is None:
            return True
        return returned.next
    return False


if __name__ == '__main__':
    node = LinkedList(1)
    node.append(2)
    node.append(3)
    node.append(2)
    node.append(1)

    node.show()
    print(rpal_check(node, 5))
