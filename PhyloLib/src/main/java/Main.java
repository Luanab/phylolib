import cli.Commands;
import data.Context;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.nio.file.NoSuchFileException;

public class Main {

    public static void main(String[] args) {
        try {
            Commands commands = new Commands();
            commands.parse(args);
            Context context = new Context();
            Distance.run(commands, context);
            Correction.run(commands, context);
            Algorithm.run(commands, context);
            Optimization.run(commands, context);
        } catch (NoSuchFileException e) {
            System.err.println("Error: No such file named '" + e.getMessage() + "'...");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
