package com.kanxue.xposed01;

import android.util.Log;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookConstructors implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.i("Xposed01", loadPackageParam.packageName);
        XposedBridge.log("Xposed01->app packagename" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.equals("com.kanxue.xposedhook01")) {
            XposedBridge.log("kanxue " + loadPackageParam.packageName);


     /*     public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookConstructor(java.lang.Class<?> clazz, java.lang.Object... parameterTypesAndCallback)
            public static de.robv.android.xposed.XC_MethodHook.Unhook findAndHookConstructor(java.lang.String className, java.lang.ClassLoader classLoader, java.lang.Object... parameterTypesAndCallback)
*/
            ClassLoader classLoader = loadPackageParam.classLoader;
            // 可以通过反射机制 拿到我们所需要hook的 包名.类名对象
            Class StudentClass = classLoader.loadClass("com.kanxue.xposedhook01.Student");
            XposedHelpers.findAndHookConstructor(StudentClass, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("com.kanxue.xposedhook01.Student() is called!!beforeHookedMethod");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("com.kanxue.xposedhook01.Student() is called!!afterHookedMethod");
                }
            });

            //    public Student(String name2) {
            //        this.name = name2;
            //        this.id = "default";
            //    }
            //       public java.lang.Object thisObject;
            //        public java.lang.Object[] args;
            //        private java.lang.Object result;
            XposedHelpers.findAndHookConstructor(StudentClass, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    // 获取参数数组
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobjarray = param.args;
                    String name = (String) argsobjarray[0];
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String) is called!!beforeHookedMethod--" + name);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String) is called!!afterHookedMethod");
                }
            });
            //    public Student(String name2, String id2) {
            //        this.name = name2;
            //        this.id = id2;
            //    }

            XposedHelpers.findAndHookConstructor("com.kanxue.xposedhook01.Student", loadPackageParam.classLoader, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobjarray = param.args;
                    String name = (String) argsobjarray[0];
                    String id = (String) argsobjarray[1];
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!afterHookedMethod");
                }
            });


            ClassLoader pathClassLoader= loadPackageParam.classLoader;

            final Class stuClass=pathClassLoader.loadClass("com.kanxue.xposedhook01.Student");
            XposedBridge.log("StudentClass->"+stuClass);

/*            Field teacherField=stuClass.getDeclaredField("teacher");

            //teacherField.setAccessible(true);

            teacherField.set(null,"teacher666");


            String teachername1= (String) teacherField.get(null);

            XposedBridge.log("teacherField->"+teachername1);*/

            //(java.lang.Class<?> clazz, java.lang.String fieldName, java.lang.Object value)


            XposedHelpers.setStaticObjectField(stuClass,"teacher","teacher888");

            String teachername2= (String) XposedHelpers.getStaticObjectField(stuClass,"teacher");


            XposedBridge.log("XposedHelpers.getStaticObjectField->"+teachername2);


            /*XposedHelpers.findAndHookConstructor(StudentClass, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobjarray = param.args;
                    String name = (String) argsobjarray[0];
                    String id = (String) argsobjarray[1];
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!afterHookedMethod");
                }
            });
*/
            //public Student(String name2, String id2, int age2)
            XposedHelpers.findAndHookConstructor(StudentClass, String.class, String.class, int.class,String.class,String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    java.lang.Object[] argsobjarray = param.args;
                    String name = (String) argsobjarray[0];
                    String id = (String) argsobjarray[1];
                    int age = (int) (argsobjarray[2]);
                    argsobjarray[1] = "2050";
                    argsobjarray[2] = 100;

                    String teacher= (String) argsobjarray[3];
                    String nickname= (String) argsobjarray[4];


                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String) is called!!beforeHookedMethod--" + name + "---" + id + "--" + age+"---"+teacher+"---"+nickname);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    // 获取构造函数返回的对象
                    Object thisobj = param.thisObject;


             /*       Field nicknameField=stuClass.getDeclaredField("nickname");
                    XposedBridge.log(stuClass+"--nicknameField->"+nicknameField);
                    nicknameField.setAccessible(true);
                    nicknameField.set(thisobj,"bear");*/

                    XposedHelpers.setObjectField(thisobj,"nickname","chick");




                    Object returnobj = param.getResult();
                    XposedBridge.log(thisobj + "---" + returnobj);
                    XposedBridge.log("com.kanxue.xposedhook01.Student(String,String,int) is called!!afterHookedMethod");
                }
            });

            // 给构造方法的属性赋值
            XposedHelpers.findAndHookConstructor(StudentClass, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    ClassLoader pathClassLoader = loadPackageParam.classLoader;
                    Class stuClass = pathClassLoader.loadClass("com.kanxue.xposedhook01.Student");
                    XposedBridge.log("StudentClass"+stuClass);

                    // 通过java反射给构造方法的属性赋值  private static String teacher = null;
                    Field teacherField = stuClass.getDeclaredField("teacher");
                    // 检查
                    teacherField.setAccessible(true);
                    teacherField.set(null,"teacher666");
                    // Java反射获取
                    String teacherName = (String) teacherField.get(null);
                    XposedBridge.log("teacherFiled ---->"+teacherName);

                    // public static void setStaticObjectField(Class<?> clazz, String fieldName, Object value)  使用 Xposed API 对静态属性进行修改
                    XposedHelpers.setStaticObjectField(stuClass,"teacher","teacher888");
                    //  public static Object getStaticObjectField(Class<?> clazz, String fieldName) 使用 Xposed API 对静态属性进行获取
                    String teacherName2 = (String) XposedHelpers.getStaticObjectField(stuClass,"teacher");
                    XposedBridge.log("teacherFiled ---->"+teacherName2);

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Object thisObject = param.thisObject;

                    // java反射修改
/*                    Field nickNameField = stuClass.getDeclaredField("nickName");
                    nickNameField.setAccessible(true);
                    nickNameField.set(thisObject,"bear");
                    XposedBridge.log(nickNameField);*/

                    // Xposed API 修改
                    XposedHelpers.setObjectField(thisObject,"nickName","bear666");
                    Object result = param.getResult();
                    XposedBridge.log(thisObject+"----"+result);
                    XposedBridge.log("called!!afterHookedMethod");
                }
            });

        }

    }
}
