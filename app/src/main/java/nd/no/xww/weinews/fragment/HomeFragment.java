package nd.no.xww.weinews.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import nd.no.xww.library.base.adapter.FragmentTitleAdapter;
import nd.no.xww.library.mvp.base.BaseFragment;
import nd.no.xww.weinews.R;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/5
 * @time 16:24
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.vp_home_news)
    ViewPager vpHomeNews;
    @BindView(R.id.tl_news_category)
    TabLayout tlNewsCategory;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        List<Fragment> newsFragments = new ArrayList<>();
        newsFragments.add(new HotNewsFragment());
        newsFragments.add(new InlandNewsFragment());
        newsFragments.add(new InternationalNewsFragment());
        newsFragments.add(new SportsNewsFragment());
        newsFragments.add(new ScienceNewsFragment());
        newsFragments.add(new RecreationNewsFragment());
        newsFragments.add(new FashionNewsFragment());
        newsFragments.add(new SocietyNewsFragment());
        newsFragments.add(new FinanceNewsFragment());
        newsFragments.add(new MilitaryNewsFragment());

        List<String> newsTitles = new ArrayList<>();
        newsTitles.add("头条");
        newsTitles.add("国内");
        newsTitles.add("国际");
        newsTitles.add("体育");
        newsTitles.add("科技");
        newsTitles.add("娱乐");
        newsTitles.add("时尚");
        newsTitles.add("社会");
        newsTitles.add("财经");
        newsTitles.add("军事");
        for (String title : newsTitles) {
            tlNewsCategory.addTab(tlNewsCategory.newTab().setText(title));
        }
        FragmentTitleAdapter newsAdapter = new FragmentTitleAdapter(newsFragments, newsTitles, getChildFragmentManager());
        tlNewsCategory.setupWithViewPager(vpHomeNews);
        vpHomeNews.setAdapter(newsAdapter);
        vpHomeNews.setOffscreenPageLimit(10);// 预加载 10 个(全部)
    }

    @Override
    protected void loadLazyData() {
    }
}
