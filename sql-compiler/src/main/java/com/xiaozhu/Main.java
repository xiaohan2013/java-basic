package com.xiaozhu;

import com.google.common.collect.ImmutableMap;
import com.xiaozhu.algebra.AlgebraNode;
import com.xiaozhu.algebra.DataType;
import com.xiaozhu.gen.CalculatorLexer;
import com.xiaozhu.gen.CalculatorParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.codehaus.janino.ExpressionEvaluator;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        final String expr = "a + 2*3 - 2/x + log(x+1)";
        final Map<String, DataType> variableTypes = ImmutableMap.of("a", DataType.LONG,
                "x", DataType.DOUBLE);
        final Map<String, Object> variables = ImmutableMap.of("a", 10L, "x", 3.1414);

        CharStream input = CharStreams.fromString(expr);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree root = parser.input();

        {
            Object result = solveByInterpreter(root, variables);
            System.out.println("Result: " + result + " (" + result.getClass() + ")");
        }
        {
            Object result = solveByCodeGen(root, variableTypes, variables);
            System.out.println("Result: " + result + " (" + result.getClass() + ")");
        }
    }

    private static Object solveByInterpreter(ParseTree root, Map<String, Object> variables) {
        InterpreterVisitor calcVisitor = new InterpreterVisitor(variables);
        return calcVisitor.visit(root);
    }

    private static Object solveByCodeGen(ParseTree root, Map<String, DataType> variableTypes,
                                         Map<String, Object> variables) throws Exception {
        // step 1. Convert AST to Algebra node (validating)
        ValidatorVistor validateVisitor = new ValidatorVistor(variableTypes);
        AlgebraNode rootNode = validateVisitor.visit(root);

        // step 2. Generate code from algebra node and compile with janino
        String code = rootNode.generateCode();
        System.out.println("Code Generation: " + code);

        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        final int numParameters = variableTypes.size();
        String[] paraNames = new String[numParameters];
        Class[] paraType = new Class[numParameters];

        int index = 0;
        for (Map.Entry<String, DataType> entry : variableTypes.entrySet()) {
            paraNames[index] = entry.getKey();
            paraType[index] = entry.getValue() == DataType.DOUBLE ? double.class : long.class;
            index++;
        }
        evaluator.setParameters(paraNames, paraType);
        evaluator.setExpressionType(rootNode.getType() == DataType.DOUBLE ? double.class : long.class);
        evaluator.cook(code);


        // step 3. Evaluate with given parameters
        Object[] parameterValues = new Object[numParameters];
        for (int i = 0; i < paraNames.length; i++) {
            parameterValues[i] = variables.get(paraNames[i]);
        }
        return evaluator.evaluate(parameterValues);
    }
}