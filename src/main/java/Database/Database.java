package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Database implements AutoCloseable {
    private static Database database = null;
    private final Connection connection;


    public static Database getDatabaseInstance(){
        if (database == null) {
            try {
                database = new Database();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    private Database() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks?autoReconnect=true&useSSL=false", "root","cosmin123");
        createTables();
    }

    private void createTables() throws SQLException {
        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        List<String> databaseTablesName = new ArrayList<>();
        while(results.next())
            databaseTablesName.add(results.getString("TABLE_NAME").toLowerCase());

        if (databaseTablesName.size() != 5){
            HashMap<String, String> tableStatements = new HashMap<>();
            tableStatements.put("belts", "CREATE TABLE belts (name varchar(45) primary key, price varchar(45), " +
                    "description varchar(200), supplier varchar(45), size varchar(5), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("jackets", "CREATE TABLE jackets (name varchar(45) primary key, price varchar(45), " +
                    "description varchar(200), supplier varchar(45), size varchar(5), color varchar(20), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("phones", "CREATE TABLE phones (name varchar(45) primary key, price varchar(45), " +
                    "description varchar(200), supplier varchar(45), storage varchar(5), frontCamera varchar(6), backCamera varchar(6), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("jewelries", "CREATE TABLE jewelries (name varchar(45) primary key, price varchar(45), " +
                    "description varchar(200), supplier varchar(45), material varchar(5), weight varchar(6), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("suppliers", "CREATE TABLE suppliers (name varchar(45) primary key, address varchar(200), " +
                    "phoneNumber varchar(45))");
            for(Map.Entry<String, String> table : tableStatements.entrySet()) {
                boolean found = databaseTablesName.contains(table.getKey());
                if (!found) {
                    connection.createStatement().execute(table.getValue());
                }
            }
        }
    }

    public void insertProduct(String[] data, String table) {
        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM "+table+" WHERE name = ?");
            st1.setString(1, data[0]);
            ResultSet rs = st1.executeQuery();
            if(!rs.next()) {
                if(data.length == 7) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?)");
                    for (int parameterIndex = 1; parameterIndex <= 7; parameterIndex++)
                        statement.setString(parameterIndex, data[parameterIndex - 1]);
                    int i = statement.executeUpdate();
                }
                if(data.length == 8) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?)");
                    for (int parameterIndex = 1; parameterIndex <= 8; parameterIndex++)
                        statement.setString(parameterIndex, data[parameterIndex - 1]);
                    int i = statement.executeUpdate();
                }
                if(data.length == 9) {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?)");
                    for (int parameterIndex = 1; parameterIndex <= 9; parameterIndex++)
                        statement.setString(parameterIndex, data[parameterIndex - 1]);
                    int i = statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSupplier(String[] data) {
        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM suppliers WHERE name = ?");
            st1.setString(1, data[0]);
            ResultSet rs = st1.executeQuery();
            if(!rs.next()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO suppliers VALUES (?,?,?)");
                for (int parameterIndex = 1; parameterIndex <= 3; parameterIndex++)
                    statement.setString(parameterIndex, data[parameterIndex - 1]);
                int i = statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductPrice(String table, String name, String newPrice) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + table + " SET price = ? WHERE name =?");
            statement.setString(1, newPrice);
            statement.setString(2, name);
            int i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String name, String table) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE name = ?");
            statement.setString(1, name);
            int i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void close() throws Exception {
        connection.close();
    }
}
