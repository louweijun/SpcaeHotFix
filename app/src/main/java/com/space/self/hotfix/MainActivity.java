package com.space.self.hotfix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        Button tv = (Button) findViewById(R.id.btn_txt);
        HotfixManager.getInstance().init(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Other other = new Other();
                other.other(MainActivity.this);
            }
        });
        Button fix = (Button) findViewById(R.id.btn_fix);
        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotfixManager.getInstance().hotfix(new File(Environment.getExternalStorageState() + "out.dex"));
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
