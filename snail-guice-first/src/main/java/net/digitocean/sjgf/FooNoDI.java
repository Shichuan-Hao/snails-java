package net.digitocean.sjgf;

/**
 * @author: haoshichuan
 * @date: 2023/7/12 10:04
 */
public class FooNoDI {
    // 我们需要 Database 类做一些工作
    private Database database;

    FooNoDI() {
        // 啊。我该如何测试这个？如果我想使用不同的怎么办
        // database 在另一个应用程序中？
        this.database = new Database("/path/to/my/data");
    }
}
