class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100001];

        for (int cost : costs) {
            freq[cost]++;
        }

        int ans = 0;

        for (int price = 1; price <= 100000; price++) {
            int take = Math.min(freq[price], coins / price);
            ans += take;
            coins -= take * price;

            if (coins < price) {
                break;
            }
        }

        return ans;
    }
}