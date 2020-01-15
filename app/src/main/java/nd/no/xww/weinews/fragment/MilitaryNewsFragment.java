package nd.no.xww.weinews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import nd.no.xww.library.base.adapter.ItemDecoration;
import nd.no.xww.library.mvp.base.BaseFragment;
import nd.no.xww.library.mvp.inject.InjectPresenter;
import nd.no.xww.weinews.R;
import nd.no.xww.weinews.adapter.LoadingMoreHandle;
import nd.no.xww.weinews.adapter.NewsAdapter;
import nd.no.xww.weinews.bean.NewsBean;

/**
 * @author xww
 * @desciption : 军事新闻
 * @date 2020/1/5
 * @time 16:10
 */
public class MilitaryNewsFragment extends BaseFragment implements NewsContract.NewsView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout srlNews;

    @InjectPresenter
    NewsPresenter mPresenter;

    NewsAdapter mNewsAdapter;
    List<NewsBean> mNewsData;
    LinearLayoutManager mLayoutManager;

    boolean isRefreshing = true;
    boolean isLoadingMore = false;

    private static final String TAG = "HotNewsFragment";

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mNewsData = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(mContext, mNewsData);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.addItemDecoration(new ItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        rvNews.setAdapter(mNewsAdapter);

        srlNews.setOnRefreshListener(this);
        srlNews.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        rvNews.addOnScrollListener(new LoadingMoreHandle(mNewsAdapter, mLayoutManager, () -> {
            isRefreshing = false;
            isLoadingMore = true;
            getNewsData();
        }));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void loadLazyData() {
        mPresenter.getNewsData("junshi");
    }

    @Override
    public void onRequestStart() {
        srlNews.setRefreshing(true);
    }

    @Override
    public void onRequestFinished(List<NewsBean> newsBean) {
        if (isRefreshing && !isLoadingMore) {
            srlNews.setRefreshing(false);
            mNewsAdapter.refreshData(newsBean);
        } else if (!isRefreshing && isLoadingMore) {
            srlNews.setRefreshing(false);
            mNewsAdapter.addData(newsBean);
        }
    }

    @Override
    public void onShowRequestErrorInfo(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestEnd() {
        srlNews.setRefreshing(false);
    }

    @Override
    public void onRequestError() {
        srlNews.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        isRefreshing = true;
        isLoadingMore = false;
        getNewsData();
    }

    private void getNewsData() {
        srlNews.setRefreshing(true);
        mPresenter.getNewsData("junshi");
    }
}
