package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class BankDB {
    //test other branch to collaborate with other developer in githubs
    private String account, fname, lname, password, phone;
    private double amount;
    private String DBurl = "jdbc:mysql://localhost:3306/bank";
    private String DBuser = "root";
    private String DBpassword = "2124Ce1a$astu";
    Connection bank;
    
    PreparedStatement addUser;

    public BankDB() {
        try{ 
        // 1. Register driver class
        // Class.forName("com.mysql.jdbc.Driver");
        // 2. stablish connection to mysql database using driver manager
        bank = DriverManager.getConnection(DBurl, DBuser, DBpassword);
        // 3 .create statement object
       
        addUser = bank.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?)");

        bank.setAutoCommit(false);
        }catch(SQLException e){
            System.out.println("Unable to connect to database:");
        }

        /*
         * 4. execute the query
         * notice: if the query is select resultSet object must be present to store
         * geted information.
         * moreover, the query method is executeQuery(SQLquery).
         * if the query is update, insert delete, alter... other than select, which
         * don't response any data, the
         * query method should be executeUpdate(SQLquery).
         */
       
    }

    void addUser(String account,String fname, String lname,double amount, String phone,String password) {
    this.account=account;
    this.fname=fname;
    this.lname=lname;
    this.amount=amount;
    this.phone=phone;
    this.password=password;
    
    try{
    addUser.setString(0,account);
    addUser.setString(1, fname);
    addUser.setString(2, lname);
    addUser.setDouble(3, amount);
    addUser.setString(4,phone);
    addUser.setString(5, password);
    addUser.executeUpdate();
    bank.commit();
    }catch(SQLException e){
        System.out.println("unable to add user:");
    }
    }

    void displayUser(String password) {
        try{ 
        PreparedStatement getUserName = bank.prepareStatement("select * from user where password=?");
        getUserName.setString(1, password);
        ResultSet userName = getUserName.executeQuery();
        while (userName.next()) {
            String name = userName.getString("fname");
            String acount = userName.getString("account");
            Double balance = userName.getDouble("amount");
            String father = userName.getString("lname");
            String phone = userName.getString("phone");
            String passkey = userName.getString("password");
            System.out.println("\n\nUSER INFORMATION AT COMMERICAL BANK OF ETHIOPIA\nname: " + name + "\n father name: "
                    + father + "\nbalance: " + balance + "\naccount: " + acount +
                    "\nphone Number: " + phone + "\nPassword: " + passkey);

        }
        bank.close();
    }catch(SQLException e){
            System.out.println("some thing went wrong, can't get user information");
    }

    }
}