package com.kanxue.xposed01;

import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookB2b implements IXposedHookLoadPackage {
    private final static String tag = "hza";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed init ", loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.paperang.study")) {
            Log.i(tag,loadPackageParam.packageName);



            ClassLoader classLoader = loadPackageParam.classLoader;
            XposedBridge.log("classLoader -> " + classLoader);

            // Hook 类中走了的所有方法
            Class<?> RewardBasedVideo = classLoader.loadClass("g.k.b.b.a");
            XposedBridge.log("RewardBasedVideo - > " + RewardBasedVideo);
            Class<?> superclass = RewardBasedVideo.getSuperclass();
            XposedBridge.log(" RewardBasedVideo --- superclass - > " + superclass);
            for (final Method m:RewardBasedVideo.getDeclaredMethods()) {
                XposedBridge.hookMethod(m, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
//                        Log.e("hza","name:"+m.getName()+"+++++arg:"+param.args.length);
                        XposedBridge.log("hza ->"+m.getName()+"-------> arg:"+param.args.length);
                    }
                });

            }

            

        }
        ;
    }
}
