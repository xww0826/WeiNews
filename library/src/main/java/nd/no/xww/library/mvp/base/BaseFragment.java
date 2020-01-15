package nd.no.xww.library.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import nd.no.xww.library.mvp.proxy.ProxyFragment;

/**
 * 基类 Fragment ，提供懒加载功能
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    // 是否初始化了 View
    private boolean isActivityCreated = false;
    // fragment 是否可见
    private boolean isVisibleToUser = false;
    // 数据是否已经加载了
    private boolean isDataLoaded = false;

    protected Context mContext;
    protected Activity mActivity;
    protected Unbinder unbinder;

    //代理 Fragment
    private ProxyFragment mProxyFragment;

    protected abstract @LayoutRes
    int setLayout();

    protected abstract void initViews(@NonNull View view, @Nullable Bundle savedInstanceState);

    protected abstract void initData();

    // 懒加载方法
    protected abstract void loadLazyData();

    @SuppressWarnings("ConstantConditions")
    protected <T extends View> T $(@IdRes int viewId) {
        return this.getView().findViewById(viewId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mProxyFragment = createProxyFragment();
        mProxyFragment.bindPresenter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isActivityCreated = true;
        mActivity = getActivity();
        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            loadLazyOnce();
        }
    }

    // 懒加载：数据加载仅一次
    private void loadLazyOnce() {
        if (isActivityCreated && isVisibleToUser && !isDataLoaded) {
            loadLazyData();
            isDataLoaded = true;
        }
    }

    @SuppressWarnings("unchecked")
    private ProxyFragment createProxyFragment() {
        if (mProxyFragment == null) {
            return new ProxyFragment<>(this);
        }
        return mProxyFragment;
    }

    @Override
    public void onDestroy() {
        mProxyFragment.unbindPresenter();
        unbinder.unbind();
        super.onDestroy();
    }
}
