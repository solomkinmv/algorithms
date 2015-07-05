def merge_sort(array, p, r):
    """
    Recursively divide arrays on small parts and then merge them.
    :param array: original array
    :param p: left index bound
    :param r: right index bound
    """

    # check if we can divide array on two parts
    if p < r:
        q = (p + r) / 2
        merge_sort(array, p, q)
        merge_sort(array, q + 1, r)

        # merge two parts into original array
        merge(array, p, q, r)

def merge(array, p, q, r):
    """
    merging two parts into one
    left part array[p:q+1]
    right part array[q+1:r]
    :param array: original array
    :param p: left index bound
    :param q: middle index bound
    :param r: right index bound
    """

    # creating two temporary arrays and fill them with data
    n1 = q - p + 1
    n2 = r - q
    left_array_part = []
    right_array_part = []
    for i in range(n1):
        left_array_part.append(array[p + i])
    
    for j in range(n2):
        right_array_part.append(array[q + j + 1])

    # repeat n (r - p + 1) times
    # from each part take left element and set to array the smallest
    i = 0
    j = 0
    for k in range(r - p + 1):
        if i == n1:
            array[k] = right_array_part[j]
            j += 1
        elif j == n2:
            array[k] = left_array_part[i]
            i += 1
        elif left_array_part[i] <= right_array_part[j]:
            array[k] = left_array_part[i]
            i += 1
        else:
            array[k] = right_array_part[j]
            j += 1

if __name__ == '__main__':
    a = [5, 2, 4, 7, 1, 3, 2, 6]
    merge_sort(a, 0, len(a) - 1)
    print a
