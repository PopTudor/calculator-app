package tudor.com.supercalc;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
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
	private Button mButtonBracketOpen;
	private Button mButtonBracketClose;
	private Button mButtonModulo;
	private Button mButtonNegation;

	private Button mButtonLogarithm;
	private Button mButtonFactorial;
	private Button mButtonRoot;
	private Button mButtonPower;

	private TextView mTextViewResult;
	private TextView mTextViewDetail;

	private Evaluator mEvaluator;
	public MainActivityFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		mButtonOne = (Button) view.findViewById(R.id.buttonOne);
		mButtonTwo = (Button) view.findViewById(R.id.buttonTwo);
		mButtonThree = (Button) view.findViewById(R.id.buttonThree);
		mButtonFour = (Button) view.findViewById(R.id.buttonFour);
		mButtonFive = (Button) view.findViewById(R.id.buttonFive);
		mButtonSix = (Button) view.findViewById(R.id.buttonSix);
		mButtonSeven = (Button) view.findViewById(R.id.buttonSeven);
		mButtonEight = (Button) view.findViewById(R.id.buttonEight);
		mButtonNine = (Button) view.findViewById(R.id.buttonNine);
		mButtonZero = (Button) view.findViewById(R.id.buttonZero);
		mButtonDot = (Button) view.findViewById(R.id.buttonDot);

		mButtonPlus = (Button)view.findViewById(R.id.buttonPlus);
		mButtonMinus = (Button)view.findViewById(R.id.buttonMinus);
		mButtonMultiply = (Button)view.findViewById(R.id.buttonMultiply);
		mButtonDivision = (Button)view.findViewById(R.id.buttonDivision);
		mButtonEqual = (Button)view.findViewById(R.id.buttonEqual);
		mButtonBracketOpen = (Button)view.findViewById(R.id.buttonBracketOpen);
		mButtonBracketClose = (Button)view.findViewById(R.id.buttonBracketClose);
		mButtonModulo = (Button)view.findViewById(R.id.buttonModulo);
		mButtonNegation = (Button)view.findViewById(R.id.buttonNegation);

		mButtonLogarithm = (Button)view.findViewById(R.id.buttonLogarithm);
		mButtonFactorial = (Button)view.findViewById(R.id.buttonFactorial);
		mButtonRoot = (Button)view.findViewById(R.id.buttonRoot);
		mButtonPower = (Button)view.findViewById(R.id.buttonPower);

		mTextViewResult = (TextView)view.findViewById(R.id.textViewResult);
		mTextViewDetail = (TextView)view.findViewById(R.id.textViewDetail);


		mEvaluator = new Evaluator();
		eventsNumbers();
		eventsOperators();
		return view;
	}

	private void eventsNumbers(){
		mButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"1");
			}
		});
		mButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"2");
			}
		});
		mButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"3");
			}
		});
		mButtonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"4");
			}
		});
		mButtonFive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"5");
			}
		});
		mButtonSix.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"6");
			}
		});
		mButtonSeven.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"7");
			}
		});
		mButtonEight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"8");
			}
		});
		mButtonNine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"9");
			}
		});
		mButtonZero.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"0");
			}
		});



	}
	private void eventsOperators(){
		mTextViewDetail.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					String str = mEvaluator.evaluate(s.toString());
					mTextViewResult.setText(str);
				} catch (EvaluationException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mButtonDot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+".");
			}
		});

		mButtonPlus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"+");
			}
		});
		mButtonMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"-");
			}
		});
		mButtonMultiply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"*");
			}
		});
		mButtonDivision.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"/");
			}
		});
		mButtonEqual.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		mButtonBracketOpen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"(");
			}
		});
		mButtonBracketClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+")");
			}
		});
		mButtonModulo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText()+"%");
			}
		});
		mButtonNegation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewResult.setText("-"+mTextViewResult.getText());
			}
		});
	}


}
