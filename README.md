# douyin-reptiles
⭐欢迎star，您的支持是我更新的动力！

* douyin.md 思路和参考
* user.txt 包含多个公开喜欢的sec_user_id，可用于测试
* X-Bogus 用于Python签名，包含思路文档
* 生成的X-Bogus和ttwid可适用于字节跳动应用的接口
* 三种选择方式：Java、Python、Python+Java，均可在API接口下完成
* 已通过千次代理转发并发测试，基本上无失效请求
* 若有疑问，请在issue上留言


## 启动类
server.py
Application.class

### 步骤一
```
cd ./X-Bogus
pip install flask
pip install PyExecJS
python3 server.py
```
访问测试 
127.0.0.1:8787
```
获取X-Bogus POST
/X-Bogus
```
### 步骤二
启动SpringBoot
127.0.0.1:8088

两个服务都要启动缺一不可
## 如需纯Java实现（参考feat分支）
加上以下依赖，可使用hutool工具执行x-bogus的js脚本
```
        <dependency>
            <groupId>org.graalvm.sdk</groupId>
            <artifactId>graal-sdk</artifactId>
            <version>21.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.graalvm.js</groupId>
            <artifactId>js</artifactId>
            <version>21.1.0</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.graalvm.js</groupId>
            <artifactId>js-scriptengine</artifactId>
            <version>21.1.0</version>
        </dependency>
```
```
    public String getXbogus(String url, String userAgent) {
        String xbogus = null;
        xbogus = (String) ScriptUtil.invoke(ResourceUtil.readStr("js/X-Bogus.js",Charset.defaultCharset()),"sign", url, userAgent);
        if (StrUtil.isEmpty(xbogus)){
            return StrUtil.EMPTY;
        }
        return xbogus;
    }
```
### 已实现接口
```
获取用户喜欢列表 GET
/reptiles/getUserFavoriteList/xxx
获取cookie GET
/reptiles/cookie
获取ttwid GET
/reptiles/ttwid
获取xbogus GET
/reptiles/xbogus
```
### 示例图片：
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/ebff21d2-fd08-44b7-99fa-1c3fd18d8f52)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/c90557cb-fc35-4e62-bbf2-19b2758a1546)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/05571202-3049-43aa-ac81-d602894692ee)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/91eb3764-6fa6-4ad9-8434-f5f0df0ea43e)

### 代码参考(Refer)
* https://github.com/B1gM8c/X-Bogus
