package com.devdyna.synergy.api.utils;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public class LogUtil {
    private static Logger LOGGER;
    
        public LogUtil() {
            LogUtil.LOGGER = LogUtils.getLogger();
        }
    
        /**
         * send a message on logs
         */
        public static void info(String text) {
            LOGGER.info(text);
    }

            /**
         * send a error on logs
         */
        public static void error(String text) {
            LOGGER.error(text);
    }

    /**
     * send a decorative separator
     */
    public static void decor(int size) {
        if(size <=0)size = 10;
        String txt = "#";
        for(int i = 0;i<size;i++){
            txt.concat("-");
        }
        txt.concat("#");
        LOGGER.info(txt);
    }
}
