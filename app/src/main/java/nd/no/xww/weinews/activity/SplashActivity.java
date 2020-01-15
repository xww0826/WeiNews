package nd.no.xww.weinews.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import nd.no.xww.weinews.MainActivity;
import nd.no.xww.weinews.R;
import yanzhikai.textpath.SyncTextPathView;
import yanzhikai.textpath.painter.FireworksPainter;

/**
 * @author xww
 * @desciption : 启动闪屏页面
 * @date 2019/11/30
 * @time 20:46
 */
public class SplashActivity extends AppCompatActivity {

    // 路径绘制
    SyncTextPathView atpvAppName;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 2000);
    }

    private void initView() {
        mSharedPreferences = getSharedPreferences("app_night_mode", Context.MODE_PRIVATE);
        atpvAppName = findViewById(R.id.atpv_app_name);
        atpvAppName.setPathPainter(new FireworksPainter());
        atpvAppName.startAnimation(0, 1);

        mSharedPreferences.edit().putInt("position", 0).apply();
    }
}
