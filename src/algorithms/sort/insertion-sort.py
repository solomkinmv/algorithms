def insertion_sort(array):
    # start from second element
    for i in range(1, len(array)):
        key = array[i]
        j = i - 1
        while j >= 0 and key < array[j]:
            # if left element bigger than key, swap them
            array[j], array[j + 1] = array[j + 1], array[j]
            j -= 1

    return array

if __name__ == '__main__':
    a = [3, 2, 4, 5, 1]
    print "Original array:", a
    print "Sorted array:", insertion_sort(a)
