package algo;

public class StringCompressDecompress {
    public String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        // step 1: only consider non-single letters
        // abcdddd -> abcd4

        // [0, slow): processed and should be kept
        int slow = 0;
        // fast: currently being processed
        int fast = 0;
        // fastNext: first letter beyond a group of identical letters
        int fastNext = 0;
        // count the number of single letters
        int countOne = 0;
        while (fast < array.length) {
            // move until we find a different letter
            while (fastNext < array.length && array[fastNext] == array[fast]) {
                fastNext++;
            }
            // number of identical letters within this group
            int length = fastNext - fast;
            // write the letter in-place
            array[slow++] = array[fast++];

            if (length > 1) {
                int numDigits = writeCount(array, slow, length);
                fast = fastNext;
                slow += numDigits;
            }
            else {
                countOne++;
            }
        }
        // Step 2: resize
        // abcd -> a1b1c1d1 (increase size)
        // a...ab...bcdd -> a10b10cd2 -> a10b10c1d2 (decrease size)
        char[] result = new char[slow + countOne];
        // end of processed string
        fast = slow - 1;
        slow = result.length - 1;
        while (fast >= 0) {
            // count > 1
            if (Character.isDigit(array[fast])) {
                while (fast >= 0 && Character.isDigit(array[fast])) {
                    result[slow--] = array[fast--];
                }
            }
            // count = 1
            else {
                result[slow--] = '1';
            }
            result[slow--] = array[fast--];
        }
        return new String(result);
    }

    private int writeCount(char[] array, int idx, int runLength) {
        // count the number of digits (12 -> 2)
        int numDigits = 0;
        for (int i = runLength; i > 0; i /= 10) {
            numDigits++;
        }
        // start writing digits backward
        idx += numDigits - 1;
        for (int i = runLength; i > 0; i /= 10) {
            array[idx--] = (char) ('0' + i % 10);
        }
        return numDigits;
    }

    // assume there are no adjacent repeated characters with length > 9
    // count: 0-9
    public String decompress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return decodeLong(array, decodeShort(array));
    }

    // returns the length of the decoded string
    // only cares about "a0", "a1", "a2"
    private int decodeShort(char[] input) {
        int slow = 0;
        // assume there are 0-9 same adjacent letters
        for (int fast = 0; fast < input.length; fast += 2) {
            int digit = getDigit(input[fast + 1]);
            // only handle count = 0, 1, 2
            if (digit >= 0 && digit <= 2) {
                for (int j = 0; j < digit; ++j) {
                    input[slow++] = input[fast];
                }
            }
            // ignore other cases
            else {
                input[slow++] = input[fast];
                input[slow++] = input[fast + 1];
            }
        }
        // return decoded length after handling "shorter" cases
        return slow;
    }
    
    private String decodeLong(char[] input, int length) {
        // length is processed length after dealing with cases like "a0", "a1", "a2"
        int newLength = length;

        // calculate new length
        for (int i = 0; i < length; i++) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                newLength += digit - 2;
            }
        }
        char[] result = new char[newLength];
        int slow = newLength - 1;
        for (int fast = length - 1; fast >= 0; fast--) {
            int digit = getDigit(input[fast]);
            if (digit > 2 && digit <= 9) {
                fast--;
                for (int j = 0; j < digit; j++) {
                    result[slow--] = input[fast];
                }
            }
            else {
                result[slow--] = input[fast];
            }
        }
        return new String(result);
    }

    private int getDigit(char digit) {
        return digit - '0';
    }

    public static void main(String[] args) {
        StringCompressDecompress sol = new StringCompressDecompress();
        System.out.println(sol.compress("abbbacccccccccccd"));
        System.out.println(sol.decompress("a0b1c2d2e9f6"));
    }
}
