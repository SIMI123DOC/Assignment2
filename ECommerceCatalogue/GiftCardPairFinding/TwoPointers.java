package ECommerceCatalogue.GiftCardPairFinding;


public class TwoPointers {

    static void findPairs(int[] giftCards, int target) {

        int left = 0;
        int right = giftCards.length - 1;

        System.out.println("Gift Card Pairs:");

        while (left < right) {

            int sum = giftCards[left] + giftCards[right];

            if (sum == target) {

                System.out.println(
                    giftCards[left] + " + "
                    + giftCards[right] + " = " + target
                );

                left++;
                right--;

            } else if (sum < target) {

                left++;

            } else {

                right--;
            }
        }
    }

    public static void main(String[] args) {

        // Sorted gift card values
        int[] giftCards = {
            100, 150, 200, 250, 300, 350, 400
        };

        int target = 500;

        findPairs(giftCards, target);
    }
}
