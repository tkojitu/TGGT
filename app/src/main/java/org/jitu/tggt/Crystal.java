package org.jitu.tggt;

import android.animation.ValueAnimator;
import android.view.View;

public class Crystal implements ValueAnimator.AnimatorUpdateListener {
    private View view;
    private ValueAnimator animator;

    public Crystal(View view) {
        this.view = view;
        createAnimator();
    }

    private void createAnimator() {
        animator = ValueAnimator.ofInt(0);
        animator.addUpdateListener(this);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        ValueAnimator.setFrameDelay(17);
        animator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator anim) {
        view.invalidate();
    }

    public void cancel() {
        animator.cancel();
    }
}
