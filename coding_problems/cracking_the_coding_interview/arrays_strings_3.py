"""
Check for string permutation
"""

def check_permutation(s1, s2):
    chars = dict()
    if len(s1) != len(s2):
        return False
    for ch in s1:
        if ch in chars:
            chars[ch] += 1
        else:
            chars[ch] = 1
    for ch in s2:
        if ch not in chars:
            return False
        chars[ch] -= 1
        if chars[ch] < 0:
            return False
    return True


if __name__ == '__main__':
    s1 = 'abcd'
    s2 = 'bcda'
    s3 = 'dcba'
    s4 = 'aedc'
    print(check_permutation(s1, s2))
    print(check_permutation(s1, s1))
    print(check_permutation(s1, s3))
    print(check_permutation(s1, s4))
    print(check_permutation(s2, s3))
    print(check_permutation(s2, s4))
