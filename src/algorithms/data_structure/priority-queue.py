class PriorityQueue(object):
    """
    Priority Queue based on Max Heap.
    """

    def __init__(self, array):
        """
        Initialization.

        :param array: heap structure.
        :type array: list.
        """
        self.array = array
        self.last = len(self.array) - 1
        self.build_max_heap()
    
    @staticmethod
    def parent(x):
        """
        Get parent id of element.

        :param x: id of element.
        :return: id of parent element.
        """
        return 0 if x == 0 else (x + 1) / 2 - 1

    @staticmethod
    def left(x):
        """
        Get id of left child element.

        :param x: id of element.
        :return: id of left child element.
        """
        return (x + 1) * 2 - 1

    @staticmethod
    def right(x):
        """
        Get id of right child element.

        :param x: value of element.
        :return: id of right child element.
        """
        return (x + 1) * 2

    def get_heap_size(self):
        """
        Get size of heap.

        :return: size of heap.
        """
        return self.last

    def max_heapify(self, x):
        """
        Support heap properties.

        :param x: id of top heap element.
        """
        l = self.left(x)
        r = self.right(x)
        if l <= self.get_heap_size() and self.array[l] > self.array[x]:
            largest = l
        else:
            largest = x

        if r <= self.get_heap_size() and self.array[r] > self.array[largest]:
            largest = r

        if largest != x:
            self.array[x], self.array[largest] = \
                self.array[largest], self.array[x]

            self.max_heapify(largest)

    def build_max_heap(self):
        """
        Set elements in array to build max heap.
        """
        i = len(self.array) / 2 - 1
        while i >= 0:
            self.max_heapify(i)
            i -= 1

    def heap_sort(self):
        """
        Sort array using heap.

        :return: sorted array.
        """
        i = self.get_heap_size()
        while i >= 1:
            self.array[0], self.array[i] = self.array[i], self.array[0]
            self.last -= 1
            self.max_heapify(0)
            i -= 1

        self.last = len(self.array) - 1
        return self.array

    def heap_maximum(self):
        """
        Get maximum heap value
        :return: maximum element.
        """
        return self.array[0]

    def heap_extract_max(self):
        """
        Extract maximum heap element.

        :return: maximum element.
        """
        max = self.array[0]
        self.array[0] = self.array[-1]
        del self.array[-1]
        self.last -= 1
        self.max_heapify(0)

        return max

    def heap_update_element(self, x):
        """
        Update specified element.

        :param x: id of element to update.
        """
        while x >= 0 and self.array[self.parent(x)] < self.array[x]:
            self.array[x], self.array[self.parent(x)] = \
                self.array[self.parent(x)], self.array[x]
            x = self.parent(x)

    def heap_change_key(self, x, key):
        """
        Increase key of specified element.

        :param x: id of element to increase value.
        :param key: new value of element.
        """
        self.array[x] = key
        self.heap_update_element(x)

    def heap_insert(self, key):
        """
        Insert new element.

        :param key: value of new element.
        """
        self.array.append(key)
        self.last += 1
        self.heap_update_element(self.last)

    def show(self):
        """
        Print heap.
        """
        level = 1
        k = 0
        for x in self.array:
            print x, '\t',
            k += 1
            if k == level:
                print
                k = 0
                level *= 2

        print

if __name__ == '__main__':
    a = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]
    heap = Heap(a)
    heap.show()
    heap.heap_change_key(4, 20)
    heap.show()
    heap.heap_insert(5)
    heap.heap_insert(6)
    heap.heap_insert(11)
    heap.heap_insert(7)
    heap.show()
