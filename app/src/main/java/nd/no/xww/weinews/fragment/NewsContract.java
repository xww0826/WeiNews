package nd.no.xww.weinews.fragment;

import java.util.List;

import io.reactivex.Observer;
import nd.no.xww.library.mvp.base.IBasePresenter;
import nd.no.xww.library.mvp.base.IBaseView;
import nd.no.xww.weinews.bean.NewsBean;
import nd.no.xww.weinews.entity.NewsEntity;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/8
 * @time 10:56
 */
public interface NewsContract {

    interface NewsModel {
        void requestNewsApi(String type, Observer<NewsEntity> observer);
    }

    interface NewsView extends IBaseView {
        void onRequestStart();

        void onRequestFinished(List<NewsBean> newsBean);

        void onShowRequestErrorInfo(String msg);

        void onRequestEnd();

        void onRequestError();
    }

    interface NewsPresenter extends IBasePresenter {
        void getNewsData(String type);
    }
}
