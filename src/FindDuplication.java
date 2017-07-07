import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class FindDuplication {
    /**
     * use hashSet, time complex is O(n), space complex is O(n).
     * pre condition: no
     * @param nums
     */
    public void find1(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                System.out.print(nums[i] + " ");
            } else {
                set.add(nums[i]);
            }
        }

        System.out.println();
    }

    /**
     * use extra array as map,time complex is O(n), space complex is O(max(nums))
     * pre condition : the length of map array has to be equal or larger than maximum element in nums array
     * @param nums
     */
    public void find2(int[] nums) {
        int[] map = new int[nums.length + 1];
        Arrays.fill(map, 0);

        for (int i = 0; i < nums.length; i++) {
            if (map[nums[i]] == 1) {
                System.out.print(nums[i] + " ");
            } else {
                map[nums[i]]++;
            }
        }

        System.out.println();
    }

    /**
     * change the original array as map. time complex is O(n), space time complex is 0.
     * post condition: original array is changed.
     * @param nums
     */
    public void find3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])] >= 0) {
                nums[Math.abs(nums[i])] = -nums[Math.abs(nums[i])];
            } else {
                System.out.print(Math.abs(nums[i]) + " ");
            }
        }

        System.out.println();
    }

    /**
     * use sum. time complex O(n), space complex is constant.
     * pre condition : number of array is n + 1, then range of element is 1 ~ n
     * @param nums
     */
    public void find4(int[] nums) {
        int sum = ((nums.length) * (nums.length - 1)) / 2;
        int sum2 = 0;

        for (int i = 0; i < nums.length; i++) {
            sum2 += nums[i];
        }

        System.out.println(sum2 - sum);
    }

    /**
     * binary search, time complex is O(logN), space complex is constant
     * pre condition : number of array is n + 1, then range of element is 1 ~ n
     * @param nums
     */
    public void find5(int[] nums) {
        int start = 1;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + ((end - start) / 2);
            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count <= mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        System.out.println(start);
    }

    /**
     * convert unsorted array to sort array. time complex is O(nlogn), space complex is constant
     * post condition: original array is changed.
     * @param nums
     */
    public void find6(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                System.out.println(nums[i]);
            }
        }
    }

    /**
     * Main Driver.
     * @param args
     */
    public static void main(String[] args) {
        FindDuplication find = new FindDuplication();
        int[] nums1 = { 1, 2, 3, 3, 2, 5 };
        find.find1(nums1);

        int[] nums2 = { 2, 1, 1, 3, 2, 5, 5 };
        find.find2(nums2);

        int[] nums3 = { 2, 1, 1, 3, 2, 5, 5 };
        find.find3(nums3);

        int[] nums4 = { 1, 2, 3, 4, 4, 5 };
        find.find4(nums4);

        int[] nums5 = { 1, 2, 3, 4, 4, 5 };
        find.find5(nums5);

        int[] nums6 = { 2, 1, 1, 3, 2, 5, 5 };
        find.find3(nums6);
    }
}
