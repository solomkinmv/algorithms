import random


def randomized_partition(array, p, r):
    """
    Function to set x = array[r] element on its place and rearrange
    less than x numbers to the left relatively x and bigger than x
    numbers to the right relatively x.
    :param array: original array.
    :param p: left index bound.
    :param r: right index bound.
    :return: return x index.
    """
    # get random root element
    i = random.randint(p, r)
    x = array[i]
    # swap x and the most right element
    array[r], array[i] = array[i], array[r]
    i = p - 1
    for j in range(p, r):
        if array[j] <= x:
            i += 1
            array[j], array[i] = array[i], array[j]
    array[i + 1], array[r] = x, array[i + 1]

    return i + 1


def randomized_quick_sort(array, p, r):
    """
    Function to sort array[p:r] subarray.
    :param array: original array.
    :param p: left index bound.
    :param r: right index bound.
    """
    if p < r:
        q = randomized_partition(array, p, r)
        randomized_quick_sort(array, p, q - 1)
        randomized_quick_sort(array, q + 1, r)


if __name__ == '__main__':
    a = [2, 8, 7, 1, 3, 5, 6, 4]
    randomized_quick_sort(a, 0, len(a) - 1)
    print a
