package com.sungness.code.generate.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 文件读写工具类
 * Created by wanghongwei on 4/8/16.
 */
public class FileTools {
    private static final Logger log = LoggerFactory.getLogger(FileTools.class);

    /**
     * 将字符串写入指定文件名的文件中
     * @param fileName String 目标文件名
     * @param content String 文件内容
     */
    public static void writeStringToFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                log.warn("The file [" + fileName + "] existed, did no override!");
            } else {
                FileUtils.writeStringToFile(file, content, "UTF-8");
                log.debug("The new file created [" + fileName + "].");
            }
        } catch (Exception e) {
            log.error("The file write error [" + fileName + "]");
            e.printStackTrace();
        }
    }
}
