package dao;

import com.mchange.v2.c3p0.DataSources;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DBConnect
{

    public DBConnect()
    {
    }

    public static Connection getConnection()
    {
        if(ds == null)
            try
            {
                ds = DataSources.pooledDataSource(DataSources.unpooledDataSource(jdbcURL));
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        try
        {
            Connection c = ds.getConnection();
            return c;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String jdbcURL = "jdbc:mysql://localhost/magazzino?user=root&password=root";
    private static DataSource ds;

}

