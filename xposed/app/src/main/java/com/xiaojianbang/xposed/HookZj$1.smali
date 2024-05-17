.class Lcom/xiaojianbang/xposed/HookZj$1;
.super Lde/robv/android/xposed/XC_MethodHook;
.source "HookZj.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/xiaojianbang/xposed/HookZj;->handleLoadPackage(Lde/robv/android/xposed/callbacks/XC_LoadPackage$LoadPackageParam;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/xiaojianbang/xposed/HookZj;


# direct methods
.method constructor <init>(Lcom/xiaojianbang/xposed/HookZj;)V
    .registers 2
    .param p1, "this$0"    # Lcom/xiaojianbang/xposed/HookZj;

    .prologue
    .line 17
    iput-object p1, p0, Lcom/xiaojianbang/xposed/HookZj$1;->this$0:Lcom/xiaojianbang/xposed/HookZj;

    invoke-direct {p0}, Lde/robv/android/xposed/XC_MethodHook;-><init>()V

    return-void
.end method


# virtual methods
.method public afterHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V
    .registers 11
    .param p1, "param"    # Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 20
    invoke-virtual {p1}, Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;->getResult()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Class;

    .line 21
    .local v0, "clazz":Ljava/lang/Class;
    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    .line 22
    .local v1, "clazzName":Ljava/lang/String;
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    .line 23
    .local v6, "sb":Ljava/lang/StringBuilder;
    const-string v7, "LoadClass: "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 24
    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 25
    const-string v7, "dajianbang"

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v7, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 26
    const-string v7, "com"

    invoke-virtual {v1, v7}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_57

    .line 27
    invoke-virtual {v0}, Ljava/lang/Class;->getDeclaredMethods()[Ljava/lang/reflect/Method;

    move-result-object v4

    .line 28
    .local v4, "mds":[Ljava/lang/reflect/Method;
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_2d
    array-length v7, v4

    if-ge v2, v7, :cond_57

    .line 29
    aget-object v3, v4, v2

    .line 30
    .local v3, "md":Ljava/lang/reflect/Method;
    aget-object v7, v4, v2

    invoke-virtual {v7}, Ljava/lang/reflect/Method;->getModifiers()I

    move-result v5

    .line 31
    .local v5, "mod":I
    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isAbstract(I)Z

    move-result v7

    if-nez v7, :cond_54

    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isNative(I)Z

    move-result v7

    if-nez v7, :cond_54

    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isInterface(I)Z

    move-result v7

    if-nez v7, :cond_54

    .line 32
    aget-object v7, v4, v2

    new-instance v8, Lcom/xiaojianbang/xposed/HookZj$1$1;

    invoke-direct {v8, p0, v3}, Lcom/xiaojianbang/xposed/HookZj$1$1;-><init>(Lcom/xiaojianbang/xposed/HookZj$1;Ljava/lang/reflect/Method;)V

    invoke-static {v7, v8}, Lde/robv/android/xposed/XposedBridge;->hookMethod(Ljava/lang/reflect/Member;Lde/robv/android/xposed/XC_MethodHook;)Lde/robv/android/xposed/XC_MethodHook$Unhook;

    .line 28
    :cond_54
    add-int/lit8 v2, v2, 0x1

    goto :goto_2d

    .line 49
    .end local v2    # "i":I
    .end local v3    # "md":Ljava/lang/reflect/Method;
    .end local v4    # "mds":[Ljava/lang/reflect/Method;
    .end local v5    # "mod":I
    :cond_57
    return-void
.end method
