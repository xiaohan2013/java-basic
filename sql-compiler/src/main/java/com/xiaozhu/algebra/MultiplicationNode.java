package com.xiaozhu.algebra;

import com.xiaozhu.algebra.AlgebraNode;
import com.xiaozhu.algebra.DataType;

import java.util.List;

public class MultiplicationNode extends BinaryNode implements AlgebraNode {
    public MultiplicationNode(AlgebraNode left, AlgebraNode right) {
        super(left, right);
    }

    @Override
    public String generateCode() {
        if (getLeft().getType() == DataType.DOUBLE && getRight().getType() == DataType.DOUBLE) {
            return "(" + getLeft().generateCode() + " * " + getRight().generateCode() + ")";
        } else if (getLeft().getType() == DataType.DOUBLE && getRight().getType() == DataType.LONG) {
            return "(" + getLeft().generateCode() + " * (double)" + getRight().generateCode() + ")";
        } else if (getLeft().getType() == DataType.LONG && getRight().getType() == DataType.DOUBLE) {
            return "((double)" + getLeft().generateCode() + " * " + getRight().generateCode() + ")";
        } else if (getLeft().getType() == DataType.LONG && getRight().getType() == DataType.LONG) {
            return "(" + getLeft().generateCode() + " * " + getRight().generateCode() + ")";
        }
        throw new IllegalStateException();
    }
}
