package nd.no.xww.library.mvp.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nd.no.xww.library.mvp.proxy.ProxyActivity;
import nd.no.xww.library.utils.activity.ActivityManager;


/**
 * 基类 Activity
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProxyActivity mProxyActivity;

    // Before Activity call OnCreate()
    protected void onPreCreate() {
    }

    protected abstract void initLayout(@Nullable Bundle savedInstanceState);

    protected abstract void initViews();

    protected abstract void initData();

    @SuppressWarnings("SameParameterValue")
    protected <T extends View> T $(@IdRes int viewId) {
        return findViewById(viewId);
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onPreCreate();
        super.onCreate(savedInstanceState);

//        ActivityUtil.getInstance().addActivity(this);
        ActivityManager.getInstance().addActivity(this);

        initLayout(savedInstanceState);

        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();

        initViews();
        initData();
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        mProxyActivity.unbindPresenter();
//        ActivityUtil.getInstance().finishActivity(this);
        ActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
