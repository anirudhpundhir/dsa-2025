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
    public static boolean hasDuplicateSpaceOptimised(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]);

        if (nums[index] < 0) {
            return true; // duplicate found
        }

        nums[index] = -nums[index]; // mark as visited
        }
        return false;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 3, 3};

        System.out.println(hasDuplicate(nums));
        System.out.println("space optmised with sorted array => "+hasDuplicateSortedArray(nums));
        System.out.println("space optmised => "+hasDuplicateSpaceOptimised(nums));



    }
}