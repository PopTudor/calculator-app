package tudor.com.supercalc;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity implements BottomFragment.OnFragmentInteractionListener,BottomFragment2.OnFragmentInteractionListener{
	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	private static final int NUM_PAGES = 2;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally to access previous
	 * and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	private TopFragment mTopFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		mTopFragment = (TopFragment)getSupportFragmentManager().findFragmentById(R.id.topFragment);
	}
	@Override
	public void onBackPressed() {
		if (mPager.getCurrentItem() == 0) {
			// If the user is currently looking at the first step, allow the system to handle the
			// Back button. This calls finish() on this activity and pops the back stack.
			super.onBackPressed();
		} else {
			// Otherwise, select the previous step.
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	@Override
	public void onNumberClicked(int buttonPressed) {
		switch (buttonPressed){
			case R.string.num0:
				mTopFragment.setTextView(getString(R.string.num0));
				break;
			case R.string.num1:
				mTopFragment.setTextView(getString(R.string.num1));
				break;
			case R.string.num2:
				mTopFragment.setTextView(getString(R.string.num2));
				break;
			case R.string.num3:
				mTopFragment.setTextView(getString(R.string.num3));
				break;
			case R.string.num4:
				mTopFragment.setTextView(getString(R.string.num4));
				break;
			case R.string.num5:
				mTopFragment.setTextView(getString(R.string.num5));
				break;
			case R.string.num6:
				mTopFragment.setTextView(getString(R.string.num6));
				break;
			case R.string.num7:
				mTopFragment.setTextView(getString(R.string.num7));
				break;
			case R.string.num8:
				mTopFragment.setTextView(getString(R.string.num8));
				break;
			case R.string.num9:
				mTopFragment.setTextView(getString(R.string.num9));
				break;
			case R.string.dot:
				mTopFragment.setTextView(R.string.dot);
				break;
			case R.string.del:
				mTopFragment.setTextView(R.string.del);
				break;
			case R.string.delLong:
				mTopFragment.setTextView(R.string.delLong);
				break;
			case R.string.plus:
				mTopFragment.setTextView(R.string.plus);
				break;
			case R.string.minus:
				mTopFragment.setTextView(R.string.minus);
				break;
			case R.string.multiply:
				mTopFragment.setTextView(R.string.multiply);
				break;
			case R.string.division:
				mTopFragment.setTextView(R.string.division);
				break;
			case R.string.power:
				mTopFragment.setTextView(R.string.power);
				break;
			case R.string.modulo:
				mTopFragment.setTextView(R.string.modulo);
				break;
		}
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
	 * sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position){
				case 0:
					return BottomFragment.newInstance();
				case 1:
					return BottomFragment2.newInstance();
				default:
					return null;
			}
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
}
