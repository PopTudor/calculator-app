package tudor.com.supercalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

import java.math.BigDecimal;

import static tudor.com.supercalc.PrepareString.prepareStringForMathEval;


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
	private Button mButtonClear;

	private Button mButtonLogarithm;
	private Button mButtonFactorial;
	private Button mButtonRoot;
	private Button mButtonPower;

	private TextView mTextViewResult;
	private TextView mTextViewDetail;


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

		mButtonPlus = (Button) view.findViewById(R.id.buttonPlus);
		mButtonMinus = (Button) view.findViewById(R.id.buttonMinus);
		mButtonMultiply = (Button) view.findViewById(R.id.buttonMultiply);
		mButtonDivision = (Button) view.findViewById(R.id.buttonDivision);
		mButtonEqual = (Button) view.findViewById(R.id.buttonEqual);
		mButtonBracketOpen = (Button) view.findViewById(R.id.buttonBracketOpen);
		mButtonBracketClose = (Button) view.findViewById(R.id.buttonBracketClose);
		mButtonModulo = (Button) view.findViewById(R.id.buttonModulo);
		mButtonClear = (Button) view.findViewById(R.id.buttonClear);

		mButtonLogarithm = (Button) view.findViewById(R.id.buttonLogarithm);
		mButtonFactorial = (Button) view.findViewById(R.id.buttonFactorial);
		mButtonRoot = (Button) view.findViewById(R.id.buttonRoot);
		mButtonPower = (Button) view.findViewById(R.id.buttonPower);

		mTextViewResult = (TextView) view.findViewById(R.id.textViewResult);
		mTextViewDetail = (TextView) view.findViewById(R.id.textViewDetail);


		eventsNumbers();
		eventsOperators();

		return view;
	}


	public static String removeRedundantOperators(String s) {
		StringBuilder sb = new StringBuilder(s);
		String operators = "+-*/";
		int index = 0;
		while (index < sb.length() - 1) {
			char c1 = sb.charAt(index);
			char c2 = sb.charAt(index + 1);
			if (c1 == c2 && operators.indexOf(c1) != -1) {
				// remove the next character; the end is exclusive
				sb.delete(index + 1, index + 2);
			} else // added 'else' HERE
				index++;
		}
		return sb.toString();
	}

	private void eventsOperators() {
		mTextViewDetail.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence sequence, int start, int before, int count) {
				String s = (sequence.toString());
				s = PrepareString.operatorMapping(s);

				//todo move these in a new class
				Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
					@Override
					public double apply(double... args) {
						final int arg = (int) args[0];
						if ((double) arg != args[0]) {
							throw new IllegalArgumentException("Operand for factorial has to be an integer");
						}
						if (arg < 0) {
							throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
						}
						double result = 1;
						for (int i = 1; i <= arg; i++)
							result *= i;
						return result;
					}
				};
				Function log = new Function("log", 1) {
					@Override
					public double apply(double... args) {
						return Math.log10(args[0]);
					}
				};
				Function ln = new Function("ln", 1) {
					@Override
					public double apply(double... args) {
						return Math.log(args[0]);
					}
				};

				if (!s.equals("") && s != null) {
					Expression expression;
					try { // try default evaluation
						expression = new ExpressionBuilder(s)
								.operator(factorial)
								.function(log)
								.function(ln)
								.build();
						BigDecimal d = new BigDecimal(expression.evaluate())
								.setScale(5, BigDecimal.ROUND_HALF_UP) // ROUND_HALF_UP because 6.2 = 6.2
								.stripTrailingZeros();// if would be ROUND_UP then 6.2 = 6.20001
						mTextViewResult.setText(d.toPlainString());

					} catch (IllegalArgumentException e) { // if default fails
						try { // parse the string and try again

							expression = new ExpressionBuilder(prepareStringForMathEval(s))
									.operator(factorial)
									.function(log)
									.function(ln)
									.build();
							BigDecimal d = new BigDecimal(expression.evaluate())
									.setScale(5, BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros();
							mTextViewResult.setText(d.toPlainString());

						} catch (IllegalArgumentException ex) {
							mTextViewResult.setText("");
						}
					} catch (Exception e1) {
						mTextViewResult.setText("Error");
					}
				}else
					mTextViewResult.setText("");
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mTextViewDetail.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

		mButtonDot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.dot);
			}
		});

		mButtonPlus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.plus);
			}
		});
		mButtonMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.minus);
			}
		});
		mButtonMultiply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.multiply);
			}
		});
		mButtonDivision.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.division);
			}
		});

		mButtonModulo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, R.string.modulo);
			}
		});

		mButtonPower.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				mTextViewDetail.setText(str + "^");
			}
		});

		mButtonFactorial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				mTextViewDetail.setText(str + "!");
			}
		});

		mButtonLogarithm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mTextViewDetail.getText().toString();
				mTextViewDetail.setText(str + "log(");
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
				mTextViewDetail.setText(mTextViewDetail.getText() + "(");
			}
		});

		mButtonBracketClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + ")");
			}
		});

		mButtonClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String s = mTextViewDetail.getText().toString();
				String s1 = mTextViewResult.getText().toString();
				if (s.length()>0)
					mTextViewDetail.setText(s.substring(0, s.length() - 1));
			}
		});
		mButtonClear.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				mTextViewDetail.setText("");
				mTextViewResult.setText("");
				return false;
			}
		});
	}


	/**
	 * Verifica daca pe pozitia anterioara din string avem un operator si daca avem atunci il inlocuieste
	 * cu operatorul butonului apasat
	 *
	 * @param str      stringul de evaluat
	 * @param operator
	 */
	private void checkForMultipleOperators(String str, int operator) {
		if (str != null && !str.equals(""))
			switch (str.charAt(str.length() - 1)) {
				case '.':
				case '+':
				case '%':
				case '-':
				case '*':
				case '×':
				case '/':
				case '÷':
					mTextViewDetail.setText(mTextViewDetail.getText().subSequence(0, str.length() - 1) + getString(operator));
					break;
				default:
					mTextViewDetail.setText(mTextViewDetail.getText() + getString(operator));
					break;
			}
	}

	private void eventsNumbers() {
		mButtonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "1");
			}
		});
		mButtonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "2");
			}
		});
		mButtonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "3");
			}
		});
		mButtonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "4");
			}
		});
		mButtonFive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "5");
			}
		});
		mButtonSix.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "6");
			}
		});
		mButtonSeven.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "7");
			}
		});
		mButtonEight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "8");
			}
		});
		mButtonNine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "9");
			}
		});
		mButtonZero.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextViewDetail.setText(mTextViewDetail.getText() + "0");
			}
		});
	}

}
