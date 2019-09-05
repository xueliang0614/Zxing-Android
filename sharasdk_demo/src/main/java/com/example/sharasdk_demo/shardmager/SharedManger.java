package com.example.sharasdk_demo.shardmager;

import android.content.Context;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class SharedManger {

    private static SharedManger instance=null;


    public SharedManger() {
    }
    public  static SharedManger getInstance(){

        if(instance==null){
              synchronized (SharedManger.class){
                 if(instance==null){
                    instance=new SharedManger();
                 }
              }
        }

        return  instance;
    }

    /**
     *
     *  初始化
     */
    public static void init(Context context){
        MobSDK.init(context, "2c52410f15d94", "89254b33bf23ae648c2b259b12a2e0d5");
    }
    /**
     *
     *
     */

    private static Platform platform;
    public static void Shared(ShareData data, PlatformActionListener listener){

          switch (data.type){
              case QQ:
                   platform = ShareSDK.getPlatform(QQ.NAME);
                  break;
              case WECHAT:
                  platform = ShareSDK.getPlatform(Wechat.NAME);
                  break;
          }
        platform.setPlatformActionListener(listener);
        platform.share(data.params);

    }


    public enum  Plattype {
        QQ,WECHAT
    }
}
