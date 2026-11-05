package example.Multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumDemo {
    // RecursiveTask returns a result (in this case, a Long sum)
    static class ArraySumTask extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int start;
        private final int end;
        private static final int THRESHOLD = 10; // Static Threshold to stop splitting

        public ArraySumTask(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;

            // Base Case: if the task is small enough, compute sequentially
            if (length <= THRESHOLD) {
                long sum = 0;

                for (int  i = start; i <= end; ++i) {
                    sum += numbers[i];
                }
                return sum;
            }

            int mid = start + length/2;

            ArraySumTask leftTask  = new ArraySumTask(numbers, start, mid);
            ArraySumTask rightTask  = new ArraySumTask(numbers, mid, end);

            // Asynchronously fork the left task into the work stealing mode
            leftTask.fork();

            // Compute the right task directly to the current thread
            Long rightResult = rightTask.compute();

            // Wait for and retrieve the result of the left task (Join)
            Long leftResult = leftTask.join();

            // Combine the results
            return leftResult + rightResult;
        }

        public static void main(String[] args) {
            // Create an array of 100 numbers
            long[] numbers = new long[100];

            for (int i = 0; i < numbers.length; ++i) {
                numbers[i] = i + 1;
            }

            // New ForkJoinPool (defaults to available processor cores)
            try (ForkJoinPool pool = new ForkJoinPool()) {
                ArraySumTask rootTask = new ArraySumTask(numbers, 0, numbers.length);

                // Invoke the root task
                Long totalSum = pool.invoke(rootTask);

                System.out.println("Calculated Total Sum: " + totalSum);
            }
        }
    }
}
