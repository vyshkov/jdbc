import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author vovan
 */
public class Sample1 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); //CNFE

        final String conUrl = "jdbc:mysql://localhost:3306/test";
        Connection con = DriverManager.getConnection(conUrl, "root", "1234"); //SQLE

        Statement stmt = con.createStatement();
        java.sql.Time r;
        ResultSet rs = stmt.executeQuery("select * from USERS");

        printResultSet(rs);

        stmt.close();

        System.out.println("Finish");
    }

    static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsCount = rsmd.getColumnCount();

        printHeader(columnsCount, rsmd);

        while (rs.next()) {
            printRow(columnsCount, rs);
        }

        printFooter();
    }

    private static void printHeader(int columnsCount, ResultSetMetaData rsmd) throws SQLException {
        System.out.println("\n----------------*----------------");
        System.out.print("|");
        for (int columnNumber = 1; columnNumber <= columnsCount; columnNumber++) {
            System.out.printf("%-15s|", rsmd.getColumnName(columnNumber));
        }
        System.out.println("\n----------------*----------------");
    }

    private static void printRow(int columnsCount, ResultSet rs) throws SQLException {
        System.out.print("|");
        for (int i = 1; i <= columnsCount; i++) {
            System.out.printf("%-15s|", rs.getString(i));
        }
        System.out.println("");
    }

    private static void printFooter() {
        System.out.println("|_______________*_______________|\n\n");
    }

}
