package com.space.self.hotfix.mian;

import android.content.Context;
import android.widget.Toast;

import com.space.self.hotfix.HotFixImp;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class Other {

    @HotFixImp(methodName = "other", className = "com.space.self.hotfix.Other")
    public void other(Context context) {
        Toast.makeText(context, "10", Toast.LENGTH_LONG).show();
    }
}
