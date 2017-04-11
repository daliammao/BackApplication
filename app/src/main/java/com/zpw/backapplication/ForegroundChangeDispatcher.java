package com.zpw.backapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhoupengwei
 * @time: 2017/1/19-下午4:58
 * @Email: 496946423@qq.com
 * @desc:
 */
public class ForegroundChangeDispatcher {
    private static List<ForegroundChangeListener> listeners =
            new ArrayList<ForegroundChangeListener>();

    public static void addListener(ForegroundChangeListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        listeners.add(listener);
    }

    public static void removeListener(ForegroundChangeListener listener) {
        listeners.remove(listener);
    }

    public static void onAppFore2Back() {
        for (ForegroundChangeListener listener : listeners) {
            listener.onAppFore2Back();
        }
    }

    public static void onAppBack2Fore() {
        for (ForegroundChangeListener listener : listeners) {
            listener.onAppBack2Fore();
        }
    }
}
