package com.engine.scheduler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobQueueTest {

    @Test
    void testPriorityOrdering() {
        JobQueue queue = new JobQueue();
        
        Job<String> lowJob = new Job<>("low", 1, () -> "low");
        Job<String> highJob = new Job<>("high", 10, () -> "high");
        
        queue.submit(lowJob);
        queue.submit(highJob);
        
        // High priority should come out first
        Job<?> next = queue.pollNext();
        assertEquals("high", next.getId());
    }

    @Test
    void testJobExecution() throws Exception {
        Job<Integer> job = new Job<>("calc", 5, () -> 2 + 2);
        assertEquals(JobStatus.PENDING, job.getStatus());
        
        Integer result = job.execute();
        assertEquals(4, result);
        assertEquals(JobStatus.COMPLETED, job.getStatus());
    }
}
