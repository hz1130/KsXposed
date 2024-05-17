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

public class HookZouxiu implements IXposedHookLoadPackage {
    private final static String tag = "hza";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed", loadPackageParam.packageName);
        ClassLoader classLoader = loadPackageParam.classLoader;
        //com.md.fashion.beauty.catwalk.battle
        if (loadPackageParam.packageName.equals("com.hypercat.fnf2")) {
            Log.i(tag,loadPackageParam.packageName);
            //(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback)
           /* XposedHelpers.findAndHookMethod("com.applovin.mediation.unity.MaxUnityAdManager", classLoader, "loadInterstitial", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objects = param.args;
                    String arg0 = (String)objects[0];
                    Log.i(tag,"arg0 -> "+arg0);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object result = param.getResult();
                    Log.i(tag,"MaxRewardedInterstitialAd ->"+result);
                }
            });*/


            //String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback
           /* XposedHelpers.findAndHookMethod("com.applovin.mediation.unity.MaxUnityAdManager", classLoader, "retrieveInterstitial", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objects = param.args;
                    String string1 = objects[0].toString();
                    Log.i(tag,"retrieveInterstitial -> String : "+string1);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object result = param.getResult();
                    Log.i(tag," retrieveInterstitial -> result : " + result);
                }
            });*/

            Class<?> UnityPlayer = classLoader.loadClass("com.unity3d.player.UnityPlayer");
            Method unitySendMessage = UnityPlayer.getDeclaredMethod("UnitySendMessage", String.class, String.class, String.class);
            XposedBridge.hookMethod(unitySendMessage, new XC_MethodHook() {
        /*        @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objects = param.args;
                    String s1 = objects[0].toString();
                    String s2 = objects[1].toString();
                    String s3 = objects[2].toString();
                    Log.i(tag,"beforeHookedMethod -> s1 :  "+s1 + " s2 :  "+s2 + " s3 : "+s3 );
                }*/

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Object[] objects = param.args;
                    String s1 = objects[0].toString();
                    String s2 = objects[1].toString();
                    String s3 = objects[2].toString();
//                    Log.e(tag,"afterHookedMethod -> s1 :  "+s1 + " s2 :  "+s2 + " s3 : "+s3 );
                    Log.e(tag,"s1 : " + s1);
                    Log.e(tag,"s2 : " + s2);
                    Log.e(tag,"s3 : " + s3);
                }
            });

            Class<?> AppLovinSdk = XposedHelpers.findClass("com.applovin.sdk.AppLovinSdk", classLoader);
            Class<?> Activity = XposedHelpers.findClass("android.app.Activity", classLoader);
            //String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback
            XposedHelpers.findAndHookConstructor("com.applovin.mediation.ads.MaxInterstitialAd", classLoader, "MaxInterstitialAd", String.class,AppLovinSdk,Activity, new XC_MethodHook() {

     /*           @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objects = param.args;
                    String string1 = objects[0].toString();
                    Log.i(tag,"MaxInterstitialAd -> String : "+string1);
                }*/

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object result = param.getResult();
                    Log.i(tag," MaxInterstitialAd -> result : " + result);
                }
            });

        }
        ;
    }
}
