# douyin-reptiles
douyin-reptiles

* douyin.md 思路和参考
* user.txt 包含多个sec_user_id，可用于测试
* X-Bogus 用于Python签名

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
启动springboot
127.0.0.1:8088

两个服务都要启动缺一不可

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
示例图片：
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/ebff21d2-fd08-44b7-99fa-1c3fd18d8f52)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/c90557cb-fc35-4e62-bbf2-19b2758a1546)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/05571202-3049-43aa-ac81-d602894692ee)
![image](https://github.com/RookieDevp/douyin-reptiles/assets/88661272/91eb3764-6fa6-4ad9-8434-f5f0df0ea43e)


