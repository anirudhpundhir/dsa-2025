import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day1Test {

    private final AnagramChecker anagrams = new AnagramChecker();
    private final TwoSumPair twoSum = new TwoSumPair();

    @ParameterizedTest
    @CsvSource({"listen,silent,true", "hello,world,false", "aab,abb,false", "a,a,true", "ab,abc,false"})
    void anagramVariantsAgree(String s, String t, boolean expected) {
        assertEquals(expected, anagrams.isAnagram(s, t));
        assertEquals(expected, anagrams.isAnagramOptimised(s, t));
        assertEquals(expected, anagrams.isAnagramCountingSortStyle(s, t));
    }

    @Test
    void containsDuplicate() {
        assertTrue(ContainDuplicates.hasDuplicate(new int[]{1, 2, 3, 3}));
        assertFalse(ContainDuplicates.hasDuplicate(new int[]{1, 2, 3}));
        assertFalse(ContainDuplicates.hasDuplicate(new int[]{}));
        assertTrue(ContainDuplicates.hasDuplicateSortedArray(new int[]{3, 1, 3}));
        assertFalse(ContainDuplicates.hasDuplicateSortedArray(new int[]{-1, 5, 1000}));
    }

    @Test
    void signMarkingRestoresInputAndChecksRange() {
        int[] nums = {1, 3, 2, 3};
        assertTrue(ContainDuplicates.hasDuplicateSpaceOptimised(nums));
        assertArrayEquals(new int[]{1, 3, 2, 3}, nums);
        assertFalse(ContainDuplicates.hasDuplicateSpaceOptimised(new int[]{3, 1, 2, 4}));
        assertThrows(IllegalArgumentException.class,
                () -> ContainDuplicates.hasDuplicateSpaceOptimised(new int[]{1, 2, 4}));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {"2,7,11,15;9;0,1", "3,2,4;6;1,2", "3,3;6;0,1", "-3,4,3,90;0;0,2"})
    void twoSumVariantsAgree(String numsCsv, int target, String expectedCsv) {
        int[] nums = parse(numsCsv);
        int[] expected = parse(expectedCsv);
        assertArrayEquals(expected, twoSum.twoSum(nums, target));
        assertArrayEquals(expected, twoSum.twoSumSpaceOptimised(nums, target));
        assertArrayEquals(expected, twoSum.twoSumSortingSpaceOptimised(nums, target));
    }

    @Test
    void twoSumReturnsEmptyWhenNoPair() {
        assertEquals(0, twoSum.twoSum(new int[]{1, 2}, 10).length);
        assertEquals(0, twoSum.twoSumSpaceOptimised(new int[]{1, 2}, 10).length);
        assertEquals(0, twoSum.twoSumSortingSpaceOptimised(new int[]{1, 2}, 10).length);
    }

    private static int[] parse(String csv) {
        String[] parts = csv.split(",");
        int[] out = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            out[i] = Integer.parseInt(parts[i].trim());
        }
        return out;
    }
}
