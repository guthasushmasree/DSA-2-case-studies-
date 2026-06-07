public class Knapsack01 {

    public static void main(String[] args) {

        int[] weight = {5, 8, 3, 10, 4, 6, 7, 2};
        int[] value  = {40,50,20,70,30,35,45,15};

        String[] item = {"A","B","C","D","E","F","G","H"};

        int n = 8;
        int W = 24;

        int[][] dp = new int[n + 1][W + 1];

        for(int i = 1; i <= n; i++) {

            for(int w = 0; w <= W; w++) {

                if(weight[i-1] <= w) {

                    dp[i][w] = Math.max(
                        dp[i-1][w],
                        dp[i-1][w - weight[i-1]] + value[i-1]
                    );
                }
                else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        System.out.println("================================");
        System.out.println(" 0/1 KNAPSACK OPTIMAL SOLUTION ");
        System.out.println("================================");
        System.out.println("Truck Capacity : " + W + " tons");
        System.out.println("Maximum Value  : Rs." + dp[n][W] + "k");

        System.out.println("\nSelected Items:");

        int w = W;

        for(int i = n; i > 0; i--) {

            if(dp[i][w] != dp[i-1][w]) {

                System.out.println(item[i-1] +
                        " (Weight=" + weight[i-1] +
                        ", Value=Rs." + value[i-1] + "k)");

                w -= weight[i-1];
            }
        }
    }
}