package net.digitocean.sjgf;

/**
 * @author: haoshichuan
 * @date: 2023/7/12 10:04
 */
public class FooDI {

    // 我们需要 Database 类做一些工作
    private Database database;

    // database 来自其他地方。在哪里？这不是我需要关心的，那是
    // 使用者需要关心：他们可以选择使用哪个 database。
    FooDI(Database database) {
        this.database = database;
    }
}
