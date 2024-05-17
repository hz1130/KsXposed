package com.kanxue.xposed01;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookHza implements IXposedHookLoadPackage {
    private final static String tag = "hza";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i(tag, " init " + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.smile.gifmaker")) {

            Log.i(tag, loadPackageParam.packageName);
            Class<?> aClazz = XposedHelpers.findClass("com.kwai.chat.kwailink.adapter.KlinkConfig", loadPackageParam.classLoader);
            Log.i(tag, "clazz : " + aClazz);
            // 使用 Xposed 替换目标方法的行为
            XposedHelpers.findAndHookMethod("com.kwai.chat.kwailink.adapter.KlinkConfig", loadPackageParam.classLoader, "isQuicBlockEnabled", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    Log.i(tag, "isQuicBlockEnabled info");
                    return false;
                }
            });


            XposedHelpers.findAndHookMethod("com.kwai.video.player.kwai_player.KwaiPlayerBaseBuilder", loadPackageParam.classLoader, "setEnableQuickStart", boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[0] = false;
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod("com.kuaishou.aegon.Aegon", loadPackageParam.classLoader, "nativeUpdateConfig", java.lang.String.class, java.lang.String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.e(tag, "args[0] ====== " + param.args[0]);
                    Log.e(tag, "args[1] ====== " + param.args[1]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });


            // 使用 Xposed 替换目标方法的行为
            XposedHelpers.findAndHookMethod("目标类的完整路径", loadPackageParam.classLoader, "d", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    // 获取原始返回值
                    String originalResult = (String) param.getResult();
                    // 打印原始返回值
                    Log.i(tag, "Original result: " + originalResult);
                    // 将原始返回值返回
                    param.setResult(originalResult);
                }
            });


//            Class<?> aClazz = XposedHelpers.findClass("com.hfdcxy.android.by.test.a", loadPackageParam.classLoader);

            //(Class<?> clazz, String methodName, Object... parameterTypesAndCallback)
         /*   XposedHelpers.findAndHookMethod(aClazz, "a", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.e(tag,"String : " + param.args[0]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.e(tag,"Result : " + param.getResult().toString().substring(0,6));
                }
            });


            //String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback
            XposedHelpers.findAndHookMethod("com.hfdcxy.android.by.test.b", loadPackageParam.classLoader, "a", SharedPreferences.class, TextView.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.e(tag,"第三个参数为初始为 ： " + param.args[2]);
                    param.args[2] = 99999;

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.e(tag,"修改后的第三个参数为 ： " + param.args[2]);
                }
            });*/


        }
        ;
    }
}
