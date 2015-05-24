package tudor.com.supercalc;

/**
 * Created by Tudor on 21-May-15.
 */
public class PrepareString {
	
	public static String prepareStringForMathEval(String str) {
		String replaced = null;
//		str = str.replaceAll("π", String.format("(%0.10f)",Math.PI));
//		str = str.replaceAll("e", String.format("(%0.10f)",Math.E));


		if (str.matches("(\\d+[+\\-*/]*)*\\)*\\(+")){ // (x[+-*/])( || 2( => 2 | 222( => 222 | 5+( => 5| 2+3( |2+3+(
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

	public static String operatorMapping(String expression){
		String tmp = expression.replaceAll("×","*").replaceAll("÷","/");
		return tmp;
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

}
