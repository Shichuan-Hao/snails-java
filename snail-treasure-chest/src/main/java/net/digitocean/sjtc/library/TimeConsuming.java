package net.digitocean.sjtc.library;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author: haoshichuan
 * @date: 2023/7/12 13:41
 */
public class TimeConsuming {
    public static void jdkWasteTime() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.printf("耗时： %d ms.", (end - start));
    }

    public static void commons_lang3_StopWatch() throws InterruptedException {
        // new 关键字
        StopWatch stopWatch1 = new StopWatch();
        // create 工厂方法
        Thread.sleep(1000);
        long time = stopWatch1.getTime();
        System.out.printf("the result of getTime() : %d ms", time);
    }


    public static void main(String[] args) throws InterruptedException {
        //jdkWasteTime();
        commons_lang3_StopWatch();
    }
}
