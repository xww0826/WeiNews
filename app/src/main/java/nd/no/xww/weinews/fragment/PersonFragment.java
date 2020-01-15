package nd.no.xww.weinews.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Switch;

import butterknife.BindView;
import nd.no.xww.library.mvp.base.BaseFragment;
import nd.no.xww.weinews.MainActivity;
import nd.no.xww.weinews.R;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/5
 * @time 16:25
 */
public class PersonFragment extends BaseFragment {

    @BindView(R.id.sw_night_mode)
    Switch swNightMode;

    SharedPreferences mSharedPreferences;
    boolean isNightMode;

    @Override
    protected int setLayout() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initViews(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mSharedPreferences = mContext.getSharedPreferences("app_night_mode", Context.MODE_PRIVATE);

        isNightMode = mSharedPreferences.getBoolean("night_mode", false);
        if (isNightMode) {
            swNightMode.setChecked(true);
        } else {
            swNightMode.setChecked(false);
        }
    }

    @Override
    protected void initData() {
        swNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && !isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                mSharedPreferences.edit().putBoolean("night_mode", true).apply();
            } else if (!isChecked && isNightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                mSharedPreferences.edit().putBoolean("night_mode", false).apply();
            }
            mSharedPreferences.edit().putInt("position", 1).apply();
            mContext.startActivity(new Intent(mContext, MainActivity.class));
            mActivity.overridePendingTransition(R.anim.night_mode_open_anim, R.anim.night_mode_close_anim);
            mActivity.finish();
        });
    }

    @Override
    protected void loadLazyData() {
    }
}
