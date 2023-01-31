package com.xiaozhu.compiler;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.util.ResourceFinderClassLoader;
import org.codehaus.commons.compiler.util.resource.*;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.ScriptEvaluator;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class JaninoMain {
    // 在Java中调用，且动态编译Java代码并加载
    public static void main(String[] args)  {
        try {
            testExpression();
        } catch (CompileException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void testClassBody(){

    }

    public static void testCompiler() throws Exception {
        ICompiler compiler = CompilerFactoryFactory
                .getDefaultCompilerFactory(Thread.currentThread().getContextClassLoader())
                .newCompiler();

        Map<String, byte[]> classes = new HashMap<String, byte[]>();
        compiler.setClassFileCreator(new MapResourceCreator(classes));

//        compiler.compile(new File(""));
        compiler.compile(new Resource[]{
                new StringResource(
                        "pkg1/A.java",
                        "package pkg1; public class A { public static int meth() { return pkg2.B.meth(); } }"
                ),
                new StringResource(
                        "pkg2/B.java",
                        "package pkg2; public class B { public static int meth() { return 77;            } }"
                ),
        });

        // 配置用生成class的classloader
        ClassLoader cl = new ResourceFinderClassLoader(new MapResourceFinder(classes), ClassLoader.getSystemClassLoader());

        Object result = cl.loadClass("pkg1.A").getDeclaredMethod("meth").invoke(null);
        System.out.println(result);
        // Assert.assertEquals(77, cl.loadClass("pkg1.A").getDeclaredMethod("meth").invoke(null));
    }

    public static void testScript() throws CompileException, InvocationTargetException {
        ScriptEvaluator se = new ScriptEvaluator();
        se.cook(
                ""
                        + "static void method1() {\n"
                        + "    System.out.println(1);\n"
                        + "}\n"
                        + "\n"
                        + "method1();\n"
                        + "method2();\n"
                        + "\n"
                        + "static void method2() {\n"
                        + "    System.out.println(2);\n"
                        + "}\n"
        );
        se.evaluate();
    }

    public static void testExpression() throws CompileException, InvocationTargetException{
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        evaluator.setDebuggingInformation(true, true, true);
        evaluator.setParameters(new String[]{"param"}, new Class[]{Param.class});
        evaluator.setExpressionType(String.class);
//        evaluator.cook("param.getA()");
//        evaluator.cook("param.toString()");
        evaluator.cook("param.getb()");
        Param param = new Param();
        param.setA("a");
        param.setB("b");
        String resultStr = (String) evaluator.evaluate(new Param[]{param});
        System.out.println(resultStr);
    }

    static class Param {
        public Param(){}
        private String a;
        private String b;

//        public String getA() {
//            return a;
//        }

        public void setA(String a) {
            this.a = a;
        }

        public String getb() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "{a = "+a+", b = "+b+"}";
        }
    }
}
