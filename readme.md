# 电商秒杀基础项目

## 1. 项目初始化

### 1.1 创建Spring Boot项目

利用IDEA创建Spring Boot工程，勾选spring-boot-starter-web，jdbc，mysql，devtool等依赖。同时添加MyBatis-Plus与代码生成相关的依赖。

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!--MyBatis Plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.2</version>
        </dependency>
        <!--mybatis plus代码生成器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.2</version>
        </dependency>

    </dependencies>

```

### 1.2 初始化数据库

运行`schema.sql`创建数据库和表

```mysql
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `imooc_miaosha` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `imooc_miaosha`;

--
-- Table structure for table `item`
--
-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `price` decimal(64, 2) NOT NULL DEFAULT 0.00,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `sales` int(100) NOT NULL DEFAULT 0,
  `img_url` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 'iphone99', 800.00, '最好用的iphone', 3, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563645867825&di=65cbf1d86165f7185ce7772e2e8a4bca&imgtype=0&src=http%3A%2F%2Fp0.ifengimg.com%2Fpmop%2F2017%2F1127%2F753C746E59ACA849F681F4FC5B75ACD494092110_size15_w600_h400.jpeg');
INSERT INTO `item` VALUES (2, 'iphone99', 800.00, '最好用的iphone', 0, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563645867825&di=65cbf1d86165f7185ce7772e2e8a4bca&imgtype=0&src=http%3A%2F%2Fp0.ifengimg.com%2Fpmop%2F2017%2F1127%2F753C746E59ACA849F681F4FC5B75ACD494092110_size15_w600_h400.jpeg');
INSERT INTO `item` VALUES (3, 'iphone8', 200.00, '第二好用的iphone', 3, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563647556769&di=cc5c9241446eee425165e9b04a87768c&imgtype=0&src=http%3A%2F%2Fi9.hexun.com%2F2018-03-17%2F192644421.jpg');
INSERT INTO `item` VALUES (4, 'iphone8', 200.00, '第二好用的iphone', 0, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563647556769&di=cc5c9241446eee425165e9b04a87768c&imgtype=0&src=http%3A%2F%2Fi9.hexun.com%2F2018-03-17%2F192644421.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (9, 97, 1);
INSERT INTO `item_stock` VALUES (10, 100, 2);
INSERT INTO `item_stock` VALUES (11, 97, 3);
INSERT INTO `item_stock` VALUES (12, 300, 4);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_price` decimal(40, 2) NOT NULL DEFAULT 0.00,
  `promo_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019080200000000', 40, 1, 23.00, 1, 23.00, 1);
INSERT INTO `order_info` VALUES ('2019080200000100', 40, 1, 23.00, 1, 23.00, 1);
INSERT INTO `order_info` VALUES ('2019080200000200', 40, 1, 23.00, 1, 23.00, 1);
INSERT INTO `order_info` VALUES ('2019080200000300', 40, 3, 200.00, 1, 200.00, 0);
INSERT INTO `order_info` VALUES ('2019080200000400', 40, 3, 200.00, 1, 200.00, 0);
INSERT INTO `order_info` VALUES ('2019080200000500', 40, 3, 200.00, 1, 200.00, 0);

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES (1, 'iphone大减价', '2019-08-01 19:55:47', '2019-08-09 19:55:52', 1, 23.00);
INSERT INTO `promo` VALUES (2, 'iphone8大减价', '2019-08-27 20:17:17', '2019-09-05 20:18:18', 3, 3.00);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 6, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `gender` tinyint(2) NOT NULL DEFAULT 0 COMMENT '1代表男性\r\n',
  `age` int(11) NOT NULL DEFAULT 0,
  `telephone` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `register_mode` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '//byphone,bywechat,byalipay,',
  `third_party_id` int(64) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telephone_unique_index`(`telephone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (40, '1', 1, 1, '123', 'byphone', 0);
INSERT INTO `user_info` VALUES (52, '1', 1, 1, '111111', 'byphone', 0);
INSERT INTO `user_info` VALUES (53, '1', 1, 11111111, '11', 'byphone', 0);
INSERT INTO `user_info` VALUES (55, '1', 1, 1, '111', 'byphone', 0);

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrypt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `use_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (18, 'ICy5YqxZB1uWSwcVLSNLcA==', 40);
INSERT INTO `user_password` VALUES (19, 'xMpCOKC5I4INzFCab3WEmw==', 0);
INSERT INTO `user_password` VALUES (20, 'xMpCOKC5I4INzFCab3WEmw==', 0);
INSERT INTO `user_password` VALUES (21, 'ICy5YqxZB1uWSwcVLSNLcA==', 0);
INSERT INTO `user_password` VALUES (22, 'xMpCOKC5I4INzFCab3WEmw==', 52);
INSERT INTO `user_password` VALUES (23, 'xMpCOKC5I4INzFCab3WEmw==', 53);
INSERT INTO `user_password` VALUES (24, 'xMpCOKC5I4INzFCab3WEmw==', 55);

SET FOREIGN_KEY_CHECKS = 1;
```



## 2. MyBatis-Plus代码生成

### 2.1 数据源配置

修改`application.yml`文件：

```yaml
spring:
  # 数据库连接配置
  datasource:
    url: jdbc:mysql://localhost:3306/imooc_miaosha?serverTimezone=Asia/Shanghai
    username: root
    password: root
    type: com.zaxxer.hikari.HikariDataSource
  # 开发插件
  devtools:
    livereload:
      enabled: true
    restart:
      enabled: true

# mybatis配置
mybatis-plus:
  global-config:
    db-config:
      id-type: auto
      logic-not-delete-value: 0
      logic-delete-value: 1
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath:/mapper/**/*.xml

```

### 2.2 使用代码生成器

在`src/test/java`任意子目录创建类：

```java
public class MyBatisPlusCodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!StringUtils.isBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("hundanli");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/imooc_miaosha?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.tcl.imooc.miaosha");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板，可以使用默认
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setXml("templates/mapper.xml");
        templateConfig.setMapper("templates/mapper.java");


        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
```

执行`main`方法按提示输入模块名"user"和两个表名"user_info,user_password"。执行完成后即可看到生成了user模块的controller，service，mapper，xml以及entity层的代码，然后稍微对service层的继承关系做修改即可。另外需要做的事情是

- 在mapper层添加`@Repository`注解，如：

  ```java
  @Repository
  public interface UserInfoMapper extends BaseMapper<UserInfo> {
  
  }
  ```

- 主启动类添加`@MapperScan`注解

  ```java
  @SpringBootApplication
  @MapperScan(basePackages = "com.tcl.imooc.miaosha.*.mapper")
  public class MiaoshaApplication {
      ...
  }
  ```

  

### 2.3 编写查询测试

#### 1. service

```java
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    UserInfoMapper mapper;

    @Override
    public UserInfo getById(Integer id) {
        return mapper.selectById(id);
    }

}
```

#### 2. controller

```java
@RestController
@RequestMapping("/user/user-info")
public class UserInfoController {

    @Autowired
    IUserInfoService service;

    @GetMapping("")
    public UserInfo queryById(@RequestParam(name = "id") Integer id) {
        return service.getById(id);
    }

}
```

#### 3. API测试

程序启动后，可以使用IDEA的Rest API工具进行测试。首先在`src/test/resources/`目录下创建test.http文件，编写下列内容：

```http
###
GET http://localhost:8080/user/user-info?id=40
```

点击左边绿色的三角按钮即可运行测试。



## 3. 通用类设计

创建`common`包

### 3.1 返回类型

```java
@Data
public class Result {

    /**
     *  若status=success，则data内返回前端需要的json数据
     *  若status=fail，则data内使用通用的错误码格式
     */
    private Object data;
    /**
     * 表明对应请求的返回处理结果“success”或“fail”
     */
    private String status;

    public static Result success(Object data) {
        return new Result(data, "success");
    }

    public static Result fail(Object data) {
        return new Result(data, "fail");
    }

    public Result() {
    }

    public Result(Object data, String status) {
        this.data = data;
        this.status = status;
    }

}
```

### 3.2 错误信息类

#### 1. 定义错误接口类

```java
public interface Error {

    /** 错误码
     * @return int
     */
    int getErrorCode();

    /** 错误信息
     * @return String
     */
    String getErrorMsg();

    /** 设置信息
     * @param msg String
     */
    Error setErrorMsg(String msg);
}
```



#### 2. 定义错误信息枚举类

```java
public enum ErrorEnum implements Error {
    /**
     * 枚举实例
     */
    DATA_INVALID(100001, "参数不合法"),
    DATA_NOT_EXIST(400001, "数据不存在"),
    UNKNOWN_ERROR(500000, "未知错误")
    ;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public Error setErrorMsg(String msg) {
        this.errorMsg = errorMsg;
        return this;
    }

    private final int errorCode;
    private String errorMsg;

    ErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}

```



#### 3. 定义业务异常类

这里使用包装器模式，将`Error`对象转为`BusinessException`。

```java
public class BusinessException extends Exception implements Error{

    private final Error error;

    /**
     * @param error 包装通用错误类构造异常实例
     */
    public BusinessException(Error error) {
        super();
        this.error = error;
    }

    public BusinessException(Error error, String msg) {
        super();
        this.error = error;
        this.setErrorMsg(msg);
    }


    @Override
    public int getErrorCode() {
        return error.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return error.getErrorMsg();
    }

    @Override
    public Error setErrorMsg(String msg) {
        this.error.setErrorMsg(msg);
        return this;
    }
}
```



### 3.3 全局异常处理

使用Spring MVC的`@ControllerAdvice`捕捉异常和进行处理。

```java
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("业务调用抛出异常: ", e.getCause());
        HashMap<String, Object> data = new HashMap<>(2);
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            data.put("errorCode", businessException.getErrorCode());
            data.put("errorMsg", businessException.getErrorMsg());
        } else {
            data.put("errorCode", ErrorEnum.UNKNOWN_ERROR.getErrorCode());
            data.put("errorMsg", ErrorEnum.UNKNOWN_ERROR.getErrorMsg());
        }
        return Result.fail(data);
    }

}
```

### 3.4 重写Controller

将返回类型统一为前面定义的`Result`，以及显示抛出异常。

```java
    @GetMapping("")
    public Result queryById(@RequestParam(name = "id") Integer id) throws BusinessException{
        UserInfo userInfo = service.getById(id);
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.DATA_NOT_EXIST);
        }
        return Result.success(userInfo);
    }
```



## 4. 用户管理模块

### 4.1 OTP验证码

术语解析：**一次性密码**（英语：one-time password，简称**OTP**），又称动态密码或单次有效密码，是指电脑系统或其他数字设备上只能使用一次的密码，有效期为只有一次登录会话或交易。[概念来源](https://zh.wikipedia.org/wiki/%E4%B8%80%E6%AC%A1%E6%80%A7%E5%AF%86%E7%A2%BC)

#### 4.1.1 后台逻辑

在controller包下新建子包vo，创建类：

```java
@Data
public class TelephoneVo {

    @NotBlank
    private String telephone;
}
```

Controller方法：

```java
    @PostMapping(value = "/getotp")
    public Result getOtp(@RequestBody @Valid TelephoneVo telephone, HttpServletRequest request) {
        int otp = ThreadLocalRandom.current().nextInt(100000, 1000_000);
        String otpCode = String.valueOf(otp);
        request.getSession().setAttribute(telephone.getTelephone(), otpCode);
        // 将OTP验证码同对应用户的手机号关联，使用httpSession的方式绑定手机号与OTPCDOE
        log.error("{} ==> {}", telephone, otpCode);
        return Result.success(null);
    }
```

为了允许跨域，在Controller类上添加注解：`@CrossOrigin`

#### 4.1.2 页面开发和美化

页面美化使用了基于bootstrap的metronic样式模板。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>获取验证码</title>
    <link href="asserts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="content">
    <h3 class="form-title">手机验证</h3>
    <div class="form-group">
        <label class="control-label">手机号</label>
        <div>
            <label for="telephone">
                <input class="form-control" type="text" placeholder="手机号" name="telephone" id="telephone"/>
            </label>
        </div>
    </div>
    <div class="form-actions">
        <button class="btn blue" id="getotp" type="submit">
            获取验证码
        </button>
    </div>
</div>


<script src="https://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#getotp").click(function () {
            let telephone = $("#telephone").val();
            console.log(telephone);

            if (telephone === null || telephone.trim() === "") {
                alert("请填写手机号!")
                return false;
            }

            let data = {"telephone": telephone};
            data = JSON.stringify(data);

            $.ajax({
                url: "http://localhost:8080/user/user-info/getotp",
                method: "POST",
                contentType: "application/json",
                data: data,
                success: function (data) {
                    if (data.status === "success") {
                        alert("验证码发送成功，请查收！");
                    } else {
                        alert("验证码发送失败，请稍后重试！");
                    }
                }
            });

        });

    });
</script>
</body>
</html>
```

### 4.2 用户注册

#### 4.2.1 后台逻辑

RegisterVo：

```java
@Data
public class RegisterVo {
    @NotBlank(message = "用户名不能为空！")
    private String name;
    @NotNull(message = "性别不能为空！")
    @Min(value = 0)
    private Integer gender;
    @NotNull(message = "年龄不能为空！")
    @Min(0)
    private Integer age;
    @NotBlank(message = "手机号码不能为空！")
    private String telephone;
    @NotBlank(message = "登录方式不能为空！")
    private String registerMode;
    @NotBlank(message = "验证码不能为空！")
    private String otpCode;

    @NotBlank(message = "密码不能为空！")
    private String password;
}
```

Controller：

```java
    @PostMapping("/register")
    public Result register(@RequestBody @Valid RegisterVo registerVo, HttpServletRequest request) throws BusinessException, NoSuchAlgorithmException {
        String otpCode = registerVo.getOtpCode();
        String sessionOtp = (String) request.getSession().getAttribute(registerVo.getTelephone());
        if (!StringUtils.equals(otpCode, sessionOtp)) {
            throw new BusinessException(ErrorEnum.DATA_INVALID, ErrorEnum.DATA_INVALID.getErrorMsg());
        }
        service.register(registerVo);

        return Result.success(null);
    }
```

Service：

```java
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void register(RegisterVo registerVo) throws NoSuchAlgorithmException, BusinessException {
        // 1.保存用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(registerVo, userInfo);
        try {
            userInfoMapper.insert(userInfo);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ErrorEnum.DATA_INVALID.setErrorMsg("手机号码已经被注册"));
        }

        // 2.密码加密+保存
        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(userInfo.getId());
        userPassword.setEncryptPassword(encryptPassword(registerVo.getPassword()));
        userPasswordMapper.insert(userPassword);

    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(digest.digest(password.getBytes()));
    }
```

此外，还可以在全局异常处理类中添加一个方法，用于捕捉参数类型转换的异常：

```java
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleException(HttpMessageNotReadableException e) {
        log.error("业务调用抛出异常: {}", e.getMessage());
        HashMap<String, Object> data = new HashMap<>(2);
        data.put("errorCode", ErrorEnum.DATA_INVALID.getErrorCode());
        data.put("errorMsg", ErrorEnum.DATA_INVALID.getErrorMsg());
        return Result.fail(data);
    }
```



#### 4.2.2 页面开发

在`resources/static`目录下新建`register.html`：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script src="https://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
    <link href="asserts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="content">
    <h3 class="form-title">用户注册</h3>
    <div class="form-group">
        <label class="control-label">手机号</label>
        <div>
            <input class="form-control" type="text" placeholder="手机号" name="telephone" id="telephone"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">验证码</label>
        <div>
            <input class="form-control" type="text" placeholder="验证码" name="otpCode" id="otpCode"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">用户昵称</label>
        <div>
            <input class="form-control" type="text" placeholder="用户昵称" name="name" id="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">性别</label>
        <div class="form-control">
            <label for="male">男</label><input type="radio" value="1" name="gender" id="male"/>
            <label for="female">女</label><input type="radio" value="0" name="gender" id="female"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">年龄</label>
        <div>
            <input class="form-control" type="text" placeholder="年龄" name="age" id="age"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label">密码</label>
        <div>
            <input class="form-control" type="password" placeholder="密码" name="password" id="password"/>
        </div>
    </div>
    <div class="form-actions">
        <button class="btn blue" id="register" type="submit">
            注册
        </button>
    </div>
</div>

<script>
    jQuery(document).ready(function () {

        //绑定otp的click事件用于向后端发送获取手机验证码的请求
        $("#register").on("click", function () {

            let telephone = $("#telephone").val();
            let otpCode = $("#otpCode").val();
            let password = $("#password").val();
            let age = $("#age").val();
            let gender = $('input[name="gender"]:checked').val();
            let name = $("#name").val();
            if (telephone == null || telephone === "") {
                alert("手机号不能为空");
                return false;
            }
            if (otpCode == null || otpCode === "") {
                alert("验证码不能为空");
                return false;
            }
            if (name == null || name === "") {
                alert("用户名不能为空");
                return false;
            }
            if (gender == null || gender === "") {
                alert("性别不能为空");
                return false;
            }
            if (age == null || age === "") {
                alert("年龄不能为空");
                return false;
            }
            if (password == null || password === "") {
                alert("密码不能为空");
                return false;
            }

            //映射到后端@RequestMapping(value = "/register", method = {RequestMethod.POST}})
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/user/register",
                data: JSON.stringify({
                    "telephone": telephone,
                    "otpCode": otpCode,
                    "password": password,
                    "age": age,
                    "gender": gender,
                    "name": name,
                    "registerMode": "byphone"
                }),
                //允许跨域请求
                xhrFields: {withCredentials: true},

                success: function (data) {
                    if (data.status === "success") {
                        alert("注册成功");
                    } else {
                        alert("注册失败：" + data.data.errorMsg);
                    }
                },
                error: function (data) {
                    alert("注册失败：" + data.responseText);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
```



### 4.3 用户登录

#### 4.3.1 后台逻辑

新建LoginVo类，用于接收手机号和密码：

```java
@Data
public class LoginVo {
    @NotBlank
    private String telephone;
    @NotBlank
    private String password;
}
```

Controller：

```java
    @PostMapping("/login")
    public Result login(@RequestBody @Valid LoginVo loginVo, HttpServletRequest request) throws NoSuchAlgorithmException, BusinessException {
        UserInfo userInfo = service.login(loginVo);
        request.getSession().setAttribute("IS_LOGIN", true);
        request.getSession().setAttribute("LOGIN_USER", userInfo);
        return Result.success("登录成功");
    }
```

Service：

```java
    @Override
    public UserInfo login(LoginVo loginVo) throws BusinessException, NoSuchAlgorithmException {
        // 检查用户是否已经注册
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getTelephone, loginVo.getTelephone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.DATA_NOT_EXIST.setErrorMsg("该手机号码尚未注册！"));
        }
        // 校验密码是否正确
        LambdaQueryWrapper<UserPassword> passwordWrapper = new LambdaQueryWrapper<>();
        passwordWrapper.eq(UserPassword::getUserId, userInfo.getId());
        passwordWrapper.eq(UserPassword::getEncryptPassword, encryptPassword(loginVo.getPassword()));
        if (userPasswordMapper.selectCount(passwordWrapper) < 1) {
            throw new BusinessException(ErrorEnum.PASSWORD_INCORRECT);
        }
        return userInfo;
    }
```

#### 4.3.2 页面开发

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script src="https://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
    <link href="asserts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="content">
    <h3 class="form-title">用户登录</h3>
    <div class="form-group">
        <label class="control-label" for="telephone">手机号</label>
        <div>
            <input class="form-control" type="text" placeholder="手机号" name="telephone" id="telephone"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label" for="password">密码</label>
        <div>
            <input class="form-control" type="password" placeholder="密码" name="password" id="password"/>
        </div>
    </div>
    <div class="form-actions">
        <button class="btn blue" id="login" type="submit">
            登录
        </button>
        <button class="btn green" id="register" type="submit">
            注册
        </button>
    </div>
</div>
<script>
    jQuery(document).ready(function () {

        //绑定注册按钮的click事件用于跳转到注册页面
        $("#register").on("click", function () {
            window.location.href = "getotp.html";
        });

        //绑定登录按钮的click事件用于登录
        $("#login").on("click", function () {

            let telephone = $("#telephone").val();
            let password = $("#password").val();
            if (telephone == null || telephone.trim() === "") {
                alert("手机号不能为空");
                return false;
            }
            if (password == null || password.trim() === "") {
                alert("密码不能为空");
                return false;
            }

            //映射到后端@PostMapping(value = "/login")
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/user/login",
                data: JSON.stringify({
                    "telephone": telephone,
                    "password": password
                }),
                //允许跨域请求
                xhrFields: {withCredentials: true},
                success: function (data) {
                    if (data.status === "success") {
                        alert("登录成功");
                    } else {
                        alert("登录失败：" + data.data.errorMsg);
                    }
                },
                error: function (data) {
                    alert("登录失败：" + data.responseText);
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
```



## 5. 商品信息模块

### 5.1 代码生成

运行MyBatis-Plus代码生成器，模块名和数据表分别输入"item"以及"item,item_stock"，即可快速生成商品模块的controller，service，mapper和entity的代码。

### 5.2 商品添加

#### 5.2.1 后台接口

ItemVo：

```java
@Data
public class ItemVo {

    private Integer id;
    /**
     * 名称
     */
    @NotBlank
    private String title;

    /**
     * 库存
     */
    @Min(0)
    @NotNull
    private Integer stock;
    /**
     * 描述
     */
    @NotBlank
    private String description;
    /**
     * 价格
     */
    @Min(0)
    @NotNull
    private BigDecimal price;
    /**
     * 销量
     */
    @Null
    private Integer sales;
    /**
     * 图片
     */
    @NotBlank
    private String imgUrl;
}

```

Controller：

```java
    @PostMapping("/create")
    public Result createItem(@RequestBody @Valid ItemVo itemVo) {
        itemVo = service.createItem(itemVo);
        return Result.success(itemVo);
    }
```

Service：

```java
    @Override
    public ItemVo createItem(ItemVo itemVo) {
        Item item = new Item();
        BeanUtils.copyProperties(itemVo, item);
        itemMapper.insert(item);

        ItemStock itemStock = new ItemStock();
        itemStock.setItemId(item.getId());
        itemStock.setStock(itemVo.getStock());
        stockMapper.insert(itemStock);
        itemVo.setId(item.getId());

        return itemVo;
    }
```

#### 5.2.2 页面开发

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创建商品</title>
    <script src="https://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
    <link href="asserts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div class="content">
    <h3 class="form-title">创建商品</h3>
    <div class="form-group">
        <label class="control-label" for="title">商品名</label>
        <div>
            <input class="form-control" type="text" name="title" id="title"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label" for="description">商品描述</label>
        <div>
            <input class="form-control" type="text" name="description" id="description"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label" for="price">价格</label>
        <div>
            <input class="form-control" type="number" step="any" name="price" id="price"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label" for="imgUrl">图片</label>
        <div>
            <input class="form-control" type="text" name="imgUrl" id="imgUrl"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label" for="stock">库存</label>
        <div>
            <input class="form-control" type="number" step="1" name="stock" id="stock"/>
        </div>
    </div>
    <div class="form-actions">
        <button class="btn blue" id="create" type="submit">
            创建
        </button>
    </div>
</div>

</body>

<script>
    jQuery(document).ready(function () {

        //绑定register按钮的click事件
        $("#create").on("click", function () {

            let title = $("#title").val().trim();
            let description = $("#description").val().trim();
            let price = $("#price").val().trim();
            let imgUrl = $("#imgUrl").val().trim();
            let stock = $("#stock").val().trim();

            if (title == null || title === "") {
                alert("商品名不能为空");
                return false;
            }
            if (description == null || description === "") {
                alert("商品描述不能为空");
                return false;
            }
            if (price == null || price === "") {
                alert("价格不能为空");
                return false;
            }
            if (imgUrl == null || imgUrl === "") {
                alert("图片不能为空");
                return false;
            }
            if (stock == null || stock === "") {
                alert("库存不能为空");
                return false;
            }

            //映射到后端/create
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/item/create",
                data: JSON.stringify({
                    "title": title,
                    "description": description,
                    "price": price,
                    "imgUrl": imgUrl,
                    "stock": stock
                }),
                //允许跨域请求
                xhrFields: {withCredentials: true},
                success: function (data) {
                    if (data.status === "success") {
                        alert("创建成功");
                    } else {
                        alert("创建失败: " + data.data.errorMsg);
                    }
                },
                error: function (data) {
                    alert("创建失败: " + data.responseText);
                }
            });
            return false;
        });
    });
</script>
</html>
```

### 5.3 商品列表

#### 5.3.1 后台接口

PageVo:

```java
@Data
public class PageVo {
    @Min(1)
    @NotNull
    Integer pageNum;
    @Min(5)
    @NotNull
    Integer pageSize;
}

```

Controller：

```java
    @PostMapping("/list")
    public Result getItemList(@RequestBody @Valid PageVo pageVo) {
        return Result.success(service.listItem(pageVo.getPageNum(), pageVo.getPageSize()));
    }
```

Service：

```java
    @Override
    public IPage<ItemVo> listItem(Integer pageNum, Integer pageSize) {
        List<ItemVo> itemVos;
        Page<Item> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Item::getSales);
        Page<Item> items = itemMapper.selectPage(page, queryWrapper);

        itemVos = items.getRecords().stream().map(new Function<Item, ItemVo>() {
            @Override
            public ItemVo apply(Item item) {
                // 属性拷贝
                ItemVo itemVo = new ItemVo();
                BeanUtils.copyProperties(item, itemVo);

                // 查询库存
                LambdaQueryWrapper<ItemStock> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ItemStock::getItemId, item.getId());
                ItemStock itemStock = stockMapper.selectOne(queryWrapper);
                itemVo.setStock(itemStock.getStock());

                return itemVo;
            }
        }).collect(Collectors.toList());

        // 构造返回
        Page<ItemVo> voPage = new Page<>();
        voPage.setRecords(itemVos);
        return voPage;
    }

```

#### 5.3.2 页面开发

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <script src="https://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
    <link href="asserts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="asserts/css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="content">
    <h3 class="form-title">商品列表</h3>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>商品名</th>
                <th>商品图片</th>
                <th>商品描述</th>
                <th>商品价格</th>
                <th>商品库存</th>
                <th>商品销量</th>
            </tr>
            </thead>

            <tbody id="container">

            </tbody>
        </table>
    </div>
</div>
</body>

<script>
    // 定义全局商品数组信息
    let g_itemList = [];
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf8",
            url: "http://localhost:8080/item/list",
            data: JSON.stringify({
                "pageNum": 1,
                "pageSize": 10
            }),
            xhrFields: {
                withCredentials: true,
            },
            success: function (data) {
                if (data.status === "success") {
                    g_itemList = data.data.records;
                    reloadDom();
                } else {
                    alert("获取商品信息失败: " + data.data.errorMsg);
                }
            },
            error: function (data) {
                alert("获取商品信息失败: " + data.responseText);
            }
        });
    });

    function reloadDom() {
        for (let i = 0; i < g_itemList.length; i++) {
            let itemVO = g_itemList[i];
            let dom =
                "<tr data-id='" + itemVO.id + "' id='itemDetail" + itemVO.id + "'>\
			<td>" + itemVO.title + "</td>\
			<td><img style='width:100px;height:auto;' src='" + itemVO.imgUrl + "'/></td>\
			<td>" + itemVO.description + "</td>\
			<td>" + itemVO.price + "</td>\
			<td>" + itemVO.stock + "</td>\
			<td>" + itemVO.sales + "</td>\
			</tr>";
            $("#container").append($(dom));
            //点击一行任意的位置 跳转到商品详情页
            $("#itemDetail" + itemVO.id).on("click", function (e) {
                window.location.href = "getitem.html?id=" + $(this).data("id");
            });
        }

    }
</script>

</html>
```

### 5.4 商品详情

类似，不再列出。查看完整代码。



## 6. 商品交易模块

### 6.1  代码生成

数据库设计：

订单表

```sql
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_price` decimal(40, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;
```

用于生成交易流水号（订单ID）的表：

```sql
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;
```

与前面类似，使用MyBatis Plus生成器生成代码。

**注意**：因为订单ID是自主生成，因此在`OrderInfo`类的id字段以及`SequenceInfo`的name字段添加注解`@TableId(type = IdType.INPUT)`。

### 6.2 交易下单

#### 1. 订单Service逻辑

```java
@Data
public class OrderVo {
    @NotNull
    @Min(1)
    private Integer itemId;
//    @NotNull
    @Min(1)
    private Integer userId;
    @NotNull
    @Min(1)
    private Integer amount;
}
```

```java
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public OrderInfo createItem(OrderVo orderVo) throws BusinessException {
        // 判断用户是否，订单号是否合法
        UserInfo userInfo = userInfoService.getById(orderVo.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("用户不存在！"));
        }
        ItemVo itemVo = itemService.getItemById(orderVo.getItemId());
        if (itemVo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("商品不存在！"));
        }
        // 商品减库存
        itemService.decreaseStock(orderVo.getItemId(), orderVo.getAmount());


        // 订单入库
        OrderInfo orderInfo = new OrderInfo();
        // 生成交易流水号（订单ID）
        orderInfo.setId(generateOrderId());
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setItemId(itemVo.getId());
        orderInfo.setItemPrice(itemVo.getPrice());
        orderInfo.setAmount(orderVo.getAmount());
        orderInfo.setOrderPrice(itemVo.getPrice().multiply(BigDecimal.valueOf(orderVo.getAmount())));
        orderInfoMapper.insert(orderInfo);

        // 修改商品销量
        itemService.increaseSales(itemVo.getId(), orderInfo.getAmount());

        return orderInfo;
    }
```

订单号生成：

```java
    /**
     * @return 订单号
     */
    private String generateOrderId() {
        // 订单号包含16位
        StringBuilder orderId = new StringBuilder();
        // 前8位是时间：年月日
        String prefix = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).replace("-", "");
        orderId.append(prefix);

        // 中间6位是递增序列
        SequenceInfo sequenceInfo = sequenceInfoMapper.selectByName("order_info");
        Integer currentValue = sequenceInfo.getCurrentValue();
        // 修改数据库流水号
        sequenceInfo.setCurrentValue(currentValue + sequenceInfo.getStep());
        sequenceInfoMapper.updateById(sequenceInfo);
        String mid = currentValue.toString();
        for (int i = 0; i < 6 - mid.length(); i++) {
            orderId.append('0');
        }
        orderId.append(mid);

        //最后2位是数据库分库分表信息，保留
        orderId.append("00");
        return orderId.toString();
    }
```

#### 2. 商品库存和销量修改

```java
    @Override
    public void decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        ItemStock itemStock = stockService.selectByItemId(itemId);
        if (itemStock.getStock() < amount) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("商品库存不足！仅剩" + itemStock.getStock() + "件"));
        }
        itemStock.setStock(itemStock.getStock() - amount);
        stockMapper.updateById(itemStock);
    }

    @Override
    public void increaseSales(Integer itemId, Integer amount) {
        itemMapper.increaseSales(itemId, amount);
    }
```

```xml
    <update id="increaseSales">
        update item
        set sales = sales + #{amount}
        where id = #{itemId};
    </update>
```

#### 3. Controller逻辑

```java
    @Autowired
    IOrderInfoService orderInfoService;

    @PostMapping("/createorder")
    public Result createOrder(@RequestBody @Valid OrderVo orderVo, HttpServletRequest request) throws BusinessException {
        // 验证是否登录
        Boolean isLogin = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin) {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserInfo user = (UserInfo) request.getSession().getAttribute("LOGIN_USER");
        orderVo.setUserId(user.getId());

        OrderInfo orderInfo = orderInfoService.createItem(orderVo);
        return Result.success(orderInfo);
    }
```

#### 4. getitem.html页面修改

```javascript
        $("#createOrder").on("click", function () {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/order/createorder",
                contentType: "application/json",
                data: JSON.stringify({
                    "itemId": g_itemVO.id,
                    "amount": 1, //暂时写死为一件
                    "promoId": g_itemVO.promoId,
                }),
                xhrFields: {
                    withCredentials: true
                },
                success: function (data) {
                    if (data.status === "success") {
                        alert("下单成功");
                        window.location.reload();
                    } else {
                        alert("下单失败: " + data.data.errorMsg);
                        if (data.data.errorCode === 400003) {
                            window.location.href = "login.html";
                        }
                    }
                },
                error: function (data) {
                    alert("下单失败：" + data.responseText);
                }
            });
        });
```



## 7. 秒杀模块

### 7.1 代码生成

订单模型：

```java
@Data
public class PromoVo {
    private Integer id;

    private String promoName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;


    /**
     * 促销活动状态：1表示未开始，2表示进行中，3表示已结束
     */
    private Integer status;
}
```

数据库：

```sql
CREATE TABLE `promo`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;
```

运行代码生成器。



### 7.2 促销与商品整合

#### 1. 促销活动信息查询

```java
@Service
public class PromoServiceImpl implements IPromoService {

    @Autowired
    PromoMapper mapper;

    @Override
    public PromoVo getPromoByItemId(Integer itemId) {
        LambdaQueryWrapper<Promo> queryWrapper = new LambdaQueryWrapper<>();
        Promo promo = mapper.selectOne(queryWrapper.eq(Promo::getItemId, itemId));
        if (promo == null) {
            return null;
        }
        PromoVo promoVo = new PromoVo();

        BeanUtils.copyProperties(promo, promoVo);

        // 设置促销活动状态
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(promo.getEndDate())) {
            promoVo.setStatus(3);
        } else if (now.isAfter(promo.getStartDate())) {
            promoVo.setStatus(2);
        } else {
            promoVo.setStatus(1);
        }
        return promoVo;
    }
}
```

#### 2. 商品信息查询修改

ItemVo类增加PromoVo字段：

```java
    /**
     * 促销活动信息
     */
    private PromoVo promoVo;
```

ItemService：

```java
    @Override
    public ItemVo getItemById(Integer itemId) {
        // 查询基本信息
        ItemVo itemVo = new ItemVo();
        Item item = itemMapper.selectById(itemId);
        BeanUtils.copyProperties(item, itemVo);

        // 查询库存信息
        ItemStock itemStock = stockService.selectByItemId(itemId);
        itemVo.setStock(itemStock.getStock());

        // 查询促销信息
        PromoVo promoVo = promoService.getPromoByItemId(itemId);
        if (promoVo != null && promoVo.getStatus() < 3) {
            itemVo.setPromoVo(promoVo);
        }
        return itemVo;
    }
```

OrderService：

```java
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public OrderInfo createItem(OrderVo orderVo) throws BusinessException {
        OrderInfo orderInfo = new OrderInfo();

        // 判断用户是否，订单号是否合法
        UserInfo userInfo = userInfoService.getById(orderVo.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("用户不存在！"));
        }
        ItemVo itemVo = itemService.getItemById(orderVo.getItemId());
        if (itemVo == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("商品不存在！"));
        }
        // 判断促销活动是否合法
        if (orderVo.getPromoId() != null) {
            PromoVo promoVo = promoService.getPromoByItemId(orderVo.getPromoId());
            if (promoVo == null || !promoVo.getItemId().equals(itemVo.getId())) {
                throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("活动信息不准确"));
            } else if (promoVo.getStatus() != 2) {
                throw new BusinessException(ErrorEnum.PARAMETER_INVALID.setErrorMsg("活动尚未开始"));
            }
            // 设置促销价格和订单促销id
            itemVo.setPrice(promoVo.getPromoItemPrice());
            orderInfo.setPromoId(promoVo.getId());

        }

        // 商品减库存
        itemService.decreaseStock(orderVo.getItemId(), orderVo.getAmount());

        // 订单入库
        // 生成交易流水号（订单ID）
        orderInfo.setId(generateOrderId());
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setItemId(itemVo.getId());
        orderInfo.setItemPrice(itemVo.getPrice());
        orderInfo.setAmount(orderVo.getAmount());
        orderInfo.setOrderPrice(itemVo.getPrice().multiply(BigDecimal.valueOf(orderVo.getAmount())));
        orderInfoMapper.insert(orderInfo);

        // 修改商品销量
        itemService.increaseSales(itemVo.getId(), orderInfo.getAmount());

        return orderInfo;
    }

```

前端页面修改：

```java
    $(document).ready(function () {
        // 获取商品详情
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/item/get",
            data: {
                "id": getParam("id"),
            },
            xhrFields: {
                withCredentials: true
            },
            success: function (data) {
                if (data.status === "success") {
                    g_itemVO = data.data;
                    reloadDom();
                    setInterval(reloadDom, 1000);
                } else {
                    alert("获取信息失败: " + data.data.errorMsg);
                }
            },
            error: function (data) {
                alert("获取信息失败: " + data.responseText);
            }
        });

        $("#createOrder").on("click", function () {
            let amount = $("#amount").val();
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/order/createorder",
                contentType: "application/json",
                data: JSON.stringify({
                    "itemId": g_itemVO.id,
                    "amount": amount,
                    "promoId": g_itemVO.promoVo.id,
                }),
                xhrFields: {
                    withCredentials: true
                },
                success: function (data) {
                    if (data.status === "success") {
                        alert("下单成功");
                        window.location.reload();
                    } else {
                        alert("下单失败: " + data.data.errorMsg);
                        if (data.data.errorCode === 400003) {
                            window.location.href = "login.html";
                        }
                    }
                },
                error: function (data) {
                    alert("下单失败: " + data.responseText);
                }
            });
        });
    });

    function reloadDom() {
        $("#title").text(g_itemVO.title);
        $("#imgUrl").attr("src", g_itemVO.imgUrl);
        $("#description").text(g_itemVO.description);
        $("#price").text(g_itemVO.price);
        $("#stock").text(g_itemVO.stock);
        $("#sales").text(g_itemVO.sales);
        let promoVo = g_itemVO.promoVo;
        if (promoVo.status === 1) {
            // 秒杀活动还未开始
            console.log(promoVo.startDate);
            let startTime = promoVo.startDate.replace(new RegExp("-", "gm"), "/");
            startTime = (new Date(startTime)).getTime();
            let nowTime = Date.parse(new Date());
            let delta = (startTime - nowTime) / 1000;
            if (delta <= 0) {
                // 活动开始了
                promoVo.status = 2;
                reloadDom();
            }
            $("#promoStartDate").text("秒杀活动将于：" + promoVo.startDate + " 开始售卖 倒计时：" + delta + "  秒");
            $("#promoPrice").text(promoVo.promoItemPrice);
            $("#createOrder").attr("disabled", true);
        } else if (promoVo.status === 2) {
            // 秒杀活动进行中
            $("#promoStartDate").text("秒杀正在进行中");
            $("#promoPrice").text(promoVo.promoItemPrice);
            $("#createOrder").attr("disabled", false);
            $("#normalPriceContainer").hide();
        }
    }
```





## 小结

### 1. 应用分层设计

建议首先根据产品需求文档设计出业务对象模型（Business Object Model），然后再设计出数据对象模型（Data Object Model）。各层的对象应该严格区分开来。但是有时候严格区分又会出现冗余设计。

### 2. 数据库设计

数据表设计时要考虑到业务层的需求和数据库的扩展性进行分表。

### 3. 跨域Session

1.通过打开本地html文件的方式，chrome浏览器不能跨域使用session，但是edge浏览器支持。

2.通过不同端口启动应用互相调用，两个浏览器都可以实现跨域使用session。







