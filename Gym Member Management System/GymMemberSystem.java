import java.sql.*;
import java.util.Scanner;

public class GymMemberSystem {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/gymdb";
        String user = "harsh";         // change to your MySQL username
        String pass = "newpassword";     // change to your MySQL password

        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL Driver
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Database connected successfully!");

            while (true) {
                System.out.println("\n=== Gym Member Management System ===");
                System.out.println("1. Add Member");
                System.out.println("2. Display All Members");
                System.out.println("3. Update Member Details");
                System.out.println("4. Delete Member");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        // INSERT
                        System.out.print("Enter Member ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Member Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Plan (Monthly/Yearly): ");
                        String plan = sc.nextLine();
                        System.out.print("Enter Join Date (YYYY-MM-DD): ");
                        String date = sc.next();
                        System.out.print("Enter Contact Number: ");
                        String contact = sc.next();

                        String insert = "INSERT INTO members VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement psInsert = con.prepareStatement(insert);
                        psInsert.setInt(1, id);
                        psInsert.setString(2, name);
                        psInsert.setString(3, plan);
                        psInsert.setString(4, date);
                        psInsert.setString(5, contact);

                        int rows = psInsert.executeUpdate();
                        System.out.println(rows + " record inserted successfully!");
                        psInsert.close();
                        break;

                    case 2:
                        // DISPLAY
                        String select = "SELECT * FROM members";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(select);

                        System.out.println("\nID\tName\t\tPlan\t\tJoin Date\tContact");
                        System.out.println("----------------------------------------------------------");
                        while (rs.next()) {
                            System.out.printf("%d\t%s\t\t%s\t\t%s\t%s\n",
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("plan"),
                                    rs.getDate("join_date"),
                                    rs.getString("contact"));
                        }
                        rs.close();
                        stmt.close();
                        break;

                    case 3:
                        // UPDATE
                        System.out.print("Enter Member ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Plan: ");
                        String newPlan = sc.nextLine();
                        System.out.print("Enter new Contact: ");
                        String newContact = sc.next();

                        String update = "UPDATE members SET plan=?, contact=? WHERE id=?";
                        PreparedStatement psUpdate = con.prepareStatement(update);
                        psUpdate.setString(1, newPlan);
                        psUpdate.setString(2, newContact);
                        psUpdate.setInt(3, uid);

                        int updated = psUpdate.executeUpdate();
                        System.out.println(updated + " record updated successfully!");
                        psUpdate.close();
                        break;

                    case 4:
                        // DELETE
                        System.out.print("Enter Member ID to delete: ");
                        int did = sc.nextInt();

                        String delete = "DELETE FROM members WHERE id=?";
                        PreparedStatement psDelete = con.prepareStatement(delete);
                        psDelete.setInt(1, did);

                        int deleted = psDelete.executeUpdate();
                        System.out.println(deleted + " record deleted successfully!");
                        psDelete.close();
                        break;

                    case 5:
                        // EXIT
                        System.out.println("👋 Exiting... Goodbye!");
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("❌ Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
