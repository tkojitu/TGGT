package org.jitu.tggt;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CrystalView extends View {
    Crystal crystal = new Crystal(this);
    VirtualMachine vm = new VirtualMachine();

    public CrystalView(Context context) {
        super(context);
    }

    public CrystalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrystalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void redraw() {
        vm.run();
    }

    public void onDraw(Canvas c) {
        vm.execute(c);
    }

    public void load(File file) {
        try {
            vm.load(file);
        } catch (IOException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (SyntaxErrorException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
