class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int ans = 0;

        for (int start = 0; start < n; start++) {

            int targetCount = 0;

            for (int end = start; end < n; end++) {

                if (nums[end] == target)
                    targetCount++;

                int len = end - start + 1;

                if (targetCount > len / 2)
                    ans++;
            }
        }

        return ans;
    }
}