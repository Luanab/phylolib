package pt.ist.phylolib.command.algorithm;

import pt.ist.phylolib.command.ICommand;
import pt.ist.phylolib.data.matrix.Matrix;
import pt.ist.phylolib.data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix}.
 */
public abstract class Algorithm implements ICommand<Matrix, Tree> {

}
