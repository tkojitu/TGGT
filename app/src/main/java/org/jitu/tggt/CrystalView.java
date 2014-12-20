package org.jitu.tggt;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class CrystalView extends View {
    private Crystal crystal = new Crystal(this);
    private TickListener listener;

    public CrystalView(Context context) {
        super(context);
    }

    public CrystalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrystalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTickListener(TickListener arg) {
        listener = arg;
    }

    public void onDraw(Canvas canvas) {
        if (listener != null) {
            listener.onTick(canvas);
        }
    }
}
