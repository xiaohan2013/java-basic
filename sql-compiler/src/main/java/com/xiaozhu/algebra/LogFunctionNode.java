package com.xiaozhu.algebra;

import java.util.Collections;
import java.util.List;

public class LogFunctionNode implements AlgebraNode {
    private final AlgebraNode arg;

    public LogFunctionNode(AlgebraNode arg) {
        assert arg.getType() == DataType.DOUBLE;
        this.arg = arg;
    }

    @Override
    public DataType getType() {
        return DataType.DOUBLE;
    }

    @Override
    public List<AlgebraNode> getInputs() {
        return Collections.singletonList(arg);
    }

    @Override
    public String generateCode() {
        return "java.lang.Math.log(" + arg.generateCode() + ")";
    }
}
