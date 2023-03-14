

### 开源API

https://open.douyin.com/platform 抖音开放平台API

https://github.com/VideoData/DY-Data/blob/main/%E6%8A%96%E9%9F%B3%E7%94%A8%E6%88%B7Api/%E6%8A%96%E9%9F%B3Api%EF%BC%9A%E7%94%A8%E6%88%B7%E5%96%9C%E6%AC%A2%E5%88%97%E8%A1%A8.md  抖音Api：用户喜欢列表

https://api.tikhub.io/docs tikhub 第三方（X）成本高 1200 200k

https://api.tikhub.io/docs#tag/Douyin/operation/get_douyin_user_profile_liked_videos_douyin_profile_liked_videos__get

<img src="https://cdn.discordapp.com/attachments/1070512514607108133/1079620471458381864/TikHub_PriceList_V2-1.png" alt="img" style="zoom:25%;" />

- [web_app.py](https://github.com/Evil0ctal/Douyin_TikTok_Download_API/blob/main/web_app.py) - [PyWebIO](https://www.pyweb.io/)
- [web_api.py](https://github.com/Evil0ctal/Douyin_TikTok_Download_API/blob/main/web_api.py) - [FastAPI](https://fastapi.tiangolo.com/)
- [scraper.py](https://github.com/Evil0ctal/Douyin_TikTok_Download_API/blob/main/scraper.py) - [AIOHTTP](https://docs.aiohttp.org/)

> ***scraper.py:***

- 向[Douyin|TikTok]的API提交请求并取回数据，处理后返回字典(dict)，支持异步。

> ***web_api.py:***

- 获得请求参数并使用`Scraper()`类处理数据后以JSON形式返回，视频下载，配合iOS快捷指令实现快速调用，支持异步。

> ***web_app.py:***

- 为`web_api.py`以及`scraper.py`制作的简易Web程序，将网页输入的值进行处理后使用`Scraper()`类处理并配合`web_api.py`的接口输出在网页上(类似前后端分离)

### example

第一次请求：

https://www.douyin.com/aweme/v1/web/aweme/favorite/?device_platform=webapp&aid=6383&channel=channel_pc_web&sec_user_id=MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50&max_cursor=0&min_cursor=0&count=10&publish_video_strategy_type=2&pc_client_type=1&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1536&screen_height=864&browser_language=zh-CN&browser_platform=Win32&browser_name=Chrome&browser_version=110.0.0.0&browser_online=true&engine_name=Blink&engine_version=110.0.0.0&os_name=Windows&os_version=10&cpu_core_num=8&device_memory=8&platform=PC&downlink=10&effective_type=4g&round_trip_time=50&webid=7202581455338505760&msToken=dp1MU0wt_QuaFegHu4xOAd8DsDwaSh9DsF6WAQu2UY7fpbvCXksg8olcazKGkRyHK_9AeFFR-w5uu-9Thwk9nAc0bUvdnwJnMDt2rMEq5KCCfq1hq_hw&X-Bogus=DFSzswVuQnUANJPItam3xM9WX7r-

第二次请求：

https://www.douyin.com/aweme/v1/web/aweme/favorite/?device_platform=webapp&aid=6383&channel=channel_pc_web&sec_user_id=MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50&max_cursor=0&min_cursor=0&count=10&publish_video_strategy_type=2&pc_client_type=1&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1536&screen_height=864&browser_language=zh-CN&browser_platform=Win32&browser_name=Chrome&browser_version=110.0.0.0&browser_online=true&engine_name=Blink&engine_version=110.0.0.0&os_name=Windows&os_version=10&cpu_core_num=8&device_memory=8&platform=PC&downlink=10&effective_type=4g&round_trip_time=0&webid=7202581455338505760&msToken=ksH8DlAXGYvfzIwgwQeMhbQS_IbI_xvpuiiGhTbDKaWmd8sSBbV_Pe-ron73EwZlbxOqxfY1xhGOQG0l9Y41QwCFwKeULcjEZx_b1eCtvg9foB8BRRGcFqDGEAVAPw==&X-Bogus=DFSzswVue3GANJPItamohe9WX7r7

区别 msToken、X-Bogus，每次请求会改变，否则{}



https://www.douyin.com/aweme/v1/web/aweme/favorite/

需求：==aweme_list==[]

```
aweme_id
author_user_id
```

### Query String Parameters

```
device_platform: webapp
aid: 6383
channel: channel_pc_web
sec_user_id: MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50
max_cursor: 0
min_cursor: 0
count: 10
publish_video_strategy_type: 2
pc_client_type: 1
version_code: 170400
version_name: 17.4.0
cookie_enabled: true
screen_width: 1536
screen_height: 864
browser_language: zh-CN
browser_platform: Win32
browser_name: Chrome
browser_version: 110.0.0.0
browser_online: true
engine_name: Blink
engine_version: 110.0.0.0
os_name: Windows
os_version: 10
cpu_core_num: 8
device_memory: 8
platform: PC
downlink: 10
effective_type: 4g
round_trip_time: 50
webid: 7202581455338505760
msToken: dp1MU0wt_QuaFegHu4xOAd8DsDwaSh9DsF6WAQu2UY7fpbvCXksg8olcazKGkRyHK_9AeFFR-w5uu-9Thwk9nAc0bUvdnwJnMDt2rMEq5KCCfq1hq_hw
X-Bogus: DFSzswVuQnUANJPItam3xM9WX7r-
```

```
device_platform: webapp
aid: 6383
channel: channel_pc_web
publish_video_strategy_type: 2
source: channel_pc_web
sec_user_id: MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50
pc_client_type: 1
version_code: 170400
version_name: 17.4.0
cookie_enabled: true
screen_width: 1536
screen_height: 864
browser_language: zh-CN
browser_platform: Win32
browser_name: Chrome
browser_version: 110.0.0.0
browser_online: true
engine_name: Blink
engine_version: 110.0.0.0
os_name: Windows
os_version: 10
cpu_core_num: 8
device_memory: 8
platform: PC
downlink: 10
effective_type: 4g
round_trip_time: 0
webid: 7202581455338505760
msToken: W2GV7tEZVfi6R6v370YbLlWR_ClKPElMw8QQ70-ZkUCB38WuFfLmiSTFucX4G8EKB9R-KXK2Lj4UrtqysyjGwLwK4ch_UffLYZax1CdRFATwGEagFIL1
X-Bogus: DFSzswVO7atANriStamqde9WX7n4
```

![image-20230314015757903](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314015757903.png)

> msToken，该参数其实就是一个107位的随机数，由大小写英文字母、数字组成
>
> `webid` 直接请求主页返回内容里就有，`msToken` 与 cookie 有关 某些接口不验证 `webid` 和 `msToken`，直接置空即可

### X-Bogus参考资料

> X-Bogus 你可以理解为是一个根据视频ID及user-agent通过JS生成的用户信息参数，它可以用于校验
>
> https://baijiahao.baidu.com/s?id=1747190670465615168&wfr=spider&for=pc X-Bogus
>
> https://github.com/B1gM8c/X-Bogus
>
> https://blog.csdn.net/ljc545w/article/details/128834402?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-128834402-blog-124726193.pc_relevant_landingrelevant&spm=1001.2101.3001.4242.2&utm_relevant_index=4 解析某音X-Bogus参数

### X-Bogus解析测试

> X-Bogus会对params、form-data、user-agent、时间、canvas进行校验
>
> https://www.iculture.cc/knowledge/pig=33360?only_author=1
>
> 0.74 Fuf:/ # 谁还没个证 https://v.douyin.com/SqDKCWW/ 复制此链接，打开Dou音搜索，直接观看视频！
>
> https://v.douyin.com/SqDKCWW/
>
> 重新访问->https://www.douyin.com/video/7209082846806379787
>
> 视频ID: 7209082846806379787
>
> https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=7209082846806379787 返回值空

### Request Headers

```
:authority: www.douyin.com
:method: GET
:path: /aweme/v1/web/aweme/favorite/?device_platform=webapp&aid=6383&channel=channel_pc_web&sec_user_id=MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50&max_cursor=0&min_cursor=0&count=10&publish_video_strategy_type=2&pc_client_type=1&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1536&screen_height=864&browser_language=zh-CN&browser_platform=Win32&browser_name=Chrome&browser_version=110.0.0.0&browser_online=true&engine_name=Blink&engine_version=110.0.0.0&os_name=Windows&os_version=10&cpu_core_num=8&device_memory=8&platform=PC&downlink=10&effective_type=4g&round_trip_time=0&webid=7202581455338505760&msToken=UeKkafNW8oQ9hlMcRMWGe_lTDZNR0sI0V2t5GKxMVunLCwvr_EOTZs_6sqL3c7EOiCI755I9E2fpuS1-Tfr7T_ccujt878J7pUcsvBV9Ch_hiBWdqQL67VNavwjQQQ==&X-Bogus=DFSzswVu8JvANJPItamAme9WX7nw
:scheme: https
accept: application/json, text/plain, */*
accept-encoding: gzip, deflate, br
accept-language: zh-CN,zh;q=0.9
cookie: passport_csrf_token=fbc170f6d028622287eb948d73f49e22; passport_csrf_token_default=fbc170f6d028622287eb948d73f49e22; s_v_web_id=verify_ldrd4cms_iZGBwzGS_FHEB_4b7Y_AM1d_Pumvuurb94nh; xgplayer_user_id=848251892053; ttwid=1%7CN-rAdtOYXiKx93JfpMXe7TBaO80jVySuDm_3-EXOPTc%7C1676981691%7C4bc17fc85bf68923847ae317863658c043e801b0ec20440a87597eeb193d450a; download_guide=%223%2F20230313%22; my_rd=1; SEARCH_RESULT_LIST_TYPE=%22single%22; douyin.com; strategyABtestKey=%221678725544.777%22; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWNsaWVudC1jc3IiOiItLS0tLUJFR0lOIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG5NSUlCRHpDQnRRSUJBREFuTVFzd0NRWURWUVFHRXdKRFRqRVlNQllHQTFVRUF3d1BZbVJmZEdsamEyVjBYMmQxXHJcbllYSmtNRmt3RXdZSEtvWkl6ajBDQVFZSUtvWkl6ajBEQVFjRFFnQUU5STdibnJGN0tveDF6YjliM09GNkNMbk5cclxuckZDbTRleVpWUlJEQ01RSDhPdEIyOCt4ZExMQ2l4TlgxS1lvZmtMemxtdnlhcDh5ekJDdTNKZHduOG9oN2FBc1xyXG5NQ29HQ1NxR1NJYjNEUUVKRGpFZE1Cc3dHUVlEVlIwUkJCSXdFSUlPZDNkM0xtUnZkWGxwYmk1amIyMHdDZ1lJXHJcbktvWkl6ajBFQXdJRFNRQXdSZ0loQUlRZEhnZ0EzbVRRcHdjM0R4cW9tQjZVNlVDcGJXbmgxMWdXaGJNNTExK2VcclxuQWlFQStGRk1LL0VzTG9NSHU2QklyYlc3NHJ3T3QxUkVEalBiKzNhUk9kR0o1TGM9XHJcbi0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG4ifQ==; csrf_session_id=981a41081213621a78f8cf5928bff4b8; VIDEO_FILTER_MEMO_SELECT=%7B%22expireTime%22%3A1679331867425%2C%22type%22%3A1%7D; __ac_nonce=0640f596f00cc5c66f61c; __ac_signature=_02B4Z6wo00f01d6WBggAAIDBXpT8S1WedqnetgKAABOsTXMHuI6fhfLyHxCyaBHFhhAWJCql-Gc1o2UZZPKAGjDGGmMBE0m.5xWo-UkHRzSX-UnyU2q0uaIS7AoHIGS2TmZs32TuyhaOa9Lz51; msToken=rpdlFKzwYVRw16KxkRvWChs8LhiObzTkw3mwI73SSsd4oU8GuOL-eLqxE4wi2l9N_3wTSPeichvkU0HAXer3oeL-p0f414CJy5i0EEvqPXsqOcJ1XwCFEOh78Qykkw==; home_can_add_dy_2_desktop=%221%22; msToken=UeKkafNW8oQ9hlMcRMWGe_lTDZNR0sI0V2t5GKxMVunLCwvr_EOTZs_6sqL3c7EOiCI755I9E2fpuS1-Tfr7T_ccujt878J7pUcsvBV9Ch_hiBWdqQL67VNavwjQQQ==; tt_scid=6nUtlGboubHkxkWMhCsykDcqbsfqujl80t0CD1y1t4YZmpdF7922fYacAZ7yIg3u6060
referer: https://www.douyin.com/user/MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50?showTab=like
sec-ch-ua: "Chromium";v="110", "Not A(Brand";v="24", "Google Chrome";v="110"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
sec-fetch-dest: empty
sec-fetch-mode: cors
sec-fetch-site: same-origin
user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36
```

### 测试



### 常见问题

**非常常见的问题是当 tiktok 将您的 IP/PROXY 列入黑名单时，在这种情况下，您可以尝试设置会话，成功的机会会更高**

获取会话：

- 在任何浏览器中打开https://www.tiktok.com/
- 登录您的帐户
- 右键单击 -> 检查器 -> 网络
- 刷新页面 -> 选择对 tiktok 发出的任何请求 -> 转到请求标头部分 -> Cookies
- 在 cookies 中查找**sid_tt**值。它通常看起来像这样：**sid_tt=521kkadkasdaskdj4j213j12j312；**
- **sid_tt=521kkadkasdaskdj4j213j12j312；**- 这将是您经过身份验证的会话 cookie 值，应该用于抓取用户/主题标签/音乐/趋势提要

设置会话：

- **命令行界面**：

  - 使用选项**--session**设置单个会话。例如**--session sid_tt=521kkadkasdaskdj4j213j12j312；**

  - 

    使用选项--session-file

    设置会话列表文件的路径。例如

    --session-file /var/bob/sessionList.txt

    - 示例内容/var/bob/sessionList.txt：

    ```
    sid_tt=521kkadkasdaskdj4j213j12j312;
    sid_tt=521kkadkasdaskdj4j213j12j312;
    sid_tt=521kkadkasdaskdj4j213j12j312;
    sid_tt=521kkadkasdaskdj4j213j12j312;
    ```

- 在**MODULE**中，您可以通过设置选项值 sessionList 来设置会话。例如**sessionList:["sid_tt=521kkadkasdaskdj4j213j12j312;", "sid_tt=12312312312312;"]**



```
bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWNsaWVudC1jc3IiOiItLS0tLUJFR0lOIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG5NSUlCRHpDQnRRSUJBREFuTVFzd0NRWURWUVFHRXdKRFRqRVlNQllHQTFVRUF3d1BZbVJmZEdsamEyVjBYMmQxXHJcbllYSmtNRmt3RXdZSEtvWkl6ajBDQVFZSUtvWkl6ajBEQVFjRFFnQUU5STdibnJGN0tveDF6YjliM09GNkNMbk5cclxuckZDbTRleVpWUlJEQ01RSDhPdEIyOCt4ZExMQ2l4TlgxS1lvZmtMemxtdnlhcDh5ekJDdTNKZHduOG9oN2FBc1xyXG5NQ29HQ1NxR1NJYjNEUUVKRGpFZE1Cc3dHUVlEVlIwUkJCSXdFSUlPZDNkM0xtUnZkWGxwYmk1amIyMHdDZ1lJXHJcbktvWkl6ajBFQXdJRFNRQXdSZ0loQUlRZEhnZ0EzbVRRcHdjM0R4cW9tQjZVNlVDcGJXbmgxMWdXaGJNNTExK2VcclxuQWlFQStGRk1LL0VzTG9NSHU2QklyYlc3NHJ3T3QxUkVEalBiKzNhUk9kR0o1TGM9XHJcbi0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG4ifQ==; msToken=mBaNX7BGgpiNu1urGDFKVEBrVn6JjevMvKxr5EG8Cm_SuwDGpzlHS3pf2azyVbXN0_79oSb4iuGLC0R2-0iBbuIApTBTnJmNg1qXPj6auObjWMWcIqdizg==; msToken=o01NRabe1fwse268wdBgtvwVhRHv9wnAXLMXVG5-7_2hVyTE8_LLI_hCBlmcmbn0BsrL48omjA2-_5BpHOwKN7jTLqW32iLOJZRCefJwnmAlBP2dzf0RTD6ICqjVbxA=; __ac_nonce=06410073400b91f78de59; __ac_signature=_02B4Z6wo00f01u6Dk6gAAIDCboFp6N8mRhruo5cAAN--fQVZJNDvcgYYV24NbKz8TXCQwjWZn4xMG-6OmHQ7c5hFnTMdlZqY4ZjlgW25d3T7uzbgfl2mKcm2WY.SJko9t.F3xius4eW8Sgg8fd; home_can_add_dy_2_desktop="0"
```

```
ttwid=1|N-rAdtOYXiKx93JfpMXe7TBaO80jVySuDm_3-EXOPTc|1676981691|4bc17fc85bf68923847ae317863658c043e801b0ec20440a87597eeb193d450a; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWNsaWVudC1jc3IiOiItLS0tLUJFR0lOIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG5NSUlCRHpDQnRRSUJBREFuTVFzd0NRWURWUVFHRXdKRFRqRVlNQllHQTFVRUF3d1BZbVJmZEdsamEyVjBYMmQxXHJcbllYSmtNRmt3RXdZSEtvWkl6ajBDQVFZSUtvWkl6ajBEQVFjRFFnQUU5STdibnJGN0tveDF6YjliM09GNkNMbk5cclxuckZDbTRleVpWUlJEQ01RSDhPdEIyOCt4ZExMQ2l4TlgxS1lvZmtMemxtdnlhcDh5ekJDdTNKZHduOG9oN2FBc1xyXG5NQ29HQ1NxR1NJYjNEUUVKRGpFZE1Cc3dHUVlEVlIwUkJCSXdFSUlPZDNkM0xtUnZkWGxwYmk1amIyMHdDZ1lJXHJcbktvWkl6ajBFQXdJRFNRQXdSZ0loQUlRZEhnZ0EzbVRRcHdjM0R4cW9tQjZVNlVDcGJXbmgxMWdXaGJNNTExK2VcclxuQWlFQStGRk1LL0VzTG9NSHU2QklyYlc3NHJ3T3QxUkVEalBiKzNhUk9kR0o1TGM9XHJcbi0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG4ifQ==; msToken=mBaNX7BGgpiNu1urGDFKVEBrVn6JjevMvKxr5EG8Cm_SuwDGpzlHS3pf2azyVbXN0_79oSb4iuGLC0R2-0iBbuIApTBTnJmNg1qXPj6auObjWMWcIqdizg==;
```

```
ttwid=1|N-rAdtOYXiKx93JfpMXe7TBaO80jVySuDm_3-EXOPTc|1676981691|4bc17fc85bf68923847ae317863658c043e801b0ec20440a87597eeb193d450a; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWNsaWVudC1jc3IiOiItLS0tLUJFR0lOIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG5NSUlCRHpDQnRRSUJBREFuTVFzd0NRWURWUVFHRXdKRFRqRVlNQllHQTFVRUF3d1BZbVJmZEdsamEyVjBYMmQxXHJcbllYSmtNRmt3RXdZSEtvWkl6ajBDQVFZSUtvWkl6ajBEQVFjRFFnQUU5STdibnJGN0tveDF6YjliM09GNkNMbk5cclxuckZDbTRleVpWUlJEQ01RSDhPdEIyOCt4ZExMQ2l4TlgxS1lvZmtMemxtdnlhcDh5ekJDdTNKZHduOG9oN2FBc1xyXG5NQ29HQ1NxR1NJYjNEUUVKRGpFZE1Cc3dHUVlEVlIwUkJCSXdFSUlPZDNkM0xtUnZkWGxwYmk1amIyMHdDZ1lJXHJcbktvWkl6ajBFQXdJRFNRQXdSZ0loQUlRZEhnZ0EzbVRRcHdjM0R4cW9tQjZVNlVDcGJXbmgxMWdXaGJNNTExK2VcclxuQWlFQStGRk1LL0VzTG9NSHU2QklyYlc3NHJ3T3QxUkVEalBiKzNhUk9kR0o1TGM9XHJcbi0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG4ifQ==;
```

==ttwid==      ==bd_ticket_guard_client_data==（必须）  

可从你需要的接口获取在cookie中

![image-20230314140505954](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314140505954.png)

![image-20230314140406551](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314140406551.png)

![image-20230314140542428](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314140542428.png)

repo：第一次max_cursor：1678693312000   	<---	req：  0

​			第二次max_cursor：1678591577000   	<--	1678693312000     sub=101,735,000

​			第三次max_cursor：1678574102000   	<--			1678591577000   sub=17,475,000



### Parameters

1、count：当前加载的作品数量（当前接口默认值是10，不传回20）

2、max_cursor：结束时间戳，默认的值是0

3、min_cursor：开始时间戳，默认是0

4、aid：可以理解为平台id

### ttwid

https://ttwid.bytedance.com/ttwid/union/register/   字节ttwid注册 POST

```
{
    "region": "cn",
    "aid": 1768,
    "needFid": false,
    "service": "www.ixigua.com",
    "migrate_info": {
        "ticket": "",
        "source": "node"
    },
    "cbUrlProtocol": "https",
    "union": true
}
```

```
{
    "aid": 4916,
    "service": "so.toutiao.com",
    "unionHost": "https://ttwid.bytedance.com",
    "union": True,
    "needFid": False,
}
```

ttwid -> post请求https://ttwid.bytedance.com/ttwid/union/register/（参数写死）->跳转重定向->从响应头取cookie->拼接写死bd_ticket_guard_client_data->形成主请求cookie

ps：只能请求一次，否则第二次失败

```
{
    "redirect_url": "https://www.ixigua.com/ttwid/union/register/callback/?aid=1768&ticket=1pvtm3jC0a_HffcC7-kxiyycrMZICj0bwOSySBybCoBwfc9BZ2UdzHZ6WAs7aM4Xi",
    "status_code": 0,
    "message": "union register success"
}
```

请求成功：响应头Set-Cookie，其中ttwid就是需要的 

```
{"status_code":0,"message":"callback success","sub_status_code":2002}
```



![image-20230314152439075](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314152439075.png)

请求失败：

```
{"status_code":1001,"message":"parse params fail","sub_status_code":2002}
```

![image-20230314151901955](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20230314151901955.png)





```
ttwid=1%7C-qXHnyxKofZSM5VXOtPXhAfXQbtKISUxWaqGEf0yYTo%7C1678776336%7Ce14f65300b8f5865d7f3f774e488da6d41035c825fee07240db138d7db59b7aa;download_guide="3/20230313"; my_rd=1; SEARCH_RESULT_LIST_TYPE="single"; strategyABtestKey="1678725544.777"; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWNsaWVudC1jc3IiOiItLS0tLUJFR0lOIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG5NSUlCRHpDQnRRSUJBREFuTVFzd0NRWURWUVFHRXdKRFRqRVlNQllHQTFVRUF3d1BZbVJmZEdsamEyVjBYMmQxXHJcbllYSmtNRmt3RXdZSEtvWkl6ajBDQVFZSUtvWkl6ajBEQVFjRFFnQUU5STdibnJGN0tveDF6YjliM09GNkNMbk5cclxuckZDbTRleVpWUlJEQ01RSDhPdEIyOCt4ZExMQ2l4TlgxS1lvZmtMemxtdnlhcDh5ekJDdTNKZHduOG9oN2FBc1xyXG5NQ29HQ1NxR1NJYjNEUUVKRGpFZE1Cc3dHUVlEVlIwUkJCSXdFSUlPZDNkM0xtUnZkWGxwYmk1amIyMHdDZ1lJXHJcbktvWkl6ajBFQXdJRFNRQXdSZ0loQUlRZEhnZ0EzbVRRcHdjM0R4cW9tQjZVNlVDcGJXbmgxMWdXaGJNNTExK2VcclxuQWlFQStGRk1LL0VzTG9NSHU2QklyYlc3NHJ3T3QxUkVEalBiKzNhUk9kR0o1TGM9XHJcbi0tLS0tRU5EIENFUlRJRklDQVRFIFJFUVVFU1QtLS0tLVxyXG4ifQ==; 
```

### bd_ticket_guard_client_data

写死，客户端参数，放config配置

### 接口

1、X-Bogus生成（url） return str

2、ttwid 注册 https://ttwid.bytedance.com/ttwid/union/register/ 取cookie

3、

### 对比

nickname

==sec_uid==

### 测试用户

https://www.douyin.com/user/MS4wLjABAAAApmi-USaKOChKt5pX20FjhjJMcH2bFRfh04i2aP-zVlI?showTab=like

MS4wLjABAAAApmi-USaKOChKt5pX20FjhjJMcH2bFRfh04i2aP-zVlI

https://www.douyin.com/user/MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50?showTab=like

MS4wLjABAAAAlgprH3J4bbkgRZjKOvJgcxbK3BFJ1YrgAqm13cZGe50