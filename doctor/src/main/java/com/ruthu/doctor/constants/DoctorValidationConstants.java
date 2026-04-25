package com.ruthu.doctor.constants;

public final class DoctorValidationConstants {

    private DoctorValidationConstants() {}

    public static final String NAME_NOT_EMPTY = "doctorName should not be empty";
    public static final String NAME_SIZE = "doctorName should be between 3 and 50 characters";

    public static final String MOBILE_NOT_EMPTY = "doctorMobileNumber should not be empty";
    public static final String MOBILE_PATTERN = "doctorMobileNumber should be a valid 10-digit number";

    public static final String EMAIL_NOT_EMPTY = "doctorEmail should not be empty";
    public static final String EMAIL_VALID = "doctorEmail should be a valid email address";

    public static final String PROFESSION_NOT_EMPTY = "professionType should not be empty";
    public static final String PROFESSION_SIZE = "professionType should be between 3 and 50 characters";
    public static final String PROFESSION_NOT_FOUND = "professionType is not found please give properly";
}