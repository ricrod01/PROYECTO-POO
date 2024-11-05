/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para simular los empleados de la tienda.
 * fecha_creación = 10/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.util.List;
import java.util.Map;

class Empleado
{
    /**
     * @param id Identificador del empleado.
     * @param nombre Nombre del empleado.
     * @param tipo Tipo de empleado.
     * @param contrasenia Contraseña del empleado.
     * @param despedido Booleano que indica si el empleado ha sido despedido o no.
    */

    private int id;
    private String nombre;
    private String tipo;
    private String contrasenia;
    private boolean despedido;

    /**
     * @param nombre Nombre del empleado.
     * @param tipo Tipo de empleado.
     * @param contrasenia Contraseña del empleado.
    */

    public Empleado(String nombre, String tipo, String contrasenia)
    {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contrasenia = contrasenia;
        this.despedido = false;
    }

    /**
     * @param id Identificador del empleado.
    */

    public Empleado(int id)
    {
        this.id = id;
        if(this.ExisteId())
        {
            DatabaseConnector db = new DatabaseConnector();
            String sql = "SELECT * FROM EMPLEADOS WHERE ID_EMPLEADO = " + this.id;
            List<Map<String, Object>> empleados = db.executeQuery(sql);
            Map<String, Object> fila = empleados.get(0);
            this.nombre = (String) fila.get("NOMBRE");
            this.tipo = (String) fila.get("TIPO");
            this.contrasenia = (String) fila.get("CONTRASENIA");
            this.despedido = false;
        }
    }

    /**
     * @param id Identificador del empleado.
    */

    public void SetId(int id)
    {
        this.id = id;
    }

    /**
     * @param nombre Nombre del empleado.
    */

    public void SetNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @param tipo Tipo de empleado.
    */

    public void SetTipo(String tipo)
    {
        this.tipo = tipo;
    }

    /**
     * @param contrasenia Contraseña del empleado.
    */

    public void SetContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    /**
     * @param despedido Booleano que indica si el empleado ha sido despedido o no.
    */

    public void SetDespedido(boolean despedido)
    {
        this.despedido = despedido;
    }

    /**
     * @return Devuelve el identificador del empleado.
    */

    public int GetId()
    {
        return this.id;
    }

    /**
     * @return Devuelve el nombre del empleado.
    */

    public String GetNombre()
    {
        return this.nombre;
    }

    /**
     * @return Devuelve el tipo de empleado.
    */

    public String GetTipo()
    {
        return this.tipo;
    }

    /**
     * @return Devuelve un booleano que indica si el empleado ha sido despedido o no.
    */

    public boolean GetDespedido()
    {
        return this.despedido;
    }

    /**
     * @param contrasenia2 Contraseña ingresada, la cual se comparará con la registrada.
     * @return Devuelve un booleano que informa si la contraseña ingresada es igual a la base de datos o no.
    */

    public boolean CorroborarContrasenia(String contrasenia2)
    {
        return this.contrasenia.equals(contrasenia2);
    }

    /**
     * @return Devuelve true si el id existe y false en caso contrario.
    */

    public boolean ExisteId()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT NOMBRE FROM EMPLEADOS WHERE DESPEDIDO = false AND ID_EMPLEADO = " + this.id;
        List<Map<String, Object>> empleados = db.executeQuery(sql);
        return !empleados.isEmpty();
    }

    /**
     * Permite insertar una fila en la tabla EMPLEADOS.
    */

    public void InsertarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "INSERT INTO EMPLEADOS (NOMBRE, TIPO, CONTRASENIA, DESPEDIDO) VALUES ('" + 
        this.nombre + "', '" + this.tipo + "', '" + this.contrasenia + "', " + this.despedido + ")";
        db.executeUpdate(query);
    }

    /**
     * Permite modificar una fila en la tabla EMPLEADOS.
    */

    public void ModificarFila()
    {
        DatabaseConnector db = new DatabaseConnector();
        String query = "UPDATE EMPLEADOS SET NOMBRE = '" + this.nombre + "', TIPO = '" + this.tipo + "', CONTRASENIA = '" + this.contrasenia + "', DESPEDIDO = " + this.despedido + " WHERE ID_EMPLEADO = " + this.id;
        db.executeUpdate(query);
    }

    /**
     * @return Devuelve la información detallada del empleado.
    */

    public String Informacion()
    {
        DatabaseConnector db = new DatabaseConnector();
        String sql = "SELECT * FROM EMPLEADOS WHERE DESPEDIDO = false AND ID_EMPLEADO = " + this.id;
        List<Map<String, Object>> empleados = db.executeQuery(sql);
        Map<String, Object> fila = empleados.get(0);
        Integer id_empleado = (Integer) fila.get("ID_EMPLEADO");
        String nombre_empleado = (String) fila.get("NOMBRE");
        String tipo_empleado = (String) fila.get("TIPO");
        String texto = "Id: " + id_empleado + "\nNombre: " + nombre_empleado + "\nTipo: " + tipo_empleado;
        return texto;
    }
}