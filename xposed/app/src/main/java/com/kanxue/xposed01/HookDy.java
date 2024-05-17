package com.kanxue.xposed01;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Arrays;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookDy implements IXposedHookLoadPackage {
    private final static String tag = "hza";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i(tag, " init " + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.ss.android.ugc.aweme")) {

            Log.i(tag, loadPackageParam.packageName);
//            Class<?> aClazz = XposedHelpers.findClass("com.kwai.chat.kwailink.adapter.KlinkConfig", loadPackageParam.classLoader);
//            Log.i(tag, "clazz : " + aClazz);
            // 使用 Xposed 替换目标方法的行为

            // 获取目标类
            Class<?> targetClass = XposedHelpers.findClass("org.chromium.CronetClient", loadPackageParam.classLoader);

            // Hook 某个方法修改返回值
            XposedHelpers.findAndHookMethod("org.chromium.CronetClient", loadPackageParam.classLoader, "tryCreateCronetEngine", android.content.Context.class, boolean.class, boolean.class, boolean.class, boolean.class, java.lang.String.class, java.util.concurrent.Executor.class, boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[4] = false;
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });


            // Hook类的所有方法打印返回值和参数
            for (final Method method : targetClass.getDeclaredMethods()) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        // 打印方法名
                        Log.e(tag, "枚举的所有类名: " + method.getName());

                        // 打印参数
                        if (param.args != null && param.args.length > 0) {
                            Log.e(tag, "打印参数Arguments: " + Arrays.toString(param.args));
                        } else {
                            XposedBridge.log("No arguments.");
                        }
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        // 打印返回值
                        Log.e(tag, "打印返回值 Method: " + method.getName() + " returned: " + param.getResult());
                    }
                });
            }

        }
        ;
    }
}
