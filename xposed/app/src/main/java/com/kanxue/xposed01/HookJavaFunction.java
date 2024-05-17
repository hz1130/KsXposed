package com.kanxue.xposed01;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookJavaFunction implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("HookJava->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.kanxue.xposedhook01")) {
            XposedBridge.log("kanxue " + loadPackageParam.packageName);
            /* public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookMethod(java.lang.Class<?> clazz, java.lang.String methodName, java.lang.Object... parameterTypesAndCallback) { *//* compiled code *//* }

            public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookMethod(java.lang.String className, java.lang.ClassLoader classLoader, java.lang.String methodName, java.lang.Object... parameterTypesAndCallback) { *//* compiled code *//* }
             */
            ClassLoader classLoader = loadPackageParam.classLoader;

            XposedBridge.log("loadPackageParam.classLoader->" + classLoader);



            Class StuClass = classLoader.loadClass("com.kanxue.xposedhook01.Student");
            //  public static Class<?> findClass(String className, ClassLoader classLoader)
            Class StuClassByXposedApi = XposedHelpers.findClass("com.kanxue.xposedhook01.Student",classLoader);


//            public static String publicstaticfunc(String arg1, int arg2) {
//                String result = privatestaticfunc("privatestaticfunc", 200);
//                return arg1 + "---" + arg2 + "---" + result;
//            }
//
            // 共有静态方法
            XposedHelpers.findAndHookMethod(StuClass, "publicstaticfunc", String.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objectarray=param.args;
                    String arg0= (String) objectarray[0];
                    int arg1= (int) objectarray[1];
                    objectarray[0]="changedbyxposedjava";
                    objectarray[1]=888;
                    XposedBridge.log("beforeHookedMethod publicstaticfunc->arg0:"+arg0+"---arg1:"+arg1);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    String result= (String) param.getResult();
                    param.setResult("changedbyxposed->afterHookedMethod");
                    XposedBridge.log("afterHookedMethod publicstaticfunc->result:"+result);

                }
            });

            // 私有静态方法
            XposedHelpers.findAndHookMethod(StuClass, "privatestaticfunc", String.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objectarray = param.args;
                    String arg0 = (String) objectarray[0];
                    int arg1 = (int) objectarray[1];
                    objectarray[0] = "changedbyxposedjava";
                    objectarray[1] = 888;
                    XposedBridge.log("beforeHookedMethod privatestaticfunc->arg0:" + arg0 + "---arg1:" + arg1);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    String result = (String) param.getResult();
                    param.setResult("changedbyxposed->afterHookedMethod");
                    XposedBridge.log("afterHookedMethod privatestaticfunc->result:" + result);

                }
            });
//            private static String privatestaticfunc(String arg1, int arg2) {
//                return arg1 + "---" + arg2;
//            }
//
            // 共有方法
            XposedHelpers.findAndHookMethod(StuClass, "publicfunc", String.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objectarray = param.args;
                    String arg0 = (String) objectarray[0];
                    int arg1 = (int) objectarray[1];
                    objectarray[0] = "changedbyxposedjava";
                    objectarray[1] = 888;
                    XposedBridge.log("beforeHookedMethod publicfunc->arg0:" + arg0 + "---arg1:" + arg1);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    String result = (String) param.getResult();
                    param.setResult("changedbyxposed->afterHookedMethod");
                    XposedBridge.log("afterHookedMethod publicfunc->result:" + result);

                }
            });
//            public String publicfunc(String arg1, int arg2) {
//                String result = privatefunc("privatefunc", 300);
//                return arg1 + "---" + arg2 + "---" + result;
//            }
//
///*            XposedHelpers.findAndHookMethod(StuClass, "privatefunc", String.class, int.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    Object[] objectarray = param.args;
//                    String arg0 = (String) objectarray[0];
//                    int arg1 = (int) objectarray[1];
//                    XposedBridge.log("beforeHookedMethod privatefunc->arg0:" + arg0 + "---arg1:" + arg1);
//
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//
//                    String result = (String) param.getResult();
//                    XposedBridge.log("afterHookedMethod privatefunc->result:" + result);
//
//                }
//            });*/
//            private String privatefunc(String arg1, int arg2) {
//                return arg1 + "---" + arg2;
//            }


            // 私有方法
            XposedHelpers.findAndHookMethod("com.kanxue.xposedhook01.Student", loadPackageParam.classLoader, "privatefunc", String.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object[] objectarray = param.args;
                    String arg0 = (String) objectarray[0];
                    int arg1 = (int) objectarray[1];
                    XposedBridge.log("beforeHookedMethod11 privatefunc->arg0:" + arg0 + "---arg1:" + arg1);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    String result = (String) param.getResult();
                    XposedBridge.log("afterHookedMethod11 privatefunc->result:" + result);

                }
            });

            // hook 内部类  public static Class<?> findClass(String className, ClassLoader classLoader)
            Class personClass = XposedHelpers.findClass("com.kanxue.xposedhook01.Student$person", loadPackageParam.classLoader);
            // 需要hook 的方法 填入对应的参数
            XposedHelpers.findAndHookMethod(personClass, "getpersonname", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("beforeHookedMethod getpersonname->" + param.args[0]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("afterHookedMethod getpersonname->" + param.getResult());
                }
            });
          //    public static java.lang.Class<?> findClass(java.lang.String className, java.lang.ClassLoader classLoader)
            //Class StuClassByXposed=XposedHelpers.findClass("com.kanxue.xposedhook01.Student",classLoader);




        }
    }
}
