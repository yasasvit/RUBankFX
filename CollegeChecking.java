package src;
/**
* A specific type of checking account that extends the Checking class. 
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class CollegeChecking extends Checking {
    
    private static final double ANNUAL_INTEREST_RATE = 0.01;
    private static final double MONTHLY_FEE = 0.0;

    private int campusCode;

    /**
    * Constructor to create College Checking account
     * @param Profile object representing holder of account
     * @param double representing balance of account
     * @param int representing campus code
    */
    public CollegeChecking(Profile holder, double balance, int campusCode) {
        super(holder, balance);
        this.campusCode = campusCode;
    }

    /**
     * Returns the campus code
     * @return integer representing campus code
     */
    public int getCampusCode() {
        return campusCode;
    }
    /**
     * Getter that returns CC for this CollegeChecking class
     * @return "CC" representing account type
     */
    @Override
    public String getAccountType() {
        return "CC";
    }
    /**
     * Getter that returns the monthly fee
     * @return double that represents the monthly fee
     */
    @Override
    public double monthlyFee() {
        return MONTHLY_FEE;
    }

    /**
    * Checks if the provided object is an instance of CollegeChecking
    * @param Object obj represents a potential collegeChecking account
    * @return a boolean if the object is an instance of CollegeChecking
    */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollegeChecking) {
            CollegeChecking other = (CollegeChecking) obj;
            return super.equals(other);
        }
        return false;
    }
    /**
     * Provides a string representation of the college checking account
     * @param Object obj represents a potential collegeChecking account
     * @return a string representation of collegeChecking
     */
    @Override
    public String toString() {
        String campus = "";
        switch (campusCode) {
            case 0:
                campus = "NEW_BRUNSWICK";
                break;
            case 1:
                campus = "NEWARK";
                break;
            case 2:
                campus = "CAMDEN";
                break;
            default:
                break;
        }
        return super.toString() + "::" + campus;

    }
}
