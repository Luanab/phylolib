package command.algorithm.mst;

import cli.Option;
import cli.Options;
import data.Context;
import exception.MissingInputException;

public final class GoeBURST extends MinimumSpanningTree {

	private int[][] lvs;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		super.init(context, options);
		int lvs = Integer.parseInt(options.remove(Option.LVS, "3"));
		this.lvs = new int[elements()][lvs];
		for (int i = 0; i < elements(); i++)
			for (int j = 0; j < elements(); j++)
				if (distance(i, j) < lvs)
					this.lvs[i][lvs]++;
	}

	@Override
	protected int tiebreak(int ifrom, int ito, int jfrom, int jto) {
		int diff;
		for (int lv = 0; lv < lvs[0].length; lv++) {
			diff = Math.max(lvs[ifrom][lv], lvs[ito][lv]) - Math.max(lvs[jfrom][lv], lvs[jto][lv]);
			if (diff != 0)
				return diff;
			diff = Math.min(lvs[ifrom][lv], lvs[ito][lv]) - Math.min(lvs[jfrom][lv], lvs[jto][lv]);
			if (diff != 0)
				return diff;
		}
		diff = Math.min(ifrom, ito) - Math.min(jfrom, jto);
		return diff != 0 ? diff : (Math.max(ifrom, ito) - Math.max(jfrom, jto));
	}

}
