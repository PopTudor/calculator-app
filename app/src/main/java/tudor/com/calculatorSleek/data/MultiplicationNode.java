package tudor.com.calculatorSleek.data;

import java.util.Locale;

/**
 * Created by Tudor on 17-Aug-16.
 */

public class MultiplicationNode extends OperationNode {
	public MultiplicationNode(Node left, Node right) {
		super(left, right);
	}

	@Override
	double evaluate() {
		return left.evaluate() * right.evaluate();
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH, "(%f*%f)", left.evaluate(), right.evaluate());
	}
}
