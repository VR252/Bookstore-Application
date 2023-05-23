/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

public abstract class User 
{
    private String username;
    private String password;
    
    public void setUser(String username)
    {
        this.username = username;
    }
    
    public String getUser()
    {
        return username;
    }
        
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public abstract boolean verifyLogin(String username, String password);       
}