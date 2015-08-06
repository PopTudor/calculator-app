package tudor.com.supercalc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomFragment extends Fragment {
	private Button mButtonOne;
	private Button mButtonTwo;
	private Button mButtonThree;
	private Button mButtonFour;
	private Button mButtonFive;
	private Button mButtonSix;
	private Button mButtonSeven;
	private Button mButtonEight;
	private Button mButtonNine;
	private Button mButtonZero;
	private Button mButtonDot;

	private Button mButtonPlus;
	private Button mButtonMinus;
	private Button mButtonMultiply;
	private Button mButtonDivision;
	private Button mButtonEqual;
	private Button mButtonModulo;
	private Button mButtonDel;
	private Button mButtonPower;

	private OnFragmentInteractionListener mListener;

	public static BottomFragment newInstance() {
		BottomFragment fragment = new BottomFragment();
		return fragment;
	}

	public BottomFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bottom, container, false);

//		mButtonOne = (Button) view.findViewById(R.id.buttonOne);
//		mButtonTwo = (Button) view.findViewById(R.id.buttonTwo);
//		mButtonThree = (Button) view.findViewById(R.id.buttonThree);
//		mButtonFour = (Button) view.findViewById(R.id.buttonFour);
//		mButtonFive = (Button) view.findViewById(R.id.buttonFive);
//		mButtonSix = (Button) view.findViewById(R.id.buttonSix);
//		mButtonSeven = (Button) view.findViewById(R.id.buttonSeven);
//		mButtonEight = (Button) view.findViewById(R.id.buttonEight);
//		mButtonNine = (Button) view.findViewById(R.id.buttonNine);
//		mButtonZero = (Button) view.findViewById(R.id.buttonZero);
//		mButtonDot = (Button) view.findViewById(R.id.buttonDot);
//
//		mButtonPlus = (Button) view.findViewById(R.id.buttonPlus);
//		mButtonMinus = (Button) view.findViewById(R.id.buttonMinus);
//		mButtonMultiply = (Button) view.findViewById(R.id.buttonMultiply);
//		mButtonDivision = (Button) view.findViewById(R.id.buttonDivision);
//		mButtonEqual = (Button) view.findViewById(R.id.buttonEqual);
//		mButtonModulo = (Button) view.findViewById(R.id.buttonModulo);
//		mButtonDel = (Button) view.findViewById(R.id.buttonClear);
//		mButtonPower = (Button) view.findViewById(R.id.buttonPower);


		return view;
	}
	private void eventsOperators(){

	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
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
		public void onFragmentInteraction(Uri uri);
	}

}
