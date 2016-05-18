package com.sungness.code.generate.engine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代码生成器应用程序启动入口
 * 加载bean配置文件后，从ApplicationContext 获取 CodeEngine bean 对象，
 * 并调用起start方法，启动生成程序。
 */
public class CodeApp {
    public static void main(String[] args) {
        try {
            ApplicationContext context =
                    new ClassPathXmlApplicationContext("sungness-code-context.xml");
            CodeEngine codeEngine = context.getBean(CodeEngine.class);
            codeEngine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

