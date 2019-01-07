def sort_and_count_inversions(array):
    """
    Recursively divide arrays on small parts.
    :rtype : merged sorted array and count of inversions
    :param array: original array
    """

    # check if we can divide array on two parts
    if len(array) != 1:
        q = len(array) / 2
        left_array_part, x = sort_and_count_inversions(array[:q])
        right_array_part, y = sort_and_count_inversions(array[q:])

        # merge two parts into original array
        array, z = merge_and_count_split_inversions(
            array, left_array_part, right_array_part)
        return array, x + y + z
    else:
        return array, 0

def merge_and_count_split_inversions(array, left_array_part, right_array_part):
    """
    Count inversions and merge small parts of array.
    :rtype : sorted array and count of inversions
    :param array: original array
    :param left_array_part: left part of array
    :param right_array_part: right part of array
    """

    i = 0
    j = 0
    count = 0
    # repeat n times
    # from each part take left element and set to array the smallest
    # if there is inversions add their quantity to count
    for k in range(len(left_array_part) + len(right_array_part)):
        if i == len(left_array_part):
            array[k] = right_array_part[j]
            j += 1
            count += len(left_array_part) - i
        elif j == len(right_array_part):
            array[k] = left_array_part[i]
            i += 1
        elif left_array_part[i] <= right_array_part[j]:
            array[k] = left_array_part[i]
            i += 1
        else:
            array[k] = right_array_part[j]
            j += 1
            count += len(left_array_part) - i

    return array, count

if __name__ == '__main__':
    a = [5, 2, 4, 7, 1, 3, 2, 6]
    c = sort_and_count_inversions(a)[1]
    print 'Sorted array:', a
    print 'Inversions:', c
