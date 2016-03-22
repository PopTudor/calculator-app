package tudor.com.calculatorSleek.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import tudor.com.calculatorSleek.android.R;
import tudor.com.calculatorSleek.fragments.BottomFragment;
import tudor.com.calculatorSleek.fragments.BottomFragment2;
import tudor.com.calculatorSleek.fragments.TopFragment;


public class MainActivity extends FragmentActivity
		implements BottomFragment.OnFragmentInteractionListener, BottomFragment2.OnFragmentInteractionListener {
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

	public static void setButtonsFont(ArrayList<Button> buttons, int size) {
		for (Button button : buttons)
			button.setTextSize(size);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Fabric.with(this, new Crashlytics(), new Answers());
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		mTopFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.topFragment);


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

	/**
	 * Process BottomFragment buttons taps. Operands + operators + delete key
	 *
	 * @param buttonPressed
	 */
	@Override
	public void onFirstSymbolFragmentClicked(int buttonPressed) {
		switch (buttonPressed) {
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
			case R.string.factorial:
				mTopFragment.setTextView("!");
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
			case R.string.equal: // ads
				mTopFragment.setTextView(R.string.equal);
				break;
			default:
				break;
		}
	}

	/**
	 * Process BottomsFragment2 buttons taps. Trigonometric functions and others
	 *
	 * @param symbol
	 */
	@Override
	public void onSecondSymbolFragmentClicked(int symbol) {
		switch (symbol) {
			case R.string.pi:
				mTopFragment.setTextView(getString(R.string.pi));
				break;
			case R.string.e:
				mTopFragment.setTextView(getString(R.string.e));
				break;
			case R.string.logarithm:
				mTopFragment.setTextView(getString(R.string.logarithm) + "(");
				break;
			case R.string.logarithmNat:
				mTopFragment.setTextView(getString(R.string.logarithmNat) + "(");
				break;
			case R.string.floor:
				mTopFragment.setTextView(R.string.floor);
				break;
			case R.string.ceiling:
				mTopFragment.setTextView(R.string.ceiling);
				break;
			case R.string.rand:
				mTopFragment.setTextView(R.string.rand);
				break;
			case R.string.sin:
				mTopFragment.setTextView(getString(R.string.sin) + "(");
				break;
			case R.string.cos:
				mTopFragment.setTextView(getString(R.string.cos) + "(");
				break;
			case R.string.tan:
				mTopFragment.setTextView(getString(R.string.tan) + "(");
				break;
			case R.string.sinh:
				mTopFragment.setTextView(getString(R.string.sinh) + "(");
				break;
			case R.string.cosh:
				mTopFragment.setTextView(getString(R.string.cosh) + "(");
				break;
			case R.string.tanh:
				mTopFragment.setTextView(getString(R.string.tanh) + "(");
				break;
		}

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
			switch (position) {
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
