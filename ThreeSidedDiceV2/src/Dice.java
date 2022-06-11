import java.util.Scanner;

public class Dice {
    private double mass;
    private double radius;
    private double height;

    /*constructor*/
    public Dice() {}

    public Dice( double mass, double radius, double height ) {
        this.mass = mass;
        this.radius = radius;
        this.height = height;
    }

    public static Dice getDice() {
        Dice dice = new Dice();
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please input the mass of the dice(g): ");
//        dice.mass = scanner.nextDouble() / 1000;
        dice.mass = 0.01;
        System.out.print("Please input the radius of the dice(cm): ");
        dice.radius = scanner.nextDouble() / 100;
        System.out.print("Please input the height of the dice(cm): ");
        dice.height = scanner.nextDouble() / 100;

        return dice;

    }

    public double getHeight() {
        return height;
    }
    public double getMass() {
        return mass;
    }
    public double getRadius() {
        return radius;
    }

}
