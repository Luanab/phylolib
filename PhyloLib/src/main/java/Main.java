import cli.Commands;
import data.Context;
import exception.CommandLineException;
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
            System.err.println("Error: No such file by the name of '" + e.getMessage() + "'...");
        } catch (CommandLineException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
