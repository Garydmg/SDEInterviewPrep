package algo;

public class RemoveSpaces {
    /**
     * Remove all leading, trailing, and duplicated empty spaces
     * " a b   c" -> "a b c"
     * @param input
     * @return
     */
    String removeAllSpaces(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        char array[] = input.toCharArray();
        /**
         *  [0, slow): processed + should be kept
         *  [0, fast): processed
         */
        int slow = 0;
        for (int fast = 0; fast < array.length; ++fast) {
            // always copy when array[fast] is not empty
            if (array[fast] != ' ') {
                array[slow++] = array[fast];
            }
            // array[fast] is empty
            else {
                // because we don't want leading spaces
                if (slow == 0) {
                    continue;
                }
                // if previous position contains a character, need to copy a space
                // case when A _ _ B -> A _ B
                if (array[slow - 1] != ' ') {
                    array[slow++] = array[fast];
                }
            }
        }
        // post-processing: happens when there are trailing spaces
        // would only copy one trailing space
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }

    public static void main(String[] args) {
        RemoveSpaces sol = new RemoveSpaces();

        String input = " a   b c   ";
        System.out.println(sol.removeAllSpaces(input));
    }
}
