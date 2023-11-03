package src;
import java.util.Calendar;
/**
 Represents a Date with year, month, and day
 @author Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;
    public static final int QUADYEAR = 4;
    public static final int CENTURY = 100;
    public static final int QUADCENTURY = 400;

    /**
     * Creates a date object given a string representing the date
     * @param date String representing the date
     */
    public Date(String date) {
        String[] tokens = date.split("/");
        this.month = Integer.parseInt(tokens[0]);
        this.day = Integer.parseInt(tokens[1]);
        this.year = Integer.parseInt(tokens[2]);
    }
    /**
     * Creates a date object given information about the month, day, and year
     * @param month Integer representing the month
     * @param day Integer representing the day
     * @param year Integer representing the year
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    /**
     * Creates a date object based on today's date
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.year = calendar.get(Calendar.YEAR);
    }
    /**
     * Creates a date object that has identical info to another date object
     * @param date another date object
     */
    public Date(Date d) {
        this.year = d.year;
        this.month = d.month;
        this.day = d.day;
    }
    /**
     * Returns the year of a date object
     * @return int representing the year
     */
    // Getter methods
    public int getYear() {
        return year;
    }
    /**
     * Returns the month of a date object
     * @return int representing the month
     */
    public int getMonth() {
        return month;
    }
    /**
     * Returns the day of a date object
     * @return int representing the day
     */
    public int getDay(){
        return day;
    }
    /**
     * Compares the current date object to another date object
     * @param date the data object you are comparing the current one with
     * @return -1 if year is earlier, 0 if year is same, 1 if year is after
     */
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }
    /**
     * Checks if the current date object is equal another date object
     * @param date the object you are comparing the current one with
     * @return true if date objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;

        if (this.year != date.year) {return false; }
        if (this.month != date.month) {return false; }
        if (this.day != date.day) {return false; }
        return true;
    }
    /**
     * Returns a Date object based on today's object
     * @return Date object using today's date
     */
    public static Date today() {
        return new Date();
    }
    /**
     * Check whether the date object is valid
     * @return true if date object is valid, false otherwise
     */
    public boolean isValid() {
        if (month <= 0 || month > 12 || day <= 0 || day > 31 || year <= 0 || year > 2024) {
            return false;
        }

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[2] = 29; // When it's a leap year, Feb has 29 days
        }
        return day <= daysInMonth[month];
    }
    /**
     * Check whether the date is from the past
     * @return true if date is in the pase, false otherwise
     */
    public boolean isPast() {
        Date currentDate = new Date();
        return this.compareTo(currentDate) < 0;
    }
    /**
     * Check whether the date is within 6 months from the future
     * @return true if date is within the 6 months, false otherwise
     */
    public boolean isWithinSixMonths() {
        Date futureDate = new Date(3, 15, 2024);
        return this.compareTo(futureDate) <= 0;
    }
    /**
     * Check whether the year is a leap year
     * @param year we are checking
     * @return true if year is leap year, false otherwise
     */
    private boolean isLeapYear(int year) {
        if (year % QUADYEAR == 0) {
            if (year % CENTURY == 0) {
                return year % QUADCENTURY == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Converts the date into a string representation
     * @return String representation of the date
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }


}