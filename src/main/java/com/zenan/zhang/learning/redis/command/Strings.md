### String 相关命令
#### set key value [EX seconds] [PX milliseconds] [NX|XX]
* 将键key设定为指定的字符串值，如果存在则覆盖
#### get key
* 返回key的value，如果不存在返回nil,如果key的value不是String，返回错误
#### strlen key
* 返回key的String类型value的长度
#### append key value
* 如果key已经存在，并且值为字符串，那么将value追加到原value结尾；如果不存在创建空字符串的key，在追加
#### decr key (incr)
* 对key对应的value(必须为数字)做减1操作
#### decrby key decrememt
* 减 decrement大小值
#### getset key value
* 将value赋给key,返回旧value值
#### mget key [key...]
* 返回所有指定的key的value

