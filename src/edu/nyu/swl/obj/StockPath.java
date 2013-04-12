package edu.nyu.swl.obj;

import java.util.List;

/**
 * The interface for creating StockPath. The returned list should be ordered by date
 * @author wenlingshi
 *
 */
public interface StockPath {
	public List<DatePrice> getPrices();

}
