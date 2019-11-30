import java.util.HashSet;
import java.util.Set;

/**
 * @author fanqie
 * @date 2019/11/30
 */
public class ArrayProblem {

    /**
     * 167. Two Sum II - Input array is sorted
     *
     * Given an array of integers that is already sorted in ascending order,
     * find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target,
     * where index1 must be less than index2.
     *
     * Note:
     *
     * Your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution
     * and you may not use the same element twice.
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            final int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left, right};
            } else if (sum > target) {
                --right;
            } else {
                ++left;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 125. Valid Palindrome
     *
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (chars[i] >= 'a' && chars[i] <= 'z' ||
                    chars[i] >= '0' && chars[i] <= '9') {
                sb.append(chars[i]);
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 32;
                sb.append(chars[i]);
            }
        }
        chars = sb.toString().toCharArray();
        final int leftBorder = chars.length / 2;
        for (int i = 0; i < leftBorder; ++i) {
            final int otherPos = chars.length - 1 - i;
            if (chars[i] != chars[otherPos]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 344. Reverse String
     *
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array
     * in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     */
    public void reverseString(char[] s) {
        final int leftBoundary = s.length / 2;
        for (int i = 0; i < leftBoundary; i++) {
            final int otherPos = s.length - 1 - i;
            char tmp = s[i];
            s[i] = s[otherPos];
            s[otherPos] = tmp;
        }
    }

    /**
     * 345. Reverse Vowels of a String
     *
     * Write a function that takes a string as input and reverse only the vowels of a string.
     */
    public String reverseVowels(String s) {
        final Set<Character> target = new HashSet<Character>() {{
            add('a'); add('e'); add('i'); add('o'); add('u');
            add('A'); add('E'); add('I'); add('O'); add('U');
        }};

        final char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            while (left < right && !target.contains(chars[left])) {
                ++left;
            }
            while (left < right && !target.contains(chars[right])) {
                --right;
            }
            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;
                ++left;
                --right;
            } else {
                break;
            }
        }
        return String.copyValueOf(chars);
    }

    /**
     * 11. Container With Most Water
     *
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
     * which together with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     */
    public int maxArea(int[] height) {
//        int left = 0, right = height.length - 1;
//        int target = Math.min(height[left], height[right]);
//        int res = target * (right - left);
//        while (left < right) {
//            int nextLeft = left;
//            while (nextLeft < right && target >= height[nextLeft]) {
//                ++nextLeft;
//            }
//            int nextRight = right;
//            while (nextRight > nextLeft && height[nextRight] <= target) {
//                --nextRight;
//            }
//            if (nextLeft < nextRight) {
//                final int nextTarget = Math.min(height[left], height[right]);
//                final int area = nextTarget * (nextRight - nextLeft);
//                if (area > res) {
//                    res = area;
//                }
//                target = nextTarget;
//                left = nextLeft;
//                right = nextRight;
//            } else {
//                break;
//            }
//        }
//        return res;
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            final int h = Math.min(height[left], height[right]);
            final int width = right - left;
            final int area = h * width;
            if (area > res) {
                res = area;
            }

            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return res;
    }

    /**
     * 209. Minimum Size Subarray Sum
     *
     * Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s.
     * If there isn't one, return 0 instead.
     */
    public int minSubArrayLen(int s, int[] nums) {
        //滑动窗口[left, right]
        int left = 0, right = -1;
        int sum = 0;
        int length = nums.length + 1;

        //只要左边框依旧有效
        while (left < nums.length) {
            //选择右边扩张或者左边扩张
            if (right + 1 < nums.length && sum < s) {
                ++right;
                sum += nums[right];
            } else {
                sum -= nums[left];
                ++left;
            }

            //扩张后更新结果状态
            if (sum >= s) {
                final int curLength = right - left + 1;
                if (length > curLength) {
                    length = curLength;
                }
            }
        }
        return length == nums.length + 1 ? 0 : length;
    }
}