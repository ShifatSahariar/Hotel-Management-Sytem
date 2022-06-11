package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName ;
    private final String lastName;
    private final String email;
    public Customer(String firstName, String lastName, String validEmail) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailValidation(validEmail);
    }


    public  String getEmail(){
        return  this.email;
    }
    private String emailValidation(String email) {
        String EMAIL_REGEX = "^(.+)@(.+).com$";
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Email is not valid ");
        } else {
            return email;
        }

    }
    @Override
    public String toString() {
        return "Customer Name : " + firstName +"  "+ lastName + " \n Email " + email;
    }
}
