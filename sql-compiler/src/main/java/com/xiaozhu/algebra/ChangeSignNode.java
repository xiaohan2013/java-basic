package com.xiaozhu.algebra;

import java.util.List;

public class ChangeSignNode extends UnaryNode implements AlgebraNode {
    public ChangeSignNode(AlgebraNode input) {
        super(input);
    }

    @Override
    public String generateCode() {
        return "(-(" + getInput().generateCode() + "))";
    }
}
