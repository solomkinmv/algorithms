class Reverse {
    static String reverse(String s) {
        char[] chArray = s.toCharArray();
        StringBuffer res = new StringBuffer();
        for (char ch: chArray) {
            res.insert(0, ch);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("abcdefg"));
    }
}
