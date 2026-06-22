class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;

        int pos = 0;
        while (pos < n && nums[pos] < 0) {
            pos++;
        }

        int neg = pos - 1;

        int[] result = new int[n];
        int k = 0;

        while (neg >= 0 && pos < n) {
            int leftSquare = nums[neg] * nums[neg];
            int rightSquare = nums[pos] * nums[pos];

            if (leftSquare <= rightSquare) {
                result[k++] = leftSquare;
                neg--;
            } else {
                result[k++] = rightSquare;
                pos++;
            }
        }

        while (neg >= 0) {
            result[k++] = nums[neg] * nums[neg];
            neg--;
        }

        while (pos < n) {
            result[k++] = nums[pos] * nums[pos];
            pos++;
        }

        return result;
    }
}