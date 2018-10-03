package digital.and.telecomprovider.model;

import java.util.Objects;

/**
 * Java representation of a London landline phone number
 */
public class PhoneNumber {

    private final int countryCode;
    private final int areaCode;
    private final int phoneNumber;

    public PhoneNumber(int countryCode, int areaCode, int phoneNumber) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Creates a new PhoneNumber.
     *
     * <p>A valid phone number consists of a plus sign and country code,
     * followed by the area code and phone number, all of which are separated by a space.</p>
     * <p>Example: +44 20 12345678</p>
     *
     * @param phoneNumber the phone number as a String
     */
    public PhoneNumber(String phoneNumber) {
        String[] split = phoneNumber.split(" ");
        if (split.length < 3 || !split[0].startsWith("+")) {
            throw new IllegalArgumentException("Incorrect phone number format!");
        }

        this.countryCode = Integer.parseInt(split[0].substring(1));
        this.areaCode = Integer.parseInt(split[1]);
        this.phoneNumber = Integer.parseInt(split[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return countryCode == that.countryCode &&
                areaCode == that.areaCode &&
                phoneNumber == that.phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, areaCode, phoneNumber);
    }

    @Override
    public String toString() {
        return "+" + countryCode +
                " (0) " + areaCode +
                " " + phoneNumber;
    }

}
