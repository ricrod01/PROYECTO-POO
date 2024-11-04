/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para realizar conexiones, consultas y actualizaciones en la base de datos.
 * fecha_creación = 1/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector
{
    /**
     * @param URL Dirección de la base de datos.
     * @param USER Usuario de la base de datos.
     * @param PASSWORD Contraseña de la base de datos.
    */

    private static final String URL = "jdbc:mysql://localhost:3306/prueba";
    private static final String USER = "root";
    private static final String PASSWORD = "proyectojav@";

    /**
     * @return Devuelve una conexión a la base de datos.
    */

    private Connection connect()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } 
        catch (SQLException e)
        {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Permite cerrar la conexión a la base de datos.
     * @param connection Conexión a la base de datos.
    */

    private void close(Connection connection)
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    /**
     * @param query Consulta a realizar.
     * @return Devuelve los resultados de la consulta realizada.
    */

    public List<Map<String, Object>> executeQuery(String query)
    {
        List<Map<String, Object>> results = new ArrayList<>();
        Connection connection = connect();
        
        if (connection != null)
        {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query))
            {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();        
                    
                while (resultSet.next())
                {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++)
                    {
                        row.put(metaData.getColumnName(i), resultSet.getObject(i));
                    }
                    
                    results.add(row);
                }
            }
            
            catch (SQLException e)
            {
                System.out.println("Error al ejecutar consulta: " + e.getMessage());
            }
            
            finally
            {
                close(connection);
            }
        }
        return results;
    }

    /**
     * Permite actualizar algún registro de la base de datos.
     * @param query Consulta a realizar.
    */
    public void executeUpdate(String sql)
    {
        Connection connection = connect();
        
        if (connection != null)
        {
            try
            {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
            
            catch (SQLException e)
            {
                System.out.println("Error al ejecutar actualización: " + e.getMessage());
            }
            
            finally
            {
                close(connection);
            }
        }
    }
}

