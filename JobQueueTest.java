package com.engine.scheduler;

import java.util.concurrent.PriorityBlockingQueue;

public class JobQueue {
    private final PriorityBlockingQueue<Job<?>> queue;

    public JobQueue() {
        this.queue = new PriorityBlockingQueue<>();
    }

    public void submit(Job<?> job) {
        queue.offer(job);
    }

    public Job<?> pollNext() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }
}
