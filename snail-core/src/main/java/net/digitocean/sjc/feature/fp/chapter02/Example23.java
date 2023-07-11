package net.digitocean.sjc.feature.fp.chapter02;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * 编写 Lambda 表达式的不同形式
 * @author: haoshichuan
 * @date: 2023/7/11 10:53
 */
public class Example23 {


    void differentFormsOfLambdaExpressions() {
        // 第一种，不包含参数，使用空括号 () 表示没有参数
        // 这种 Lambda 表达式实现了 Runnable 接口，该接口只有一个 run 方法，没有参数，且返回类型为 void
        Runnable noArguments = () -> System.out.println("Hello World");


        // 第二种，包含且只包含一个参数，可省略参数的括号
        ActionListener buttonClicked = event -> System.out.println("button clicked");

        // 第三种，Lambda 表达式主体包含多行语句（代码块），使用大括号 {} 将代码块括起来
        // 该代码块和普通方法遵循的规则别无二致，可以用返回或抛出异常来推出
        // 只有一行代码的 lambda 表达式也可以用大括号，用来明确 Lambda 表达式从何处开始，到哪里结束
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };

        // 第四种：包含多个参数的方法的 Lambda 表达式
        // 此时，有必要思考怎么阅读该 Lambda 表达式
        // 这行代码不是两个数字相加，而是创建了一个函数，用来计算两个数字相加的结果
        // 变量 add 的类型是 BinaryOperator<Long>，它不是两个数字的和，而是将两个数字相加的那行代码
        BinaryOperator<Long> add = (x, y) -> x + y;

        // 第五种，前几种 Lambda 表达式种的参数类型都是由编译器推断得出
        // 有时最好也可以显示声明参数类型，此时就需要使用小括号将参数括起来
        // 多个参数的情况也是如此
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }
}
