package tudor.com.calculatorSleek;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomFragment2 extends Fragment {
	private Button mButtonLogarithm;
	private Button mButtonLogarithmNat;

	private Button mButtonPi;
	private Button mButtonE;

	private Button mButtonFloor;
	private Button mButtonCeiling;

	private Button mButtonRand;

	private Button mButtonSin;
	private Button mButtonCos;
	private Button mButtonTan;

	private Button mButtonSinh;
	private Button mButtonCosh;
	private Button mButtonTanh;

	private OnFragmentInteractionListener mListener;

	public static BottomFragment2 newInstance() {
		BottomFragment2 fragment = new BottomFragment2();
		return fragment;
	}

	public BottomFragment2() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bottom_fragment2, container, false);

		mButtonLogarithm = (Button) view.findViewById(R.id.buttonLogarithm);
		mButtonLogarithmNat = (Button) view.findViewById(R.id.buttonLogarithmNat);

		mButtonPi = (Button) view.findViewById(R.id.buttonPi);
		mButtonE = (Button) view.findViewById(R.id.buttonE);

		mButtonFloor = (Button) view.findViewById(R.id.buttonFloor);
		mButtonCeiling = (Button) view.findViewById(R.id.buttonCeiling);

		mButtonRand = (Button) view.findViewById(R.id.buttonRand);

		mButtonSin = (Button) view.findViewById(R.id.buttonSin);
		mButtonCos = (Button) view.findViewById(R.id.buttonCos);
		mButtonTan = (Button) view.findViewById(R.id.buttonTan);

		mButtonSinh = (Button) view.findViewById(R.id.buttonSinh);
		mButtonCosh = (Button) view.findViewById(R.id.buttonCosh);
		mButtonTanh = (Button) view.findViewById(R.id.buttonTanh);

		eventsOperators();

		// ads
//		AdView mAdView = (AdView) view.findViewById(R.id.adView);
//		AdRequest adRequest = new AdRequest.Builder().build();
//		mAdView.loadAd(adRequest);
		return view;
	}

	private void eventsOperators() {
		mButtonLogarithm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSecondSymbolFragmentClicked(R.string.logarithm);
			}
		});
		mButtonLogarithmNat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSecondSymbolFragmentClicked(R.string.logarithmNat);
			}
		});
		/*	PI --- E	*/
		mButtonPi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSecondSymbolFragmentClicked(R.string.pi);
			}
		});
		mButtonE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSecondSymbolFragmentClicked(R.string.e);
			}
		});

		mButtonCeiling.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.ceiling);
			}
		});
		mButtonFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.floor);
			}
		});
		mButtonRand.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.rand);
			}
		});

		mButtonSin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.sin);
			}
		});
		mButtonCos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.cos);
			}
		});
		mButtonTan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.tan);
			}
		});

		mButtonSinh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.sinh);
			}
		});
		mButtonCosh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.cosh);
			}
		});
		mButtonTanh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mListener.onSecondSymbolFragmentClicked(R.string.tanh);
			}
		});

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onSecondSymbolFragmentClicked(int symbol);
	}

}
