package controller;

public abstract class Utility {
	
	/**
	 * Function to check if a string is composed by non literal char's such as white spaces, numbers, symbols,
	 * and to check if it is greater than two chars (a meaningful word. Like 'paper', 'white', 'table'.)
	 * 
	 * @param s String to be checked
	 * 
	 * @return true If string is literal string and has more than 2 char's
	 * @return false if not true :)
	 */
	public static boolean stringIsWord(String s){
		if (s.matches("[a-zA-Z]+") && s.length()>2){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function to check if a string is composed by non literal char's such as white spaces, numbers, symbols,
	 * and to check if it is between 1 and 5 chars
	 * Can be used to check primarily conjunctions ('to', 'a', 'in', 'else', 'for'..)
	 * 
	 * @param s String to be checked
	 * 
	 * @return true If string is literal string
	 * @return false if not true :)
	 */
	public static boolean stringIsConjunction(String s){
		if (s.matches("[a-zA-Z]+") && s.length()<=4){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function to check if a string is composed by non literal char's such as numbers, and some non-literal symbols,
	 * such as $,%,...
	 * It will try to make sense out of a string, for example check that there's more literal char's than white spaces, etc..
	 * To calibrate the function, paragraphs of newspapers in different languages and about different topis were used
	 * The paragraphs are listed in the "ParagraphExamples" file
	 * 
	 * @param s String to be checked
	 * 
	 * @return true If string is literal string and has more than 2 char's
	 * @return false if not true :)
	 */
	public static boolean stringIsSentence(String s){
		String literal = s.replaceAll("[^\\p{L}\\p{Nd}]", "");
		String numbers = literal.replaceAll("[^0-9]+", "");
		String punctuation = s.replaceAll("[^\\.|,|;|:|!|?|À|Á|'|\"]+", "");
		
		double li = (double)(100*literal.length())/s.length();
		double num = (double)(100*numbers.length())/s.length();
		double pun = (double)(100*punctuation.length())/s.length();
		
		if((li>75&&li<85) && num<5 && pun>0 && pun<5){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	/**
	 * Function to check if a string is composed only by numbers (no commas, nor dots)
	 * 
	 * @param s String to be checked
	 * 
	 * @return true If string is numerical string, w/o strings nor dots
	 * @return false if not true :)
	 */
	public static boolean stringIsNumerical(String s){
		if (s.matches("[0-9]+")){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function to standardize string. Removes everything not literal and ASCII and puts all to lowercase
	 * 
	 * @param s String to be transformed
	 * 
	 * @return Lowercase, "purified" string.
	 */
	public static String purifyString(String s){
		return (s.replaceAll("[^A-z]", "")).toLowerCase();
	}
}
