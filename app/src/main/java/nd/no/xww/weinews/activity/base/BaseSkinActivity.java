package nd.no.xww.weinews.activity.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import nd.no.xww.library.mvp.base.BaseActivity;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/14
 * @time 18:22
 */
public abstract class BaseSkinActivity extends BaseActivity {

    protected SharedPreferences mSharedPreferences;
    private final String COLOR_NIGHT = "#828282";
    private final String COLOR_DAY = "#ffffff";
    protected String mModeColor = COLOR_DAY;

    @Override
    protected void onPreCreate() {
        super.onPreCreate();
        mSharedPreferences = getSharedPreferences("app_night_mode", Context.MODE_PRIVATE);
        if (mSharedPreferences.getBoolean("night_mode", false)) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            mModeColor = COLOR_NIGHT;
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            mModeColor = COLOR_DAY;
        }
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
    }
}
