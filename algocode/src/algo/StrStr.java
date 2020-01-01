package algo;

public class StrStr {
    /**
     * Find first occurrence of the small string in the large string
     * @param small
     * @param large
     * @return
     */
    public int strstrI(String small, String large) {
        if (small.isEmpty()) return 0;
        if (small.length() > large.length()) return -1;

        for (int i = 0; i <= large.length() - small.length(); ++i) {
            // if first letter of small string matches large.charAt(i)
            if (small.charAt(0) == large.charAt(i)) {
                if (checkMatch(i, small, large)) {
                    return i;
                }
            }
        }
        return -1;
    }
    private boolean checkMatch(int start, String small, String large) {
        for (int i = 0; i < small.length(); ++i) {
            if (large.charAt(start + i) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String large = "bcabc";
        String small = "abc";
        StrStr sol = new StrStr();
        System.out.println(sol.strstrI(small, large));
    }
}
