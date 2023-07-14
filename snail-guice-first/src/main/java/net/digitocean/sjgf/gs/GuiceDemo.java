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

        // Greeter 声明它需要一个字符串消息和一个整数表示消息被打印的次数。
        // @Inject 注释将此构造函数标记为可供 Guice 使用
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
