package DataConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/qlsv", "hdlinh", "1234");
            return connect;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
