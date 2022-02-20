package dhl;

import java.util.*;

class Question3 {

    public static void main(String[] args) {
        List<Integer> start =  Arrays.asList(1,2,3);
        List<Integer> duration =  Arrays.asList(2,2,1);
        List<Integer> volume =  Arrays.asList(1,2,3);

        System.out.println(Question3.phoneCalls(start, duration, volume));
    }

    private static int phoneCalls(List<Integer> start, List<Integer> duration, List<Integer> volume) {
        int size = start.size();
        int[] dp = new int[size];
        int[][] jobs = new int[size][size];

        for (int i = 0; i < size; i++) {
            jobs[i][0] = start.get(i);
            jobs[i][1] = start.get(i) + duration.get(i);
            jobs[i][2] = volume.get(i);
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        dp[0] = jobs[0][2];

        for (int i = 1; i < size; i++) {
            int l = 0;
            int h = i - 1;

            while (l <= h) {
                int mid = (l + h) / 2;
                if (jobs[mid][1] <= jobs[i][0])
                    l = mid + 1;
                else
                    h = mid - 1;
            }

            dp[i] = jobs[i][2];
            if (h != -1)
                dp[i] += dp[h];
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[size - 1];
    }

}


