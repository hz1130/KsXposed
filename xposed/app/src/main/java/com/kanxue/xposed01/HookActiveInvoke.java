package com.kanxue.xposed01;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookActiveInvoke implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("XposedActiveInvoke", loadPackageParam.packageName);
        XposedBridge.log("XposedActiveInvoke->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.kanxue.xposedhook01")) {
            XposedBridge.log("kanxue " + loadPackageParam.packageName);

//            public static String publicstaticfunc(String arg1, int arg2) {
//                String result = privatestaticfunc("privatestaticfunc", 200);
//                Log.i("Xposed","publicstaticfunc is called!");
//                return arg1 + "---" + arg2 + "---" + result;
//            }
//
					//共有静态方法
            ClassLoader classLoader = loadPackageParam.classLoader;
            Class StuClass = classLoader.loadClass("com.kanxue.xposedhook01.Student");
            Method publicstaticfunc_method = StuClass.getDeclaredMethod("publicstaticfunc", String.class, int.class);
            // static修饰不需要传入实例
            publicstaticfunc_method.invoke(null, "InvokedByXposed", 100);


//            public static java.lang.Object callMethod(java.lang.Object obj, java.lang.String methodName, java.lang.Object... args) { /* compiled code */ }
//
//            public static java.lang.Object callMethod(java.lang.Object obj, java.lang.String methodName, java.lang.Class<?>[] parameterTypes, java.lang.Object... args) { /* compiled code */ }
//
//            public static java.lang.Object callStaticMethod(java.lang.Class<?> clazz, java.lang.String methodName, java.lang.Object... args)
//
//            public static java.lang.Object callStaticMethod(java.lang.Class<?> clazz, java.lang.String methodName, java.lang.Class<?>[] parameterTypes, java.lang.Object... args)

            Class<?>[] parameterTypes = {String.class, int.class};

            XposedHelpers.callStaticMethod(StuClass, "publicstaticfunc", parameterTypes, "publicstaticfunc is called by XposedHelpers.callStaticMethod11", 100);

            XposedHelpers.callStaticMethod(StuClass, "publicstaticfunc", "publicstaticfunc is called by XposedHelpers.callStaticMethod22", 200);

            XposedHelpers.callStaticMethod(StuClass, "privatestaticfunc", parameterTypes, "privatestaticfunc is called by XposedHelpers.callStaticMethod11", 400);
            XposedHelpers.callStaticMethod(StuClass, "privatestaticfunc", "privatestaticfunc is called by XposedHelpers.callStaticMethod22", 300);


            Method privatestaticfunc_method = StuClass.getDeclaredMethod("privatestaticfunc", String.class, int.class);

            privatestaticfunc_method.setAccessible(true);

            String result = (String) privatestaticfunc_method.invoke(null, "privatestaticfuncIsInvokedByXposed", 200);
            XposedBridge.log("privatestaticfuncIsInvokedByXposed->result:" + result);
//            private static String privatestaticfunc(String arg1, int arg2) {
//                Log.i("Xposed","privatestaticfunc is called!");
//                return arg1 + "---" + arg2;
//            }
//            public static java.lang.Object callMethod(java.lang.Object obj, java.lang.String methodName, java.lang.Object... args) { /* compiled code */ }
//
//            public static java.lang.Object callMethod(java.lang.Object obj, java.lang.String methodName, java.lang.Class<?>[] parameterTypes, java.lang.Object... args) { /* compiled code */ }


//            public static java.lang.Object newInstance(java.lang.Class<?> clazz, java.lang.Object... args) { /* compiled code */ }
//
//            public static java.lang.Object newInstance(java.lang.Class<?> clazz, java.lang.Class<?>[] parameterTypes, java.lang.Object... args) { /* compiled code */ }


            Object StuObjByXposed = XposedHelpers.newInstance(StuClass, "StuObjByXposed.newInstance", "500");
            String result1 = (String) XposedHelpers.callMethod(StuObjByXposed, "publicfunc", "publicfunc is called by XposedHelpers.callMethod", 125);
            XposedBridge.log("publicfunc XposedHelpers.callMethod result->" + result1);

            XposedHelpers.callMethod(StuObjByXposed, "privatefunc", "privatefunc is called by XposedHelpers.callMethod", 130);


            Method publicfunc_method = StuClass.getDeclaredMethod("publicfunc", String.class, int.class);


//             public Student(String name, String id) {
//                this.name = name;
//                this.id = id;
//
//            }

            // public Student(String name, String id)
            Constructor StuCon = StuClass.getDeclaredConstructor(String.class, String.class);

            Object StuObj = StuCon.newInstance("InstanceByXposed", "300");

            publicfunc_method.invoke(StuObj, "publicfuncInvokedByXposed", 100);


//            public String publicfunc(String arg1, int arg2) {
//                String result = privatefunc("privatefunc", 300);
//                Log.i("Xposed","publicfunc is called!"+"---"+arg1+"---"+arg2);
//                person tmp=new person();
//                String tmpresult=tmp.getpersonname("person");
//                return arg1 + "---" + arg2 + "---" + result+"---"+tmpresult;
//            }
//

            Method privatefunc_method = StuClass.getDeclaredMethod("privatefunc", String.class, int.class);
            privatefunc_method.setAccessible(true);
            String privatefunc_result = (String) privatefunc_method.invoke(StuObj, "privatefuncInvokedByXposed", 200);
            XposedBridge.log("privatefunc activeInvokeByXposed-->" + privatefunc_result);
//            private String privatefunc(String arg1, int arg2) {
//                Log.i("Xposed","privatefunc is called!"+"---"+arg1+"---"+arg2);
//                return arg1 + "---" + arg2;
//            }


            //Student cstudent = new Student("xiaohua", "2020");
            XposedHelpers.findAndHookConstructor(StuClass, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("Student(String,String) is called beforeHookedMethod");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("Student(String,String) is called afterHookedMethod");
                    Object cstudent=param.thisObject;
                    XposedHelpers.callMethod(cstudent,"publicfunc","publicfunc is called XposedHelpers.findAndHookConstructor",666);
                    XposedHelpers.callMethod(cstudent,"privatefunc","privatefunc is called XposedHelpers.findAndHookConstructor",888);
                }
            });


//            public String getNickname() {
//                return nickname;
//            }

            XposedHelpers.findAndHookMethod(StuClass, "getNickname", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Object obj=param.thisObject;
                    XposedHelpers.callMethod(obj,"publicfunc","beforeHookedMethod publicfunc is called XposedHelpers.callMethod",444);
                    XposedHelpers.callMethod(obj,"privatefunc","beforeHookedMethod privatefunc is called XposedHelpers.callMethod",333);

                    XposedBridge.log("getNickname is called beforeHookedMethod->"+obj);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object obj=param.thisObject;
                    XposedHelpers.callMethod(obj,"publicfunc","afterHookedMethod publicfunc is called XposedHelpers.callMethod",222);
                    XposedHelpers.callMethod(obj,"privatefunc","afterHookedMethod privatefunc is called XposedHelpers.callMethod",111);

                    XposedBridge.log("getNickname is called afterHookedMethod->"+param.thisObject);
                }
            });

        }
    }
}
