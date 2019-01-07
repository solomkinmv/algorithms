def partition(array, p, r):
    """
    Function to set x = array[r] element on its place and rearrange
    less than x numbers to the left relatively x and bigger than x
    numbers to the right relatively x.
    :param array: original array.
    :param p: left index bound.
    :param r: right index bound.
    :return: return x index.
    """
    x = array[r]
    i = p
    for j in range(p, r):
        if array[j] <= x:
            array[j], array[i] = array[i], array[j]
            i += 1
    array[i], array[r] = x, array[i]

    return i


def quick_sort(array, p, r):
    """
    Function to sort array[p:r] subarray.
    :param array: original array.
    :param p: left index bound.
    :param r: right index bound.
    """
    if p < r:
        q = partition(array, p, r)
        quick_sort(array, p, q - 1)
        quick_sort(array, q + 1, r)


if __name__ == '__main__':
    a = [2, 8, 7, 1, 3, 5, 6, 4]
    quick_sort(a, 0, len(a) - 1)
    print a
