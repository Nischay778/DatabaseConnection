package org.example;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DatabaseConnection {
    public static void main(String[] args) throws IOException , RuntimeException {
//        public String fetchData(){
//        String jdbcUrl = "jdbc:mysql://CRED-666CJS3:3306/employee";
        Properties properties = new Properties();
        FileInputStream ip= null;
        ip = new FileInputStream("config.properties");
        properties.load(ip);

        String jdbcUrl = "jdbc:mysql://CRED-666CJS3:3306/employee?user=root&password=Nischay@123";
        String dbUser = "ASHUT";
        String dbPassword = "password";
        String data = "init Data ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            String sql = properties.getProperty("query1");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            WriteToCSV writeToCSV = new WriteToCSV();   //write to csv file

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            String[] columnHeaders = new String[columns];
            for(int i=1; i<=columns; i++)
            {
                columnHeaders[i-1] = metaData.getColumnName(i);
            }

            writeToCSV.WriteToCSV(columnHeaders);
            String[] rowData = new String[columns];
            while (resultSet.next())
            {
                for(int  i = 1; i<=columns; i++ )
                {
                    rowData[i-1] = resultSet.getString(i);
                }
                writeToCSV.WriteToCSV(rowData);
            }
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}