package bookstore;

import java.util.Scanner;


public class Admin extends User 
{
    String username;
    String password;
    
    public Admin(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    private String getUsername(){
        return this.username;
    }
   
    private String getPass(){
        return this.password;
    }
    
    
    @Override 
    public boolean verifyLogin(String username, String password)
    {
        boolean result;
        if(username.equals(this.getUsername()) && password.equals(this.getPass()))
        {
            result = true;
            //System.out.println(result);
        }
        else
        {
            result = false;
            //System.out.println(result);
        }
        return result;
    }
         
}