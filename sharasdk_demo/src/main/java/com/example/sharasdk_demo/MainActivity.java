package com.example.sharasdk_demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sharasdk_demo.shardmager.ShareDailog;
import com.example.sharasdk_demo.shardmager.ShareDialog;
import com.example.sharasdk_demo.shardmager.SharedManger;
import com.example.sharasdk_demo.zxing.app.CaptureActivity;
import com.mob.MobSDK;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_QRCODE = 0x01;
    private ImageView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedManger.init(this);
         viewById = (ImageView) this.findViewById(R.id.img);
    }


    
    public void doOpenCamera() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }

    public void btn(View view){

        doOpenCamera();


//        Intent intent = new Intent(this, CaptureActivity.class);
//        startActivity(intent);
//        ShareDailog shareDailog = new ShareDailog(MainActivity.this);
//        shareDailog.setText("hello wod");
//        shareDailog.setTitle("sd");
//        shareDailog.show();
       // ShareDialog shareDialog = new ShareDialog(MainActivity.this,false);
//        shareDialog.setTitle("-----");
//        shareDialog.sett
       // shareDialog.show();
//
//
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
//
//// 启动分享GUI
//        oks.show(this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");
                    Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                    if (code.contains("http") || code.contains("https")) {
//                        Intent intent = new Intent(this, AdBrowserActivity.class);
//                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                       // startActivity(intent);
                        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                    }
                } else if(resultCode==200){
                    //int qr_code = data.getIntExtra("QR_CODE");
                    //Bundle extras = data.getExtras();
                    Bundle extras = data.getExtras();
                    Bitmap qr_code = (Bitmap)extras.get("QR_CODE");
                    viewById.setImageBitmap(qr_code);
                }
                break;
        }
    }
}
