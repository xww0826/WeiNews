package nd.no.xww.library.base.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 功能：viewpager 添加　fragments 的适配器
 *
 * @author : xww
 * @created at : 19-3-9
 * @time : 下午7:14
 */
public final class FragmentTitleAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public FragmentTitleAdapter(List<Fragment> fragments, List<String> titles, FragmentManager fm) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
