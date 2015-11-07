"""
Reverse string
"""

def reverse(s):
    s_list = list(s)
    for i in range(len(s) // 2):
        s_list[i], s_list[len(s) - 1 - i] = s_list[len(s) - 1 - i], s_list[i]
    return ''.join(s_list)


if __name__ == '__main__':
    s = 'hello world'
    print(reverse(s))
