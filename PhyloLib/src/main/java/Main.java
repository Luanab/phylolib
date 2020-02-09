import data.Context;
import flow.Parameters;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

public class Main {

    public static void main(String[] args) {
        try {
            Parameters parameters = new Parameters(args);
            Context context = new Context();
            Distance.run(parameters, context);
            Correction.run(parameters, context);
            Algorithm.run(parameters, context);
            Optimization.run(parameters, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
