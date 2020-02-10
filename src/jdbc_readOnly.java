import java.sql.*;

public class jdbc_readOnly {
        public static void main(String[] args) {
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {

                System.out.println("Java JDBC PostgreSQL reading INFO");

                System.out.println("Connected to PostgreSQL database!");
                Statement statement = connection.createStatement();
                System.out.println("Reading records...");
                System.out.printf("%-30.30s %-30.30s%n", "id", "name");
                ResultSet resultSet = statement.executeQuery("SELECT id,name FROM public.info");
                while (resultSet.next()) {
                    System.out.printf("%-30.30s  %-30.30s%n", resultSet.getInt("id"), resultSet.getString("name"));
                }
            } catch (SQLException e) {
                System.out.println("Connection failure.");
                e.printStackTrace();
            }
        }
}
