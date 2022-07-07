package com.fushin.easyrpc.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 丁成文
 * @date 2022/7/7
 */
public class CompletableFutureTest {
    @Test
    void test01()throws Exception{
//        CompletableFuture f = CompletableFuture.runAsync(()->{
//            System.out.println("开始创建哦");
//        });
//        f.thenApply(item->1);
//        System.out.println(f.get());
//        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(()->{
//            try{
//                Thread.sleep(10000);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return 1;
//        });
        CompletableFuture<Integer> f = new CompletableFuture<>();
        new Thread(()->{
            try{
                Thread.sleep(1000);
                f.complete(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        System.out.println(f.get());
//        System.in.read();
    }
}
