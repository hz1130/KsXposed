package com.kanxue.xposed01;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookFlag implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("HookFlag", loadPackageParam.packageName);
        XposedBridge.log("HookFlag->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.kanxue.xposedflag")) {
            XposedBridge.log("HookFlag " + loadPackageParam.packageName);


            ClassLoader classLoader=loadPackageParam.classLoader;

            Class Flag1Class=classLoader.loadClass("com.kanxue.xposedflag.Flag1");
            XposedHelpers.setStaticIntField(Flag1Class,"length",3);


            XposedHelpers.findAndHookConstructor("com.kanxue.xposedflag.Flag2", classLoader, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("go into com.kanxue.xposedflag.Flag2()->beforeHookedMethod");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("leave com.kanxue.xposedflag.Flag2()->afterHookedMethod");

                    Object Flag2obj=param.thisObject;
                    XposedHelpers.setObjectField(Flag2obj,"flag","123");
                    System.out.println("hza--------"+Flag2obj);
                }
            });

        }
    }
}