/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

/**
 *
 * @author aad_s
 */
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Bookstore {
    
    public ArrayList<Customer> customerList = new ArrayList<>();
    public ArrayList<Book> bookList = new ArrayList<>();
    private Admin admin;
    private static Bookstore instance;
    protected static int count;
    
    protected Bookstore(){
    }
    
    public static Bookstore getInstance(){
        if(instance == null)
            instance = new Bookstore();
        return instance;
    }
    
    public void addBook(Book book){
        bookList.add(book);
        //System.out.println("Added book: \n" + book);
    }
    
    public void removeBook(String bookname){
        bookList.removeIf(b -> b.getName().equals(bookname));
    }
    
    public void addCustomer(Customer customer){
        customerList.add(customer);
        //System.out.println("Added customer: \n" + customer);
    }
    
    public void removeCustomer(String username){
        customerList.removeIf(c-> c.getUsername().equals(username));
    }
    
    public void printBooks(){
        if(!bookList.isEmpty()){
            System.out.println("Books in bookstore: ");
            for(Book b: bookList){
                System.out.println(b);
            }
        }
    }
    
    public void printCustomers(){
        if(!customerList.isEmpty()){
            System.out.println("Customer list: ");
            for(Customer c: customerList){
                System.out.println(c);
            }
        }
    }
    
    public int returnCount(){
        System.out.println(count);
        return count;
    }
    
    public boolean verifyLogin(String username, String password){
        for(int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getUsername().equals(username) && customerList.get(i).getPassword().equals(password)){
                count = i;
                return true;
            }
        }
        
        return false;
    }
    protected void readBooks(File books){
        bookList.clear();
        try{
            Scanner scan = new Scanner (books);
            if(books.length() != 0){
                while (scan.hasNextLine()){
                    String[] token = scan.nextLine().split("\\s+");
                    String name = token[0];
                    double price = Double.parseDouble(token[1]);
                    bookList.add(new Book(name, price));
                }
                scan.close();
            }else{
                System.out.println("No books found.");
            }
        }
        catch(IOException e){
            System.out.println("An Error occured");
            e.printStackTrace();
        }
    }
    
    public void writeCustomer(Customer c){
        try{
            FileWriter myWriter = new FileWriter("tempCustomer.txt", false);
                String information = c.getUsername() + " " + c.getPassword() + " " + c.getPoints() + "\n";
                myWriter.write(information);
   
            myWriter.close();
        }
            catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }  
    }
    protected void writeBooks(File books){
        try{
            FileWriter myWriter = new FileWriter("Books.txt", false);
            for (Book b: this.bookList){
                String information = b.getName() + " " + b.getPrice() + "\n";
                myWriter.write(information);
            }
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }  
    }
    
    protected void readCustomers(File customers){
        customerList.clear();
        try{
            Scanner scan = new Scanner (customers);
            if(customers.length() != 0){
                while (scan.hasNextLine()){
                    String[] tokens = scan.nextLine().split("\\s+");
                    String username = tokens[0];
                    String password = tokens[1];
                    int points = Integer.parseInt(tokens[2]);
                    customerList.add(new Customer(username, password, points));
                }
                scan.close();
            }else{
                System.out.println("No customers found.");
            }
        }
        catch (IOException e){
            System.out.println("An Error occured");
            e.printStackTrace();
        }
    }
    
    protected void writeCustomers(File customer){
        try{
            FileWriter myWriter = new FileWriter(customer, false);
            for (Customer c: this.customerList){
                String information = c.getUsername() + " " + c.getPassword() + " " + c.getPoints() + "\n";
                myWriter.write(information);
            }
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
    
    
   
}