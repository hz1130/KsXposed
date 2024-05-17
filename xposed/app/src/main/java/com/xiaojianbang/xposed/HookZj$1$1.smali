.class Lcom/xiaojianbang/xposed/HookZj$1$1;
.super Lde/robv/android/xposed/XC_MethodHook;
.source "HookZj.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/xiaojianbang/xposed/HookZj$1;->afterHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/xiaojianbang/xposed/HookZj$1;

.field final synthetic val$md:Ljava/lang/reflect/Method;


# direct methods
.method constructor <init>(Lcom/xiaojianbang/xposed/HookZj$1;Ljava/lang/reflect/Method;)V
    .registers 3
    .param p1, "this$1"    # Lcom/xiaojianbang/xposed/HookZj$1;

    .prologue
    .line 32
    iput-object p1, p0, Lcom/xiaojianbang/xposed/HookZj$1$1;->this$1:Lcom/xiaojianbang/xposed/HookZj$1;

    iput-object p2, p0, Lcom/xiaojianbang/xposed/HookZj$1$1;->val$md:Ljava/lang/reflect/Method;

    invoke-direct {p0}, Lde/robv/android/xposed/XC_MethodHook;-><init>()V

    return-void
.end method


# virtual methods
.method public beforeHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V
    .registers 9
    .param p1, "param"    # Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 35
    iget-object v2, p0, Lcom/xiaojianbang/xposed/HookZj$1$1;->val$md:Ljava/lang/reflect/Method;

    invoke-virtual {v2}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "complexParameterFunc"

    invoke-virtual {v2, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_26

    .line 36
    iget-object v3, p1, Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;->args:[Ljava/lang/Object;

    array-length v4, v3

    const/4 v2, 0x0

    :goto_12
    if-ge v2, v4, :cond_26

    aget-object v0, v3, v2

    .line 37
    .local v0, "obj":Ljava/lang/Object;
    const-string v5, "dajianbang"

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 36
    add-int/lit8 v2, v2, 0x1

    goto :goto_12

    .line 40
    .end local v0    # "obj":Ljava/lang/Object;
    :cond_26
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    .line 41
    .local v1, "sb":Ljava/lang/StringBuilder;
    const-string v2, "Hook Method: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 42
    iget-object v2, p0, Lcom/xiaojianbang/xposed/HookZj$1$1;->val$md:Ljava/lang/reflect/Method;

    invoke-virtual {v2}, Ljava/lang/reflect/Method;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 43
    const-string v2, "dajianbang"

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 44
    return-void
.end method
