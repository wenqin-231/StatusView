package com.lewis.ui.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.lewis.widget.ui.base.BaseStatusActivity;

/**
 * Created by Lewis on 2017/9/28.
 * Description:
 */

public class NavigationActivity extends BaseStatusActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

	private ViewPager mViewPager;
	private BottomNavigationView mBottomNavigationView;
	private PageFragment mCurrentFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);

		mToolbar.setNavigationIcon(null);

		mViewPager = (ViewPager) findViewById(R.id.view_pager);
		mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);

		mBottomNavigationView.setOnNavigationItemSelectedListener(this);
		mToolbar.setTitle("home");

		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				switch (position) {
					case 0:
						mBottomNavigationView.setSelectedItemId(R.id.menu_home);
						break;
					case 1:
						mBottomNavigationView.setSelectedItemId(R.id.menu_msg);
						break;
					case 2:
						mBottomNavigationView.setSelectedItemId(R.id.menu_mine);
						break;
				}

//				mStatusView.setStatus(StatusView.Status.LOADING);
//				new Handler().postDelayed(new Runnable() {
//					@Override
//					public void run() {
//						mStatusView.setStatus(StatusView.Status.NORMAL);
//					}
//				}, 2000);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(pagerAdapter);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_home:
				mViewPager.setCurrentItem(0);
				setTitle(item.getTitle());
				break;
			case R.id.menu_msg:
				mViewPager.setCurrentItem(1);
				setTitle(item.getTitle());
				break;
			case R.id.menu_mine:
				mViewPager.setCurrentItem(2);
				setTitle(item.getTitle());
				break;
		}
		return true;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			int bnvHeight = mBottomNavigationView.getHeight();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				bnvHeight += mBottomNavigationView.getTranslationZ();
			}

			mStatusView.setMarginBottom(bnvHeight + 4);
		}
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {


		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return PageFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return mBottomNavigationView.getMaxItemCount();
		}
	}
}

