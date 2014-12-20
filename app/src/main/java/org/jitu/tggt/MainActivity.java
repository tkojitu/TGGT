package org.jitu.tggt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements TickListener, PortListener {
    private static final int REQUEST_ACTION_GET_CONTENT = 11;

    private VirtualMachine vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = new VirtualMachine();
        vm.setPortListener(this);
        getCrystalView().setTickListener(this);
    }

    private CrystalView getCrystalView() {
        return (CrystalView) findViewById(R.id.view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_ACTION_GET_CONTENT:
            if (resultCode == RESULT_OK) {
                String path = data.getData().getPath();
                load(new File(path));
            }
            break;
        default:
            break;
        }
    }

    public void load(File file) {
        try {
            vm.load(file);
        } catch (IOException | SyntaxErrorException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickLoad(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        try {
            startActivityForResult(intent, REQUEST_ACTION_GET_CONTENT);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickReload(View view) {
        try {
            vm.reload();
        } catch (IOException | SyntaxErrorException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDraw(View view) {
        vm.run();
    }

    public void onClickSave(View view) {
        try {
            String path = vm.save();
            getTextName().setText(path);
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClickStop(View view) {
        vm.abend();
    }

    @Override
    public void onTick(Canvas canvas) {
        vm.execute(canvas);
    }

    @Override
    public void onPort(String text) {
        getTextName().setText(text);
    }

    private TextView getTextName() {
        return (TextView) findViewById(R.id.text_name);
    }
}
