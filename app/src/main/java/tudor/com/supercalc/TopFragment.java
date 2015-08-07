package tudor.com.supercalc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

import java.math.BigDecimal;

import static java.lang.Double.parseDouble;
import static tudor.com.supercalc.PrepareString.prepareStringForMathEval;

public class TopFragment extends Fragment {
	private Button mButtonBracketOpen;
	private Button mButtonBracketClose;

	private Button mButtonDel;
	private Button mButtonRoot;

	private TextView mTextViewResult;
	private TextView mTextViewDetail;

	public TopFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_top, container, false);

		mButtonBracketOpen = (Button) view.findViewById(R.id.buttonBracketOpen);
		mButtonBracketClose = (Button) view.findViewById(R.id.buttonBracketClose);

		mButtonRoot = (Button) view.findViewById(R.id.buttonRoot);
		mButtonDel = (Button) view.findViewById(R.id.buttonDel);


		mTextViewDetail = (TextView) view.findViewById(R.id.textViewDetail);
		mTextViewResult = (TextView) view.findViewById(R.id.textViewResult);
		mTextViewDetail.setMovementMethod(new LinkMovementMethod());
		mTextViewResult.setMovementMethod(new LinkMovementMethod());
		mTextViewResult.requestFocus(); // without these IDK what happens in scrollable textview because it won't display it's contents
		mTextViewDetail.requestFocus();


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

				if (!s.equals("") && s != null && s.matches(".*\\D+.*")) {
					Expression expression;
					s = prepareStringForMathEval(s);
					try { // try default evaluation
						expression = new ExpressionBuilder(s)
								.operator(factorial)
								.function(log)
								.function(ln)
								.build();
						BigDecimal d = new BigDecimal(expression.evaluate())
								.setScale(15, BigDecimal.ROUND_HALF_UP) // ROUND_HALF_UP because 6.2 = 6.2
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
									.setScale(15, BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros();
							mTextViewResult.setText(d.toPlainString());

						} catch (NumberFormatException infinityException) {
							mTextViewResult.setText(getString(R.string.infinity));
						} catch (IllegalArgumentException ex) {
							mTextViewResult.setText("");
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
			@Override
			public boolean onLongClick(View v) {
				mTextViewDetail.setText("");
				mTextViewResult.setText("");
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

	public void setTextView(String str){
		mTextViewDetail.setText(mTextViewDetail.getText()+str);
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
				if (result!=null && !result.isEmpty())
					mTextViewDetail.setText(String.valueOf((int) Math.floor(parseDouble(result))));
				mTextViewResult.setText("");
				break;
			case R.string.rand:
				mTextViewDetail.setText(String.valueOf(Math.random()));
				break;
			case R.string.equal: // TODO: 07-Aug-15 add nice animations
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
					mTextViewDetail.setText(mTextViewDetail.getText().subSequence(0, str.length() - 1) + getString(operator));
					break;
				default:
					mTextViewDetail.setText(mTextViewDetail.getText() + getString(operator));
					break;
			}
	}
}
