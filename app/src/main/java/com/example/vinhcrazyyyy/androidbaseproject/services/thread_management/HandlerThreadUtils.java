package com.example.vinhcrazyyyy.androidbaseproject.services.thread_management;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/*HandlerThread is handy class for starting a new worker thread
* that "sequentially run tasks". If you need a "single background thread"
* that starts a loop capable of running code or processing messages
* in the order that they arrive, this is a tool for the job.
*
* The HandlerThread is a convenience class class that initiates a Looper
* within a Thread to process Runnable or Message objects.
* Note that a Handler is used to handle the insertion of the Runnable
* or Message objects into the looper's queue*/
public class HandlerThreadUtils {

    Handler mHandler;

    private void createHandlerThread() {
        //Create a new background thread for processing messages
        //or runnable sequentially
        HandlerThread handlerThread = new HandlerThread("HandlerThreadName");
        //Starts the background thread
        handlerThread.start();
        //Create a handler attached to the HandlerThread's Looper
        mHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                //Process received messages here!
            }
        };

        /*Now we can enqueue either a Message to pass data or process a Runnable
        * to execute code. In either case, the enqueued objects is added through
        * the Handler into the Looper's internal MessageQueue for processing*/
    }

    //Executing Runnables on HandlerThread
    //Once the HandlerThread is started, we can execute code on the worker thread
    //through the Handler
    private void executeRunnableOnHandlerThread() {

        //Execute the specified code on the worker thread
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //Do something here;
            }
        });

        /*In the code above, we invoke the "post" method on the handler to enqueue
         * a Runnable to be executed as soon as possible. The Handler class supports
         * several other ways to schedule a Runnable to be processed at a future time:
         *
         * post : Immediately enqueue Runnable to be executed
         * postAtTime : Enqueue Runnable to execute at absolute time specified in millis
         * postDelayed : Enqueue Runnable to execute after the specified delay in millis
         * postAtFrontOfQueue : Immediately enqueue Runnable to the front to be executed
         * */
    }

    /*Processing Messages on HandlerThread
     * Rather than running arbitrary code on the background thread using a Runnable
     * as shown above, Handler can also enqueue messages with bundled information.
     * To send a Message on the worker thread through the Handler*/
    private void processMessagesOnHandlerThread() {

        //Secure a new message to send
        Message message = mHandler.obtainMessage();
        //Create a bundle
        Bundle b = new Bundle();
        b.putString("message", "foo");
        //Attach bundle to the message
        message.setData(b);
        //Send message through the handler
        mHandler.sendMessage(message);
        //or instead send an empty message with
        //mHandler.sendEmptyMessage(0);

        /*In the code above, we invoke the sendMessage() method on the handler
        * to enqueue a Message to be processed as soon as possible.
        * The Handler class supports several other ways to schedule a Message
        * to be processed at a future time:
        *
        * sendMessage : Pushes a message onto the end of the message queue
        * sendMessageDelayed : Pushes a message onto the end of the message queue
        * sendMessageAtTime : Push a message onto the end of the message queue
        * sendEmptyMessage : Sends Message containing only a single int code.
        * sendEmptyMessageDelayed : Sends Message to be delivered after the specified time elapses
        * sendEmptyMessageAtTime : Sends Message to be delivered at the specified absolute time
        * */

        /*The "empty message" still "contains a single int value"
        * http://stackoverflow.com/a/15339106/313399
        * representing the type of message. Empty messages can be used for simple handlers
        * receiving a "refresh" or "notify" type message not requiring additional data.
        * */

        /*Messages vs Runnables ?
        * Often the purpose for a Handler accepting both Runnable and Message comes into question.
        * Keep in mind that "Runnable is simply a Message storing the codeblock"
        * http://stackoverflow.com/a/22533988/313399
        * and that both are contained within the same MessageQueue to be processed.
        * Howver, one advantage of a Message is that a class sending one to a Handler
        * doesn't necessarily need to "know anything about the implementation"
        * http://stackoverflow.com/a/20205625/313399
        * which can enable better encapsulation.
        * */
    }

    private void stopHandlerThread(HandlerThread handlerThread) {
        //The worker thread can be stop immediately with
        handlerThread.quit();
        /*On API >= 18, we should use quitSafely() instead to
        finish processing pending messages before shutting down.*/
    }

    /*HandlerThread Caveats
    * HandlerThread is great for running tasks linearly (sequentially) on a thread
    * and affords the developer control over how and when messages and runnables
    * are processed by exposing access to the underlying Looper and Handler.
    * However, if we need the ability to run tasks concurrently with one another,
    * then we should use a ThreadPoolExecutor which helps us manage thread pool
    * to execute the tasks in parallel.*/
}
