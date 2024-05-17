/*
package com.kanxue.xposed01;

import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookApp implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.wg.test.meta")) {
            XposedBridge.log("kanxue -> AppPackage " + loadPackageParam.packageName);

            ClassLoader classLoader = loadPackageParam.classLoader;
            XposedBridge.log("classLoader -> " + classLoader);

            Class<?> GoogleMediationAdapter = classLoader.loadClass("com.applovin.mediation.adapters.GoogleMediationAdapter");
            XposedBridge.log("GoogleMediationAdapter - > " + GoogleMediationAdapter);
            Class<?> superclass = GoogleMediationAdapter.getSuperclass();
            XposedBridge.log(" GoogleMediationAdapterClazz --- superclass - > " + superclass);
            for (Method m:GoogleMediationAdapter.getDeclaredMethods()) {
                XposedBridge.hookMethod(m, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
//                        Log.e("hza","name:"+m.getName()+"+++++arg:"+param.args.length);
                        XposedBridge.log("hza ->"+m.getName()+"-------> arg:"+param.args.length);
                    }
                });

            }

            Class<?> MaxNativeAdClazz = XposedHelpers.findClass("com.applovin.mediation.nativeAds.MaxNativeAd", classLoader);
            XposedBridge.log("MaxNativeAdClazz - > " + MaxNativeAdClazz);




            // public static Unhook findAndHookMethod(Class<?> clazz, String methodName, Object... parameterTypesAndCallback)

        }
        ;
    }
}
*/
