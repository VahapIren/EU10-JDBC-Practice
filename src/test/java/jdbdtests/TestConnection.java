package jdbdtests;

import Utilities.ConfigurationReader;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        String dbUrl= ConfigurationReader.getProperty("dbUrl");
        String dbUsername=ConfigurationReader.getProperty("dbUsername");
        String dbPassword=ConfigurationReader.getProperty("dbPassword");

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("SELECT * from regions");

        //next()->move pointer to the first row

        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
        //getting information with column index(starts1)
        System.out.println(resultSet.getString(2));

/*

        //1-europe
        //2-Americas
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        //move to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        //move to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));


 */

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
}
