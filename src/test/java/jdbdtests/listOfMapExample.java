package jdbdtests;

import Utilities.ConfigurationReader;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class listOfMapExample {
    String dbUrl= ConfigurationReader.getProperty("dbUrl");
    String dbUsername=ConfigurationReader.getProperty("dbUsername");
    String dbPassword=ConfigurationReader.getProperty("dbPassword");

    @Test
    public void test1(){

        //creating list for keeping all rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1=new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");
        System.out.println(row1.toString());

        Map<String, Object> row2=new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");
        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        //get steven lastname dirctly from list
        System.out.println(queryData.get(0).get("last_name"));

        System.out.println(queryData.get(1).get("salary"));

    }

    @Test
    public void test2() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("SELECT first_name,last_name,salary,job_id from employees");

        //in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd= resultSet.getMetaData();

        resultSet.next();

        //creating list for keeping all rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1=new HashMap<>();
        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));
        System.out.println(row1.toString());

        resultSet.next();

        Map<String, Object> row2=new HashMap<>();
        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));
        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);




        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
