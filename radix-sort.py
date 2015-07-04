def radix_sort(array, number_of_digits):
    """
    Main function of radix sort. Function sorts numbers by digits from
    smaller to bigger.

    :param array: original array.
    :param number_of_digits: maximum number of digits in one number.
    :return: sorted array.
    """
    for d in range(number_of_digits):
        number_of_valid_values = 10  # digit values from 0 to 9
        array = counting_sort(
            array, number_of_valid_values, d, number_of_digits)

    return array


def counting_sort(a, number_of_valid_values, d, number_of_digits):
    """
    Counting sort function with some changes to sort by digits.

    :param a: original array.
    :param number_of_valid_values: count of numbers from 0 to max(a).
    :param d: index of digit by which to sort function.
    :param number_of_digits: maximum number of digits in one number.
    :return: sorted array by specified digit.
    """
    # create new array with size k and filled 0 values
    c = [0] * number_of_valid_values
    # create copy of `a` array for output sorted array (we need just array
    # with the same size)
    b = list(a)

    # create array of digits with d index from right side
    digit_array = []
    for number in a:
        str_number = str(number).zfill(number_of_digits)
        i = len(str_number) - d - 1  # index from right side
        digit_array.append(int(str_number[i]))

    # walk through the array and increment c[value] elements
    for i in range(len(digit_array)):
        c[digit_array[i]] += 1

    # walk through the array and add value from previous element
    for i in range(number_of_valid_values - 1):
        c[i + 1] += c[i]

    # fill result array
    for i in range(len(a)):
        j = len(a) - i - 1  # reverse index
        number = str(a[j]).zfill(number_of_digits)
        digit = int(number[len(number) - 1 - d])
        b[c[digit] - 1] = a[j]  # subtract -1 because array index starts from 0
        c[digit] -= 1

    return b


if __name__ == '__main__':
    a = [123, 435, 87, 5, 23, 1234, 874, 345, 485]
    d = len(str(max(a)))
    b = radix_sort(a, d)
    print b
