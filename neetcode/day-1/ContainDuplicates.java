import java.util.*;

class ContainDuplicates {

    // Time -> O(N) , space -> O(n)
    public static boolean hasDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int i : nums){

            if(!set.add(i)){
                return true;
            }
        }

        return false;
    }

    // Note: sorts the input array in place.
    public static boolean hasDuplicateSortedArray(int[] nums) {
        Arrays.sort(nums); // O(N log N) time, O(1) or O(log N) space depending on sort impl
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    // Time -> O(N) , space -> O(1)
    // Sign-marking trick: only valid when every value is in [1, n] (e.g. LeetCode 442/448);
    // value v is marked at index v - 1.
    // Mutates the input while running and restores it before returning.
    public static boolean hasDuplicateSpaceOptimised(int[] nums) {
        for (int v : nums) {
            if (v < 1 || v > nums.length) {
                throw new IllegalArgumentException("values must be in [1, n]: " + v);
            }
        }
        boolean found = false;
        for (int i = 0; i < nums.length && !found; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                found = true; // duplicate found
            } else {
                nums[index] = -nums[index]; // mark as visited
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        return found;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3, 3};

        System.out.println(hasDuplicate(nums));
        System.out.println("space optimised with sorted array => " + hasDuplicateSortedArray(nums.clone()));
        System.out.println("space optimised => " + hasDuplicateSpaceOptimised(nums.clone()));
    }
}
