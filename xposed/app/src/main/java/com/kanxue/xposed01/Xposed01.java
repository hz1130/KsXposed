package com.kanxue.xposed01;

import android.content.pm.ApplicationInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Xposed01 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.example.test")) {
            XposedBridge.log("kanxue " + loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class,
                    "getDeviceId",
                    new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return XposedBridge.invokeOriginalMethod(param.method,param.thisObject,param.args);
                    //return "this is imei";
                }
            });
            XposedHelpers.findAndHookMethod(
                    TelephonyManager.class,
                    "getSubscriberId",
                    new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return "this is imsi";
                }
            });

        }
        ;
    }
}
