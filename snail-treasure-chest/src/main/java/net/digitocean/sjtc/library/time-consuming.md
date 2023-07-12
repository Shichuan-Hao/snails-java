# 别再用 System.currentTimeMillis() 统计耗时了，太 Low，StopWatch 好用到爆！

## 背景
你还在用 System.currentTimeMillis… 统计耗时？

比如下面这段代码：
```java
@Test
public void jdkWasteTime() throws InterruptedException {
    long start = System.currentTimeMillis();
    Thread.sleep(3000);
    System.out.printf("耗时：%dms.", System.currentTimeMillis() - start);
}
```
System.currentTimeMillis…这种方式统计耗时确实是用的最多的，因为它不用引入其他的 JAR 包，JDK 就能搞定，但是它用起来有几个不方便的地方： 
1. 需要定义初始时间值，再用当前时间进行手工计算； 
2. 统计多个任务的耗时比较麻烦，如果 start 赋值搞错可能还会出现逻辑问题；

有没有其他的更好的替代方案呢？ 答案是肯定的：StopWatch！

## StopWatch
--------------------------------------------------
版权声明：未经授权，禁止私自复制、盗取、采集、转载到其他平台。
本文链接：https://www.javastack.cn/stopwatch-waste-time-statistics/
--------------------------------------------------
