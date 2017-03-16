package com.space.self.hotfix;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class HotfixManager {

    private Context context;
    public static HotfixManager hotfixManager = new HotfixManager();


    public static HotfixManager getInstance() {
        return hotfixManager;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void hotfix(File file) {
        DexClassLoader classLoader = new DexClassLoader(file.getPath(), Environment.getExternalStorageState(), null, getClass().getClassLoader());
        try {
            DexFile dexfile = DexFile.loadDex(file.getAbsolutePath(), "", 0);
            Enumeration entries = dexfile.entries();
            while (entries.hasMoreElements()) {
                String name = (String) entries.nextElement();
                Class<?> srcFixClass = classLoader.loadClass(name);
                for (Method srcMethod : srcFixClass.getMethods()) {
                    HotFixImp hotFixImp = srcMethod.getAnnotation(HotFixImp.class);
                    if (hotFixImp != null) {
                        String hotFixClassName = hotFixImp.className();
                        String hotFixMethodName = hotFixImp.className();
                        Class<?> destFixClass = Class.forName(hotFixClassName);
                        Method dest = destFixClass.getMethod(hotFixMethodName, srcMethod.getParameterTypes());
                        hotFix(srcMethod, dest);
                        Log.d("hotFix", "src :" + srcFixClass.getName() + "." + srcMethod.getName());
                        Log.d("hotFix", "dst :" + destFixClass.getName() + "." + dest.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public native void hotFix(Method src, Method dest);


}
