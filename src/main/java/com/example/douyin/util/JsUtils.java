package com.example.douyin.util;

import com.example.douyin.service.JsMethods;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
public class JsUtils {

    public static JsMethods loadJs(Class<JsMethods> clazz){
        JsMethods n = null;
        try {
            //创建一个脚本引擎管理器
            ScriptEngineManager manager = new ScriptEngineManager();
            //获取一个指定的名称的脚本管理器
            ScriptEngine engine = manager.getEngineByName("js");
            //获取js文件所在的目录的路径
            String jsName = Thread.currentThread().getClass().getResource("/").getPath() + "/js/X-Bogus.js";
            engine.eval(new FileReader(jsName));
            //从脚本引擎中返回一个给定接口的实现
            Invocable invocable = (Invocable) engine;
            return invocable.getInterface(clazz);
        }catch (Exception e){
         e.printStackTrace();
        }
        return n;
    }
}
