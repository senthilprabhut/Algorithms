package array;

/**
 * Difficulty Level: MEDIUM
 *
 * Problem:
 *   There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *   You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 *   You begin the journey with an empty tank at one of the gas stations.
 *   Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *   Note:  The solution is guaranteed to be unique.
 *
 * Links:
 *  https://leetcode.com/problems/gas-station/description/
 *  https://www.programcreek.com/2014/03/leetcode-gas-station-java/
 */
public class q41_GasStations {
    public static void main(String[] args) {
        q41_GasStations gs = new q41_GasStations();
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {1,3,2,4,5};
        System.out.println(gs.canCompleteCircuit(gas,cost));
    }

    /**
     * Approach:
     *  To solve this problem, we need to understand and use the following 2 facts:
     *  1) If the sum of gas >= the sum of cost (reqd gas), then the circle can be completed.
     *  2) If A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
     *
     *  Proof of fact 2:
     *  If gas[A] < cost[A], then A can not even reach B.
     *  So to reach C from A, gas[A] must >= cost[A].
     *  Given that A can not reach C, we have gas[A] + gas[B] < cost[A] + cost[B], and gas[A] >= cost[A],
     *  Therefore, gas[B] < cost[B], i.e., B can not reach C.
     *
     * Links:
     *  https://www.programcreek.com/2014/03/leetcode-gas-station-java/
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int start=0, gasInTank=0, totalRemainingGas=0;  //totalRemGas = totalGas-totalCost

        //if car fails at 'start', record the next station
        for (int i=0; i<gas.length; i++) {
            gasInTank += gas[i] - cost[i];
            if (gasInTank < 0) {
                start = i+1;
                totalRemainingGas += gasInTank;
                gasInTank = 0; //Gas in tank cannot be negative
            }
        }

        //if totalGas < totalCost, then totalRemGas will be < 0. This means circle cannot be completed
        return (totalRemainingGas + gasInTank < 0) ? -1 : start;
    }
}
