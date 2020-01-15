package nd.no.xww.weinews.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nd.no.xww.weinews.R;
import nd.no.xww.weinews.activity.base.BaseSkinActivity;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/14
 * @time 14:00
 */
public class NewsDetailActivity extends BaseSkinActivity {

    @BindView(R.id.web_View)
    WebView webView;


    String mUrl;
    StringBuffer mWebHtmlString;

    private static final String TAG = "NewsDetailActivity";

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        super.initLayout(savedInstanceState);
        setContentView(R.layout.activity_news_detial);
        ButterKnife.bind(this);
        mUrl = getIntent().getStringExtra("news_url");
        Log.i(TAG, "url : " + mUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initViews() {
        super.initViews();
        webView.getSettings().setDefaultTextEncodingName("UTF -8");
        webView.getSettings().setJavaScriptEnabled(true);

        mWebHtmlString = new StringBuffer();
    }


    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    @Override
    protected void initData() {
        super.initData();

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Document doc = Jsoup.connect(mUrl).timeout(3000).get();
            String headHtml = doc.select("head").toString();
            String articleHtml = doc.select("article").toString();
            mWebHtmlString.append("<!doctype html>");
            mWebHtmlString.append("<html lang=\"zh-cn\" style=\"font-size: 100px;\">");
            mWebHtmlString.append(headHtml);
            mWebHtmlString.append("<body style=\"background-color:" + mModeColor + ";\">");
            mWebHtmlString.append(articleHtml);
            mWebHtmlString.append("</body>");
            mWebHtmlString.append("</html>");

            emitter.onNext(mWebHtmlString.toString());
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        webView.loadData(mWebHtmlString.toString(), "text/html; charset=UTF-8", null);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
