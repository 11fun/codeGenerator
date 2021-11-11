# codeGenerator
代码生成器

- 基于mybatis-plus代码生成器，Freemarker模版引擎。
- 根据MYSQL数据库的表，字段生成Springboot的controller，entity，server，serverImpl，mapper各层。
- 网上找的生成器驱动代码，修改了一下，自用。
- 修改了mybatis-plus自带的Entity模版，根据字段备注生成非空验证注解与长度验证注解。
- Maven引入：
- 	<!--代码生成器 -->
    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-generator</artifactId>
      <version>3.2.0</version>
    </dependency>
     <dependency>
       <groupId>org.freemarker</groupId>
       <artifactId>freemarker</artifactId>
       <version>2.3.30</version>
       <scope>test</scope>
          </dependency>
    <!--代码生成器-end -->
- generatorTemplate模版文件夹放项目target/classes/下。    
- 修改CodeGenerator参数。
- JUnit运行CodeGenerator即可。  
 
