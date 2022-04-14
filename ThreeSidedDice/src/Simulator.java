import java.util.Random;

public class Simulator {
    private final int simTimes; // the times of the simulation
    private final double verAngle;    // vertical angle for the dice to stand up by the edge
    private int face1, face2, edge;


    public Simulator ( Dice dice, int simTimes ) {
        this.verAngle = dice.getVerAngle();
        face1 = face2 = edge = 0;
        this.simTimes = simTimes;
    }


    public void simulate() {
        Random random = new Random();

        double maxAngle = Math.PI;
        double angle;
        for( int cnt = 1; cnt <= simTimes; cnt ++ ) {
            // range (0, 2pi]
            angle = random.nextDouble(maxAngle);
            if( angle < ( maxAngle /2 ))
                if( angle < verAngle )
                    face1 ++;
                else edge++;
            else
                if( angle < ( maxAngle - verAngle ) )
                    edge ++;
                else face2 ++;
        }

    }

    public void getResult() {
        System.out.printf( "The number of times on three sides is respective: %d, %d, %d;\n", face1, face2, edge );
        int sum = face1 + face2 + edge;
        float p1, p2, p3;
        p1 = (float) face1/sum;
        p2 = (float) face2/sum;
        p3 = (float) edge/sum;
        System.out.printf( "The percentage of times on three sides is respective: %f, %f, %f;\n", p1, p2, p3 );

    }
}
