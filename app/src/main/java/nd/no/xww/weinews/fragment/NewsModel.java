package nd.no.xww.weinews.fragment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nd.no.xww.library.mvp.base.BaseModel;
import nd.no.xww.weinews.API;
import nd.no.xww.weinews.entity.NewsEntity;
import nd.no.xww.weinews.net.HttpUtils;
import nd.no.xww.weinews.net.NewsService;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/8
 * @time 11:00
 */
public class NewsModel extends BaseModel implements NewsContract.NewsModel {

    @Override
    public void requestNewsApi(String type, Observer<NewsEntity> observer) {
        HttpUtils.getInstance()
                .create(NewsService.class)
                .getNews(type, API.APP_KEY)
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribeOn(Schedulers.io())//IO线程加载数据
                .subscribe(observer);
    }
}
