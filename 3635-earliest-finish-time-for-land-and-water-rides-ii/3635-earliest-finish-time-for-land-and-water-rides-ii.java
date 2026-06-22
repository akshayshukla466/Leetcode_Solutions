import java.util.*;

class Solution {

    private long solve(int[] firstFinish,
                       int[] secondStart,
                       int[] secondDuration) {

        int n = secondStart.length;

        int[][] rides = new int[n][2];

        for (int i = 0; i < n; i++) {
            rides[i][0] = secondStart[i];
            rides[i][1] = secondDuration[i];
        }

        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));

        int[] starts = new int[n];

        long[] prefixMinDur = new long[n];
        long[] suffixMinStartPlusDur = new long[n];

        for (int i = 0; i < n; i++) {
            starts[i] = rides[i][0];
        }

        prefixMinDur[0] = rides[0][1];
        for (int i = 1; i < n; i++) {
            prefixMinDur[i] = Math.min(prefixMinDur[i - 1], rides[i][1]);
        }

        suffixMinStartPlusDur[n - 1] =
                (long) rides[n - 1][0] + rides[n - 1][1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMinStartPlusDur[i] = Math.min(
                    suffixMinStartPlusDur[i + 1],
                    (long) rides[i][0] + rides[i][1]
            );
        }

        long ans = Long.MAX_VALUE;

        for (int finishTime : firstFinish) {

            int idx = lowerBound(starts, finishTime);

            long best = Long.MAX_VALUE;
            if (idx < n) {
                best = Math.min(best, suffixMinStartPlusDur[idx]);
            }
            if (idx > 0) {
                best = Math.min(best,
                        (long) finishTime + prefixMinDur[idx - 1]);
            }

            ans = Math.min(ans, best);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public int earliestFinishTime(int[] landStartTime,
                                  int[] landDuration,
                                  int[] waterStartTime,
                                  int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;

        int[] landFinish = new int[n];
        int[] waterFinish = new int[m];

        for (int i = 0; i < n; i++) {
            landFinish[i] = landStartTime[i] + landDuration[i];
        }

        for (int i = 0; i < m; i++) {
            waterFinish[i] = waterStartTime[i] + waterDuration[i];
        }

        long landThenWater =
                solve(landFinish, waterStartTime, waterDuration);

        long waterThenLand =
                solve(waterFinish, landStartTime, landDuration);

        return (int) Math.min(landThenWater, waterThenLand);
    }
}