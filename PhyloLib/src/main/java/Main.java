import data.Context;
import flow.Parameters;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        try {
            // parse parameters
            Parameters parameters = new Parameters(args);
            // create components
            Optional<Distance> distance = Distance.get(parameters);
            Optional<Correction> correction = Correction.get(parameters);
            Optional<Algorithm> algorithm = Algorithm.get(parameters);
            List<Optimization> optimizations = Optimization.get(parameters);
            // in case there is no component or there is a jump from distance to optimization
            if ((distance.isPresent() || correction.isPresent()) != optimizations.isEmpty() && !algorithm.isPresent())
                throw new Exception("Invalid combination of components...");
            // run components
            Context context = new Context();
            if (distance.isPresent())
                distance.get().run(context);
            if (correction.isPresent())
                correction.get().run(context);
            if (algorithm.isPresent())
                algorithm.get().run(context);
            if (!optimizations.isEmpty())
                for (Optimization optimization : optimizations)
                    optimization.run(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
