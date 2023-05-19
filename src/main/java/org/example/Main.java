package org.example;

import config.SpringConfig;
import entity.CustomerEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static CustomerRepository customerRepository = (CustomerRepository) context.getBean("customerRepository");
    public static void main(String[] args) {
//        createNewCustomer();
//        listAllCustomer();
//        findCustomerById(2);
//        findCustomerByName("Liemm");
//        findCustomerByPhoneOrEmail("09058818000","thanhliemphan@gmail.com");
        findCustomerBySexAndYearsOldBetween("male",20,30);
    }
    private static void createNewCustomer(){
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName("Liem");
        customerEntity.setBirthDay(LocalDate.parse("1997-08-16"));
        customerEntity.setSex("Male");
        customerEntity.setEmail("thanhliemphan97@gmail.com");
        customerEntity.setPhone("0905881800");
        customerEntity.setAddress("218,Ton Duc Thang,Da Nang");

        CustomerEntity result = customerRepository.save(customerEntity);
        if (result != null){
            System.out.println("A new customer saved successfully,customer ID = "+customerEntity.getId());
        }
    }
    private static void listAllCustomer(){
        List<CustomerEntity> customerEntities = (List<CustomerEntity>) customerRepository.findAll();
        System.out.println("Found " + customerEntities.size() + " customer in the table book");
        System.out.println("They are: ");
        for (CustomerEntity customer:customerEntities) {
            System.out.println(customer.toString());
        }
    }
    private static void findCustomerById(int customerID){
        if (customerRepository.existsById(customerID) == true){
            CustomerEntity customerEntity = customerRepository.findById(customerID).get();
            System.out.println("Found a customer with book ID = " + customerID);
            System.out.println(customerEntity.toString());
        } else {
            System.out.println("Not found any customer with book ID = " + customerID);
        }
    }
    private static void findCustomerByName(String name){
        List<CustomerEntity> customerEntities = customerRepository.findByName(name);
        if (customerEntities.size()!=0) {
            System.out.println("Found " + customerEntities.size() + " customer(s) have name is " + name);
            System.out.println("They are:");
            for (CustomerEntity customer:customerEntities) {
                System.out.println(customer.toString());
            }
        } else {
            System.out.println("Not found any customer have name is " + name);
        }
    }
    private static void findCustomerByPhoneOrEmail(String phone,String email){
        List<CustomerEntity> customerEntityList = customerRepository.findByPhoneOrEmail(phone,email);
        if (customerEntityList.size()!=0) {
            System.out.println("Found " + customerEntityList.size() + " customer(s) have phone is " + phone + " or email is " + email);
            System.out.println("They are:");
            for (CustomerEntity customer:customerEntityList) {
                System.out.println(customer.toString());
            }
        }
        else{
            System.out.println("Not found any customer have phone is " + phone + " or email is " + email);
        }
    }
    private static void findCustomerBySexAndYearsOldBetween(String sex, int startAge, int endAge){
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusYears(endAge);
        LocalDate endDate = currentDate.minusYears(startAge);
        List<CustomerEntity> customerEntityList = customerRepository.findBySexAndBirthDayBetween(sex,startDate,endDate);
        if (customerEntityList.size()!=0){
            System.out.println("Found " + customerEntityList.size() + " customer(s) have sex is " + sex + " and years old from " + startAge + " to " + endAge);
            System.out.println("They are:");
            for (CustomerEntity customer:customerEntityList) {
                System.out.println(customer.toString());
            }
        } else {
            System.out.println("Not found any customer have sex is " + sex + " and years old from " + startAge + " to " + endAge);
        }
    }
}