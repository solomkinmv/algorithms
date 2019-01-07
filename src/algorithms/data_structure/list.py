class Node:
	def __init__(self, value):
		self.value = value
		self.next = None

class List:
	def __init__(self):
		self.head = None

	def add(self, value):
		if self.head is None:
			self.head = Node(value)
			return

		# find last not empty element
		node = self.head
		while node.next is not None:
			node = node.next

		node.next = Node(value)

	def delete(self, index):
		node = self.head

		# edge case, index = 0
		# if index == 0 then remove head
		# if head is not None then set head as head.next else head = None
		if index == 0:
			if self.head is not None:
				self.head = self.head.next
			else:
				self.head = None

		i = 1
		while node.next is not None and i < index:
			node = node.next
			i += 1

		node.next = node.next.next

		# list: 1 -> 2 -> 3 -> 4

	def print(self):
		node = self.head
		print("List: ", end="")
		while node is not None:
			print(str(node.value) + " -> ", end="")
			node = node.next

		print()


if __name__ == '__main__':
	lst = List()

	lst.print()
	lst.add(2)
	lst.add(1)
	lst.add(3)
	lst.add(4)
	lst.print()

	lst.delete(2)
	lst.print()
