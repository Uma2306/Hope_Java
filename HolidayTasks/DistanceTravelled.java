Leetcode 2739: 
class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int distance = 0;
        int mileage = 10; 
        while (mainTank > 0) {
            if (mainTank >= 5) {
                mainTank -= 5;
                distance += 5 * mileage;

                if (additionalTank >= 1) {
                    mainTank += 1;
                    additionalTank -= 1;
                }
            } else {
                distance += mainTank * mileage;
                mainTank = 0;
            }
        }
        return distance;
    }
}
