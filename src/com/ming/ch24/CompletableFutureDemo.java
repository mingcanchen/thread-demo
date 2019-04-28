package com.ming.ch24;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wolter
 */
public class CompletableFutureDemo {

    /**
     * 任务 1：洗水壶 -> 烧开水
     */
    static CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
        System.out.println("T1: 洗水壶...");
        sleep(1, TimeUnit.SECONDS);

        System.out.println("T1: 烧开水...");
        sleep(15, TimeUnit.SECONDS);
    });

    /**
     * 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
     */
    static CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
        System.out.println("T2: 洗茶壶...");
        sleep(1, TimeUnit.SECONDS);

        System.out.println("T2: 洗茶杯...");
        sleep(2, TimeUnit.SECONDS);

        System.out.println("T2: 拿茶叶...");
        sleep(1, TimeUnit.SECONDS);
        return " 龙井 ";
    });

    static CompletableFuture<String> f4 = CompletableFuture.supplyAsync(() -> {
        System.out.println("T3: 洗茶壶...");
        sleep(1, TimeUnit.SECONDS);

        System.out.println("T3: 洗茶杯...");
        sleep(2, TimeUnit.SECONDS);

        System.out.println("T3: 拿茶叶...");
        sleep(10, TimeUnit.SECONDS);
        System.out.println("等我结束,别急啊");
        return " 龙井3 ";
    });

    /**
     * 任务 3：任务 1 和任务 2 完成后执行：泡茶
     */
    static CompletableFuture<String> f3 = f1.thenCombine(f4, (__, tf) -> {
        System.out.println("T1: 拿到茶叶:" + tf);
        System.out.println("T1: 泡茶...");
        return " 上茶:" + tf;
    });


    static CompletableFuture<String> f0 =
            CompletableFuture.supplyAsync(
                    () -> "Hello World")      //①
                    .thenApply(s -> s + " QQ")  //②
                    .thenApply(String::toUpperCase);//③

//System.out.println(f0.join());
//    // 输出结果
//    HELLO WORLD
//    QQ


// 等待任务 3 执行结果
//    System.out.println(f3.join());

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }


// 一次执行结果：
//    T1: 洗水壶...
//    T2: 洗茶壶...
//    T1: 烧开水...
//    T2: 洗茶杯...
//    T2: 拿茶叶...
//    T1: 拿到茶叶: 龙井
//    T1: 泡茶...
//    上茶: 龙井

    public static void main(String[] args) {

//        String join = f3.join();
//        System.out.println(join);
        System.out.println(f0.join());
//        f0.join();
    }
}
