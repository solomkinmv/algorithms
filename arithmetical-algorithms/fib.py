def fib_rec(n):
    """
    Returns nth fibonacci number.
    Realization with recursion.
    """
    if n == 1:
        return 0
    elif n == 2:
        return 1

    return fib_rec(n - 1) + fib_rec(n - 2)

def fib(n):
    a = 0
    b = 1
    if n == 1:
        return a
    elif n == 2:
        return b
    i = 2
    while i < n:
        a, b = b, a + b
        i += 1

    return b


if __name__ == '__main__':
    print fib(5)
    print fib_rec(5)
