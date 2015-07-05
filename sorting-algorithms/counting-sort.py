def counting_sort(a, k):
    """
    Counting sort function.
    :param a: original array.
    :param k: count of numbers from 0 to max(a).
    :return: sorted array.
    """
    # create new array with size k and filled 0 values
    c = [0] * k
    b = list(a)

    # walk through the array and increment c[value] elements
    for i in range(len(a)):
        c[a[i]] += 1

    # walk through the array and add value from previous element
    for i in range(k - 1):
        c[i + 1] += c[i]

    # fill result array
    for i in range(len(a)):
        j = len(a) - i - 1  # reverse index
        b[c[a[j]] - 1] = a[j]  # subtract -1 because array index starts from 0
        c[a[j]] -= 1

    return b


if __name__ == '__main__':
    a = [2, 8, 7, 1, 3, 5, 6, 4]
    k = max(a) + 1  # count of numbers from 0 to max(a)
    b = counting_sort(a, k)
    print b
