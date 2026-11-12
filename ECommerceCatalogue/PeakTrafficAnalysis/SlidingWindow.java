package ECommerceCatalogue.PeakTrafficAnalysis;


public class SlidingWindow {

    static void busiestWindow(int[] requests) {

        int windowSize = 180; // 3 hours

        int sum = 0;

        // Calculate requests in the first 3 hours
        for (int i = 0; i < windowSize; i++) {
            sum = sum + requests[i];
        }

        int maxSum = sum;
        int start = 0;

        // Slide the window
        for (int i = windowSize; i < requests.length; i++) {

            sum = sum - requests[i - windowSize] + requests[i];

            if (sum > maxSum) {
                maxSum = sum;
                start = i - windowSize + 1;
            }
        }

        int end = start + windowSize;

        System.out.println("Busiest 3-Hour Window:");

        System.out.println("Start Time: "
                + (start / 60) + ":"
                + String.format("%02d", start % 60));

        System.out.println("End Time: "
                + (end / 60) + ":"
                + String.format("%02d", end % 60));

        System.out.println("Total Requests: " + maxSum);
    }

    public static void main(String[] args) {

        // Requests received every minute for 24 hours
        int[] requests = new int[1440];

        // Initially, 100 requests per minute
        for (int i = 0; i < requests.length; i++) {
            requests[i] = 100;
        }

        // Peak traffic from 9 AM to 12 PM
        for (int i = 540; i < 720; i++) {
            requests[i] = 500;
        }

        busiestWindow(requests);
    }
}
