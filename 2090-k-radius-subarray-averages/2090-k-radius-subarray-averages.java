class Solution {
    public int[] getAverages(int[] nums, int k) {

        int n = nums.length;
        int[] ans = new int[n];

        Arrays.fill(ans, -1);

        long sum = 0;

        for (int i = 0; i < n; i++) {

            sum += nums[i];

            
            if (i >= 2 * k) {

                ans[i - k] = (int)(sum / (2 * k + 1));

                sum -= nums[i - 2 * k];
            }
        }

        return ans;
    }
}