## 第一章 深入Web请求过程
### 1.1 B/S 网络架构概述

 - C/S (Client/Server 客户端/服务端)  采用长连接交互
 - B/S (Browser/Server 浏览器/服务端)  基于统一的应用层协议HTTP来交互数据，无状态短连接
 
 用户在浏览器中输入www.taobao.com 发生操作：

 1、 请求DNS将域名解析为对应IP；
 
 2、 然后根据IP地址在互联网上找到对应服务器；
 
 3、向这个服务器发送一个get请求，服务器返回默认数据资源给用户；服务器可能多台，由负载均衡设备来平均分配所有用户请求。
 
### 1.2 发起一个请求
- 发起一个HTTP请求的过程就是建立一个Socket通信的过程
- Linux 中curl命令发起http请求,例如curl "www.baidu.com" 可以返回这个页面html数据

![](https://raw.githubusercontent.com/NAN009/learning/master/src/main/java/com/zenan/zhang/learning/reading/notes/resource/curl.png)

### 1.3 HTTP 解析
HTTP请求头
<table>
<tr>
<th>请求头</th>
<th>说明</th>
</tr>
<tr>
<th>Accept-Charset</th>
<th>用于指定浏览器接收的字符集</th>
</tr>
<tr>
<th>Accept-Encoding</th>
<th>指定可接受的内容编码，如Accept-Encoding:gzip.deflate</th>
</tr>
<tr>
<th>Accept-Language</th>
<th>指定一种自然语言，如Accept-Language:zh-cn</th>
</tr>
<tr>
<th>Host</th>
<th>指定被请求资源的Internet主机端口号，如Host:www.taobao.com</th>
</tr>
<tr>
<th>User-Agent</th>
<th>浏览器将它的操作系统、浏览器和其他属性告诉服务器</th>
</tr>
<tr>
<th>Connection</th>
<th>当前连接是否保持，如Connectjion:Keep-Alive</th>
</tr>
<tr>
<th>Pragma</th>
<th>不适用浏览器缓存，如Pragma:no-cache</th>
</tr>
<tr>
<th>Cache-Control</th>
<th>不适用浏览器缓存，如Cache-Control:no-cache</th>
</tr>
</table>

![](https://raw.githubusercontent.com/NAN009/learning/master/src/main/java/com/zenan/zhang/learning/reading/notes/resource/Request%20Headers.png)

Http响应头
<table>
<tr>
<th>响应头</th>
<th>说明</th>
</tr>
<tr>
<th>Server</th>
<th>使用服务名称，如：Server:Apache/1.3.6(Unix)</th>
</tr>
<tr>
<th>Content-Type</th>
<th>指明发送给接受者的实体中文的媒体类型，如Content-Type:text/html;charset=GBK</th>
</tr>
<tr>
<th>Content-Encoding</th>
<th>与请求报头Accept-Encoding 对应，告诉浏览器服务端采用什么压缩编码</th>
</tr>
<tr>
<th>Content-Language</th>
<th>与Accept-Language 对应，描述资源所用的自然语言</th>
</tr>
<tr>
<th>Content-Length</th>
<th>指明实体正文的长度，用以字节方式存储十进制数字来表示</th>
</tr>
<tr>
<th>Keep-Alive</th>
<th>保持连接的时间，如Keep-Alive:timeout=5,max=120</th>
</tr>
<tr>
</table>

![](https://raw.githubusercontent.com/NAN009/learning/master/src/main/java/com/zenan/zhang/learning/reading/notes/resource/Response%20Headers.png)


 
 