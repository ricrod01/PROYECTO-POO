/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para simular las ubicaciones dentro de la empresa.
 * fecha_creación = 10/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.util.List;
import java.util.Map;

class Ubicacion
{
    /**
     * @param id Identificador de la ubicación.
     * @param nombre Nombre de la ubicación.
    */
   
    private int id;
    private String nombre;

    /**
     * @param id Identificador de la ubicación.
     * @param nombre Nombre de la ubicación.
    */

    public Ubicacion(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @param id Identificador de la ubicación.
    */

    public Ubicacion(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre de la ubicación.
    */

    public Ubicacion(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @param id Identificador de la ubicación.
    */

    public void SetId(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre de la ubicación.
    */

    public void SetNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    /**
     * @return Identificador de la ubicación.
    */

    public int GetId()
    {
        return this.id;
    }

    /**
     * @return Nombre de la ubicación.
    */

    public String GetNombre()
    {
        return this.nombre;
    }

    /**
     * @return Devuelve true si el id existe y false en caso contrario.
    */

    public boolean ExisteId()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT NOMBRE FROM UBICACIONES WHERE ID_UBICACION = " + this.id;
        List<Map<String, Object>> ubicaciones = db.executeQuery(sql);
        return !ubicaciones.isEmpty();
    }

    /**
     * Permite insertar una fila en la tabla UBICACIONES.
    */

    public void InsertarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "INSERT INTO UBICACIONES (NOMBRE) VALUES ('" + this.nombre + "')";
        db.executeUpdate(query);
    }

    /**
     * Permite modificar una fila en la tabla UBICACIONES.
    */

    public void ModificarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "UPDATE UBICACIONES SET NOMBRE = '" + this.nombre + "' WHERE ID_UBICACION = " + this.id;
        db.executeUpdate(query);
    }

    /**
     * @return Devuelve la información importante de la ubicación.
    */

    @Override
    public String toString()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT NOMBRE FROM UBICACIONES WHERE ID_UBICACION = " + this.id;
        List<Map<String, Object>> ubicaciones = db.executeQuery(sql);
        Map<String, Object> fila = ubicaciones.get(0);
        String nombre_ubicacion = (String) fila.get("NOMBRE");
        return nombre_ubicacion;
    }
}