package command.algorithm;

import command.ICommand;
import data.matrix.Matrix;
import data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix}.
 */
public abstract class Algorithm implements ICommand<Matrix, Tree> {

}
