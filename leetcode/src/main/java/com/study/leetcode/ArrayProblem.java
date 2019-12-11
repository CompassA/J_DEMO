package com.study.leetcode;

import java.util.*;

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
     * 15. 3Sum
     * Medium
     *
     * 5023
     *
     * 610
     *
     * Favorite
     *
     * Share
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     * The solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        final List<List<Integer>> res = new ArrayList<>(0);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ) {
            final int target = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int leftNum = nums[left];
                final int rightNum = nums[right];
                if (target + leftNum == -rightNum) {
                    res.add(Arrays.asList(target, leftNum, rightNum));
                    //数组有序时去重 记录当前数  while 数组下标未越界且数字与当前数相等 递增数组
                    while (left < nums.length && nums[left] == leftNum) {
                        ++left;
                    }
                    while (right >= 0 && nums[right] == rightNum) {
                        --right;
                    }
                } else if (target + leftNum < -rightNum) {
                    ++left;
                } else {
                    --right;
                }
            }
            while (i < nums.length && nums[i] == target) {
                ++i;
            }
        }
        return res;
    }

    /**
     *16. 3Sum Closest
     * Medium
     *
     * 1489
     *
     * 109
     *
     * Favorite
     *
     * Share
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     *
     *
     * */
    public int threeSumClosest(int[] nums, int target) {
        int tolerance = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            final int curNum = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                final int sum = curNum + nums[left] + nums[right];
                int difference = Math.abs(target - sum);
                if (difference == 0) {
                    return target;
                }
                if (tolerance > difference) {
                    res = sum;
                    tolerance = difference;
                }
                if (sum < target) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return res;
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
//        //滑动窗口[left, right]
////        int left = 0, right = -1;
////        int sum = 0;
////        int length = nums.length + 1;
////
////        //只要左边框依旧有效
////        while (left < nums.length) {
////            //选择右边扩张或者左边扩张
////            if (right + 1 < nums.length && sum < s) {
////                ++right;
////                sum += nums[right];
////            } else {
////                sum -= nums[left];
////                ++left;
////            }
////
////            //扩张后更新结果状态
////            if (sum >= s) {
////                final int curLength = right - left + 1;
////                if (length > curLength) {
////                    length = curLength;
////                }
////            }
////        }
////        return length == nums.length + 1 ? 0 : length;
        int left = 0, right = -1, sum = 0;
        int res = nums.length + 1;
        while (right + 1 < nums.length) {
            //增加右边框
            ++right;
            sum += nums[right];

            //循环增加左边框
            while (sum >= s) {
                final int curLength = right - left + 1;
                if (res > curLength) {
                    res = curLength;
                }
                sum -= nums[left];
                ++left;
            }

        }
        return nums.length + 1 == res ? 0 : res;
    }

    /**
     * 438. Find All Anagrams in a String
     *
     * Given a string s and a non-empty string p,
     * find all the start indices of p's anagrams in s.
     *
     * Strings consists of lowercase English letters only and the length of
     * both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     */
    public List<Integer> findAnagrams(String s, String p) {
        final List<Integer> res = new ArrayList<>(0);
        if (s.length() < p.length()) {
            return res;
        }

        int left = 0, right = -1, charInTarget = 0;
        final char[] target = p.toCharArray();
        final char[] chars = s.toCharArray();
        final int[] cnt = new int[256];
        for (final char c : target) {
            ++cnt[c];
        }

        while (right + 1 < chars.length) {
            //增加右边框
            ++right;
            if (cnt[chars[right]] > 0) {
                ++charInTarget;
            }
            --cnt[chars[right]];

            //循环增加左边框
            while (charInTarget == target.length) {
                if (right - left + 1 == target.length) {
                    res.add(left);
                }
                ++cnt[chars[left]];
                if (cnt[chars[left]] > 0) {
                    --charInTarget;
                }
                ++left;
            }
        }
        return res;
    }

    /**
     * 76. Minimum Window Substring
     *
     * Share
     * Given a string S and a string T,
     * find the minimum window in S which will contain
     * all the characters in T in complexity O(n).
     */
    public String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) {
            return res;
        }
        final int[] cnt = new int[256];
        final char[] chars = s.toCharArray();
        final char[] target = t.toCharArray();
        for (final char c : target) {
            ++cnt[c];
        }

        int left = 0, right = -1;
        int charInTarget = 0;
        int minLength = Integer.MAX_VALUE;
        while (right + 1 < chars.length) {
            //扩张右边界
            ++right;
            //cnt[i] > 0 表示该字符在集合中
            if (cnt[chars[right]] > 0) {
                ++charInTarget;
            }
            //减少字符数量，表示字符进入窗口
            --cnt[chars[right]];

            while (charInTarget == target.length) {
                final int curLength = right - left + 1;
                if (curLength < minLength) {
                    minLength = curLength;
                    res = s.substring(left, right + 1);
                }
                cnt[chars[left]]++;
                if (cnt[chars[left]] > 0) {
                    --charInTarget;
                }
                ++left;
            }
        }
        return res;
    }
}