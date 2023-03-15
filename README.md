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