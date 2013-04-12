package edu.nyu.swl.obj;

import org.joda.time.DateTime;

/**
 * Represents the price of stock for each day
 * @author wenlingshi
 *
 */
public class DatePrice {
	private DateTime date;
	private double price;
	
	public DatePrice(DateTime date, double price) {
		super();
		this.date = date;
		this.price = price;
	}

	public DateTime getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}
	
	
}
