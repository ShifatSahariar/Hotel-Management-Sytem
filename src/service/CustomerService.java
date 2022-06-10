package service;

import model.Customer;

import java.util.*;

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


    private HashMap<String,Customer> customersMap = new HashMap<>();

    // Adding new customers using hashmap .
    public void addCustomer(String email , String firstName , String lastName){
    customersMap.put(email,new Customer(firstName, lastName, email));
    }
    //getting the customer from hashmap using the key > email
    public Customer getCustomer(String customerEmail){
        return customersMap.get(customerEmail);
    }
    // getting all the customers from the hashmap as values .
    public Collection<Customer> getAllCustomers(){
        if (customersMap.isEmpty()){
            System.out.println("No Customers found");
            return null;
        }
        else return customersMap.values();
    }
}
