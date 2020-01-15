package nd.no.xww.weinews.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/8
 * @time 20:47
 */
public class LoadingMoreHandle extends RecyclerView.OnScrollListener {

    private NewsAdapter mNewsAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;
    private OnLoadMoreListener onLoadMoreListener;

    public LoadingMoreHandle(NewsAdapter newsAdapter, LinearLayoutManager layoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.mNewsAdapter = newsAdapter;
        this.mLayoutManager = layoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        // if the last visible item + 1 == all data size
        if (lastVisibleItemPosition + 1 == mNewsAdapter.getItemCount()) {
            if (!isLoading) {
                isLoading = true;

                new Handler().postDelayed(() -> {
                    onLoadMoreListener.loadMoreFinished();
                    isLoading = false;
                }, 500);
            }
        }
    }
}
