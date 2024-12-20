import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Thread.State;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
public class App {
    public static void main(String[] args) throws Exception {
        // 1. Register driver class 
        //Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/bank";
        String user="root";
        String password="2124Ce1a$astu";
        //2. stablish connection to mysql database using driver manager
        Connection bank = DriverManager.getConnection(url, user, password);
        Connection bankAdmin = DriverManager.getConnection(url, user, password);
        bank.setAutoCommit(false);
        bankAdmin.setAutoCommit(false);
        //3 .create statement object
        Statement getUserName = bank.createStatement();
        Statement addUser = bank.createStatement();
        Statement modify= bankAdmin.createStatement();
        String addUserQuery="INSERT INTO user VALUES('100048w9ee..','belete','marie',20000.248,'0248294','temu27')";
        String addUserQuery2="INSERT INTO user VALUES('103s8w9ee..','maralew','getu',20000.248,'0248294','temu27')";
        addUser.addBatch(addUserQuery);
        addUser.addBatch(addUserQuery2);
        addUser.executeBatch();
        /*4. execute the query
        notice: if the query is select resultSet object must be present to store geted information.
        moreover, the query method is executeQuery(SQLquery).
        if the query is update, insert delete, alter... other than select, which don't response any data, the
        query method should be executeUpdate(SQLquery).
        */
        String modifyUser = "update user set fname='balcha' where fname='abreham'";
        modify.executeUpdate(modifyUser);
       ResultSet userName= getUserName.executeQuery("select * from user");
       bank.commit();
       while( userName.next()){
        String name= userName.getString("fname");
        String acount = userName.getString("account");
        float balance = userName.getFloat("amount");
        String father= userName.getString("lname");
        String phone= userName.getString("phone");
        String passkey = userName.getString("password");
        System.out.println("\n\nUSER INFORMATION AT COMMERICAL BANK OF ETHIOPIA\nname: "+name+ "\n father name: "+ father+"\nbalance: "+balance+"\naccount: "+acount+
        "\nphone Number: "+phone+"\nPassword: "+passkey);

       }
       bank.close();
    }
}
