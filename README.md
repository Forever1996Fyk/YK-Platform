# YK-Platform

### 简介
主要是用当前比较热门, 有很基础的的技术框架SpringBoot, Shiro, Thymleaf, Mybatis搭建的基本权限管理系统。本平台是基于RuoYi的项目进行二次重构。
主要是改造成适用自己的编程习惯, 编码风格, 以及一些有关通用代码的整合。其中权限管理是细化到按钮级别, 而且还是很多其他我自己会用到的功能。
比如利用多线程进行多文件上传, 文档的预览等等。

### 基本功能
1. 系统管理
* 用户管理
* 角色管理
* 菜单管理
* 字典管理
* 日志管理
2. 文件管理
* 图片管理
* 视频管理
* 文档管理
3. 系统工具
* 代码生成

### 具体功能
1. 文件上传功能: 由于本身使用bootstrap-fileinput插件, 这个插件是自带异步上传功能, 所以是不需要使用多线程的。但是为了适应并学习多线程，所以加上了多线程操作
而且文件上传有三种类型, 本地上传, FastDfs, Aliyun OSS都是可以进行上传，下载，预览操作。
2. 权限功能: 本项目没有涉及到很复杂的权限, 只有用户-角色-菜单。没有部门管理, 这个可以根据自身的需求进行改写。
3. 日志管理, 代码生成, 字典管理是完全按照RuoYi大佬的项目编写的，基本上没有什么变化。
4. 搭建FastDfs服务参考这篇博客[用FastDFS一步步搭建文件管理系统](https://www.cnblogs.com/chiangchou/p/fastdfs.html#_label0_3)
这篇博客写的搭建过程非常详细，而且是没有问题的。
5. AliyunOSS工具类可以参考这篇博客[阿里云OSS对象存储操作工具类](https://blog.csdn.net/qq_41695977/article/details/90752481)
6. 事务处理: 我们一般都是在业务层处理事务, 也就是service层。但是一定要注意, 这里是有坑的!
(1) 遇到检查异常时，事务开启，也无法回滚。 例如下面这段代码，用户依旧增加成功，并没有因为后面遇到检查异常而回滚！！
    ```java
    @Transactional
    public int insertUser(User user) throws Exception {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        // 模拟抛出SQLException异常
        boolean flag = true;
        if (flag) {
            throw new SQLException("发生异常了..");
        }
        return rows;
    }
    ```
   原因分析：因为Spring的默认的事务规则是遇到运行异常（RuntimeException）和程序错误（Error）才会回滚。如果想针对非检查异常进行事务回滚，可以在@Transactional 注解里使用 rollbackFor 属性明确指定异常。
   ```java
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user) throws Exception {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        // 模拟抛出SQLException异常
        boolean flag = true;
        if (flag) {
            throw new SQLException("发生异常了..");
        }
        return rows;
    }
    ```
   (2) 在业务层或者其他地方抛出了异常，但是又在service层进行捕获，这样当然也是不会事务回滚的
   ```java
    @Transactional
    public int insertUser(User user) throws Exception {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        // 模拟抛出SQLException异常
        boolean flag = true;
        if (flag) {
            try {
                // 谨慎：尽量不要在业务层捕捉异常并处理
                throw new SQLException("发生异常了..");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rows;
    }
    ```
   **所以解决事务问题推荐的方法是, 不要在业务层进行异常的捕获, 除非业务需要可以使用第一种处理方式, 否则就在业务层抛出, 在控制层捕获处理相关代码**
   在这个项目里我采用的是配置类的方式进行全局的事务管理, 所以不需要添加@Transactional注解, 但是也可以添加用来处理相应的业务.
### 用处
几乎可以用于所有中小型管理系统项目, 因为这本身只是一个框架, 功能并不多, 可以根据业务需求不断的添加代码
比如: 定时任务, 工作流等主流功能。因为这些功能之前我都是做过的, 比较熟悉, 要用的话可以直接集成。最重要的还是要理解原理! 
