

#### 1、#{} 与 ${} 的区别

1、\#{}是预编译处理，占位符替换；${}字符串替换
2、处理#{}会将sql中#{}替换为？号，调用PreparedStatement的set方法赋值；
3、处理${}时，只是简单的替换，使用#{}可以防止sql注入
#### 2、实体类中属性名和表中字段不一致
1、在查询是通过sql语句中定义别名，让字段名与实体类属性名保持一致

2、通过<resultMap>来映射字段名与实体类属性名

#### 3、模糊查询like语句怎么写
1、在java层拼接字符串“%Demo%”，然后传入#{value}
select * from table where columon1 like #{value}

2、在sql语句中拼接通配符，会引起sql注入
 select * from table where columon1 like "%"#{value}"%"

####4、通常xml映射文件都会写一个Dao接口与之对应，Dao接口原理？Dao接口里的方法，参数不同市，方法能重载吗？
1、Dao接口就是Mapper接口的全限定名，也是映射mapper文件中namespace 的值

2、接口的方法名就是Mapper映射文件中MappedStatement id的值，接口方法内参数就是传递给sql的参数；

3、Mapper接口是没有实现类的，当调用接口方法时，接口全限定名+方法名拼接为key值，可以唯一确定一个MappedStatement

4、Dao接口方法不能重载，因为全限定名+方法名的保存和寻找策略

5、Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象；
代理对象proxy会拦截接口方法，转而执行MappedStatement所代表的sql，然后将sql执行结果返回。

####5、Mybatis是如何进行分页的？分页插件原理？
1、Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页；
可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可使用分页插件来完成物理分页

2、分页插件是使用mybatis提供的插件接口，实现自定义插件，在插件的拦截方法内拦截执行的sql，然后重写sql，根据dialect方言添加物理分页语句和物理分页参数

#### 6、Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
1、<resultMap>标签，逐一定义列名和对象属性名之间的映射关系。 另一种使用别名

有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同事使用反射给对象逐一赋值并返回，找不到映射关系的属性，无法完成赋值。

#### 7、批量插入

#### 8、获取自动生成的主键值

#### 9、Mapper如何传递多个参数
1、将参数直接传入

    public method1(String str1,String str2);
    <select id="method1" resultMap="">
        select * from table1 t where column1 = #{0} and column2=#{1}
    </select>

2、使用都在参数前添加@param注解


    method1(@param("str1")String str1,@param("str2")String str2);
    <select id="method1" resultMap="">
        select * from table1 t where column1 = #{str1} and column2=#{str2}
    </select>

#### 10、mybatis动态sql是做什么：都有哪些动态sql？
mybatis动态sql可也让我们在Xml映射文件内，以标签的形式编写动态sql,完成逻辑判断和动态拼接sql的功能；
mybatis提了了9中动态sql标签：trim|where|set|foreach|if|choose|when|otherwise|bind
其执行原理为：使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql,依次来完成动态sql功能。

#### 11、不用xml映射文件中，id是否可也重复
1、如果配置了namespace，那么id可也重复；如果没有配置namespace，则不能重复

2、原因：namespace+id是作为key确定唯一sql



    