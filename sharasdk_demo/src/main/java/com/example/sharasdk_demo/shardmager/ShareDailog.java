package com.example.sharasdk_demo.shardmager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.sharasdk_demo.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;

public class ShareDailog extends Dialog implements View.OnClickListener {


    private  DisplayMetrics dm;
    private  Context con;

    public ShareDailog(Context context) {
        super(context, R.style.SheetDialogStyle);
        this.con=context;
         dm = con.getResources().getDisplayMetrics();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.sharsdailog);
         initView();
    }

    private void initView() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width=dm.widthPixels;
        window.setAttributes(attributes);
        Button viewById = (Button) findViewById(R.id.btn);
        viewById.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btn:
                  shard(SharedManger.Plattype.QQ);
                  break;
          }
    }

    private int type;

    public DisplayMetrics getDm() {
        return dm;
    }

    public void setDm(DisplayMetrics dm) {
        this.dm = dm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String title;
    private String text;

    private void shard(SharedManger.Plattype qq) {
        ShareData shareData = new ShareData();
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setShareType(type);
        shareParams.setTitle(title);

        shareData.params=shareParams;
        shareData.type=qq;
          SharedManger.getInstance().Shared(shareData, new PlatformActionListener() {
              @Override
              public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                  Toast.makeText(con, "成功", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onError(Platform platform, int i, Throwable throwable) {
                  Toast.makeText(con, "失败", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onCancel(Platform platform, int i) {
                  Toast.makeText(con, "取消", Toast.LENGTH_SHORT).show();
              }
          });
    }
}
