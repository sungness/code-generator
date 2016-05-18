package com.sungness.code.generate.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代码生成应用程序启动入口
 * 加载bean配置文件后，从ApplicationContext获取 GeneratorTask bean对象，
 * 并调用起start方法，启动生成程序。
 */
public class GeneratorApp {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "conf/gen-service.xml");
        GeneratorTask generatorTask = context.getBean(GeneratorTask.class);
        generatorTask.start();
    }
}

