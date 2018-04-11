#### exists key
* 返回key是否存在
#### expire key seconds
* 设置key过期时间，超期后将自动删除key
#### del key [key ...]
* 删除指定的一批keys
#### keys pattern
* 查找所有符合给的模式正则表达式的key
#### pttl key
* 返回key的剩余生存时间
#### rename key newKey
* 将key重命名为newKey
#### type key
* 返回key所存储value的数据结构类型（String,list,set,zset,hash）
#### scan cursor [match pattern] [count count]
* 增量迭代一个集合元素
* scan 迭代当前数据库中key集合
* sscan 迭代set集合中的元素
* hscan 迭代Hash类型中键值对
* zscan 迭代SortSet集合中元素和对于分值