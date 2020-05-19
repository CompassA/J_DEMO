package com.study.leetcode.mapandset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author fanqie
 * @date 2019/12/7
 */
public class MapAndSet {

    /**
     * 349. Intersection of Two Arrays
     *
     * Given two arrays, write a function to compute their intersection.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        final Set<Integer> occurs = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());
        final Set<Integer> res = Arrays.stream(nums2)
                .filter(occurs::contains)
                .boxed()
                .collect(Collectors.toSet());
        final int[] nums = new int[res.size()];
        int index = 0;
        for (Integer num : res) {
            nums[index++] = num;
        }
        return nums;
    }

    /**
     * 350. Intersection of Two Arrays II
     *
     * Given two arrays, write a function to compute their intersection.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        final Map<Integer, Integer> numCnt = new HashMap<>(0);
        for (final int num : nums1) {
            int cnt = numCnt.getOrDefault(num, 0);
            numCnt.put(num, cnt + 1);
        }
        final List<Integer> res = new ArrayList<>(0);
        for (final int num : nums2) {
            int cnt = numCnt.getOrDefault(num, 0);
            if (cnt > 0) {
                res.add(num);
                numCnt.put(num, cnt - 1);
            }
        }
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            nums[i] = res.get(i);
        }
        return nums;
    }

    /**
     * 242. Valid Anagram
     *
     * Given two strings s and t ,
     * write a function to determine if t is an anagram of s.
     * Note:
     * You may assume the string contains only lowercase alphabets.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[256];
        for (final char c : s.toCharArray()) {
            ++cnt[c];
        }
        for (final char c : t.toCharArray()) {
            --cnt[c];
        }
        for (int c = 'a'; c <= 'z'; ++c) {
            if (cnt[c] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 202. Happy Number
     * A happy number is a number defined by the following process:
     * Starting with any positive integer,
     * replace the number by the sum of the squares of its digits,
     * and repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.
     * Those numbers for which this process ends in 1 are happy numbers.
     */
    public boolean isHappy(int n) {
        final Set<Integer> occurredAns = new HashSet<>(0);
        while (n != 1) {
            int sum = 0;
            do {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            } while (n != 0);
            if (occurredAns.contains(sum)) {
                return false;
            }
            n = sum;
            occurredAns.add(sum);
        }
        return true;
    }

    /**
     * 290. Word Pattern
     * Easy
     *
     * 821
     *
     * 113
     *
     * Favorite
     *
     * Share
     * Given a pattern and a string str, find if str follows the same pattern.
     *
     * Here follow means a full match,
     * such that there is a bijection between a letter in pattern
     * and a non-empty word in str.
     */
    public boolean wordPattern(String pattern, String str) {
        final String[] strings = str.split(" ");
        final char[] p = pattern.toCharArray();
        if (strings.length != p.length) {
            return false;
        }

        final Map<Character, String> charToStr = new HashMap<>(0);
        final Map<String, Character> strToChar = new HashMap<>(0);
        for (int i = 0; i < p.length; ++i) {
            if (!charToStr.containsKey(p[i])
                    && !strToChar.containsKey(strings[i])) {
                charToStr.put(p[i], strings[i]);
                strToChar.put(strings[i], p[i]);
            } else if (charToStr.containsKey(p[i])
                    && strToChar.containsKey(strings[i])) {
                if (!charToStr.get(p[i]).equals(strings[i]) ||
                        !strToChar.get(strings[i]).equals(p[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 205. Isomorphic Strings
     * Easy
     *
     * 985
     *
     * 286
     *
     * Favorite
     *
     * Share
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        final int[] sToT = new int[256];
        final int[] tToS = new int[256];

        final char[] sChars = s.toCharArray();
        final char[] tChars = t.toCharArray();

        //s和t每个位置的字母都有唯一的映射关系
        //遍历每个位置
        //检测映射关系是否被破坏
        for (int i = 0; i < sChars.length; ++i) {
            final char sChar = sChars[i];
            final char tChar = tChars[i];
            if (sToT[sChar] == 0 && tToS[tChar] == 0) {
                sToT[sChar] = tChar;
                tToS[tChar] = sChar;
            } else if (sToT[sChar] != 0 && tToS[tChar] != 0) {
                if (sToT[sChar] != tChar || tToS[tChar] != sChar) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 451. Sort Characters By Frequency
     * Medium
     *
     * 920
     *
     * 84
     *
     * Favorite
     *
     * Share
     * Given a string, sort it in decreasing order based on the frequency of characters.
     */
    public String frequencySort(String s) {
        if (s.length() == 0) {
            return "";
        }
        final Map<Character, Integer> cnt = new HashMap<>(0);
        final char[] chars = s.toCharArray();
        for (final char c : chars) {
            final int occurredNums = cnt.getOrDefault(c, 0);
            cnt.put(c, occurredNums + 1);
        }
        final Queue<Character> q = new PriorityQueue<>(cnt.keySet().size(),
                Comparator.comparing(character -> -cnt.get(character)));
        for (final char c : cnt.keySet()) {
            q.offer(c);
        }
        final char[] res = new char[chars.length];
        int index = 0;
        while (!q.isEmpty()) {
            final char curChar = q.poll();
            final int occurredTimes = cnt.get(curChar);
            for (int i = 0; i < occurredTimes; ++i) {
                res[index++] = curChar;
            }
        }
        return String.copyValueOf(res);
    }

    /**
     * 447. Number of Boomerangs
     * Easy
     *
     * 336
     *
     * 536
     *
     * Favorite
     *
     * Share
     * Given n points in the plane that are all pairwise distinct,
     * a "boomerang" is a tuple of points (i, j, k) such that the distance
     * between i and j equals the distance between i and k (the order of the tuple matters).
     *
     * Find the number of boomerangs. You may assume that n will be at most 500 and
     * coordinates of points are all in the range [-10000, 10000] (inclusive).
     *
     * Example:
     *
     * Input:
     * [[0,0],[1,0],[2,0]]
     *
     * Output:
     * 2
     *
     * Explanation:
     * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            final int[] pointA = points[i];
            final Map<Integer, Integer> map = new HashMap<>(0);
            for (int j = 0; j < points.length; ++j) {
                if (i != j) {
                    final int[] pointB = points[j];
                    final int differenceX = pointA[0] - pointB[0];
                    final int differenceY = pointA[1] - pointB[1];
                    final int dis = differenceX * differenceX + differenceY * differenceY;
                    final int curCnt = map.getOrDefault(dis, 0);
                    res += curCnt * 2;
                    map.put(dis, curCnt + 1);
                }
            }
        }
        return res;
    }

    /**
     * 149. Max Points on a Line
     * Hard
     *
     * 638
     *
     * 1618
     *
     * Favorite
     *
     * Share
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     *
     * Example 1:
     *
     * Input: [[1,1],[2,2],[3,3]]
     * Output: 3
     * Explanation:
     * ^
     * |
     * |        o
     * |     o
     * |  o
     * +------------->
     * 0  1  2  3  4
     * Example 2:
     *
     * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * Output: 4
     * Explanation:
     * ^
     * |
     * |  o
     * |     o        o
     * |        o
     * |  o        o
     * +------------------->
     * 0  1  2  3  4  5  6
     */
    public int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < points.length; ++i) {
            int[] pointA = points[i];
            Map<Fraction, Integer> cnt = new HashMap<>(0);
            int overlap = 0;
            int max = 0;
            for (int j = i + 1; j < points.length; ++j) {
                int[] pointB = points[j];
                int deltaX = pointA[0] - pointB[0];
                int deltaY = pointA[1] - pointB[1];
                if (deltaX == 0 && deltaY == 0) {
                    overlap++;
                } else {
                    Fraction fraction = Fraction.fractionFactory(deltaX, deltaY);
                    int theFractionCnt = cnt.getOrDefault(fraction, 0) + 1;
                    if (theFractionCnt > max) {
                        max = theFractionCnt;
                    }
                    cnt.put(fraction, theFractionCnt);
                }
            }
            max += overlap + 1;
            if (max > res) {
                res = max;
            }
        }
        return res;
    }

    private static class Fraction {
        private int numerator;
        private int denominator;

        /** vertical */
        private static Fraction SPECIAL_SLOPE = new Fraction(0, 0);

        private int gcd(int a, int b) {
            while (b != 0) {
                int mod = a % b;
                a = b;
                b = mod;
            }
            return a;
        }

        private Fraction(int numerator, int denominator) {
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            if (numerator == 0) {
                denominator = 1;
            } else {
                int theGcd = this.gcd(numerator, denominator);
                numerator /= theGcd;
                denominator /= theGcd;
            }

            this.numerator = numerator;
            this.denominator = denominator;
        }

        public static Fraction fractionFactory(int numerator, int denominator) {
            if (denominator == 0) {
                return SPECIAL_SLOPE;
            }
            return new Fraction(numerator, denominator);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof Fraction)) {
                return false;
            }
            Fraction otherFraction = (Fraction) other;
            return this.numerator == otherFraction.numerator &&
                    this.denominator == otherFraction.denominator;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(this.numerator) * 31
                    + Integer.hashCode(this.denominator);
        }
    }

    /**
     * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
     * Medium
     *
     * 40
     *
     * 57
     *
     * Add to List
     *
     * Share
     * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
     *
     * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. You must return the indices in increasing order.
     *
     *
     *
     * Example 1:
     *
     * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
     * Output: [0,1,4]
     * Explanation:
     * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
     * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
     * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
     * Example 2:
     *
     * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
     * Output: [0,1]
     * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
     * Example 3:
     *
     * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
     * Output: [0,1,2,3]
     *
     *
     * Constraints:
     *
     * 1 <= favoriteCompanies.length <= 100
     * 1 <= favoriteCompanies[i].length <= 500
     * 1 <= favoriteCompanies[i][j].length <= 20
     * All strings in favoriteCompanies[i] are distinct.
     * All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
     * All strings consist of lowercase English letters only.
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        favoriteCompanies.forEach(Collections::sort);
        List<Integer> res = new ArrayList<>();
        int n = favoriteCompanies.size();
        boolean[] covered = new boolean[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (isCovered(favoriteCompanies.get(i), favoriteCompanies.get(j))) {
                    covered[i] = true;
                    break;
                }
            }
            if (!covered[i]) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isCovered(List<String> small, List<String> big) {
        if (small.size() > big.size()) {
            return false;
        }
        int smallIndex = 0;
        int bigIndex = 0;
        while (smallIndex < small.size() && bigIndex < big.size()) {
            int cmpRes = small.get(smallIndex).compareTo(big.get(bigIndex));
            if (cmpRes == 0) {
                ++smallIndex;
                ++bigIndex;
            } else if (cmpRes > 0) {
                ++bigIndex;
            } else {
                return false;
            }
        }
        return smallIndex == small.size();
    }
}
