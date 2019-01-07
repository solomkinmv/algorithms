def check_substr(s, sub):
    print s, sub
    length = len(sub)
    for i in range(len(s)):
        if s[i: i + length] == sub:
            return i

    return -1

if __name__ == '__main__':
    print check_substr('abcdefg', 'abc')
    print check_substr('abcdefg', 'abcdefg')
    print check_substr('abcdefg', 'fg')
    print check_substr('abcdefg', 'ffg')
    print check_substr('abcdefg', 'cdf')
    print check_substr('abcdefg', 'cdef')
    print check_substr('abcdefg', 'a')
    print check_substr('abcdefg', 'g')
