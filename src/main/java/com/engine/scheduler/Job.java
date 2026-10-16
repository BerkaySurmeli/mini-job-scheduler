package com.engine.scheduler;

import java.util.concurrent.Callable;

public class Job<T> implements Comparable<Job<T>> {
    private final String id;
    private final int priority; // Higher number = higher priority
    private final Callable<T> task;
    private JobStatus status;

    public Job(String id, int priority, Callable<T> task) {
        this.id = id;
        this.priority = priority;
        this.task = task;
        this.status = JobStatus.PENDING;
    }

    public String getId() { return id; }
    public int getPriority() { return priority; }
    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }
    
    public T execute() throws Exception {
        this.status = JobStatus.RUNNING;
        try {
            T result = task.call();
            this.status = JobStatus.COMPLETED;
            return result;
        } catch (Exception e) {
            this.status = JobStatus.FAILED;
            throw e;
        }
    }

    @Override
    public int compareTo(Job<T> other) {
        return Integer.compare(other.priority, this.priority); // Descending order
    }
}
