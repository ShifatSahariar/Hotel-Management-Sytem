package service;


import model.Customer;

import java.util.*;
/**
 * This class is a singleton class
 * This class will store Customers data in hashmap
 * adding and getting customers information
 *
 * @author shifatsahariar
 */
public class CustomerService {
    // Declaring Static Reference
    private static CustomerService singletonCustomerService;
    private CustomerService(){}
    public static CustomerService getInstance(){
        if (singletonCustomerService == null){
             singletonCustomerService = new CustomerService();
        }

        return singletonCustomerService;
    }

    // Saving Customers in a hashmap with email as a key
    private HashMap<String,Customer> customersMap = new HashMap<>();

    // Adding new customers using hashmap .

    /**
     * method to add customer into the hashmap
     * @param email email as a key
     * @param firstName string type data
     * @param lastName string type data
     */
    public void addCustomer(String email , String firstName , String lastName){
    customersMap.put(email,new Customer(firstName, lastName, email));
    }
    //getting the customer from hashmap using the key > email
    public Customer getCustomer(String customerEmail){
        return customersMap.get(customerEmail);
    }

    /**
     * Getting all the customers information from map
     * @return Customers , if no customers found return null
     */
    // getting all the customers from the hashmap as values .
    public Collection<Customer> getAllCustomers(){
        if (customersMap.isEmpty()){
            System.out.println("No Customers found");
            return null;
        }
        else return customersMap.values();
    }
}
