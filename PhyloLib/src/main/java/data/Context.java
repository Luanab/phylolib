package data;

import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.Tree;

public class Context {

    private Dataset dataset;
    private Matrix matrix;
    private Tree tree;

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

}
