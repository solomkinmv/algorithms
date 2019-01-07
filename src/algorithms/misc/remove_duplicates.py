"""
Given a sorted array, remove the duplicates in place such that 
each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this 
in place with constant memory.

For example, given input array nums = [1, 1, 2].
Your function should return length = 2, with the first two elements 
of nums being 1 and 2 respectively. It doesn't matter what you leave 
beyond the new length.

"""
def remove_duplicates(l):
    """
    :param l: unsorted list.
    :type l: list.
    :return: sorted list.
    """
    i = 1
    while True:
        # break loop if index reached the end of the list
        if i >= len(l):
            break

        # if element is duplicated remove it
        if l[i - 1] == l[i]:
            del l[i - 1]
            i -= 1
        i += 1

    return len(l)


if __name__  == '__main__':
    l = [1, 1, 1, 2, 3, 4, 2, 5, 6, 8, 5, 7, 3, 5, 8, 9, 0, 3, 5, 4, 6, 7]
    l.sort()
    print l
    print remove_duplicates(l)
    print l

    l = [1, 1, 2]
    l.sort()
    print l
    print remove_duplicates(l)
    print l

