package com.ming.ch24;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.ming.ch24.CompletableFutureDemo.sleep;

public class ApplyToEitherTest {
    Random random = new Random();

    static CompletableFuture<String> f1 =
            CompletableFuture.supplyAsync(() -> {
//                int t = random.nextInt();
                int t = getRandom(5, 10);
                sleep(t, TimeUnit.SECONDS);
                return String.valueOf(t);
            });

    static CompletableFuture<String> f2 =
            CompletableFuture.supplyAsync(() -> {
//                int t = random.nextInt();
                int t = getRandom(5, 10);
                sleep(t, TimeUnit.SECONDS);
                return String.valueOf(t);
            });

    private static int getRandom(int i, int i1) {

        return 1;
    }

    static CompletableFuture<String> f3 =
            f1.applyToEither(f2, s -> s);

    public static void main(String[] args) {

        System.out.println(f3.join());
    }

}
