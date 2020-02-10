import java.sql.*;
import java.util.Scanner;

public class JDBC_RW {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) {
            int no_of_records,sl_num,i=0,j=0;
            String name;
            System.out.println("Java JDBC PostgreSQL RW INFO\n==============================================");
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO public.info(id, name) VALUES (?, ?)");
            ResultSet resultSet;

            System.out.println("========================================================\nExisting Table :\n\n==============================================================");
            resultSet = statement.executeQuery("SELECT id,name FROM public.info order by id");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getInt("ID"), resultSet.getString("Name"));
            }

            System.out.println("\n=============================================\n Insertion \n==================================================");
            System.out.println("Enter the number of records to enter :");
            no_of_records=s.nextInt();
            System.out.println("Enter records :");
            while (no_of_records>0){
                sl_num=s.nextInt();
                name=s.next();
                stmt.setInt(1,sl_num);
                stmt.setString(2,name);
                i =stmt.executeUpdate();
                if(i<1){
                    System.out.println("Insert failed :\n Exiting ......");
                    System.exit(1);
                }
                j++; no_of_records--;
            }

            System.out.println("Total rows inserted :"+j);
            System.out.println("========================================================\n Updated Table : \n\n==============================================================");
            resultSet = statement.executeQuery("SELECT id,name FROM public.info order by id");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getInt("ID"), resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }
}

