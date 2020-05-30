package com.study.leetcode.array;

/**42. Trapping Rain Water
 Hard

 6553

 114

 Add to List

 Share
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

 Example:

 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 * @author fanqie
 * @date 2020/5/29
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        leftMax[0] = 0;
        int max = height[0];
        for (int i = 1; i < leftMax.length; ++i) {
            leftMax[i] = max;
            max = Math.max(max, height[i]);
        }
        int[] rightMax = new int[height.length];
        rightMax[height.length-1] = 0;
        max = height[height.length-1];
        for (int j = height.length-2; j >= 0; --j) {
            rightMax[j] = max;
            max = Math.max(max, height[j]);
        }
        int res = 0;
        for (int i = 1; i < height.length; ++i) {
            if (height[i] < leftMax[i] && height[i] < rightMax[i]) {
                res += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return res;
    }

    public int trapII(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int leftMax = height[0];
        int rightMax = height[height.length-1];
        int left = 0;
        int right = height.length-1;
        int res = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    res += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                ++left;
            } else {
                if (height[right] < rightMax) {
                    res += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                --right;
            }
        }
        return res;
    }
}
