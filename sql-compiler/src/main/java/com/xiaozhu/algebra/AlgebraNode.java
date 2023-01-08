package com.xiaozhu.algebra;

import java.util.List;

public interface AlgebraNode {
    DataType getType();
    List<AlgebraNode> getInputs();
    String generateCode();
}
