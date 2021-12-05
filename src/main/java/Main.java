import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{
    public static void main(String args[]) throws SQLException, IOException
    {
        //System.out.println(new randomHouseGenerator().generateHouse().toString());
        //initializeTbProvince();System.out.println("Province Insertion Completed");
        //initializeAreas();
        //insertRandomHouses();
        generateReferences();
    }

    public static void insertRandomHouses() throws SQLException
    {
        sqlinputClass inputT = new sqlinputClass();
        randomHouseGenerator generator = new randomHouseGenerator();
        int errorCounter = 0;
        for(int i=0;i < 1000;)
        {
            try
            {
                generatedHouse newHouse = generator.generateHouse();
                inputT.insertHouse(newHouse);
                ++i;
            }
            catch(SQLException e)
            {
                ++errorCounter;
            }
        }
        System.out.println("Number of Zipcode errors or Number of Id Collisions: " + errorCounter);
    }

    public static void initializeTbProvince() throws IOException, SQLException
    {
        sqlinputClass inputT = new sqlinputClass();
        BufferedReader scanThefile = new BufferedReader(new FileReader("F:\\Feivel\\Programming\\IntelliJ\\databaseDataInserter\\data\\provincelist2.csv"));
        String line = "";

        int iterations = 0;
        while((line = scanThefile.readLine()) != null)
        {
            String[] regionDeets = line.split(",");
            int id = Integer.parseInt(regionDeets[0]);
            String name = regionDeets[1];
            inputT.inserIntoProvinceTable(id,name);
            ++iterations;
        }
        scanThefile.close();

    }

    public static void initializeAreas() throws IOException, SQLException
    {
        sqlinputClass inputT = new sqlinputClass();
        BufferedReader scanThefile = new BufferedReader(new FileReader("F:\\Feivel\\Programming\\IntelliJ\\databaseDataInserter\\data\\arealist.csv"));
        String line = "";

        int collisions = 0;
        int limit = 0;
        while((line = scanThefile.readLine()) != null && limit < 1000)
        {
            try
            {
                String[] areaDeets = line.split(",");
                int zip_id = Integer.parseInt(areaDeets[0]);
                String name = areaDeets[1];
                int randomProvince = ThreadLocalRandom.current().nextInt(1, 71+1);

                System.out.println(zip_id + " " + name + " " + randomProvince);

                inputT.insertAreaTable(zip_id,name,randomProvince);
                ++limit;

            }
            catch(SQLException e)
            {
                ++collisions;
                String[] areaDeets = line.split(",");

                int zip_id = collisions + Integer.parseInt(areaDeets[0]);

                String name = areaDeets[1];
                int randomProvince = ThreadLocalRandom.current().nextInt(1, 71+1);

                inputT.insertAreaTable(zip_id,name,randomProvince);


                System.out.println("Duplicate Detected.... Adding " + collisions);
                System.out.println(zip_id + " " + name + " " + randomProvince);
                ++limit;

            }
        }
        System.out.println("Duplicates Counted: " + collisions);
        scanThefile.close();
    }

    public static void generateReferences() throws SQLException
    {
        sqlinputClass operation = new sqlinputClass();
        int errorCount = 0;
        int iteration = 10;
        while(0 < iteration)
        {
            try
            {
                operation.insertRandomReferenceNumber();
                --iteration;
            }catch (SQLException e)
            {
                ++errorCount;
            }
        }
        System.out.println("Counted Errors: " + errorCount);
    }




}
