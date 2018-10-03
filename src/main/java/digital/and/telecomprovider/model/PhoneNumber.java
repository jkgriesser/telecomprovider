package digital.and.telecomprovider.model;

import java.util.Objects;

/**
 * Java representation of a London landline phone number
 */
public class PhoneNumber {

    private int countryCode;
    private int areaCode;
    private int phoneNumber;

    private boolean activated;

    public PhoneNumber() {
    }

    /**
     * Creates a new PhoneNumber.
     *
     * <p>A valid phone number consists of a plus sign and country code,
     * followed by the area code and phone number, all of which are separated by hyphen.</p>
     * <p>Example: +44-20-12345678</p>
     *
     * @param phoneNumber the phone number as a String
     */
    public PhoneNumber(String phoneNumber) {
        String[] split = phoneNumber.split("-");
        if (split.length < 3 || !split[0].startsWith("+")) {
            throw new IllegalArgumentException("Incorrect phone number format!");
        }

        this.countryCode = Integer.parseInt(split[0].substring(1));
        this.areaCode = Integer.parseInt(split[1]);
        this.phoneNumber = Integer.parseInt(split[2]);
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return countryCode == that.countryCode &&
                areaCode == that.areaCode &&
                phoneNumber == that.phoneNumber &&
                activated == that.activated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, areaCode, phoneNumber, activated);
    }

    @Override
    public String toString() {
        return "+" + countryCode +
                "-" + areaCode +
                "-" + phoneNumber;
    }

}
