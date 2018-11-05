"""
Check unique chars in string.
"""

def check_unique1(s):
    """
    Check with additional memory
    """
    chars = list()
    for ch in s:
        if ch in chars:
            return False
        chars.append(ch)
    return True

def check_unique2(s):
    """
    Check without additional memory
    """
    for i in range(len(s)):
        for j in range(i + 1, len(s)):
            if s[i] == s[j]:
                return False
    return True


if __name__ == '__main__':
    unique_string = "abcdefg"
    string1 = "abcdbd"
    string2 = "aabdfgh"
    print(check_unique1(unique_string))
    print(check_unique2(unique_string))
    print(check_unique1(string1))
    print(check_unique1(string2))
    print(check_unique2(string1))
    print(check_unique2(string2))
