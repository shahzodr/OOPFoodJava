import java.sql.*;
import java.util.Arrays;

public class MySQLUtils {

    private static Connection connection;
    private static final String host = "localhost";
    private static final String database = "fos_db";
    private static final String username = "root";
    private static final String password = "root";
    private static final int port = 3306;

    private static void MySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        return connection;
    }

    public static String[][] fetchOrders() {
        try {
            MySQL();
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM orders", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet results = statement.executeQuery();

            ResultSetMetaData metadata = results.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            results.last();
            int numberOfRows = results.getRow();
            results.beforeFirst();
            String[][] ResultSetArray = new String[numberOfRows][numberOfColumns];
            int i = 0;
            while (results.next()) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (j == 3) {
                        ResultSetArray[i][j] = results.getString("food");
                    } else {
                        ResultSetArray[i][j] = results.getString(j + 1);
                    }
                }
                i++;
            }

            return ResultSetArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] searchOrders(String customerName) {
        try {
            MySQL();
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM orders WHERE customerName LIKE '%" + customerName + "%'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet results = statement.executeQuery();

            ResultSetMetaData metadata = results.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            results.last();
            int numberOfRows = results.getRow();
            results.beforeFirst();
            String[][] ResultSetArray = new String[numberOfRows][numberOfColumns];
            int i = 0;
            while (results.next()) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (j == 3) {
                        ResultSetArray[i][j] = results.getString("food");
                    } else {
                        ResultSetArray[i][j] = results.getString(j + 1);
                    }
                }
                i++;
            }

            return ResultSetArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void newOrder(String customerName, String customerPhone, String[] food, double totalPrice) {
        try {
            MySQL();
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO orders (customerName, customerPhone, food, totalPrice) VALUES(?, ?, ?, ?)");
            statement.setString(1, customerName);
            statement.setString(2, customerPhone);
            statement.setString(3, Arrays.toString(food));
            statement.setDouble(4, totalPrice);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(int id) {
        try {
            MySQL();
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM orders WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrderStatus(int id, String status) {
        if (!status.equalsIgnoreCase("completed") && !status.equalsIgnoreCase("not completed")) {
            return;
        }

        try {
            MySQL();
            PreparedStatement statement = getConnection().prepareStatement("UPDATE orders SET status=? WHERE id=" + id);
            statement.setString(1, status);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] toArray(String s) {
        return s.replace("[", " ").replace("]", " ").split(",");
    }
}
