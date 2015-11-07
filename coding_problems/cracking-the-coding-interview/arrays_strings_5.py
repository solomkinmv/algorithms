"""
Compress string. If compressed string not shorter original then return original
"""

def compression1(s):
    s_list = list()
    for ch in s:
        try:
            if s_list[len(s_list) - 1][0] == ch:
                s_list[len(s_list) -1][1] += 1
            else:
                s_list.append([ch, 1])
        except Exception:
            s_list.append([ch, 1])
    for i in range(len(s_list)):
        s_list[i] = s_list[i][0] + str(s_list[i][1])
    res = ''.join(s_list)
    return res if len(res) < len(s) else s

def compression2(s):
    s_list = list()
    previous = None
    count = 0
    for ch in s:
        if previous == ch:
            count += 1
        else:
            if not previous is None:
                s_list.append(previous)
                s_list.append(str(count))
            previous = ch
            count = 1
    s_list.append(previous)
    s_list.append(str(count))
    return ''.join(s_list) if len(s_list) < len(s) else s


if __name__ == '__main__':
    s1 = "aaabbddeedddddbjj"
    s2 = "aabcddefg"
    print(compression1(s1))
    print(compression2(s1))
    print(compression1(s2))
    print(compression2(s2))
