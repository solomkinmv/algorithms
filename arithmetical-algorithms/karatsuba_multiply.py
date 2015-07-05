import math


def split_number_on_2_parts(number):
    length = len(number)
    if length == 1:
        a = '0'
        b = number
    else:
        delimiter = int(math.ceil(length / 2.))
        a = number[:delimiter]
        b = number[delimiter:]

    print 'number {0}\t:\t{1}  {2}'.format(number, a, b)
    return int(a), int(b)


def karatsuba_multiply(x, y):
    if x < 10 or y < 10:
        return x * y

    n = max([len(str(x)), len(str(y))])

    if n % 2 != 0 and n != 1:
        n += 1    

    x_str = line_up(x, n)
    y_str = line_up(y, n)

    a, b = split_number_on_2_parts(x_str)
    c, d = split_number_on_2_parts(y_str)

    ac = karatsuba_multiply(a, c)
    bd = karatsuba_multiply(b, d)
    coefficient = karatsuba_multiply(a + b, c + d) - ac - bd
    result = 10 ** n * ac + 10 ** (n / 2) * coefficient + bd
    print 'a = {0}\tb = {1}\tc = {2}\td = {3}'.format(a, b, c, d)
    print 'ac = {0}\tbd = {1}'.format(ac, bd)
    print 'k =', coefficient
    print 'n =', n
    print 'result =', result
    print '~~~~~~~~~~'

    return result


def line_up(number, n):
    number = str(number)
    length = len(number)
    result = ''
    for i in range(n - length):
        result += '0'

    return result + number

if __name__ == '__main__':
    x = int(raw_input('x = '))
    y = int(raw_input('y = '))

    print karatsuba_multiply(x, y)
