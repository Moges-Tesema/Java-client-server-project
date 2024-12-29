package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.sql.Statement;

public class BankDB {
    //test other branch to collaborate with other developer in githubs
    private String account, fname, lname, password, phone;
    private int balance;
    private String DBurl = "jdbc:mysql://localhost:3306/bank";
    private String DBuser = "root";
    private String DBpassword = "2124Ce1a$astu";
    Connection bank;
    
    Statement addUser;

    public BankDB() {
        try{ 
        // 1. Register driver class
        // Class.forName("com.mysql.jdbc.Driver");
        // 2. stablish connection to mysql database using driver manager
        bank = DriverManager.getConnection(DBurl, DBuser, DBpassword);
        // 3 .create statement object
       
        addUser = bank.createStatement();

        bank.setAutoCommit(false);
        }catch(SQLException e){
            System.out.println("Unable to connect to database:");
        }

        String addUserQuery2 = "INSERT INTO user VALUES('103s8w9ee..','maralew','getu',20000.248,'0248294','temu27')";

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

    void addUser(String fname, String lname,String account,String password,String phone) {
    this.account=account;
    this.fname=fname;
    
    String addUserQuery = "INSERT INTO user VALUES(?,?,?,?)";
    try{

    
    addUser.addBatch(addUserQuery);
    addUser.addBatch(addUserQuery);
    addUser.executeBatch();
    bank.commit();
    }catch(SQLException e){
        System.out.println("unable to add user:");
    }
    }

    void getUser(String password) {
        try{ 
        Statement  getUserName = bank.createStatement();
        ResultSet userName = getUserName.executeQuery("select * from user where passwordasd;47kl=[]");
        while (userName.next()) {
            String name = userName.getString("fname");
            String acount = userName.getString("account");
            float balance = userName.getFloat("amount");
            String father = userName.getString("lname");
            String phone = userName.getString("phone");
            String passkey = userName.getString("password");
            System.out.println("\n\nUSER INFORMATION AT COMMERICAL BANK OF ETHIOPIA\nname: " + name + "\n father name: "
                    + father + "\nbalance: " + balance + "\naccount: " + acount +
                    "\nphone Number: " + phone + "\nPassword: " + passkey);

        }
        bank.close();
    }catch(SQLException e){
            System.out.println("some thing went wrong: ");
    }

    }
}