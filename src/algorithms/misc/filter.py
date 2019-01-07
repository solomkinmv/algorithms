def filter(lst, cond):
    j = 0
    for i in range(len(lst)):
        if cond(lst[i]):
            lst[i], lst[j] = lst[j], lst[i]
            j += 1

    return lst[:j]

if __name__ == '__main__':
    lst = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
    print filter(lst, lambda x: True if (x % 3) != 0 else False)

