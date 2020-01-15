package nd.no.xww.weinews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nd.no.xww.library.base.adapter.FragmentAdapter;
import nd.no.xww.weinews.activity.base.BasePermissionActivity;
import nd.no.xww.weinews.fragment.HomeFragment;
import nd.no.xww.weinews.fragment.PersonFragment;

public class MainActivity extends BasePermissionActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    @BindView(R.id.bnv_bottom_menu)
    BottomNavigationView bnvBottomMenu;
    @BindView(R.id.vp_main_content)
    ViewPager vpMainContent;

    int position = 0;

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        bnvBottomMenu.setOnNavigationItemSelectedListener(this);
        position = mSharedPreferences.getInt("position", 0);
    }

    @Override
    protected void initData() {
        List<Fragment> contentFragments = new ArrayList<>();
        contentFragments.add(new HomeFragment());
        contentFragments.add(new PersonFragment());
        FragmentAdapter contentAdapter = new FragmentAdapter(contentFragments, getSupportFragmentManager());
        vpMainContent.setAdapter(contentAdapter);
        vpMainContent.setOffscreenPageLimit(2);
        vpMainContent.addOnPageChangeListener(this);
        vpMainContent.setCurrentItem(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_menu_home:
                vpMainContent.setCurrentItem(0);
                break;
            case R.id.nav_menu_person:
                vpMainContent.setCurrentItem(1);
                break;
        }
        return false;
    }


    @Override
    public void onPageSelected(int i) {
        bnvBottomMenu.getMenu().getItem(i).setChecked(true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
