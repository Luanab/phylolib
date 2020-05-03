package command.algorithm.mst;

import cli.Option;
import cli.Options;
import data.Context;
import data.matrix.Matrix;

public final class GoeBURST extends MinimumSpanningTree {

	private int lvs;
	private int[][] lv;

	@Override
	public void init(Context context, Options options) {
		this.lvs = Integer.parseInt(options.remove(Option.LVS, "3"));
	}

	@Override
	protected void init(Matrix matrix) {
		super.init(matrix);
		this.lv = new int[matrix.size()][lvs];
		for (int i = 0; i < matrix.size(); i++)
			for (int j = 0; j < matrix.size(); j++) {
				int distance = (int) matrix.distance(i, j);
				if (distance != 0 && distance < lvs)
					this.lv[i][distance - 1]++;
			}
	}

	@Override
	protected int tiebreak(int ifrom, int ito, int jfrom, int jto) {
		int diff;
		for (int index = 0; index < lvs; index++) {
			diff = Integer.compare(Math.max(lv[ifrom][index], lv[ito][index]), Math.max(lv[jfrom][index], lv[jto][index]));
			if (diff != 0)
				return diff;
			diff = Integer.compare(Math.min(lv[ifrom][index], lv[ito][index]), Math.min(lv[jfrom][index], lv[jto][index]));
			if (diff != 0)
				return diff;
		}
		diff = Integer.compare(Math.min(ifrom, ito), Math.min(jfrom, jto));
		return diff != 0 ? diff : Integer.compare(Math.max(id(ifrom), id(ito)), Math.max(id(jfrom), id(jto)));
	}

}
