package com.fushin.easyrpc.core.logging;

import com.fushin.easyrpc.core.util.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 丁成文
 * @date 2022/2/22
 */
public final class LoggerFactory {
    private static final String EXTENSION_FOLDER_NAME = "META-INF/services/";
    private static Class<Logger> loggerClass;
    private static String loggerName;
    static{
        String key = System.getProperty("easy.logger","log4j");
        try {
            loggerName = key;
            loggerClass = load().get(key);
            Logger logger = getLogger(LoggerFactory.class);
            logger.info("using logging: {}", loggerName);
        }catch (Exception e){
            e.printStackTrace();
            loggerClass = null;
        }
    }
    private LoggerFactory(){

    }

    public static Logger getLogger(Class<?> clazz) {
        if(null == loggerClass){
            throw new NoSuchLoggerException(loggerName);
        }
        try {
            Constructor<Logger> c = loggerClass.getConstructor(Class.class);
            c.setAccessible(true);
            return c.newInstance(clazz);
        }catch(Exception e){
            throw new NoSuchLoggerException();
        }
    }

    private static Map<String, Class<Logger>> load()throws Exception{
        ClassLoader cl = ClassUtils.getClassLoader(LoggerFactory.class);
        if(null == cl){
            throw new RuntimeException("类加载器不能为空");
        }
        Enumeration<URL> urls = cl.getResources(EXTENSION_FOLDER_NAME + Logger.class.getName());
        Map<String, Class<Logger>> classMap = new HashMap<>(16);
        while (urls.hasMoreElements()) {
            URL resourceUrl = urls.nextElement();
            File file = new File(resourceUrl.getPath());
            try(Scanner scanner = new Scanner(new FileInputStream(file))){
                while (scanner.hasNext()) {
                    String classConfig = scanner.nextLine();
                    String[] classConfigs = classConfig.split("=");
                    classMap.put(classConfigs[0].trim(), (Class<Logger>) Class.forName(classConfigs[1].trim()));
                }
            }
        }
        return classMap;
    }


}
