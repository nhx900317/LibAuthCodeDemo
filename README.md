# LibAuthCodeDemo
验证码客户端版，用于安卓端程序中验证码验证，类似于激活码的功能，通过激活码可以随时控制程序是否可用。这个是安卓客户端的工程，已经可以使用，配套的后台管理端的工程并未在本文中描述。

# 修改记录<br>
版本|修改记录 |修改时间|修改人
-------|-------|-------|-------
1.0|创建文档|2020-09-23|天河归来
1.1|增加查询aar版本号的接口<br>验证码接口增加设备id参数|2020-09-24|天河归来

# 1. 配置和使用方法<br>

## 1.1 配置环境<br>
建议使用开发环境：Android studio，版本号为4.0或以上进行开发；<br>
建议gradle版本：建议使用6.1.1或以上版本进行开发；<br>
建议android版本：建议使用(SDKVersion 29)或以上版本进行开发；<br>
aar中使用的SDK版本：SDKVersion 29，minSDKVersion 21；<br>
aar中使用的kotlin版本：1.3.50。<br>

## 1.2 使用方法<br>
将aar放到libs目录下；<br>
在gradle中添加aar和相关源的引用，具体见demo；<br>
在使用前需要对lib进行初始化，具体代码见第二章。<br>
注1：本文中方法和示例代码均用kotlin标注。<br>
具体使用方法可参见Demo。<br>
Demo说明:<br>
Demo分别展示了aar中的各项功能使用和调用方法。<br>

## 1.3 权限获取<br>
使用aar建议添加如下权限:<br>
必要权限：<br>
网络权限。<br>

# 2. 初始化<br>

## 2.1 初始化操作<br>
新建InitLibCore类的对象，传入上下文，并调用该对象的init方法进行初始化。<br>
 
    val initLibAuthCode = InitLibAuthCode()
        
    initLibAuthCode.init(context)

注：初始化操作必不可少，否则无法使用本aar库，传入的context建议使用应用Application的context。此操作建议在Application中完成。<br>

# 3. 具体方法<br>
使用AuthCodeAb类进行具体操作，其中封装的方法如下。

## 3.1 AuthCodeAb对象初始化<br>
新建AuthCodeAb对象，并初始化：

    private var mAuthCodeAb: AuthCodeAb? = null
    
    mAuthCodeAb = AuthCode(this)//传入上下文content
    
## 3.2 查询版本号方法<br>
调用AuthCodeAb的getVersion方法查询aar的版本号：
abstract fun getVersion():String
返回aar的版本号。

## 3.2 验证码验证方法<br>
调用AuthCodeAb的checkAuthCode方法验证：
abstract fun checkAuthCode(authCode: String, deviceId:String, pAuthCodeCallback: AuthCodeCallback)
参数名称|参数说明
-------|-------
authCode|验证码
deviceId|设备Id
pAuthCodeCallback|验证结果回调，AuthCodeAb.AuthCodeCallback

具体使用方法：<br>
在使用定位的Activity实现LocationAb.LocationCallback接口，或者在使用时创建LocationAb.LocationCallback，以下示例代码使用传入回调方式：<br>

    mAuthCodeAb?.checkAuthCode("88e8a57c58d7ea088e1b32881338aaab", "your device Id", object : AuthCodeAb.AuthCodeCallback {
        override fun onError(code: Int, msg: String) {
            LogDebug.d(TAG, "checkAuthCode onError,code:$code,msg:$msg")
            tvMsg!!.text = msg
        }

        override fun onSuccess(msg: String) {
            LogDebug.d(TAG, "checkAuthCode onSuccess,msg:$msg")
            tvMsg!!.text = msg
        }
    })
    
注：如不用传入回调实现接口方式，可以使用Activity实现接口代替。<br>

## 3.4 回调方法<br>
验证码结果回调方法
### 3.4.1  验证通过回调<br>
验证码验证成功时回调
fun onSuccess(msg: String)<br>
参数名称|参数说明
-------|-------
msg|操作成功

### 3.4.2  验证失败回调<br>
验证码验证失败时回调。<br>
注：失败情况比如：验证码不存在，验证码已过期，联网超时等，具体看code和msg。<br>
onError(code: Int, msg: String)<br>

参数名称|参数说明
-------|-------
code|错误码
msg|错误信息








