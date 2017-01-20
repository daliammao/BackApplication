package com.zpw.backapplication;

/**
 * @author: zhoupengwei
 * @time: 2017/1/19-下午4:51
 * @Email: zhoupengwei@qccr.com
 * @desc: 前后台切换监听
 */
public interface ForegroundChangeListener {
    /**
     * 前台转后台
     */
    void onAppFore2Back();

    /**
     * 后台转前台
     */
    void onAppBack2Fore();
}
