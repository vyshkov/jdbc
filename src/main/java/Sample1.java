import java.sql.Connection;
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
        
        Connection con = ConnectionFactoryProp.getConnection();

        Statement stmt = con.createStatement();
        
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

        printFooter(columnsCount);
    }

    private static void printHeader(int columnsCount, ResultSetMetaData rsmd) throws SQLException {
        System.out.print("*");
        for(int i = 0; i < columnsCount; i++)
            System.out.print("---------------*");
        System.out.print("\n|");
        for (int columnNumber = 1; columnNumber <= columnsCount; columnNumber++) {
            System.out.printf("%-15s|", rsmd.getColumnName(columnNumber));
        }
        System.out.print("\n*");
        for(int i = 0; i < columnsCount; i++)
            System.out.print("---------------*");
        System.out.print("\n");
    }

    private static void printRow(int columnsCount, ResultSet rs) throws SQLException {
        System.out.print("|");
        for (int i = 1; i <= columnsCount; i++) {
            System.out.printf("%-15s|", rs.getString(i));
        }
        System.out.println("");
    }

    private static void printFooter(int columnsCount) {
        System.out.print("*");
        for(int i = 0; i < columnsCount; i++)
            System.out.print("---------------*");
        System.out.print("\n");
    }

}
