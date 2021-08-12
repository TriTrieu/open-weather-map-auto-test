package utils;

public class CurrencyFormat {
    public static String toDisplayValue(int value, String format){
        String toReturn = "";
        String s =  String.valueOf(value);
        int length = s.length();
        for(int i = length; i >0 ; --i){
            toReturn += s.charAt(i - 1);
            if((i - length - 1) % 3 == 0 && i != 1) toReturn += ',';
        }
        return new StringBuilder(toReturn).reverse().toString() + format;
    }
    public static String toDisplayValue(long value, String format){
        String toReturn = "";
        String s =  String.valueOf(value);
        int length = s.length();
        for(int i = length; i >0 ; --i){
            toReturn += s.charAt(i - 1);
            if((i - length - 1) % 3 == 0 && i != 1) toReturn += ',';
        }
        return new StringBuilder(toReturn).reverse().toString() + format;
    }
}
