package com.codeyuaiiao.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CodeGenerator {

        // 数据库连接地址
        private static final String URL = "jdbc:mysql://127.0.0.1:3306/webzp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        // 数据库连接驱动
        private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
        // 数据库连接用户名
        private static final String USER_NAME = "codeyuaiiao";
        // 数据库连接密码
        private static final String PASS_WORD = "3615yuhaijiao";
        // 开发人员
        private static final String AUTHOR = "codeyuaiiao";
        // 父包名
        private static final String PARENT = "com.codeyuaiiao";
        // 模块名
//        private static final String MODULENAME = "webzp";
        // .xml文件放置路径
        private static final String XML_PATH = "/src/main/resources/mapper/";

        /**
         * 自动生成代码
         */
        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();
            // TODO 全局配置
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            // 生成文件的输出目录【默认 D 盘根目录】
            gc.setOutputDir( projectPath + "/src/main/java");
            // 作者
            gc.setAuthor(AUTHOR);
            // 是否打开输出目录
            gc.setOpen(false);
            // controller 命名方式，注意 %s 会自动填充表实体属性
            gc.setControllerName("%sController");
            // service 命名方式
            gc.setServiceName("%sService");
            // serviceImpl 命名方式
            gc.setServiceImplName("%sServiceImpl");
            // mapper 命名方式
            gc.setMapperName("%sMapper");
            // xml 命名方式
            gc.setXmlName("%sMapper");
            // 开启 swagger2 模式
            gc.setSwagger2(true);
            // 是否覆盖已有文件
            gc.setFileOverride(true);
            // 是否开启 ActiveRecord 模式
            gc.setActiveRecord(true);
            // 是否在xml中添加二级缓存配置
            gc.setEnableCache(false);
            // 是否开启 BaseResultMap
            gc.setBaseResultMap(false);
            // XML columList
            gc.setBaseColumnList(false);
            // 全局 相关配置
            mpg.setGlobalConfig(gc);

            // TODO 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl(URL);
            dsc.setDriverName(DRIVER_NAME);
            dsc.setUsername(USER_NAME);
            dsc.setPassword(PASS_WORD);
            mpg.setDataSource(dsc);

            // TODO 包配置
            PackageConfig pc = new PackageConfig();
            // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
            pc.setParent(PARENT);
            // 模块名，可以不指定
//            pc.setModuleName(MODULENAME);
            // Controller包名
            pc.setController("controller");
            // Service包名
            pc.setService("service");
            // ServiceImpl包名
            pc.setServiceImpl("service.impl");
            // Mapper 包名
            pc.setMapper("mapper");
            // Entity包名
            pc.setEntity("entity");
            mpg.setPackageInfo(pc);

            // TODO 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            // 输出文件配置
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // Mapper.xml 文件存放地址及文件名
                    return projectPath + XML_PATH  + "/" + tableInfo.getEntityName() + "Mapper.xml";
                }
            });
            // 自定义输出文件
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null));

            // TODO 策略配置
            StrategyConfig strategy = new StrategyConfig();
            // 数据库表映射到实体的命名策略，驼峰原则
            strategy.setNaming(NamingStrategy.underline_to_camel);
            // 字数据库表字段映射到实体的命名策略，驼峰原则
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            // 实体是否生成 serialVersionUID
            strategy.setEntitySerialVersionUID(true);
            // 是否生成实体时，生成字段注解
            strategy.setEntityTableFieldAnnotationEnable(true);
            // 使用lombok
            strategy.setEntityLombokModel(true);
            // 设置逻辑删除键
            strategy.setLogicDeleteFieldName("del_flag");
            // TODO 指定生成的bean的数据库表名
            strategy.setInclude(scanner("表名，多个表使用英文逗号分割").split(","));
//            strategy.setInclude("tb_admin","tb_enterprise","tb_recruitment","tb_resume","tb_user");//对那一张表生成代码
            //生成实体时去掉表前缀
            strategy.setTablePrefix(pc.getModuleName() + "tb_");
            // 驼峰转连字符
            strategy.setControllerMappingHyphenStyle(true);
            mpg.setStrategy(strategy);
            // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());

            mpg.execute();
        }

        /**
         * <p>
         * 读取控制台内容
         * </p>
         */
        public static String scanner(String tip) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder help = new StringBuilder();
            help.append("请输入" + tip + "：");
            System.out.println(help.toString());
            if (scanner.hasNext()) {
                String ipt = scanner.next();
                if (StringUtils.isNotEmpty(ipt)) {
                    return ipt;
                }
            }
            throw new MybatisPlusException("请输入正确的" + tip + "！");
        }
    }