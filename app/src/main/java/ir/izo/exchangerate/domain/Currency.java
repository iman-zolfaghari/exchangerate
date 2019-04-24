package ir.izo.exchangerate.domain;

import java.io.Serializable;

public class Currency implements Serializable {
	private String symbol;
	private double rate;
	private String name;

	public Currency(String symbol, double rate, String name) {
		this.rate = rate;
		this.name = name;
		this.symbol = symbol;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return getSymbol() + " - " + getName();
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
