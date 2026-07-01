import java.util.*;

class Solution {

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];
        for (int[] row : dist)
            Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

      
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int x = curr[0];
            int y = curr[1];

            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[2] - a[2]
        );

        boolean[][] vis = new boolean[n][n];

        pq.offer(new int[]{0, 0, dist[0][0]});
        vis[0][0] = true;

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int x = curr[0];
            int y = curr[1];
            int safe = curr[2];

            if (x == n - 1 && y == n - 1)
                return safe;

            for (int[] d : dir) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !vis[nx][ny]) {

                    vis[nx][ny] = true;

                    int newSafe = Math.min(safe, dist[nx][ny]);

                    pq.offer(new int[]{nx, ny, newSafe});
                }
            }
        }

        return 0;
    }
}