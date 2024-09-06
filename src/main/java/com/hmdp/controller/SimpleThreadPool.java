package com.hmdp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleThreadPool {

    private final BlockingQueue<Runnable> taskQueue;

    private final List<WorkerThread> threads;

    private final AtomicBoolean isShutdown;


    public SimpleThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingDeque<>();
        this.threads = new ArrayList<>(poolSize);
        this.isShutdown = new AtomicBoolean(false);

        //
        for (int i = 0; i < poolSize; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threads.add(thread);
        }
    }


    private class WorkerThread extends Thread{
        @Override
        public void run() {
            while (!isShutdown.get() || !taskQueue.isEmpty()){
                try {
                    Runnable task =taskQueue.take();
                    task.run();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void submitTask(Runnable task){
        if(!isShutdown.get()){
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            threadPool.submitTask(()->{
                System.out.println("任务："+taskId+"开始执行，由线程："+ Thread.currentThread().getName()+"执行");

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("任务："+taskId+"执行完毕，由线程："+ Thread.currentThread().getName()+"执行");
            });

        }
        String s = "";
        StringBuilder sb = new StringBuilder();
        Map<String,List<String>> map = new HashMap<>();
//        map.put(s,new ArrayList<String>(s));
    }

}
