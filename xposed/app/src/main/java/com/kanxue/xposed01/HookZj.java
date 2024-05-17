package com.xiaojianbang.xposed;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class HookZj implements IXposedHookLoadPackage {
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("dajianbang", "Hooking......");
        if (loadPackageParam.packageName.equals("com.shanghaiwindy.PanzerWarOpenSource.meta")) {
            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", new Object[]{String.class, new XC_MethodHook() {
                /* access modifiers changed from: protected */
                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Class clazz = (Class) param.getResult();
                    String clazzName = clazz.getName();
                    StringBuilder sb = new StringBuilder();
                    sb.append("LoadClass: ");
                    sb.append(clazzName);
                    Log.d("dajianbang", sb.toString());
                    if (clazzName.contains("com")) {
                        Method[] mds = clazz.getDeclaredMethods();
                        for (int i = 0; i < mds.length; i++) {
                            final Method md = mds[i];
                            int mod = mds[i].getModifiers();
                            if (!Modifier.isAbstract(mod) && !Modifier.isNative(mod) && !Modifier.isInterface(mod)) {
                                XposedBridge.hookMethod(mds[i], new XC_MethodHook() {
                                    /* access modifiers changed from: protected */
                                    public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        if (md.getName().contains("complexParameterFunc")) {
                                            for (Object obj : param.args) {
                                                Log.d("dajianbang", obj.getClass().getName());
                                            }
                                        }
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Hook Method: ");
                                        sb.append(md.toString());
                                        Log.d("dajianbang", sb.toString());
                                    }
                                });
                            }
                        }
                    }
                }
            }});
        }
    }
}