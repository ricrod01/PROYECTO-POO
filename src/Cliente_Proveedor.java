/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para simular los clientes, proveedores de la empresa o la empresa.
 * fecha_creación = 10/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.util.List;
import java.util.Map;

class Cliente_Proveedor
{
    /**
     * @param id Identificador del cliente/proveedor/la empresa.
     * @param nombre Nombre del cliente/proveedor/la empresa.
     * @param tipo Tipo de empresa Empresa/Cliente/Proveedor.
    */

    private int id;
    private String nombre;
    private String tipo;

    /**
     * @param nombre Nombre del cliente/proveedor/la empresa.
     * @param tipo Tipo de empresa Empresa/Cliente/Proveedor.
    */

    public Cliente_Proveedor(String nombre, String tipo)
    {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    /**
     * @param id Identificador del cliente/proveedor/la empresa.
    */

    public Cliente_Proveedor(int id)
    {
        this.id = id;
        if(this.ExisteId())
        {
            DatabaseConnector db = new DatabaseConnector();
            String sql = "SELECT * FROM CLIENTES_PROVEEDORES WHERE ID_C_P = " + this.id;
            List<Map<String, Object>> clientes_proveedores = db.executeQuery(sql);
            Map<String, Object> fila = clientes_proveedores.get(0);
            this.nombre = (String) fila.get("NOMBRE");
            this.tipo = (String) fila.get("TIPO");
        }
    }

    /**
     * @param id Identificador del cliente/proveedor/la empresa.
    */

    public void SetId(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre del cliente/proveedor/la empresa.
    */

    public void SetNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @param tipo Tipo de empresa Empresa/Cliente/Proveedor.
    */

    public void SetTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /**
     * @return Devuelve el identificador del cliente/proveedor/la empresa.
    */

    public int GetId()
    {
        return this.id;
    }

    /**
     * @return Devuelve el nombre del cliente/proveedor/la empresa.
    */

    public String GetNombre()
    {
        return this.nombre;
    }

    /**
     * @return Devuelve el tipo de empresa Empresa/Cliente/Proveedor.
    */

    public String GetTipo()
    {
        return this.tipo;
    }

    /**
     * @return Devuelve true si el id existe y false en caso contrario.
    */

    public boolean ExisteId()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT NOMBRE FROM CLIENTES_PROVEEDORES WHERE ID_C_P = " + this.id;
        List<Map<String, Object>> clientes_proveedores = db.executeQuery(sql);
        return !clientes_proveedores.isEmpty();
    }


    /**
     * Permite insertar una fila en la tabla CLIENTES_PROVEEDORES.
    */

    public void InsertarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "INSERT INTO CLIENTES_PROVEEDORES (NOMBRE, TIPO) VALUES ('" + this.nombre + "', '" + this.tipo + "')";
        db.executeUpdate(query);
    }

    /**
     * Permite modificar una fila en la tabla CLIENTES_PROVEEDORES.
    */

    public void ModificarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "UPDATE CLIENTES_PROVEEDORES SET NOMBRE = '" + this.nombre + "', TIPO = '" + this.tipo + "' WHERE ID_C_P = " + this.id;
        db.executeUpdate(query);
    }
}