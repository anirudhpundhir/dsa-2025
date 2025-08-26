import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TwoSumPair {

    /*
        Time complexity: O(n)
        Space complexity: O(n)

        n is the number of elements in the input
    */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                int j = seen.get(complement);
                return new int[]{Math.min(i, j), Math.max(i, j)};
            }

            seen.put(nums[i], i);
        }

        return new int[]{}; // if no pair exists
    }


    /*
        Time complexity: O(n^2)
        Space complexity: O(1)

        n is the number of elements in the input
    */
    public int[] twoSumSpaceOptimised(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // if no pair exists
    }


    /*
        Time complexity: O(n log N)
        Space complexity: O(1)

        n is the number of elements in the input
    */
    public int[] twoSumSortingSpaceOptimised(int[] nums, int target) {
        int n = nums.length;

        // Build index array referencing original positions
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        // Stable sort indices by value at nums[i]
        Arrays.sort(idx, Comparator.comparingInt(i -> nums[i]));

        int l = 0, r = n - 1;
        while (l < r) {
            long sum = (long) nums[idx[l]] + nums[idx[r]]; // avoid overflow
            if (sum == target) {
                int i = idx[l], j = idx[r];
                // return smaller index first
                return i < j ? new int[]{i, j} : new int[]{j, i};
            }
            if (sum < target) l++;
            else r--;
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSumPair pair = new TwoSumPair();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = pair.twoSum(nums, target);
        if (result.length == 2) {
            System.out.println("Pair found: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No pair found");
        }

        int[] result1 = pair.twoSumSortingSpaceOptimised(nums, target);
        if (result1.length == 2) {
            System.out.println("Pair found: " + result1[0] + ", " + result1[1]);
        } else {
            System.out.println("No pair found");
        }

        int[] result2 = pair.twoSumSpaceOptimised(nums, target);
        if (result2.length == 2) {
            System.out.println("Pair found: " + result2[0] + ", " + result2[1]);
        } else {
            System.out.println("No pair found");
        }
    }
    
}
