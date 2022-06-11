package model;

import java.util.regex.Pattern;

/**
 * This is a model class for Customers
 *
 * @author shifatsahariar
 *
 */
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

    /**
     * this method takes the input email from the constructor
     * @param email - String type email provided by user
     * @return - String email if its valid and match with provided regex
     */
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
