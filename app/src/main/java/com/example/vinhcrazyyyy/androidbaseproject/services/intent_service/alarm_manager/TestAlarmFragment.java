package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.alarm_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class TestAlarmFragment extends BaseFragment {

    @Override
    protected void setup(View view) {
        scheduleAlarm();
    }

    //Setup a recurring alarm every half hour
    private void scheduleAlarm() {
        //Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getContext().getApplicationContext(), MyAlarmReceiver.class);
        //Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(getContext(), MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Setup periodic alarm every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); //alarm is set right away
        AlarmManager alarm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        //First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        //Interval can be INTERVAL_FIFTEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_HALF_HOUR,
                pIntent);
    }
    /*This will cause the alarm to trigger immediately and then fire every half hour
    * from that point onward. Each time the alarm fires, the MyAlarmReceiver broadcast intent
    * is triggered which starts up the IntentService. The PendingIntent.FLAG_UDPATE_CURRENT
    * flag ensures that if the alarm fires very quickly, that the events will replace
    * each other rather than stack up. See the scheduling alarm docs:
    * https://developer.android.com/training/scheduling/alarms.html#set
    * for more examples of different types of scheduling
    * */

    /*After setting the alarm, if we ever want to cancel the alarm, we can do this with:*/
    public void cancelAlarm() {
        Intent intent = new Intent(getContext().getApplicationContext(), MyAlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
