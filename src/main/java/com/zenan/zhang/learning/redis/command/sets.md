### sets相关命令
#### sadd key member [member...]
* 添加一或多个指定的member元素到集合
#### smembers key
* 返回key集合所有元素
#### scard key
* 返回集合存储key 中value集合元素的数量
#### sismember key member
* 返回成员member 是否是存储集合key的成员
#### smove source destination member
* 将member从source集合移动到destination集合中
#### sunion kye [key...]
返回给定的多个集合的并集中所有成员
#### srem key member [member...]
* 在key集合中移除指定元素