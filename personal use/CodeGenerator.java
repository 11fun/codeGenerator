package com.system.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;



public class CodeGenerator {

	// 固定
	private static final String projectPath = System.getProperty("user.dir").replace("\\", "/");
	private static final String outPutDir = projectPath + "/src/main/java";
	
	
	// 自定义
	private static final String dataBaseName = "test";
	private static final String URL= "192.168.1.116";
	private static final String Name="root";
	private static final String passWord="";
	private static final String packageName = "com";
	private static final String moduleName = "system";
	private static final String author = "11fun";
	
	
	private static String[] tableName = getTableNames();
//	private static String[] tableName = new String[] {"sysfile"};
	private static final String tablePrefix = "sys";

	private static final String driverName="com.mysql.cj.jdbc.Driver";
	private static final String dataBaseURL="jdbc:mysql://"+URL+":3306/"+dataBaseName
			+ "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
	private static Connection conn = null;

	@Test
	public void main()   {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 设置全局配置
        mpg.setGlobalConfig(getGlobalConfig());
        // 设置数据源配置
        mpg.setDataSource(getDataSourceConfig());
        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);
        // 自定义配置
        mpg.setCfg(getInjectionConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 配置模板(已有默认值)
        TemplateConfig templateConfig = new TemplateConfig();
      
        templateConfig.setXml(null);
        //自定义配置模板
       
        templateConfig.setController("/generatorTemplates/controller.java");
        templateConfig.setEntity("/generatorTemplates/entity.java");
        templateConfig.setEntityKt("/generatorTemplates/entity.kt");
        templateConfig.setMapper("/generatorTemplates/mapper.java");
        templateConfig.setService("/generatorTemplates/service.java");
        templateConfig.setServiceImpl("/generatorTemplates/serviceImpl.java");
        
      
        
        mpg.setTemplate(templateConfig);
        // 策略配置
        mpg.setStrategy(getStrategyConfig());
        mpg.execute();
	}

//	private static String getPackageName() {
//		File sourceFile = new File(outPutDir);
//		if (!sourceFile.isDirectory()) {
//			return "项目路径出错！";
//		}
//		File[] pkName = sourceFile.listFiles();
//		return pkName[0].getName();
//	}
//
//	private static String getModuleName() {
//		File sourceFile = new File(outPutDir + "/" + getPackageName());
//		if (!sourceFile.isDirectory()) {
//			return "项目路径出错！";
//		}
//		File[] mdlName = sourceFile.listFiles();
//		return mdlName[0].getName();
//	}

	private static GlobalConfig getGlobalConfig() {
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(outPutDir);
		gc.setAuthor(author);
		gc.setOpen(false);
		gc.setEntityName("%sDo");
		gc.setMapperName("%sMapper");
		gc.setServiceName("%sService");
		// 是否覆盖文件，默认false不覆盖
		gc.setFileOverride(true);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
		gc.setBaseColumnList(true);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		return gc;
	}

	private static DataSourceConfig getDataSourceConfig() {
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(dataBaseURL);
		// dsc.setSchemaName("public");
//        String driverName = "com.mysql.jdbc.Driver";
		dsc.setDriverName(driverName);
		dsc.setUsername(Name);
		dsc.setPassword(passWord);
		return dsc;
	}

	private static PackageConfig getPackageConfig() {
		// 包配置
		PackageConfig pc = new PackageConfig();
		// 包名称
		pc.setParent(packageName);
		// 模块名称
		pc.setModuleName(moduleName);
		return pc;
	}

	private static InjectionConfig getInjectionConfig() {
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "templates/mapper.xml.ftl";
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName().replace("Entity", "")
						+ "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		return cfg;
	}

	private static StrategyConfig getStrategyConfig() {
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
        strategy.setSuperControllerClass("com.common.controller.BaseController");
		// 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
		strategy.setInclude(tableName);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(tablePrefix + "_");
		strategy.setEntityTableFieldAnnotationEnable(true);
		return strategy;
	}

	
	private static String[] getTableNames() {
		DBinit();
		List<String> tableList = new ArrayList<>();
		try {
			// 获取数据库的元数据
			DatabaseMetaData dbMetaData = conn.getMetaData();
			// 从元数据中获取到所有的表名
			ResultSet rs = dbMetaData.getTables(dataBaseName, null, null, new String[] { "TABLE" });
			// 存放所有表名
			
			while (rs.next()) {
				tableList.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBclose();
		String[] tableNames = new String[tableList.size()];
		tableNames = tableList.toArray(tableNames);
		
		return tableNames;
	}

	public static void DBinit() {
				// 声明Connection对象
				try {
					conn = DriverManager.getConnection(dataBaseURL, Name, passWord);
					if (!conn.isClosed()) {
						System.out.println("数据库连接成功！");
					}else {
						System.out.println("数据库连接失败！");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	

	public static void DBclose() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("数据关闭异常");
			e.printStackTrace();
		}
	}
}
