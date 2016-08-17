package tudor.com.calculatorSleek.data;

/**
 * Created by Tudor on 17-Aug-16.
 */

public class ValueNode extends Node {
	private double value;

	public ValueNode(double value) {
		this.value = value;
	}

	@Override
	double evaluate() {
		return value;
	}
}
