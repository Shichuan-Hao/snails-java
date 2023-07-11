# Java 8 Lambdas: Functional Programming for the Masses

> Lambda 表达式加入 Java，不只是为了提高开发人员的生产效率，也是业界对这一特性有根本性的需求

- [代码示例](https:/github.com/RichardWarburton/java-8-lambdas-exercises)

## 什么是函数式编程

每个人对<b>函数式编程</b>的理解不尽相同，其核心是：在思考问题时，使用不可变值和函数，函数对一个值进行处理，映射成另一个值。

不同语言社区总是对各自语言中的特性孤芳自赏，这根本不重要，只需关系如何写出好代码，而不是符合函数式编程风格的代码

示例：
- Artist 创作音乐的个人或团体
  - name: 艺术家的名字（例如：“甲壳虫乐队”）
  - members: 乐队成员（例如：“约翰·列侬”），该字段可为空
  - origin：乐队来自哪里（例如：“利武浦”）
- Track 专辑中的一直曲目
  - name：曲目名称（例如《黄色潜水艇》）
- Album 专辑，由若干曲目组成
  - name：专辑名（例如《左轮手枪》）
  - tracks：专辑上所有曲目的列表
  - musicians：参与创作本专辑的艺术家列表

<mark>本文对如何在正常的业务领域或 Java 应用中使用函数式编程技术。这些例子并不完美，但它和真实的业务领域应用比起来足够简单，
很多代码都是基于这个示例。</mark>


## Lambda 表达式
<font color=red size=3 face="黑体">Java 8 最大的变化就是引入了 Lambda —— 一种紧凑的、传递行为的方式。</font>


### 匿名函数与 Lambda

### Lambda 的形式

### Lambda 表达式本身的类型：函数接口

### 类型推断


### 总结
- Lambda 表达式是一个匿名方法，将行为像数据一样进行传递
- Lambda 表达式的常见结构：`BinaryOperator<Integer> add = (x, y) -> x + y`
- 函数接口指的是仅具有单个抽象方法的接口，用来表示 Lambda 表达式的类型
