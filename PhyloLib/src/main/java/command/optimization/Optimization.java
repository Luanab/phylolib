package command.optimization;

import cli.Arguments;
import command.Command;
import command.ICommand;
import data.Context;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.HashMap;

public abstract class Optimization implements ICommand<Tree, Tree> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.OPTIMIZATION, context::getTree, context::setTree, new HashMap<>() {{
			put("lbr", new LBR());
			put("nni", new NNI());
			put("spr", new SPR());
			put("tbr", new TBR());
		}});
	}

	@Override
	public final Tree process(Tree tree) {
		return null;
	}

	protected abstract Edge select();

	protected abstract Edge join();

	protected abstract void reduce(Edge edge);

}
