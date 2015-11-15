import random

def bubble_sort(array):
    for i in reversed(range(len(array))):
        for j in range(0, i):
            if array[j] > array[j + 1]:
                array[j], array[j + 1] = array[j + 1], array[j]

    return array

if __name__ == '__main__':
    l = list(range(10))
    random.shuffle(l)
    print(bubble_sort(l))
