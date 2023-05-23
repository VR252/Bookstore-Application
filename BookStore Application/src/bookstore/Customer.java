package bookstore;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Customer extends User{
    private String username;
    private String password;
    private int points;
    private String memStatus;
    private Cart myCart;
   
    
    public Customer(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points;
        myCart = new Cart(this);
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public String getStatus (){
        if (points >= 1000){
            memStatus = "Gold";
        }
        else{
            memStatus = "Silver";
        }
        return memStatus; 
    }
    
    public Cart getCart(){
        return this.myCart;
    }
    
    public void writeCustomer (File word){
        try{
            word.delete();
            FileWriter myWriter = new FileWriter(word, true);
                String information = this.username + " " + this.password + " " + this.points + "\n";
                myWriter.write(information);
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Customer> readCustomer(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try{
            File file = new File ("Customers.txt");
            Scanner sc = new Scanner (file);
            while (sc.hasNextLine()){
                String[] tokens = sc.nextLine().split("\\s+");
                String username = tokens[0];
                String password = tokens[1];
                int points = Integer.parseInt(tokens[2]);
                customers.add(new Customer(username, password, points));
            }
            sc.close();
        }
        catch (IOException e){
            System.out.println("An Error occured");
            e.printStackTrace();
        }
        return customers;
    }
    
    //@Override
    public boolean verifyLogin(String username, String password){
        this.username = username;
        this.password = password;
        try{
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> passwords = new ArrayList<String>();
        Scanner inFile = new Scanner(new File("Customers.txt"));
        while(inFile.hasNextLine()){
            String[] tokens = inFile.nextLine().split("\\s+");
            usernames.add(tokens[0]);
            passwords.add(tokens[1]);
        }
        inFile.close();

        boolean found = false, valid = false;
        for(int i = 0; i < usernames.size(); i++){
            if(usernames.get(i).equals(username)){
                found = true;
                if(passwords.get(i).equals(password))
                    valid = true;
        break;
            }
        }
        if(found){
            if(valid)
                System.out.println("Success!");
            else    
                System.out.println("Failed! - Incorrect password");
        }
        else 
            System.out.println("Account does not exist!");
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    return true;
    }
    
    @Override
    public String toString(){
        return ("Username: " + this.getUsername() + "\nPassword: " + this.getPassword() + "\nPoints: " + this.getPoints() + "\nStatus: " + this.getStatus() + "\n"); 
    }
}