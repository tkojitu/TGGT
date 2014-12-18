package org.jitu.tggt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    private static final int REQUEST_ACTION_GET_CONTENT = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_load) {
            return onLoad();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean onLoad() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        try {
            startActivityForResult(intent, REQUEST_ACTION_GET_CONTENT);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_ACTION_GET_CONTENT:
            if (resultCode == RESULT_OK) {
                String path = data.getData().getPath();
                getCrystalView().load(new File(path));
            }
            break;
        default:
            break;
        }
    }

    private CrystalView getCrystalView() {
        return (CrystalView) findViewById(R.id.view);
    }

    public void onClickRedraw(View view) {
        getCrystalView().redraw();
    }
}
