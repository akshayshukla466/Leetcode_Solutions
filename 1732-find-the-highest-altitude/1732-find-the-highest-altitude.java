class Solution {
    public int largestAltitude(int[] gain) {
        int curr = 0;
        int maxAltitude = 0;

        for (int x : gain) {
            curr += x;
            maxAltitude = Math.max(maxAltitude, curr);
        }

        return maxAltitude;
    }
}