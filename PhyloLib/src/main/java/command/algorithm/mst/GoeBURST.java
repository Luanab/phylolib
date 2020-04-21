package command.algorithm.mst;

import cli.Option;
import cli.Options;
import data.Context;
import data.matrix.Matrix;
import exception.MissingInputException;

public final class GoeBURST extends MinimumSpanningTree {

	private int lvs;
	private int[][] lv;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		super.init(context, options);
		this.lvs = Integer.parseInt(options.remove(Option.LVS, "3"));
	}

	@Override
	protected void init(Matrix matrix) {
		super.init(matrix);
		this.lv = new int[elements()][lvs];
		for (int i = 0; i < elements(); i++)
			for (int j = 0; j < elements(); j++)
				if (matrix.distance(i, j) < lvs)
					this.lv[i][lvs - 1]++;
	}

	@Override
	protected int tiebreak(int ifrom, int ito, int jfrom, int jto) {
		int diff;
		for (int lv = 0; lv < this.lv[0].length; lv++) {
			diff = Math.max(this.lv[ifrom][lv], this.lv[ito][lv]) - Math.max(this.lv[jfrom][lv], this.lv[jto][lv]);
			if (diff != 0)
				return diff;
			diff = Math.min(this.lv[ifrom][lv], this.lv[ito][lv]) - Math.min(this.lv[jfrom][lv], this.lv[jto][lv]);
			if (diff != 0)
				return diff;
		}
		diff = Math.min(ifrom, ito) - Math.min(jfrom, jto);
		return diff != 0 ? diff : (Math.max(id(ifrom), id(ito)) - Math.max(id(jfrom), id(jto)));
	}

}
