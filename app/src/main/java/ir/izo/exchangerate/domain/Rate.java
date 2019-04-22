package ir.izo.exchangerate.domain;

public class Rate {
	private String symbol;
	private double rate;
	private String name;

	public Rate(String symbol, double rate, String name) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
