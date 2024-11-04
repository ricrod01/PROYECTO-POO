/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase para simular las distintas getiones de la empresa.
 * fecha_creación = 1/10/2024
 * fecha_modificación = 4/11/2024
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Gestion
{   
    /**
     * @param id Identificador de la gestión.
     * @param tipo Tipo de la gestión.
     * @param productos Listado de productos gestionados.
     * @param cantidades Cantidades que se gestionarán de cada producto.
     * @param ic_empleado Identificador del empleado que realiza la transacción.
     * @param id_c_p Identificador del cliente/proveedor/la misma empresa.
     * @param descripcion Descripción de la gestión.
    */

    private int id;
    private String tipo;
    private LocalDateTime fecha;
    private ArrayList<Integer> identificadores;
    private ArrayList<Float> cantidades;
    private int id_empleado;
    private int id_c_p;
    private String descripcion;

    /**
     * @param id Identificador de la gestión.
     * @param tipo Tipo de la gestión.
     * @param productos Listado de productos gestionados.
     * @param cantidades Cantidades que se gestionarán de cada producto.
     * @param empleado Empleado que realiza la transacción.
     * @param id_c_p Identificador del cliente/proveedor/la misma empresa.
     * @param descripcion Descripción de la gestión.
    */

    public Gestion(int id, String tipo, ArrayList<Producto> productos, ArrayList<Float> cantidades, Empleado empleado, int id_c_p, String descripcion)
    {
        this.id = id;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
        this.cantidades = cantidades;
        this.id_empleado = empleado.GetId();
        this.id_c_p = id_c_p;
        this.descripcion = descripcion;
        ArrayList<Integer> id_producto = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++)
        {
            id_producto.add(productos.get(i).GetId());
            Producto producto = new Producto(productos.get(i).GetId());
            if (tipo.equals("Compras") || tipo.equals("Entradas"))
            {
                producto.AumentarExistencias(cantidades.get(i));
            }
            if (tipo.equals("Ventas") || tipo.equals("Salidas"))
            {
                if(producto.CorroborarSalida(cantidades.get(i)))
                {
                    producto.RebajarSalida(cantidades.get(i));
                }
                else
                {
                    this.cantidades.set(i, (float) 0);
                }
            }
            producto.ModificarFila();
        }
        this.identificadores = id_producto;
    }

    /**
     * Permite insertar filas en la tabla TRANSACCIONES.
    */

    public void InsertarFilaTransaccion()
    {
        DatabaseConnector db = new DatabaseConnector();

        for (int i = 0; i < this.identificadores.size(); i++)
        {   
            String query = "INSERT INTO TRANSACCIONES (ID_GESTION, ID_PRODUCTO, CANTIDAD) VALUES (" + this.id + ", " + this.identificadores.get(i) + ", " + this.cantidades.get(i) + ")";
            db.executeUpdate(query);
        }
    }

    /**
     * Permite insertar filas en la tabla GESTIONES.
    */

    public void InsertarFilaGestion()
    {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DatabaseConnector db = new DatabaseConnector();
        String fecha_formato = this.fecha.format(formato);

        String query = "INSERT INTO GESTIONES (FECHA, TIPO, ID_EMPLEADO, ID_C_P, DESCRIPCION) VALUES ('" + fecha_formato + "', '" + this.tipo +
         "', " + this.id_empleado + ", " + this.id_c_p + ", '" + this.descripcion + "')";
        db.executeUpdate(query);
    }

    /**
     * @return Devuelve el identificador, nombre y cantidad de cada producto que se gestionó.
    */

    public String ToString(ArrayList<Producto> productos)
    {
        String texto = "";
        for (int i = 0; i < this.identificadores.size(); i++)
        {
            texto = texto + "Id: " + productos.get(i).GetId() +" | Producto: " + productos.get(i).GetNombre() + " | Cantidad: " + this.cantidades.get(i) + " " + productos.get(i).GetDimension()+"\n";
        }
        return texto;
    }
}