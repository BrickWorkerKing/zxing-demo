package com.example.lzrui.zxingdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tvMsg;
    private EditText etMsg;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        etMsg = (EditText) findViewById(R.id.et_msg);
        image = (ImageView) findViewById(R.id.image);
    }

    /**
     * 扫描二维码
     *
     * @param view
     */
    public void scan(View view) {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * 生成二维码
     *
     * @param view
     */
    public void create(View view) {
        String content = etMsg.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            Bitmap bitmap = EncodingUtils.createQRCode(content, 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
            image.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            String content = bundle.getString("result");
            int width = bundle.getInt("width");
            int height = bundle.getInt("height");
            Log.i("lzrtest", "width == " + width + "  height == " + height);
            tvMsg.setText(content);
        }else{
            Toast.makeText(this, "信息获取失败！", Toast.LENGTH_LONG).show();
        }
    }
}
