/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para simular las categorías de productos que maneja la empresa.
 * fecha_creación = 10/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.util.List;
import java.util.Map;

class Categoria
{
    /**
     * @param id Identificador de la categoría.
     * @param nombre Nombre de la categoría.
    */
   
    private int id;
    private String nombre;

    /**
     * @param id Identificador de la categoría.
     * @param nombre Nombre de la categoría.
    */

    public Categoria(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @param id Identificador de la categoría.
    */

    public Categoria(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre de la categoría.
    */

    public Categoria(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @param id Identificador de la categoría.
    */

    public void SetId(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre de la categoría.
    */

    public void SetNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    /**
     * @return Identificador de la categoría.
    */

    public int GetId()
    {
        return this.id;
    }

    /**
     * @return Nombre de la categoría.
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
        String sql = "SELECT NOMBRE FROM CATEGORIAS WHERE ID_CATEGORIA = " + this.id;
        List<Map<String, Object>> categorias = db.executeQuery(sql);
        return !categorias.isEmpty();
    }

    /**
     * Permite insertar una fila en la tabla CATEGORIAS.
    */

    public void InsertarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "INSERT INTO CATEGORIAS (NOMBRE) VALUES ('" + this.nombre + "')";
        db.executeUpdate(query);
    }

    /**
     * Permite insertar una fila en la tabla CATEGORIAS.
    */

    public void ModificarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "UPDATE CATEGORIAS SET NOMBRE = '" + this.nombre + "' WHERE ID_CATEGORIA = " + this.id;
        db.executeUpdate(query);
    }

    /**
     * @return Devuelve la información importante de la categoría.
    */

    @Override
    public String toString()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT NOMBRE FROM CATEGORIAS WHERE ID_CATEGORIA = " + this.id;
        List<Map<String, Object>> categorias = db.executeQuery(sql);
        Map<String, Object> fila = categorias.get(0);
        String nombre_categoria = (String) fila.get("NOMBRE");
        return nombre_categoria;
    }
}