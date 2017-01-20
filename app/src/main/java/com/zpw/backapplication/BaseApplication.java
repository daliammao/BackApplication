package com.zpw.backapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

/**
 * @author: zhoupengwei
 * @time: 2017/1/19-下午3:40
 * @Email: zhoupengwei@qccr.com
 * @desc:
 */
public class BaseApplication extends Application {
    public void onCreate() {
        super.onCreate();

        ForegroundChangeDispatcher.addListener(new ForegroundChangeListener() {
            @Override
            public void onAppFore2Back() {
                System.out.println("前台切换到后台");
            }

            @Override
            public void onAppBack2Fore() {
                System.out.println("后台切换到前台");
            }
        });

        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            boolean lastOnForeground = true;

            @Override
            public void onActivityStopped(Activity activity) {
                lastOnForeground = isRunningForeground();
                if(!lastOnForeground){
                    ForegroundChangeDispatcher.onAppFore2Back();
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                boolean newOnForeground = isRunningForeground();
                if (!lastOnForeground && newOnForeground) {
                    ForegroundChangeDispatcher.onAppBack2Fore();
                }
                lastOnForeground = newOnForeground;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
        });
    }

    public boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        // 枚举进程
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(this.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
