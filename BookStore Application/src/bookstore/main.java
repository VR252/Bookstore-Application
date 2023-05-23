/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.Group;
import javafx.scene.control.TextField; 
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author adm
 */
public class main extends Application {
    //Instance Variables
    Scene sceneLogin, sceneAdmin;
    TableView<Book> tableBook;
    TableView<Customer> tableCustomer;
    Bookstore BT = new Bookstore();
    File Books = new File("Books.txt");
    File customersFile = new File("Customers.txt");
    Button buyButton = new Button("Buy");
    Button redeemButton = new Button("Redeem points and Buy");
    Button buttonOut1 = new Button("logout");
    Button buttonOut2 = new Button("logout");
    Button buttonOut3 = new Button("logout");
    Button buttonB1 = new Button("Add");
    Button buttonB2 = new Button("Back");         
    Button buttonB3 = new Button("Delete");
    TextField textFieldCos1 = new TextField(); 
    TextField textFieldCos2 = new TextField(); 
    Button buttonC1 = new Button("add");
    final Button buttonC2 = new Button("remove");
    Button buttonC3 = new Button("back");
     
      
    TextField textFieldBook1 = new TextField();     
    TextField textFieldBook2 = new TextField();  
    ObservableList<Book> books;
    ObservableList<Book> buyBooks;
    Customer cos;
    
    @Override
    public void start(Stage primaryStage) {
      //Login Screen variables
      Admin adminCheck = new Admin("admin", "admin");
      
      Text textW = new Text("Welcome to the BookStore App");    
      Text text1 = new Text("Username:");       
      Text text2 = new Text("Password:"); 
             
      TextField textField1 = new TextField();             
      PasswordField textField2 = new PasswordField();  
     
      Button button1 = new Button("Login"); 
      
     
      //implementing login screen 
      GridPane gridPane = new GridPane();    
      
      gridPane.setMinSize(600, 400);

      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      gridPane.setVgap(3); 
      gridPane.setHgap(3);        
      gridPane.setAlignment(Pos.CENTER); 
      
      gridPane.add(textW, 0, 0);
      gridPane.add(text1, 0, 1); 
      gridPane.add(textField1, 1, 1); 
      gridPane.add(text2, 0, 2);       
      gridPane.add(textField2, 1, 2); 
      gridPane.add(button1, 0, 3); 
      
      //Styling for buttons and text
      button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;"); 
      text1.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
      text2.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
      gridPane.setStyle("-fx-background-color: #D1CDC4;"); 
        
      //implementing owner start screen
      VBox layoutStart = new VBox(20);
      layoutStart.setAlignment(Pos.CENTER);
      Button button2 = new Button("Books");         
      Button button3 = new Button("Customers");  
       Button button4 = new Button("logout");
     
      layoutStart.getChildren().add(button2);
      layoutStart.getChildren().add(button3);
      layoutStart.getChildren().add(button4);
      
      //implementing buttons for book table
      buttonB1.setOnAction(e -> {
            Book newBook = new Book(textFieldBook1.getText(), Double.parseDouble(textFieldBook2.getText()));
            tableBook.getItems().add(newBook);
            BT.readBooks(Books);
            BT.addBook(newBook);
            BT.writeBooks(Books);
         });
      buttonB2.setOnAction(e -> {
          primaryStage.setScene(sceneAdmin);
      
      });
      buttonB3.setOnAction(e -> {
          BT.readBooks(Books);
          BT.removeBook(tableBook.getSelectionModel().getSelectedItem().getName());
          BT.writeBooks(Books);
          tableBook.getItems().remove(tableBook.getSelectionModel().getSelectedItem());
      });
      
     
      
      //implementing buttons for customer table
      buttonC1.setOnAction(e -> {
            Customer newCustomer = new Customer(textFieldCos1.getText(), textFieldCos2.getText(), 0);
            tableCustomer.getItems().add(newCustomer);
            BT.readCustomers(customersFile);
            BT.addCustomer(newCustomer);
            BT.writeCustomers(customersFile);
         });
      
 
      buttonC2.setOnAction(e -> {
          
          BT.readCustomers(customersFile);
          BT.removeCustomer(tableCustomer.getSelectionModel().getSelectedItem().getUsername());
          BT.writeCustomers(customersFile);
          tableCustomer.getItems().remove(tableCustomer.getSelectionModel().getSelectedItem());
          
      });
      
             buttonC3.setOnAction(e -> {
          primaryStage.setScene(sceneAdmin);
      
      });
      
      
      
     
        //check for admin, then go into admin screen
        button1.setOnAction(e -> {
            
            BT.readCustomers(customersFile);
            if (adminCheck.verifyLogin(textField1.getText(), textField2.getText())){
                primaryStage.setScene(sceneAdmin);
          }
           
            //Checks for the password and username of the customer
            //if it matches the textField input, send you to the customer login.
            for(Customer c: BT.customerList){
                if(c.getUsername().equals(textField1.getText()) && c.getPassword().equals(textField2.getText())){
                cos = c;
                primaryStage.setScene(new Scene(customersLoginScreen(0), 750, 550));
                }
                else{
                
                }
        }

            
      });

        //button to go into admin books screen
         button2.setOnAction(e -> {
             primaryStage.setScene(new Scene(adminBookScreen(0), 600, 550));
        

         });
       
        //button to go into costumer screen
          button3.setOnAction(e -> {primaryStage.setScene(new Scene(adminCustomerScreen(0), 600, 550));});
          
        //buttons to logout
          button4.setOnAction(e -> {primaryStage.setScene(sceneLogin);});
          buttonOut1.setOnAction(e -> {primaryStage.setScene(sceneLogin);});
          buttonOut2.setOnAction(e -> {primaryStage.setScene(sceneLogin);});
          buttonOut3.setOnAction(e -> {primaryStage.setScene(sceneLogin);});
      
         //button for purchasing using [Buy] button
          buyButton.setOnAction(e->{
                buyBooks = FXCollections.observableArrayList();
         for(Book boughtBooks: books){
             if(boughtBooks.trueorFalse()){
             buyBooks.add(boughtBooks);
             }
         }  
        primaryStage.setScene(new Scene(customerBuyScreen(0), 400, 300));
      }
 
          
          );
          //button for purchasing using [Redeem/Buy] button
          redeemButton.setOnAction(e->{
                buyBooks = FXCollections.observableArrayList();
         for(Book boughtBooks: books){
             if(boughtBooks.trueorFalse()){
             buyBooks.add(boughtBooks);
             }
         } primaryStage.setScene(new Scene(customerRedeemScreen(0), 400, 300));
                    }
 
          
          );
      
      
      //starting arguements
      sceneLogin = new Scene(gridPane);  
      sceneAdmin = new Scene(layoutStart, 500, 300);  
      primaryStage.setTitle("Bookstore App"); 
      primaryStage.setScene(sceneLogin);
      primaryStage.show(); }
      
    
/*
* Allows access into the admin books screen.
* Includes table using TableView to display a list of all books in Books.txt.
* Includes 3 buttons: add, delete, logout.
* Includes 2 textfields: name and price.
* 
* By writing a name and price for the book, after pressing add it will display
* the newly added book into the tableView. It will also be written to the 
* Books.txt file to record the current inventory of books.
*
* The Remove button deletes the selected book in the tableView and deletes it
* from the Books.txt file, and the logout button accesses the login screen
*   
* @param type
* @return the admin book screen.
*/
    
public Group adminBookScreen(int type){
    Group ABS = new Group();
    
      TableColumn<Book, String> bookNameColumn = new TableColumn<>("Name"); 
      bookNameColumn.setMinWidth(200);
      bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
      
      TableColumn<Book, Double> bookPriceColumn = new TableColumn<>("Price"); 
      bookPriceColumn.setMinWidth(200);
      bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
      
      tableBook = new TableView<>();
      tableBook.setItems(getBook());
      tableBook.getColumns().addAll(bookNameColumn, bookPriceColumn);
      
      GridPane gridBook = new GridPane();
      gridBook.add(tableBook, 0, 0, 3, 1);
      gridBook.add(buttonB1, 0, 1, 1, 1);
      gridBook.add(textFieldBook1, 1, 1, 1, 1);
      gridBook.add(textFieldBook2, 2, 1, 1, 1);
      gridBook.add(buttonB2, 0, 2, 1, 1);
      gridBook.add(buttonB3, 1, 2, 2, 1);
      
      gridBook.setHgap(10);
      gridBook.setVgap(10);
      
      gridBook.setPadding(new Insets(10,10,10,10));
      ABS.getChildren().addAll(gridBook);
        return ABS;
}
/*
* Allows access into the admin customers screen.
* Includes table using TableView to display a list of all customers in Customers.txt.
* Includes 3 buttons: add, remove, logout.
* Includes 2 textfields: username and password.
* 
* The textfield will write a username and password for a costumer, 
* after pressing add it will display the costumer info and point(point should be
* set to 0 for new costumers).
* Customer.txt file to record the current inventory of books.
*
* The Remove button deletes the selected customer in the tableView and deletes it
* from the Customer.txt file, and the logout button accesses the login screen
*   
* @param type
* @return the admin customer screen.
*/
public Group adminCustomerScreen(int type){
      Group ACS = new Group();
      //Table for customers
      TableColumn<Customer, String> cosUsernameColumn = new TableColumn<>("Username"); 
      cosUsernameColumn.setMinWidth(200);
      cosUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
      
      TableColumn<Customer, String> cosPasswordColumn = new TableColumn<>("Password"); 
      cosPasswordColumn.setMinWidth(200);
      cosPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
       
      TableColumn<Customer, Integer> cosPointsColumn = new TableColumn<>("Points"); 
      cosPointsColumn.setMinWidth(200);
      cosPointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
      
      tableCustomer = new TableView<>();
      tableCustomer.setItems(getCustomer());
      tableCustomer.getColumns().addAll(cosUsernameColumn, cosPasswordColumn, cosPointsColumn);
      
      GridPane gridCos = new GridPane();
      gridCos.add(tableCustomer, 0, 0, 5, 1);
      gridCos.add(buttonC1, 2, 1, 1, 1);
      gridCos.add(buttonC2, 0, 2, 1, 1);
      gridCos.add(buttonC3, 1, 2, 1, 1);
      gridCos.add(textFieldCos1, 0, 1, 1, 1);
      gridCos.add(textFieldCos2, 1, 1, 1, 1);
      
      gridCos.setHgap(30);
      gridCos.setVgap(10);
      
      gridCos.setPadding(new Insets(10,10,10,10));
      ACS.getChildren().addAll(gridCos);
      return ACS;
}
/*
* Allows access into the customer Login screen.
* Includes table using TableView with a list of books and checkboxes.
* Includese 3 buttons: buy, redeem/buy, logout.
*
* Buy Button accesses the buy screen with your selected books, Redeem and
* buy Button accesses the redeem and buy screen with selected books and logout
* button accesses the login screen.
* @param type
* @return the customer login screen.
*/
public Group customersLoginScreen(int type){
    Group CLS = new Group();
    Text welcomeMsg = new Text("Welcome, " + cos.getUsername() + ". You have " + cos.getPoints() + " Points. Your status is " + cos.getStatus() + ".");
        
    if (tableBook != null){tableBook.getItems().clear();
    tableBook.getColumns().clear();
        tableBook.setFocusModel(null);}
    
    else{
        tableBook = new TableView<>();
    }
        
        
        TableColumn<Book, String> titleColumn = new TableColumn<>("Book Name");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        TableColumn< Book, String > checkColumn = new TableColumn<>( "Select" );
        checkColumn.setMinWidth(100);
        checkColumn.setStyle("-fx-alignment: CENTER;");
        checkColumn.setCellValueFactory(new PropertyValueFactory<>("select"));

       
        tableBook.setItems(getBook());
        tableBook.getColumns().addAll(titleColumn, priceColumn, checkColumn);
        GridPane gridLog = new GridPane();
        gridLog.add(welcomeMsg, 0, 0);
        gridLog.add(tableBook, 0, 1, 3, 1);
        gridLog.add(buyButton, 0, 2);
        gridLog.add(redeemButton, 1, 2);
        gridLog.add(buttonOut1, 2, 2);
        CLS.getChildren().addAll(gridLog);
        return CLS;
        
        
    
    }

/*
* Allows access into the customer Buy screen.
* Includes two Text objects and a button. The texts return the total cost of
* the books and the points collected from previous and current purchase, also
* returns the current status of the costumer.
* Logout button accesses the login screen.
* @param type
* @return the customer buy screen.
*/
public Group customerBuyScreen(int type){
    Group buyBook = new Group();
     Cart bCart = new Cart(cos);
     for(Book bookAdded: buyBooks){
     bCart.addToCart(bookAdded);
     BT.readBooks(Books);
     BT.removeBook(bookAdded.getName());
     BT.writeBooks(Books);
     }
     Text buyText = new Text("Total Cost: " + bCart.getTotalPrice());
     bCart.buyBooks();
     Text pointText = new Text("Points: " + bCart.buyer.getPoints() + " Status: " + bCart.buyer.getStatus() );
      buyText.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
      pointText.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
          
     GridPane gridBuy = new GridPane();
     gridBuy.add(buyText, 0, 0);
     gridBuy.add(pointText, 0, 1);
     gridBuy.add(buttonOut2, 0, 2);
      
      gridBuy.setHgap(10);
      gridBuy.setVgap(20);
      gridBuy.setPadding(new Insets(10,10,10,10));
      
     buyBook.getChildren().addAll(gridBuy);
     return buyBook;
}

/*
* Allows access into the customer Redeem screen.
* Includes two Text objects and a button. The texts return the total cost of
* the books after redeeming the points and the points of the current purchase, 
* also returns the current status of the costumer.
* Logout button accesses the login screen.
* @param type
* @return the customer redeem screen.
*/

public Group customerRedeemScreen(int type){
    Group redeemBook = new Group();
     Cart bCart = new Cart(cos);
     for(Book bookAdded: buyBooks){
     bCart.addToCart(bookAdded);
     BT.readBooks(Books);
     BT.removeBook(bookAdded.getName());
     BT.writeBooks(Books);
     }
     bCart.buyWithPoints();
     Text buyText = new Text("Total Cost: " + bCart.getTotalPrice());
     Text pointText = new Text("Points: " + bCart.buyer.getPoints() + " Status: " + bCart.buyer.getStatus() );
     buyText.setStyle("-fx-font: normal bold 15px 'Verdana' "); 
     pointText.setStyle("-fx-font: normal bold 15px 'Verdana' ");  
     
     GridPane gridRedeem = new GridPane();
     gridRedeem.add(buyText, 0, 0);
     gridRedeem.add(pointText, 0, 1);
     gridRedeem.add(buttonOut3, 0, 2);
     gridRedeem.setHgap(10);
     gridRedeem.setVgap(20);
     gridRedeem.setPadding(new Insets(10,10,10,10));
     redeemBook.getChildren().addAll(gridRedeem);
     return redeemBook;
}

/*
* Returns a observableList of books by scanning the Books.txt file and 
* adding all Book objects from Books.txt into the books observable ArrayList.
* 
* @return a observable list of Object Book currently in the Books.txt file
* @throws IOException
*/
public ObservableList<Book> getBook(){   
    
  books = FXCollections.observableArrayList();  
    try{
            Scanner scan = new Scanner (Books);
            if(Books.length() != 0){
                while (scan.hasNextLine()){
                    String[] token = scan.nextLine().split("\\s+");
                    String name = token[0];
                    double price = Double.parseDouble(token[1]);
                    books.add(new Book(name, price));
                }
                scan.close();
            }else{
                System.out.println("No books found.");
            }
        }
        catch(IOException e){
            System.out.println("An Error occured");
            e.printStackTrace();
        };
  return books;
}

/*
* Returns a observableList of customers by scanning the Customers.txt file and 
* adding all Customer objects from Customers.txt into the customers
* observable ArrayList.
* 
* @return a observable list of ObjectCustomer currently in the Customers.txt file
* @throws IOException
*/

public ObservableList<Customer> getCustomer(){
     ObservableList<Customer> customers = FXCollections.observableArrayList();  
    try{
            Scanner scan = new Scanner (customersFile);
            if(customersFile.length() != 0){
                while (scan.hasNextLine()){
                    String[] tokens = scan.nextLine().split("\\s+");
                    String username = tokens[0];
                    String password = tokens[1];
                    int points = Integer.parseInt(tokens[2]);
                    customers.add(new Customer(username, password, points));
                }
                scan.close();
            }else{
                System.out.println("No costumers found.");
            }
        }
        catch(IOException e){
            System.out.println("An Error occured");
            e.printStackTrace();
        };
   return customers;
}

//runs main.
public static void main(String args[]){ 
        
      launch(args); 
      
   } 
}


