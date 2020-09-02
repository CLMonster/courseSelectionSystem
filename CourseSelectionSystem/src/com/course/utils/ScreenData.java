package com.course.utils;

import java.awt.*;

/**
 * @author WangDebao
 * @create 2020-08-30 15:34
 */
public class ScreenData {

    /**
     * 获取当前电脑屏幕的宽度
     * @return
     */
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
