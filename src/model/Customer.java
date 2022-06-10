package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName ;
    private String lastName;
    private String email;
    public Customer(String firstName, String lastName, String validEmail) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = validEmail;
    }


    public  String getEmail(){
        return  this.email;
    }

    @Override
    public String toString() {
        return "Customer Name : " + firstName +"  "+ lastName + " \n Email " + email;
    }
}
