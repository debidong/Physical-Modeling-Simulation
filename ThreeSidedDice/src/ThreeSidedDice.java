/**
 * Three-Sided-Dice Simulator
 * Author:          debidong
 * Version:         1.0
 * Last updated on: 2022/04/14
 */

import java.util.Scanner;

public class ThreeSidedDice {
    public static void main( String[] args) {
        double radius, height;
        int simTimes;


        /*module 1*/
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the radius of the dice: ");
        radius = scanner.nextDouble();
        System.out.print("Please input the height of the dice: ");
        height = scanner.nextDouble();
        System.out.print("Please input the times of the simulation: ");
        simTimes = scanner.nextInt();

        Dice dice = new Dice( radius, height );
        Simulator simulator = new Simulator( dice, simTimes );
        simulator.simulate();
        simulator.getResult();

    }
}
