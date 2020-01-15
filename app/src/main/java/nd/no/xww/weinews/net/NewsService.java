package nd.no.xww.weinews.net;

import io.reactivex.Observable;
import nd.no.xww.weinews.entity.NewsEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author xww
 * @desciption : 新闻 api（采用 RxJava）
 * @date 2020/1/7
 * @time 16:57
 */
public interface NewsService {

    @GET("toutiao/index")
    Observable<NewsEntity> getNews(@Query("type") String type, @Query("key") String key);
}
