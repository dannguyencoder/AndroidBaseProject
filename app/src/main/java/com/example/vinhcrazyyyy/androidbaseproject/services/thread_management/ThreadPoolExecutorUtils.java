package com.example.vinhcrazyyyy.androidbaseproject.services.thread_management;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*Using a ThreadPoolExecutor
* ThreadPoolExecutor is a great way to "execute parallelized tasks across multiple
* different threads" within a limited thread pool. If you want to execute work
* concurrently and retain control over how the work is executed, this is the
* tool for the job*/
public class ThreadPoolExecutorUtils {

    ThreadPoolExecutor executor;

    /*Constructing  the ThreadPoolExecutor*/
    private void createAThreadPoolExecutor() {
        //Determine the number of cores on the device
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        //Contruct thread pool passing in configuration options
        //int minPoolSize, int maxPoolSize, longKeepAliveTime, TimeUnit unit,
        //BlockingQueue<Runnable> workQueue
        executor = new ThreadPoolExecutor(
                NUMBER_OF_CORES*2,
                NUMBER_OF_CORES*2,
                60L,
                    TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
        /*If you are initializing ThreadPoolExecutor within a service, make sure
        * to create it within onStartCommand(). Putting it in onCreate() will
        * likely trigger RequestRejectedException errors.*/

        executeRunnablesOnThreadPoolExecutor(executor);
    }

    /*Executing Runnables on ThreadPoolExecutor
    * Next we can queue up a Runnable code block to execute on a thread in the pool with*/
    private void executeRunnablesOnThreadPoolExecutor(ThreadPoolExecutor executor) {
        //Execute a task on a thread in the thread pool
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Do some long running operation in background
                //on a worker thread in the thread pool!
            }
        });
        /*Threads are used to process each runnable concurrently as the message is received
        * util all threads are busy. If all threads are currently busy, the Executor
        * will queue a new task until a thread becomes available
        *
        * Note that the ThreadPoolExecutor is incredibly  flexible and affords the
         * developer significant control over all aspects of how tasks are executed.
         * For a more comprehensive overview of ThreadPoolExecutor and all
         * underlying components, check out this excellent tutorial by codetheory:
         * http://codetheory.in/android-java-executor-framework/*/
    }

    /*Stopping the ThreadPoolExecutor
    * The thread pool can be shutdown any time with the "shutdown" command*/
    private void stopThreadPoolExecutor() {
        executor.shutdown();
        /*This will shutdown the executor safely once all runnables have been processed.
         * To shutdown the executor immediately, instead use executor.shutdownNow() */
    }
}
