package optimization;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;

public interface IOptimizer {

	PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree);

}
