package nd.no.xww.weinews.fragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import nd.no.xww.library.mvp.base.BasePresenter;
import nd.no.xww.weinews.bean.NewsBean;
import nd.no.xww.weinews.entity.NewsEntity;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/8
 * @time 10:55
 */
public class NewsPresenter extends BasePresenter<NewsContract.NewsView, NewsModel> implements NewsContract.NewsPresenter {

    @Override
    public void getNewsData(String type) {
        getModel().requestNewsApi(type, new Observer<NewsEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                getView().onRequestStart();
            }

            @Override
            public void onNext(NewsEntity entity) {
                final int errorCode = entity.getError_code();
                if (errorCode == 0 && entity.getResult().getData().size() > 0) {
                    getView().onRequestFinished(parseEntity(entity));
                } else {
                    getView().onShowRequestErrorInfo(entity.getReason());
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().onRequestError();
            }

            @Override
            public void onComplete() {
                getView().onRequestEnd();
            }
        });
    }

    private List<NewsBean> parseEntity(NewsEntity entity) {
        List<NewsBean> newDataList = new ArrayList<>();
        List<NewsEntity.ResultBean.DataBean> dataBeans = entity.getResult().getData();
        for (int i = 0, size = dataBeans.size(); i < size; i++) {
            String title = dataBeans.get(i).getTitle();
            String pic1 = dataBeans.get(i).getThumbnail_pic_s();
            String pic2 = dataBeans.get(i).getThumbnail_pic_s02();
            String pic3 = dataBeans.get(i).getThumbnail_pic_s03();
            String authorName = dataBeans.get(i).getAuthor_name();
            String date = dataBeans.get(i).getDate();
            String url = dataBeans.get(i).getUrl();

            newDataList.add(new NewsBean(title, url, authorName, pic1, pic2, pic3, date));
        }
        return newDataList;
    }
}
