package mirosimo.car_showroom2.Utils;

public class UtilsBase {
	public static boolean isLong(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        long d = Long.parseLong(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
