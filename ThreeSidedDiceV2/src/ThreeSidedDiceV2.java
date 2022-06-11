/**
 * Three-Sided-Dice Simulator
 * Author:          debidong
 * Version:         2.0
 * Last updated on: 2022/04/16
 */

import java.util.Scanner;

public class ThreeSidedDiceV2 {
    public static void main(String[] args) {

        Dice dice = Dice.getDice();

        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please input the times of the simulation: ");
//        int simTimes = scanner.nextInt();
        int simTimes = 1000000;
        Simulator simulator = new Simulator( simTimes );


        simulator.simulate( dice );
        simulator.getResult( dice );


    }
}
