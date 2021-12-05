import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class sqlinputClass
{
    private String username;
    private String password;
    private String url;
    private Connection currentConnection;

    public sqlinputClass() throws SQLException
    {
        this.username = "root";
        this.password = "S1Em$e*r#23";
        this.url = "jdbc:mysql://localhost:3306/caliyxdb";
        this.currentConnection = DriverManager.getConnection(this.url,this.username,this.password);
    }

    //change these if needed
    public boolean inserIntoProvinceTable(int id, String name) throws SQLException
    {

        String insertQuery = "INSERT INTO `caliyxdb`.`province_table` (`province_id`, `province_name`) VALUES (?, ?);";
        PreparedStatement statement = this.currentConnection.prepareStatement(insertQuery);
        statement.setInt(1,id);
        statement.setString(2,name);
        statement.executeUpdate();
        return true;
    }

    public boolean insertAreaTable(int zip_id, String areaName, int province_id) throws SQLException
    {
        String insertQuery = "CALL caliyxdb.insertArea(?, ?, ?);";
        CallableStatement statement = this.currentConnection.prepareCall(insertQuery);
        statement.setInt(1,zip_id);
        statement.setString(2,areaName);
        statement.setInt(3,province_id);
        statement.executeUpdate();
        return true;
    }

    public boolean insertHouse(generatedHouse insertThis) throws SQLException
    {
        String insertQuery = "CALL caliyxdb.newHouse(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = this.currentConnection.prepareStatement(insertQuery);
        statement.setInt(1,insertThis.getHouseId());
        statement.setInt(2,insertThis.getFloors());
        statement.setInt(3,insertThis.getBedrooms());
        statement.setInt(4,insertThis.getKitchens());
        statement.setInt(5,insertThis.getBathrooms());
        statement.setInt(6,insertThis.getPool());
        statement.setInt(7,insertThis.getGarage());
        statement.setInt(8,insertThis.getPrice());
        statement.setInt(9, ThreadLocalRandom.current().nextInt(400,4214+1));
        statement.executeUpdate();
        return true;
    }

    public boolean insertRandomReferenceNumber() throws SQLException
    {

        int generateRandomHouse_Id = ThreadLocalRandom.current().nextInt(100000,999999+1);

        String query = "CALL caliyxdb.insertRefNumber(?,?,?);";
        CallableStatement statement = this.currentConnection.prepareCall(query);
        int min = 100000;
        int max = 999999;


        int generateRandomamount = ThreadLocalRandom.current().nextInt(100000,900000+1);
        int generateRandomRef = ThreadLocalRandom.current().nextInt(min, max +1);

        statement.setInt(1,generateRandomRef);
        statement.setInt(2,generateRandomamount);
        statement.setInt(3,generateRandomHouse_Id);
        statement.executeUpdate();

        return true;
    }
}
