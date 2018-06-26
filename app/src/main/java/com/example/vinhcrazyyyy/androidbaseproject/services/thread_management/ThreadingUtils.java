package com.example.vinhcrazyyyy.androidbaseproject.services.thread_management;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

public class ThreadingUtils {
    /*All threading management options within Android including AsyncTask, HandlerThread
    * and ThreadPoolExecutor are all built on several foundation classes
    * that power threading within Android OS:
    *
    * Runnable : Represents code that can be executed on any Thread
    * Thread : Concurrent unit of execution which runs code specified in a Runnable
    * Message : Represents data that can be sent or received through a Handler
    * Handler : Processes Runnable or Message objects on a Thread
    * Looper : Loop the processes and sends Runnable or Message objects to a Handler
    * MessageQueue : Stores the list of Runnable or Message objects dispatched by the Looper*/

    /*Note that often these objects are primarily used within the context of
    * higher-order of abstractions such as AsyncTask, HandlerThread and ThreadPoolExecutor.
    * A brief overview of these underlying concepts can be found below.
    * For a more detailed description of these basic building blocks, check out
    * this excellent post on the subject
    * http://codetheory.in/android-handlers-runnables-loopers-messagequeue-handlerthread/
    * */

    /*Runnable
    * A Runnable represents code that can be executed on a thread usually scheduled
    * through a Handler. Runnables is an abstract class that has a run() method to
    * implement*/
    private Runnable createRunnable() {
        Runnable taskToRun = new Runnable() {
            @Override
            public void run() {
                //The code to execute when the runnable is processed by a thread
            }
        };
        return taskToRun;

        /*Refer to this guide on defining runnables for additional context
        * https://developer.android.com/training/multiple-threads/define-runnable.html*/
    }

    /*Thread
    * A Thread is a concurrent unit of execution which runs code specified in a Runnable.
    * The Runnable defined above taskToRun can be executed using a Thread*/
    private void startNewThread() {
        Runnable taskToRun = createRunnable();

        //Start a new thread to execute the runnable codeblock
        Thread thread = new Thread(taskToRun);
        thread.start();
        /*See Thread docs for more details on configuring the priority or other behaviour
        * https://developer.android.com/reference/java/lang/Thread.html*/
    }

    /*Handler and Loopers
    * A Handler manages the sending and processing of Message (data)
    * or Runnalbe (code) objects to a Looper which is continuously enqueuing
    * and processing incoming messages. As the Looper is dequeuing messages,
    * the Handler also executes the messages or runnables as they get dispatched.
    * Note that a Handler "requires a Looper to function". Generally,
    * the following sequence occurs:
    * 1. Handler enqueues a Message or Runnable object onto the MessageQueue
    * 2. Looper dequeues Messages off the MessageQueue in sequential order
    * 3. Looper dispatches the Message or Runnable to the Handler to be processed*/

    /*Note that the "UI thread" that is the main thread within the app is
    * a singleton Looper processing all incoming view-related events.
    * The UI Looper can be accessed anytime with Looper.getMainLooper().
    * A Handler can therefore also be used to post code to be run
    * on the main thread from any other threads running*/
    private void runCodeOnTheUIThread() {
        /*Create a handler attached to the UI Looper*/
        Handler handler = new Handler(Looper.getMainLooper());
        //Post code to run on the main UI Thread (usually invoked from worker thread)
        handler.post(new Runnable() {
            @Override
            public void run() {
                //UI code goes here
            }
        });

        /*See this post by Kayshik Gopal for better code samples
        * http://blog.kaush.co/2014/06/20/primer-on-threading-and-handlers-in-android/
        * */

        /*Since this pattern of accessing UI thread's handler is so common
        * within an Activity, the Activity.runOnUiThread(Runnable action) method
        * simplifies the above code even further*/
        Activity activity = new Activity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //UI code goes here
            }
        });

        /*Note that the Handler supports additional "scheduling" commands
        to execute runnable code blocks after a short delay or
        at a specified future time. A Handler can also invoke itself recursively
        to "repeat periodic tasks" (i.e. polling for new updates) within an app*/
    }
}
