package com.mall;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User: lanxinghua
 * Date: 2019/10/25 14:04
 * Desc: 逆向工程
 */
public class Generator {

    public static void main(String[] args) throws Exception{
        // 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        // 是否覆盖原代码
        boolean overwrite = true;
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration configuration = parser.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(configuration, callback, warnings);
        // 执行生成代码
        generator.generate(null);
        // 输出警告信息
        warnings.forEach(System.out::println);
    }
}
