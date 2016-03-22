package tudor.com.calculatorSleek.fragments;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

import java.math.BigDecimal;

import tudor.com.calculatorSleek.android.R;

import static java.lang.Double.parseDouble;
import static tudor.com.calculatorSleek.extra.PrepareString.prepareStringForMathEval;

public class TopFragment extends Fragment {
	private Button mButtonBracketOpen;
	private Button mButtonBracketClose;

	private Button mButtonDel;
	private Button mButtonRoot;

	private TextView mTextViewResult;
	private EditText mTextViewDetail;

	public TopFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_top, container, false);

		mButtonBracketOpen = (Button) view.findViewById(R.id.buttonBracketOpen);
		mButtonBracketClose = (Button) view.findViewById(R.id.buttonBracketClose);

		mButtonRoot = (Button) view.findViewById(R.id.buttonRoot);
		mButtonDel = (Button) view.findViewById(R.id.buttonDel);


		mTextViewDetail = (EditText) view.findViewById(R.id.textViewDetail);
		mTextViewResult = (TextView) view.findViewById(R.id.textViewResult);
		mTextViewDetail.setMovementMethod(new LinkMovementMethod());
		mTextViewResult.setMovementMethod(new LinkMovementMethod());
		mTextViewResult
				.requestFocus(); // without these IDK what happens in scrollable textview because it won't display it's contents
		mTextViewDetail.requestFocus();

		mTextViewDetail.setRawInputType(InputType.TYPE_NULL);
//		mTextViewDetail.setFocusable(true);
		eventsOperators();
		return view;
	}

	private void eventsOperators() {
		mTextViewDetail.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence sequence, int start, int before, int count) {
				String s = sequence.toString();

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

				if (!s.isEmpty() && s.matches(".*\\D+.*")) {
					Expression expression;
					try { // try default evaluation
						expression = new ExpressionBuilder(s).operator(factorial).function(log).function(ln).build();
						BigDecimal d = new BigDecimal(expression.evaluate()).setScale(15,
																					  BigDecimal.ROUND_HALF_UP) // ROUND_HALF_UP because 6.2 = 6.2
											   .stripTrailingZeros();// if would be ROUND_UP then 6.2 = 6.20001

						mTextViewResult.setText(d.toPlainString());
					} catch (IllegalArgumentException | ArithmeticException e) { // if default fails
						try { // parse the string and try again

							expression = new ExpressionBuilder(prepareStringForMathEval(s)).operator(factorial).function(log)
																						   .function(ln).build();
							BigDecimal d = new BigDecimal(expression.evaluate()).setScale(7, BigDecimal.ROUND_HALF_UP)
																				.stripTrailingZeros();
							mTextViewResult.setText(d.toPlainString());

						} catch (NumberFormatException infinityException) {
							mTextViewResult.setText(getString(R.string.infinity));
						} catch (IllegalArgumentException ex) {
							mTextViewResult.setText("");
						} catch (ArithmeticException ex) {
							mTextViewResult.setText("Error");
						} catch (Exception ex) {
							mTextViewDetail.setText("");
						}
					} catch (Exception e1) {
						mTextViewResult.setText("Error");
					}
				} else
					mTextViewResult.setText("");
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		mTextViewDetail.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
//				LayoutParams lparams = new LayoutParams(
//						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//				LeBubble tv=new TextView();
//				tv.setLayoutParams(lparams);
//				tv.setText("test");
//				this.m_vwJokeLayout.addView(tv);

				return false;
			}
		});
		mButtonRoot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTextView(getString(R.string.root));
			}
		});
		mButtonDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String s = mTextViewDetail.getText().toString();
				if (s.length() > 0)
					mTextViewDetail.setText(s.substring(0, s.length() - 1));
			}
		});
		mButtonDel.setOnLongClickListener(new View.OnLongClickListener() {
			@TargetApi (Build.VERSION_CODES.LOLLIPOP)
			@Override
			public boolean onLongClick(View v) {
				mTextViewResult.setText("");
//				mTextViewDetail.scrollTo(0,0);
				mTextViewDetail.getText().clear();
				mTextViewDetail.clearFocus();
				mTextViewDetail.requestFocus();

				int[] clickCoords = new int[2];
				mTextViewResult.getLocationOnScreen(clickCoords);
				Point size = new Point();
				getActivity().getWindowManager().getDefaultDisplay().getSize(size);
				int maxRadius = size.y;

				Animator animator = ViewAnimationUtils
											.createCircularReveal(mTextViewDetail, clickCoords[0], clickCoords[1], 0,
																  maxRadius);
				animator.start();
				return true;
			}
		});

		mButtonBracketOpen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTextView("(");
			}
		});
		mButtonBracketClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTextView(")");
			}
		});

	}

	public void setTextView(String str) {
		mTextViewDetail.setText(mTextViewDetail.getText() + str);
	}

	public void setTextView(int symbol) {
		String result = mTextViewResult.getText().toString();
		switch (symbol) {
			case R.string.ceiling:
				if (result != null && !result.isEmpty())
					mTextViewDetail.setText(String.valueOf((int) Math.ceil(parseDouble(result))));
				mTextViewResult.setText("");
				break;
			case R.string.floor:
				if (result != null && !result.isEmpty())
					mTextViewDetail.setText(String.valueOf((int) Math.floor(parseDouble(result))));
				mTextViewResult.setText("");
				break;
			case R.string.rand:
				mTextViewDetail.setText(String.valueOf(Math.random()));
				break;
			case R.string.equal: // TODO: 07-Aug-15 add nice animations
				if (!mTextViewResult.getText().toString().equalsIgnoreCase("error"))
					mTextViewDetail.setText(mTextViewResult.getText());
				mTextViewResult.setText("");
				break;
			case R.string.dot: // TODO: 07-Aug-15 hande cases 2.2.2.2.2 and 2.2.2 + 1.1.1.1 etc
//				String tempStr = mTextViewDetail.getText().toString() + getString(R.string.dot);
//				Log.d("TEST", tempStr);
//				if (tempStr.matches("(\\d+\\.\\d+[\\-+/*])*"))
//					;
//				else break;
			default:
				String str = mTextViewDetail.getText().toString();
				checkForMultipleOperators(str, symbol);
		}
	}

	/**
	 * Verifica daca pe pozitia anterioara din string avem un operator si daca avem atunci il inlocuieste
	 * cu operatorul butonului apasat
	 *
	 * @param str      stringul de evaluat
	 * @param operator
	 */
	private void checkForMultipleOperators(String str, int operator) {
		if (str.isEmpty() && getString(operator).charAt(0) == '-')
			mTextViewDetail.setText(getString(operator));
		if (str != null && !str.isEmpty())
			switch (str.charAt(str.length() - 1)) {
				case '.':
				case '+':
				case '%':
				case '-':
				case '*':
				case '^':
				case '×':
				case '/':
				case '÷':
					mTextViewDetail
							.setText(mTextViewDetail.getText().subSequence(0, str.length() - 1) + getString(operator));
					break;
				default:
					mTextViewDetail.setText(mTextViewDetail.getText() + getString(operator));
					break;
			}
	}
}
