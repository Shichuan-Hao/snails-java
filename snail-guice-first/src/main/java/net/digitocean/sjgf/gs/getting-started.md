#  

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

上面的 Foo 类可以与任何 Database 对象一起使用，因为 Foo 不知道数据库是如何创建的。例如，您可以创建 Database
实现的测试版本，该版本在测试中使用内存数据库，以使测试密封且快速。

[动机](https://github.com/google/guice/wiki/Motivation)页面更详细地解释了为什么应用程序应该使用依赖项注入模式（the
dependency injection pattern）。

## Guice 核心概念

### @Inject constructor

用 `@Inject` 注释的 Java 类构造函数可以由 Guice 通过称为构造函数注入的过程来调用，在此期间构造函数的参数将由 Guice 创建和提供。

以下是使用构造函数注入的类的示例：

```java
class Greeter {
    private final String message;
    private final int count;

    // Greeter 声明它需要的一个字符串消息和一个整数表示消息被打印的次数
    // @Inject 注释将此构造函数标记为有资格使用Guice.
    @Inject
    Greeter(@Message String message, @Count int count) {
        this.message = message;
        this.count = count;
    }

    void sayHello() {
        for (int i = 0; i < count; i++) {
            System.out.println(message);
        }
    }
}
```
在上面的示例中，Greeter 类有一个构造函数，当应用程序要求 Guice 创建 Greeter 实例时会调用该构造函数，Guice 将创建所需的两个参数，
然后调用构造函数。 Greeter 类的构造函数参数是其依赖项，应用程序使用 Module 告诉 Guice 如何满足这些依赖项。


### Guice modules

应用程序包含声明对其他对象的依赖关系的对象，这些依赖关系形成图表。例如，上面的 Greeter 类有两个依赖项（在其构造函数中声明）：
- 一个 String 对象，表示打印的消息
- 一个 Integer 对象，表示打印消息的次数

Guice 模块允许应用程序指定如何满足这些依赖关系。例如：下面的 DemoModule 配置 Greeter 类的所有必须依赖项：
```java
/**
 * Guice 模块提供消息和计数的绑定
 * {@link Greeter}.
 */
import com.google.inject.Provides;

class DemoModule extends AbstractModule {
  @Provides
  @Count
  static Integer provideCount() {
    return 3;
  }

  @Provides
  @Message
  static String provideMessage() {
    return "hello world";
  }
}
```
DemoModule 使用 `@Provides` 方法来指定依赖项。

在真实的应用程序中，对象的依赖关系图会复杂得多，Guice 通过自动创建所有传递依赖关系，使创建复杂对象变得容易。

### Guice 注入
要引导您的应用程序，您需要创建一个包含一个或多个模块的 Guice Injector。
例如，Web 服务器应用程序可能有一个如下所示的 main 方法：
````java
public final class MyWebServer {
  public void start() {
    //...
  }

  public static void main(String[] args) {
    // Creates an injector that has all the necessary dependencies needed to
    // build a functional server.
    Injector injector = Guice.createInjector(
        new RequestLoggingModule(),
        new RequestHandlerModule(),
        new AuthenticationModule(),
        new DatabaseModule()
        //...
      );
    // Bootstrap the application by creating an instance of the server then
    // start the server to handle incoming requests.
    injector.getInstance(MyWebServer.class)
        .start();
  }
}
````
注入器（injector）内部保存应用程序中描述的依赖关系图。当您请求给定类型的实例时，注入器（injector）会确定要构造哪些对象，解析它们的
依赖关系，并将所有内容连接在一起。要指定如何解析依赖项，请使用[绑定]{https://github.com/google/guice/wiki/Bindings} 配置注入器。

## 一个简单的 Guice 应用程序
以下是一个简单的 Guice 应用程序，其中包含所有必要的部分：
```java
package net.digitocean.sjgf.gs;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import jakarta.inject.Inject;
import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;



/**
 * @author: haoshichuan
 * @date: 2023/7/14 10:31
 */
public class GuiceDemo {

    @Qualifier
    @Retention(RUNTIME)
    @interface Message {};

    @Qualifier
    @Retention(RUNTIME)
    @interface Count {};

    /**
     * Guice 模块提供消息和计数的绑定
     * {@link Greeter}.
     */
    static class DemoModule extends AbstractModule {

        @Provides
        @Count
        static Integer provideCount() {
            return 3;
        }

        @Provides
        @Message
        static String provideMessage() {
            return "hello world!";
        }
    }

    static class Greeter {
        private final String message;
        private final int count;

        // Greeter declares that it needs a string message and an integer
        // representing the number of time the message to be printed.
        // The @Inject annotation marks this constructor as eligible to be used by
        //  Guice.
        @Inject
        Greeter(@Message String message, @Count int count) {
            this.message = message;
            this.count = count;
        }

        void sayHello() {
            for (int i = 0; i < count; i++) {
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
        // Guice.createInjector() 接受一个或多个模块，并返回一个新的 Injector
        // 实例。大多数应用程序只会在其应用程序中调用此方法一次在 main() 方法
        Injector injector = Guice.createInjector(new DemoModule());

        // 现在我们已经有了注入器，就可以构建对象了
        Greeter greeter = injector.getInstance(Greeter.class);

        // 向控制台打印 3 次 “hello world!”。
        greeter.sayHello();
    }
}

```
GuiceDemo 应用程序使用 Guice 构建了一个小型依赖图，该依赖图能够构建 Greeter 类的实例。大型应用程序通常有许多可以构建复杂对象的模块。