package framework.utils;

/**
 * Created by TestUnity
 * Date: 2024-10-30
 * Project Name: AccuMExS
 */


public interface DateTimeFormat {

    public static final String DATE_FORMAT_DASH_DD_MMM_YYYY = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_SLASH_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_SLASH_MM_DD_YYYY = "MM/dd/yyyy";
    public static final String DATE_FORMAT_SLASH_MM_DD_YY = "MM/dd/yy";
    public static final String DATE_FORMAT_DASH_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_DASH_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd-HH-mm-ss";
    public static final String TIME_FORMAT_US_HH_MM_SS = "HH_mm_ss";
    
    public static int getMonthIndex(Object monthInput) {
        if (monthInput == null) {
            throw new IllegalArgumentException("Month cannot be null");
        }
        
        if (monthInput instanceof String) {
            String month = ((String) monthInput).toUpperCase(); // Convert to uppercase
            switch (month) {
                case "JANUARY": return 1;
                case "FEBRUARY": return 2;
                case "MARCH": return 3;
                case "APRIL": return 4;
                case "MAY": return 5;
                case "JUNE": return 6;
                case "JULY": return 7;
                case "AUGUST": return 8;
                case "SEPTEMBER": return 9;
                case "OCTOBER": return 10;
                case "NOVEMBER": return 11;
                case "DECEMBER": return 12;
                default: throw new IllegalArgumentException("Invalid month: " + month);
            }
        } else if (monthInput instanceof Integer) {
            int monthIndex = (Integer) monthInput;
            if (monthIndex < 1 || monthIndex > 12) {
                throw new IllegalArgumentException("Invalid month index: " + monthIndex);
            }
            return monthIndex; // Return the month index directly
        } else {
            throw new IllegalArgumentException("Month must be a String or Integer");
        }
    }

}
