package com.example.vinhcrazyyyy.androidbaseproject.views;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

public class CircularRevealAnimationFragment extends BaseFragment {

    private View parentView;

    public CircularRevealAnimationFragment(View parentView) {
        this.parentView = parentView;
    }

    /**
     * To reveal a previously invisible view using this effect
     * */
    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public void enterReveal() {
        //previously invisible view
        View myView = parentView.findViewById(R.id.my_view);

        //get the center for clipping circle
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;

        //get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;


        //create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, finalRadius);

        //make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    /**
     * To hide a previously visible view using this effect
     * */
    public void exitReveal() {
        //previously visible view
        final View myView = parentView.findViewById(R.id.my_view);

        //get the center for the clipping circle
        int cx = myView.getMeasuredWidth() / 2;
        int cy = myView.getMeasuredHeight() / 2;

        //get the initial radius for the clipping circle
        int initialRadius = myView.getWidth() / 2;

        //create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        //make the view invisible when the animation is done
        anim.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                myView.setVisibility(View.INVISIBLE);

                //For View Transition
                //When exiting the activity after reveal transition,
                //you want to finish the activity after completing
                //the exit reveal transition

                //Finish the activity after the exit transition completes
                getActivity().supportFinishAfterTransition();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        //start the animation
        anim.start();
    }

    /**
     * View Transition:
     * You want to call enterReveal() after the enter transition
     * of the activity has been completed
     * */
    final Transition.TransitionListener mEnterTransitionListener;
    @RequiresApi(api = VERSION_CODES.KITKAT)
    private void setupViewTransition() {


        mEnterTransitionListener = new TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @RequiresApi(api = VERSION_CODES.LOLLIPOP)
            @Override
            public void onTransitionEnd(Transition transition) {
                enterReveal();

                //For View Transition
                //Update the enterReveal() method to remove this listener from stop listening
                //to this animation. This is done to disable reveal animation when the activity ends.
                getActivity().getWindow().getEnterTransition().removeListener(mEnterTransitionListener);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };
        getActivity().getWindow().getEnterTransition().addListener(mEnterTransitionListener);
        //Update the enterReveal() method to remove this listener from stop listening
        //to this animation. This is done to disable reveal animation when the activity ends.
    }

    /**
     * To enable exit reveal transition on press of ActionBar up button
     * or back button, call exitReveal() from onOptionItemSelected()
     * and onBackPressed() respectively
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Respond to the action bar's Up/Home button
            case android.R.id.home:
                exitReveal();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        exitReveal();
    }

    @Override
    protected void setup(View view) {

    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }

    /**
     * Common Gotcha
     * If you do the reveal animation directly on the view, it might look weird
     * because the stroke part of the shape gets revealed as well. To prevent this,
     * you can embed the view inside a FrameLayout which has the stroke border
     * as a background. By doing this, the animation reveals the view while the background
     * part remains the same, so it looks like only color part is revealed.
     *
     * {@link drawble/circular_button.xml}
     * */

    /**
     * Enhancements
     * The reveal effect can be made to look more natural if it starts where the user tapped.
     * To do this, you could get the x & y co-ordinates from the MotionEvent
     * and use that to start the reveal effect.
     * */

    private View myView;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            //get the final radius for the clipping circle
            int finalRadius = Math.max(myView.getWidth(), myView.getHeight()) / 2;

            //create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myView, motionEvent.getX(),
                            motionEvent.getY(), 0 , finalRadius);

            //make the view visible and start the animation
            myView.setVisibility(View.VISIBLE);
            anim.start();
        }

        return false;
    }
}
