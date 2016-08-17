package tudor.com.calculatorSleek.data;

/**
 * Created by Tudor on 17-Aug-16.
 */

public abstract class OperationNode extends Node {
	protected Node left, right;

	public OperationNode(Node left, Node right) {
		this.left = left;
		this.right = right;
	}
}
