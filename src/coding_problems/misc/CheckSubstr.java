package coding_problems.misc;

class CheckSubstr {
    static int checkSubstr(String s, String sub) {
        for (int i = 0; i < s.length() - sub.length() + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < sub.length(); j++) {
                if (s.charAt(i + j) != sub.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(checkSubstr("abcdefg", "bc"));
        System.out.println(checkSubstr("abcdefg", "ac"));
        System.out.println(checkSubstr("abcdefg", "a"));
        System.out.println(checkSubstr("abcdefg", "fg"));
        System.out.println(checkSubstr("abcdefg", "abcdefg"));
    }
}
