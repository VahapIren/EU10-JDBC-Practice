package jdbdtests;

import Utilities.ConfigurationReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl= ConfigurationReader.getProperty("dbUrl");
    String dbUsername=ConfigurationReader.getProperty("dbUsername");
    String dbPassword=ConfigurationReader.getProperty("dbPassword");
    @Test
    public void test1() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("SELECT * from departments");

        //next()->move pointer to the first row
//        resultSet.next();
//        System.out.println(resultSet.getString(2));

        //display departments table in 10-Administration-200-1700 format

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)+" - "
                    +resultSet.getString(3)+" - "+resultSet.getInt(4));
        }

        resultSet= statement.executeQuery("SELECT * from regions");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException{

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("SELECT * from departments");

        //how to find how many rows we have for the query
        resultSet.last();
        int rowCount=resultSet.getRow();
        System.out.println(rowCount);

        //to move to  first row
        resultSet.beforeFirst();

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException{

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("SELECT * from employees");

        //get the database related data inside the dbMataData object
        DatabaseMetaData dbMetaData= connection.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());

        //get the resultsetmetadat
        ResultSetMetaData rsMetaData= resultSet.getMetaData();

        //how many columns we have
        int columnCount = rsMetaData.getColumnCount();
        System.out.println(columnCount);

        //getting columns names
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));
        System.out.println(rsMetaData.getColumnName(3));

        //priny all column names dynamically

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }





        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
