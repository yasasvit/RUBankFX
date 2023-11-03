package src;
/**
* Profile class represents a person's profile, containing their first name, last name, and date of birth. 
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    /**
    * Constructor that takes the first name, last name, and date of birth as parameters and initializes them
    * @param String fname representing first name
     * @param String lname representing last name
     * @param Date object representing dob
    */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Gets the first name of the profile
     * @return String representing the first name
     */
    public String getFname() {
        return fname;
    }
    /**
     * Gets the first name of the profile
     * @return String representing the first name
     */
    public String getLname() {
        return lname;
    }
    /**
     * Gets the date of birth of the profile
     * @return Date object representing the date of birth
     */
    public Date getDob() {

        return dob;
    }
    /**
     * Gets the full name (first + last name) of the profile
     * @return String representing the full name
     */
    public String getFullName() {

        return fname + " " + lname;
    }

    /**
    * Checks if the provided object is an instance of Profile and then compares 
    * the first name, last name, and date of birth.
    * @param Object which represents the instance of Profile
    * @return a boolean which indicates it obj matches the Profile information
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Profile other = (Profile) obj;
        if (!fname.equals(other.fname)) {
            return false;
        }
        if (!lname.equals(other.lname)) {
            return false;
        }
        if (!dob.equals(other.dob)) {
            return false;
        }
        return true;
    }

    /**
    * Combines the hash codes of the first name, last name, and date of birth.
    * @return an int representation of the generated hashcode
    */
    @Override
    public int hashCode() {
        int result = fname.hashCode();
        result = 31 * result + lname.hashCode();
        result = 31 * result + dob.hashCode();
        return result;
    }

    /**
     * Converts the profile to String representation
     * @return String that represents the Profile
     */
    @Override
    public String toString() {
        return getFullName() + " " + dob.toString();
    }

    /**
    * Allows profiles to be compared based on their full names.
    * @param Profile object "other" which is to be compared
    * @return an int to represent comparison status
    */
    @Override
    public int compareTo(Profile other) {
        return this.getFullName().compareTo(other.getFullName());
    }
}
