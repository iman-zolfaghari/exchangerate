package ir.izo.exchangerate.domain;

public interface Consumer<T> {
	void consume(T t);
}
