package tudor.com.supercalc;

/**
 * Created by Tudor on 21-May-15.
 */
public class PrepareString {
	public static String prepareStringForMathEval(String str) {
		String replaced = null;

		if (str.charAt(str.length() - 1) == '(') {
			str = str.substring(0, str.length() - 1);
		}

		if (str.matches("(\\d+[+\\-*/]*)*\\(+")){ // (x[+-*/])( || 2( => 2 | 222( => 222 | 5+( => 5| 2+3( |2+3+(
			replaced = str.replaceAll("[+\\-*/]*\\(+", ""); //
		}else if (str.matches("(\\d+\\(+)+\\S+")) { // (x()y  23(2 => 23*(2)|23(23(24 => 23*(23*(24))
			int count = 0;
			StringBuilder builder = new StringBuilder(str);
			for (int i=0;i<str.length();++i)
				if (builder.charAt(i)=='(')
					++count;
				else if (builder.charAt(i)==')')
					--count;
			for (int i=0;i<count;++i)
				builder.append(')');
			replaced = builder.toString().replaceAll("\\(", "*(");
		}else if (str.matches("(\\d+[+\\-*/])+")){// when one operator is at the end of expressin like 2+ => 2, 5+3-4+ => 5+3-4
			replaced = str.substring(0,str.length()-1);
		}
		else if (str.matches("(.+\\(+)+\\S+")){ // 23+(4=>23+(4)
			int count = 0;
			if (isOperator(str.charAt(str.length()-1)))
				str = str.substring(0,str.length()-1);

			StringBuilder builder = new StringBuilder(str);
			for (int i=0;i<str.length();++i)
				if (builder.charAt(i)=='(')
					++count;
				else if (builder.charAt(i)==')')
					--count;
			for (int i=0;i<count;++i)
				builder.append(')');
			replaced = builder.toString();
		}else
			return str;

		return replaced;
	}

	private static boolean isOperator(char ch) {
		switch (ch) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '%':
				return true;
			default:
				return false;
		}
	}



}
