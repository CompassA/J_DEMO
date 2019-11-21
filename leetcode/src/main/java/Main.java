import java.util.HashMap;
import java.util.Map;

/**
 * @author FanQie
 * @date 2019/11/20 23:40
 */
public class Main {

    /**
     * NO.1  two sum
     *
     * Given an array of integers,
     * return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     */
    public int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            final int otherNum = target - nums[i];
            if (map.containsKey(otherNum) && map.get(otherNum) != i) {
                return new int[] { i, map.get(otherNum) };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
