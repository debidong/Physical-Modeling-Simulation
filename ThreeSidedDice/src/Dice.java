public class Dice {
    private final double verAngle; // vertical angle for the dice to stand up by the edge,
                                 // range (0,pi/2)

    private final double radius;
    private final double height;

    public Dice(double radius, double height) {
        this.radius = radius;
        this.height = height;
        this.verAngle = Math.atan( 2 * radius / height );
    }

    public double getVerAngle() {
        return verAngle;
    }

}
