import java.util.Random;

public class Simulator {
    private double fallingHei; // falling height
    private final int simTime;    // times of the simulation
    private int face1, face2, edge; // store the times of the results


    public Simulator( int simTime ) {
        this.fallingHei = 1.0; // this parameter is set to 1.0m by default, also can be changed
                               // falling height should be set higher than both the 2*radius and 2*halfHeight
                               // to make the dice reflect at least once after each drop
        this.simTime = simTime;
        face1 = face2 = edge = 0;
    }

    public double getFallingHei() {
        return fallingHei;
    }

    public void setFallingHei(double fallingHei) {
        this.fallingHei = fallingHei;
    }

    public void simulate(Dice dice) {
        double halfHeight = dice.getHeight() / 2;
        double radius = dice.getRadius();

        double g = 9.80665;

        /*used to calculate the momentum*/
        double mass = dice.getMass();
        double J = Math.pow(radius, 2) * mass / 2 + Math.pow(halfHeight, 2) * mass / 3;
        double temp = Math.sqrt(Math.pow(radius, 2) + Math.pow(halfHeight, 2));
        double L;  // L = f(θ) * temp
                   // M = L * F

        double k = 0.6; // v <- kv after every reflection
        double K = mass * (k + 1) / J;

        double angle;
        double verAngle = Math.atan(radius / halfHeight);

        double currentHei;
        double fallingTime;

        double aV;  // angular velocity
        double vV;  // vertical velocity

        double PI = Math.PI;
        Random random = new Random();

        /*loops for simulation*/
        for (int cnt = 1; cnt <= simTime; cnt++) {

            angle = random.nextDouble(2 * PI);
            currentHei = fallingHei;
            fallingTime = Math.sqrt(2 * currentHei / g);
            aV = 0;
            vV = Math.sqrt( 2 * currentHei * g );
            angle += aV * fallingTime;

            /*if being able to reflection*/
            while (currentHei > 2 * radius && currentHei > 2 * halfHeight) {
                if (angle < PI) {
                    if (angle < PI / 2) {
                        // 0 - ver
                        if (angle < PI /2 - verAngle) {
                            L = Math.sin( PI/2 - angle - verAngle ) * temp;
                            aV -= K * vV * L;
                        }
                        //ver - 90
                        else {
                            L = Math.sin( angle + verAngle - PI/2 ) * temp;
                            aV += K * vV * L;
                        }
                    }
                    else {
                        // 90 - ver
                        if (angle < PI /2 + verAngle) {
                            L = Math.sin( PI - angle - verAngle ) * temp;
                            aV -= K * vV * L;
                        }
                        // ver - 180
                        else {
                            L = Math.sin( angle - verAngle - PI/2 ) * temp;
                            aV += K * vV * L;
                        }
                    } // if, (0, PI]
                } else {
                    if (angle < PI * 3 / 2) {
                        // 180 - ver
                        if (angle < PI * 3/2 - verAngle) {
                            L = Math.sin( PI * 3/2 - angle - verAngle ) * temp;
                            aV -= K * vV * L;
                        }
                        // ver - 270
                        else {
                            L = Math.sin( angle + verAngle - PI * 3/2 ) * temp;
                            aV += K * vV * L;
                        }
                    } else {
                        // 270 - ver
                        if (angle < PI * 3/2 + verAngle) {
                            L = Math.sin( PI * 3/2 - angle + verAngle ) * temp;
                            aV -= K * vV * L;
                        }

                        // ver - 360
                        else {
                            L = Math.sin( angle - verAngle - PI * 3/2 ) * temp;
                            aV += K * vV * L;
                        }

                    }
                } // else, (PI, 2PI]

                vV *= k;
                currentHei = Math.pow( vV, 2 ) / ( 2 * g );
                angle += 2 * vV * aV / g;
            }//while

            /*set the angle in range of [0, 2PI)*/
            angle = angle % ( 2 * PI );
            if( angle < 0 ) angle += 2 * PI;

            /*calculate the result*/
            if( angle > 3 * PI /2 - verAngle ) {
                if( angle > 3 * PI /2 + verAngle )
                    face1 ++;
                else
                    edge ++;
            } else {
                if( angle > PI /2 + verAngle )
                    face2 ++;
                else {
                    if( angle < PI /2 - verAngle )
                        face1 ++;
                    else
                        edge ++;
                }
            }

        }//for, loop for simulation

    }

    public void getResult() {
        System.out.println("----------------------------------------------------------------");
        System.out.println( "The number of times on three sides is respective: ");
        System.out.println( "Face1: " + face1 + " | Face2: " + face2 + " | Edge: " + edge);
        int sum = face1 + face2 + edge;
        float p1, p2, p3;
        p1 = (float) face1/sum;
        p2 = (float) face2/sum;
        p3 = (float) edge/sum;
        System.out.printf( "The percentage of times on three sides is respective: %f, %f, %f;\n", p1, p2, p3 );
        System.out.println("----------------------------------------------------------------");

    }
}