package command.algorithm.mst;

import cli.Format;
import cli.Option;
import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.ClusterSet;
import data.tree.Pair;
import exception.MissingInputException;

public final class GoeBURST extends MinimumSpanningTree {

	private Dataset dataset;
	private int lvs;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		this.dataset = context.readDataset(options);
		this.lvs = Integer.parseInt(options.remove(Option.LVS, Format.NATURAL, "3"));
	}

	@Override
	protected int tiebreak(Matrix matrix, ClusterSet set, Pair<Integer, Integer> i, Pair<Integer, Integer> j) {
		int lvs = Math.min(this.lvs, dataset.get(0).length());
		int[] isource = new int[lvs];
		int[] itarget = new int[lvs];
		int[] jsource = new int[lvs];
		int[] jtarget = new int[lvs];
		for (int index = 0; index < matrix.size(); index++) {
			isource[(int) matrix.get(i.getFirst(), index)]++;
			itarget[(int) matrix.get(i.getSecond(), index)]++;
			jsource[(int) matrix.get(j.getFirst(), index)]++;
			jtarget[(int) matrix.get(j.getSecond(), index)]++;
		}
		int difference;
		for (int lv = 1; lv <= lvs; lv++) {
			difference = Math.max(isource[lv], itarget[lv]) - Math.max(jsource[lv], jtarget[lv]);
			if (difference != 0)
				return difference;
			difference = Math.min(isource[lv], itarget[lv]) - Math.min(jsource[lv], jtarget[lv]);
			if (difference != 0)
				return difference;
		}
		difference = Math.min(i.getFirst(), i.getSecond()) - Math.min(j.getFirst(), j.getSecond());
		return difference != 0 ? difference : (Math.max(i.getFirst(), i.getSecond()) - Math.max(j.getFirst(), j.getSecond()));
	}

}
