# google guice

Guice 是一个可以让您的应用程序更轻松地使用依赖项注入 (DI) 的框架。 
本文总结如何使用 Guice 将依赖项注入合并到您的应用程序中。

## 什么是依赖注入？

[依赖注入（Dependency injection）]{https://en.wikipedia.org/wiki/Dependency_injection}

依赖注入是一种设计模式。其中，类将其依赖项声明为参数，而不是直接创建这些依赖项。

例如，希望调用服务的客户端不必知道如何构造服务，而是由一些外部代码负责向客户端提供服务。

下面是一个不使用依赖注入的简单代码示例：
```java
class Foo {
    // 我们需要 Database 类做一些工作
    private Database database;

    FooDI(Database database) {
        // 啊。我该如何测试这个？如果我想使用不同的怎么办
        // database 在另一个应用程序中？
        this.database = database;
    }
}
```
上面的 *Foo 类*直接创建了一个*固定的 Database 对象*。这可以防止此类与 *Database 对象*一起使用，并且
不允许在测试中将*真实 database* 替换为*测试 database* 。 

我们可以使用依赖项注入模式来解决所有这些问题，而不是编写不可测试或不灵活的代码。

下面相同的示例，不过这次使用依赖注入：
```java
public class Foo {
    // 我们需要 Database 类做一些工作
    private Database database;

    // database 来自其他地方。在哪里？这不是我需要关心的，那是
    // 使用者需要关心：他们可以选择使用哪个 database。
    Foo(Database database) {
        this.database = database;
    }
}
```

上面的 Foo 类可以与任何 Database 对象一起使用，因为 Foo 不知道数据库是如何创建的。例如，您可以创建 Database 实现的测试版本，该版本在测试中使用内存数据库，以使测试密封且快速。


[动机](https://github.com/google/guice/wiki/Motivation)页面更详细地解释了为什么应用程序应该使用依赖项注入模式（the dependency injection pattern）。

## Guice 核心概念

### @Inject constructor


