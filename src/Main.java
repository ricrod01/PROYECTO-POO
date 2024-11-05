/**
 * @author Ricardo Rodríguez
 * @version 2
 * Clase Main del proyecto. Aquí se realiza la interfaz con la que interactuará el usuario.
 * fecha_creación = 10/10/2024
 * fecha_modificación = 4/11/2024
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        boolean flag_empleado = true;
        boolean flag_administrador = false;
        boolean flag_vendedor = false;
        boolean flag_comprador = false;
        boolean flag_bodeguero = false;
        DatabaseConnector db = new DatabaseConnector();
        Empleado empleado = null;

        while(flag_empleado)
        {
            //Parte de validación del empleado, autenticar un una contraseña y pasar al siguiente en caso de que sea correcta
            flag_empleado = false;
            empleado = new Empleado(1);
            flag = true;
        }


        while (flag) 
        {
            System.out.println("\n¿Qué desea hacer?");
            System.out.println("1) Crear/modificar una ubicación.");
            System.err.println("2) Crear/modificar una categoría.");
            System.out.println("3) Crear/modificar/eliminar un producto.");
            System.out.println("4) Crear/modificar/eliminar un empleado.");
            System.out.println("5) Crear/modificar un cliente/proveedor.");
            System.out.println("6) Gestionar una transacción.");
            System.out.println("7) Ver información de un producto/ubicación/categoría/empleado/cliente/proveedor.");
            System.out.println("8) Salir.");
            String menu = sc.nextLine();

            if(menu.equals("1"))
            {
                System.out.println("\n¿Qué desea hacer?");
                System.out.println("1) Agregar una ubicación.");
                System.out.println("2) Modificar una ubicación.");
                String opcion = sc.nextLine();
                
                if (opcion.equals("1"))
                {
                    System.out.println("\nIngrese el nombre de la ubicación.");
                    String nombre = sc.nextLine();
                    Ubicacion ubicacion = new Ubicacion(nombre);
                    ubicacion.InsertarFila();
                }
                else if (opcion.equals("2"))
                {
                    Ubicacion ubicacion = new Ubicacion("");
                    try
                    {
                        System.out.println("\nIngrese el identificador de la ubicación.");
                        String id_str = sc.nextLine();
                        int id = Integer.parseInt(id_str);
                        if(id >= 1)
                        {
                            ubicacion.SetId(id);
                            if(ubicacion.ExisteId())
                            {
                                System.out.println("\nLa ubicación que eligió es: ");
                                System.out.print(ubicacion);
                                
                                System.out.println("\nIngrese el nuevo nombre de la ubicación.");
                                String nombre = sc.nextLine();
                                ubicacion.SetNombre(nombre);
                                ubicacion.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else
                            {
                                System.out.println("\nIngresó un id que no existe.");
                            }
                        }
                        else
                        {
                            System.out.println("\nIngresó un número inválido.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nIngresó algo que no es un número.");
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if(menu.equals("2"))
            {
                System.out.println("\n¿Qué desea hacer?");
                System.out.println("1) Agregar una categoría.");
                System.out.println("2) Modificar una categoría.");
                String opcion = sc.nextLine();

                if (opcion.equals("1"))
                {
                    System.out.println("\nIngrese el nombre de la categoría.");
                    String nombre = sc.nextLine();
                    Categoria categoria = new Categoria(nombre);
                    categoria.InsertarFila();
                }
                else if (opcion.equals("2"))
                {
                    Categoria categoria = new Categoria("");
                    try
                    {
                        System.out.println("\nIngrese el identificador de la categoría.");
                        String id_str = sc.nextLine();
                        int id = Integer.parseInt(id_str);
                        if(id >= 1)
                        {
                            categoria.SetId(id);
                            if(categoria.ExisteId())
                            {
                                System.out.println("\nLa categoría que eligió es: ");
                                System.out.print(categoria);
                                
                                System.out.println("\nIngrese el nuevo nombre de la categoría.");
                                String nombre = sc.nextLine();
                                categoria.SetNombre(nombre);
                                categoria.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else
                            {
                                System.out.println("\nIngresó un id que no existe.");
                            }
                        }
                        else
                        {
                            System.out.println("\nIngresó un número inválido.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nIngresó algo que no es un número.");
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if(menu.equals("3"))
            {
                System.out.println("\n¿Qué desea hacer?");
                System.out.println("1) Crear un producto.");
                System.out.println("2) Modificar un producto.");
                System.out.println("3) Eliminar un producto.");
                String opcion = sc.nextLine();

                if(opcion.equals("1"))
                {
                    System.out.println("\nIngrese el nombre del producto.");
                    String nombre = sc.nextLine();
                    boolean flag_ingreso = true;
                    String tipo = "";
                    
                    while (flag_ingreso)
                    { 
                        System.out.println("\nIngrese el tipo del producto.");
                        System.out.println("1) Materia prima.");
                        System.out.println("2) Producto terminado (Aquellos productos que fabrica la empresa que están listos para las ventas).");
                        System.out.println("3) Intermedio.");
                        System.out.println("4) Producto reventa (Aquellos productos que se compran para vender y no los fabrica la empresa).");
                        String tipo_opcion = sc.nextLine();

                        switch (tipo_opcion)
                        {
                            case "1" -> tipo = "MP";
                            case "2" -> tipo = "PT";
                            case "3" -> tipo = "IN";
                            case "4" -> tipo = "PR";
                        }
                        if(tipo.equals(""))
                        {
                            System.out.println("\nHa ingresado una opción incorrecta.");
                        }
                        else
                        {
                            flag_ingreso = false;
                        }
                    }

                    System.out.println("\nIngrese las dimensiones de medición del producto. ('Unidad' si requiere que se maneje en enteros).");
                    String dimension = sc.nextLine();

                    flag_ingreso = true;
                    Categoria categoria = new Categoria("");
                    while(flag_ingreso)
                    {
                        try
                        {
                            System.out.println("\nIngrese el identificador de la categoría.");
                            String id_str = sc.nextLine();
                            int id = Integer.parseInt(id_str);
                            if(id >= 1)
                            {
                                categoria.SetId(id);
                                if(categoria.ExisteId())
                                {
                                    System.out.println("\nLa categoría que eligió es: ");
                                    System.out.print(categoria);
                                    flag_ingreso = false;
                                }
                                else
                                {
                                    System.out.println("\nIngresó un id que no existe.");
                                }
                            }
                            else
                            {
                                System.out.println("\nIngresó un número inválido.");
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nIngresó algo que no es un número.");
                        }
                    }
                    flag_ingreso = true;
                    Ubicacion ubicacion = new Ubicacion("");
                    while(flag_ingreso)
                    {
                        try
                        {
                            System.out.println("\nIngrese el identificador de la ubicación.");
                            String id_str = sc.nextLine();
                            int id = Integer.parseInt(id_str);
                            if(id >= 1)
                            {
                                ubicacion.SetId(id);
                                if(ubicacion.ExisteId())
                                {
                                    System.out.println("\nLa ubicación que eligió es: ");
                                    System.out.print(ubicacion);
                                    flag_ingreso = false;
                                }
                                else
                                {
                                    System.out.println("\nIngresó un id que no existe.");
                                }
                            }
                            else
                            {
                                System.out.println("\nIngresó un número inválido.");
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nIngresó algo que no es un número.");
                        }
                    }
                    Producto producto = new Producto(nombre, tipo, (float) 0, dimension, ubicacion.GetId(), categoria.GetId());
                    producto.InsertarFila();
                }
                else if(opcion.equals("2"))
                {
                    boolean flag_ingreso = true;
                    Producto producto = new Producto(0);
                    try
                    {
                        System.out.println("\nIngrese el id del producto que desea modificar.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        producto = new Producto(id);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nHa ingresado datos incorrectos.");
                    }
                    if(producto.GetId() != 0 && producto.ExisteId())
                    {
                        System.out.println("\nEl producto que eligió es: " + producto.GetNombre());
                        while(flag_ingreso)
                        {
                            System.out.println("\nQué desea modificar?");
                            System.out.println("1) Nombre.");
                            System.out.println("2) Tipo.");
                            System.out.println("3) Dimensión.");
                            System.out.println("4) Id categoría.");
                            System.out.println("5) Id ubicación.");
                            System.out.println("cualquier otra tecla) Salir.");
                            String opcion2 = sc.nextLine();
                            if(opcion2.equals("1"))
                            {
                                System.out.println("\nIngrese el nuevo nombre.");
                                String nombre = sc.nextLine();
                                producto.SetNombre(nombre);
                                producto.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else if(opcion2.equals("2"))
                            {
                                String tipo = "";
                                System.out.println("\nIngrese el nuevo tipo de producto.");
                                System.out.println("1) Materia prima.");
                                System.out.println("2) Producto terminado (Aquellos productos que fabrica la empresa que están listos para las ventas).");
                                System.out.println("3) Intermedio.");
                                System.out.println("4) Producto reventa (Aquellos productos que se compran para vender y no los fabrica la empresa).");
                                String tipo_opcion = sc.nextLine();
                                switch (tipo_opcion)
                                {
                                    case "1" -> tipo = "MP";
                                    case "2" -> tipo = "PT";
                                    case "3" -> tipo = "IN";
                                    case "4" -> tipo = "PR";
                                }
                                if(tipo.equals(""))
                                {
                                    System.out.println("\nHa ingresado una opción incorrecta.");
                                }
                                else
                                {
                                    producto.SetTipo(tipo);
                                    producto.ModificarFila();
                                    System.out.println("\nSe ha modificado.");
                                }
                            }
                            else if(opcion2.equals("3"))
                            {
                                System.out.println("\nIngrese las nuevas dimensiones del producto.");
                                String dimension = sc.nextLine();
                                producto.SetDimension(dimension);
                                producto.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else if(opcion2.equals("4"))
                            {
                                Categoria categoria = new Categoria(0);
                                try
                                {
                                    System.out.println("\nIngrese el identificador de la categoría.");
                                    String id_str = sc.nextLine();
                                    int id = Integer.parseInt(id_str);
                                    if(id >= 1)
                                    {
                                        categoria.SetId(id);
                                        if(categoria.ExisteId())
                                        {
                                            System.out.println("\nLa categoría que eligió es: ");
                                            System.out.print(categoria);
                                            producto.SetCategoria(id);
                                            producto.ModificarFila();
                                            System.out.println("\nSe ha modificado.");
                                        }
                                        else
                                        {
                                            System.out.println("\nIngresó un id que no existe.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("\nIngresó un número inválido.");
                                    }
                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("\nIngresó algo que no es un número.");
                                }
                            }
                            else if(opcion2.equals("5"))
                            {
                                Ubicacion ubicacion = new Ubicacion(0);
                                try
                                {
                                    System.out.println("\nIngrese el identificador de la ubicación.");
                                    String id_str = sc.nextLine();
                                    int id = Integer.parseInt(id_str);
                                    if(id >= 1)
                                    {
                                        ubicacion.SetId(id);
                                        if(ubicacion.ExisteId())
                                        {
                                            System.out.println("\nLa ubicación que eligió es: ");
                                            System.out.print(ubicacion);
                                            producto.SetUbicacion(id);
                                            producto.ModificarFila();
                                            System.out.println("\nSe ha modificado.");
                                        }
                                        else
                                        {
                                            System.out.println("\nIngresó un id que no existe.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("\nIngresó un número inválido.");
                                    }
                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("\nIngresó algo que no es un número.");
                                }
                            }
                            else
                            {
                                flag_ingreso = false;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("\nHa ingresado un id de producto inexistente.");
                    }
                }
                else if(opcion.equals("3"))
                {
                    boolean flag_ingreso = true;
                    Producto producto;
                    while(flag_ingreso)
                    {
                        try
                        {
                            System.out.println("\nIngrese el id del producto que desea descontinuar.");
                            int id = sc.nextInt();
                            sc.nextLine();
                            producto = new Producto(id);
                            if(producto.ExisteId())
                            {
                                System.out.println("\nEl producto que eligió es: " + producto.GetNombre());
                                System.out.println("\n¿Desea descontinuarlo?");
                                System.out.println("1) Si.");
                                System.out.println("2) No.");
                                String opcion2 = sc.nextLine();
                                if(opcion2.equals("1"))
                                {
                                    producto.SetDescontinuado(true);
                                    producto.ModificarFila();
                                    System.out.println("\nSe ha descontinuado el producto.");
                                    flag_ingreso = false;
                                }
                                else if(opcion2.equals("2"))
                                {
                                    System.out.println("\n¿Desea salir de este menú.");
                                    System.out.println("1) Si.");
                                    System.out.println("Cualquier otra tecla) No.");
                                    opcion2 = sc.nextLine();
                                    if(opcion2.equals("1"))
                                    {
                                        flag_ingreso = false;
                                    } 
                                }
                                else
                                {
                                    System.out.println("\nIngresó una opción incorrecta.");
                                }
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nIngresó algo que no es un número.");
                        }
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if(menu.equals("4"))
            {
                System.out.println("\n¿Qué desea hacer?");
                System.out.println("1) Agregar un empleado.");
                System.out.println("2) Modificar un empleado.");
                System.out.println("3) Eliminar un empleado.");
                String opcion = sc.nextLine();

                if(opcion.equals("1"))
                {
                    System.out.println("\nIngrese el nombre del empleado.");
                    String nombre = sc.nextLine();
                    boolean flag_ingreso = true;
                    String tipo = "";
                    while (flag_ingreso)
                    { 
                        System.out.println("\nIngrese el tipo de empleado.");
                        System.out.println("1) Administrador");
                        System.out.println("2) Vendedor");
                        System.out.println("3) Comprador");
                        System.out.println("4) Bodeguero");
                        String tipo_opcion = sc.nextLine();
                        switch (tipo_opcion)
                        {
                            case "1" -> tipo = "Administrador";
                            case "2" -> tipo = "Vendedor";
                            case "3" -> tipo = "Comprador";
                            case "4" -> tipo = "Bodeguero";
                        }
                        if (tipo.equals(""))
                        {
                            System.out.println("\nHa ingresado una opción incorrecta.");
                        }
                        else
                        {
                            flag_ingreso = false;
                        }
                    }
                    flag_ingreso = true;
                    String contrasenia = null;
                    while (flag_ingreso)
                    {
                        System.out.println("\nIngrese la contraseña del empleado.");
                        contrasenia = sc.nextLine();
                        System.out.println("\nIngrese de nuevo la contraseña.");
                        String contrasenia2 = sc.nextLine();
                        if(contrasenia.equals(contrasenia2))
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("Las contraseñas no coinciden.");
                        }
                    }
                    Empleado empleado_nuevo = new Empleado(nombre, tipo, contrasenia);
                    empleado_nuevo.InsertarFila();
                }
                else if(opcion.equals("2"))
                {
                    boolean flag_ingreso = true;
                    Empleado empleado2 = new Empleado(0);
                    try
                    {
                        System.out.println("\nIngrese el id del empleado que desea modificar.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        empleado2 = new Empleado(id);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nHa ingresado datos incorrectos.");
                    }
                    if(empleado2.GetId() != 0 && empleado2.ExisteId())
                    {
                        System.out.println("\nEl empleado que eligió es: " + empleado2.GetNombre());
                        while (flag_ingreso)
                        { 
                            System.out.println("\n¿Qué desea modificiar?");
                            System.out.println("1) Nombre del empleado.");
                            System.out.println("2) Tipo de empleado.");
                            System.out.println("3) Contraseña del empleado.");
                            System.out.println("Cualquier otra tecla) Salir.");
                            String opcion2 = sc.nextLine();
                            if(opcion2.equals("1"))
                            {
                                System.out.println("\nIngrese el nuevo nombre.");
                                String nombre = sc.nextLine();
                                empleado2.SetNombre(nombre);
                                empleado2.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else if(opcion2.equals("2"))
                            {
                                String tipo = "";
                                System.out.println("\nIngrese el nuevo tipo de empleado.");
                                System.out.println("1) Administrador");
                                System.out.println("2) Vendedor");
                                System.out.println("3) Comprador");
                                System.out.println("4) Bodeguero");
                                String tipo_opcion = sc.nextLine();
                                switch (tipo_opcion)
                                {
                                    case "1" -> tipo = "Administrador";
                                    case "2" -> tipo = "Vendedor";
                                    case "3" -> tipo = "Comprador";
                                    case "4" -> tipo = "Bodeguero";
                                }
                                if (tipo.equals(""))
                                {
                                    System.out.println("\nHa ingresado una opción incorrecta.");
                                }
                                else
                                {
                                    empleado2.SetTipo(tipo);
                                    empleado2.ModificarFila();
                                    System.out.println("\nSe ha modificado.");
                                }
                            }
                            else if(opcion2.equals("3"))
                            {
                                boolean flag_ingreso2 = true;
                                while(flag_ingreso2)
                                {
                                    System.out.println("\nIngrese la nueva contraseña.");
                                    String contrasenia1 = sc.nextLine();
                                    System.out.println("\nIngrese la nueva contraseña de nuevo.");
                                    String contrasenia2 = sc.nextLine();
                                    if(contrasenia1.equals(contrasenia2))
                                    {
                                        empleado2.SetContrasenia(contrasenia1);
                                        empleado2.ModificarFila();
                                        System.out.println("\nSe ha modificado.");
                                        flag_ingreso2 = false;
                                    }
                                    else
                                    {
                                        System.out.println("\nLas contraseñas no coinciden.");
                                    }
                                }
                            }
                            else
                            {
                                flag_ingreso = false;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("El empleado elegido no existe o ya fue despedido.");
                    }
                }
                else if(opcion.equals("3"))
                {
                    boolean flag_ingreso = true;
                    Empleado empleado2 = new Empleado(0);
                    while(flag_ingreso)
                    {
                        try
                        {
                            System.out.println("\nIngrese el id del empleado que desea eliminar.");
                            int id = sc.nextInt();
                            sc.nextLine();
                            empleado2 = new Empleado(id);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nHa ingresado datos incorrectos.");
                        }
                        if(empleado2.GetId() != 0 && empleado2.ExisteId())
                        {
                            System.out.println("\nEl empleado que eligió es: " + empleado2.GetNombre());
                            System.out.println("\n¿Desea eliminarlo.");
                            System.out.println("1) Si.");
                            System.out.println("2) No.");
                            String opcion2 = sc.nextLine();
                            if(opcion2.equals("1"))
                            {
                                empleado2.SetDespedido(true);
                                empleado2.ModificarFila();
                                System.out.println("\nSe ha eliminado.");
                                flag_ingreso = false;
                            }
                            else if(opcion2.equals("2"))
                            {
                                System.out.println("\n¿Desea salir de este menú.");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                opcion2 = sc.nextLine();
                                if(opcion2.equals("1"))
                                {
                                    flag_ingreso = false;
                                } 
                            }
                            else
                            {
                                System.out.println("\nIngresó una opción incorrecta.");
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if (menu.equals("5"))
            {
                System.out.println("\n¿Qué desea hacer?");
                System.out.println("1) Agregar un cliente/proveedor.");
                System.out.println("2) Modificar un cliente/proveedor.");
                String opcion = sc.nextLine();

                if(opcion.equals("1"))
                {
                    System.out.println("\nIngrese el nombre del cliente/proveedor.");
                    String nombre = sc.nextLine();
                    boolean flag_ingreso = true;
                    String tipo = "";
                    while (flag_ingreso)
                    { 
                        System.out.println("\nIngrese el tipo de empleado.");
                        System.out.println("1) Cliente.");
                        System.out.println("2) Proveedor.");
                        String tipo_opcion = sc.nextLine();
                        switch (tipo_opcion)
                        {
                            case "1" -> tipo = "Cliente";
                            case "2" -> tipo = "Proveedor";
                        }
                        if (tipo.equals(""))
                        {
                            System.out.println("\nHa ingresado una opción incorrecta.");
                        }
                        else
                        {
                            flag_ingreso = false;
                        }
                    }
                    Cliente_Proveedor cliente_proveedor = new Cliente_Proveedor(nombre, tipo);
                    cliente_proveedor.InsertarFila();
                }
                else if(opcion.equals("2"))
                {
                    boolean flag_ingreso = true;
                    Cliente_Proveedor empresa = new Cliente_Proveedor(0);
                    try
                    {
                        System.out.println("\nIngrese el id del cliente/proveedor que desea modificar.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        empresa = new Cliente_Proveedor(id);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nHa ingresado datos incorrectos.");
                    }
                    if(empresa.GetId() > 1 && empresa.ExisteId())
                    {
                        System.out.println("\nEl cliente/proveedor que eligió es: " + empresa.GetNombre());
                        while (flag_ingreso)
                        { 
                            System.out.println("\n¿Qué desea modificiar?");
                            System.out.println("1) Nombre del cliente/proveedor.");
                            System.out.println("2) Tipo de empresa.");
                            System.out.println("Cualquier otra tecla) Salir.");
                            String opcion2 = sc.nextLine();
                            if(opcion2.equals("1"))
                            {
                                System.out.println("\nIngrese el nuevo nombre.");
                                String nombre = sc.nextLine();
                                empresa.SetNombre(nombre);
                                empresa.ModificarFila();
                                System.out.println("\nSe ha modificado.");
                            }
                            else if(opcion2.equals("2"))
                            {
                                String tipo = "";
                                System.out.println("\nIngrese el nuevo tipo de empleado.");
                                System.out.println("1) Cliente");
                                System.out.println("2) Proveedor");
                                String tipo_opcion = sc.nextLine();
                                switch (tipo_opcion)
                                {
                                    case "1" -> tipo = "Cliente";
                                    case "2" -> tipo = "Proveedor";
                                }
                                if (tipo.equals(""))
                                {
                                    System.out.println("\nHa ingresado una opción incorrecta.");
                                }
                                else
                                {
                                    empresa.SetTipo(tipo);
                                    empresa.ModificarFila();
                                    System.out.println("\nSe ha modificado.");
                                }
                            }
                            else
                            {
                                flag_ingreso = false;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("La empresa elegida no existe.");
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if(menu.equals("6"))
            {
                System.out.println("\n¿Qué tipo de transacción desea hacer?");
                System.out.println("1) Compra.");
                System.out.println("2) Venta.");
                System.out.println("3) Aumento de existencias.");
                System.out.println("4) Disminución de existencias.");
                String opcion = sc.nextLine();

                boolean flag_ingreso = true;
                ArrayList<Producto> productos = new ArrayList<>();
                ArrayList<Float> cantidades = new ArrayList<>();

                if (opcion.equals("1"))
                {
                    Cliente_Proveedor proveedor = null;
                    int id_c_p = 0;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Cuál es id del proveedor?");
                        String id_proveedor_str = sc.nextLine();
                        try
                        {
                            int id_proveedor = Integer.parseInt(id_proveedor_str);
                            proveedor = new Cliente_Proveedor(id_proveedor);
                            if(proveedor.ExisteId() && proveedor.GetTipo().equals("Proveedor"))
                            {
                                System.out.println("\nEl proveedor que eligió: " + proveedor.GetNombre());
                                System.out.println("¿Es correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    id_c_p = proveedor.GetId();
                                    flag_ingreso = false;
                                }
                            }
                            else
                            {
                                System.out.println("\nIngresó un identificador que no existe o no es un proveedor.");
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nIngresó algo que no es un número.");
                        }
                    }
                    flag_ingreso = true;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Desea ingresar un producto al listado?");
                        System.out.println("1) Si.");
                        System.out.println("2) No.");
                        String opcion2 = sc.nextLine();
                        if(opcion2.equals("1")) 
                        {  
                            System.out.println("\nIngrese el identificador del producto.");
                            String identificador = sc.nextLine();
                            String query_productos = "SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = " + identificador + " AND TIPO IN ('MP', 'PR') AND DESCONTINUADO = false";
                            try
                            {
                                List<Map<String, Object>> prod = db.executeQuery(query_productos);
                                Map<String, Object> fila = prod.get(0);
                                String nombre_producto = (String) fila.get("NOMBRE");
                                System.out.println("\nEl nombre del producto que eligió es: " + nombre_producto);
                                System.out.println("¿Es el producto correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    int id_producto = Integer.parseInt(identificador);
                                    Producto producto = new Producto(id_producto);
                                    System.out.println("");
                                    System.out.println("¿Qué cantidad de este producto comprará?" + " Sus dimensiones son: " + producto.GetDimension());
                                    String cantidad_comprada_str = sc.nextLine();
                                    try
                                    {
                                        float cantidad_comprada = Float.parseFloat(cantidad_comprada_str);
                                        if(cantidad_comprada > 0)
                                        {
                                            productos.add(producto);
                                            cantidades.add(cantidad_comprada);
                                        }
                                        else
                                        {
                                            System.out.println("\nCantidades incorrectas.");
                                        }
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        System.out.println("\nIngresó datos incorrectos");
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("\nEl identificador que ingresó no existe o no corresponde a una Materia Prima o Producto de Reventa o se encuentra descontinuado.");
                            }
                        }
                        else if(opcion2.equals("2"))
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nIngresó una opción incorrecta.");
                        }
                    }
                    flag_ingreso = true;
                    String descripcion = null;
                    while(flag_ingreso && !productos.isEmpty())
                    {
                        System.out.println("\nIngrese un comentario acerca de la gestión (máximo 50 caracteres).");
                        descripcion = sc.nextLine();
                        if(descripcion.length() <= 50)
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nExcedió el número de carateres.");
                        }
                    }
                    if(!productos.isEmpty())
                    {
                        String query_id_max = "SELECT MAX(ID_GESTION) AS MAXIMO FROM GESTIONES";
                        List<Map<String, Object>> maximos = db.executeQuery(query_id_max);
                        Map<String, Object> filas = maximos.get(0);
                        Integer id_maximo_prueba = (Integer) filas.get("MAXIMO");
                        int id_maximo; 
                        if (id_maximo_prueba == null)
                        {
                            id_maximo = 0;
                        }
                        else
                        {
                            id_maximo = id_maximo_prueba;
                        }
                        Gestion gestion = new Gestion(id_maximo + 1, "Compras", productos, cantidades, empleado, id_c_p, descripcion);
                        gestion.InsertarFilaGestion();
                        gestion.InsertarFilaTransaccion();
                        System.out.println("\nEl listado que se gestionó: ");
                        System.out.println(gestion.ToString(productos));
                    }
                    else
                    {
                        System.out.println("\nNo se ingresó ningún producto.");
                    }
                }

                else if (opcion.equals("2"))
                {
                    Cliente_Proveedor cliente = null;
                    int id_c_p = 0;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Cuál es id del cliente?");
                        String id_proveedor_str = sc.nextLine();
                        try
                        {
                            int id_proveedor = Integer.parseInt(id_proveedor_str);
                            cliente = new Cliente_Proveedor(id_proveedor);
                            if(cliente.ExisteId() && cliente.GetTipo().equals("Cliente"))
                            {
                                System.out.println("\nEl cliente que eligió: " + cliente.GetNombre());
                                System.out.println("¿Es correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    id_c_p = cliente.GetId();
                                    flag_ingreso = false;
                                }
                            }
                            else
                            {
                                System.out.println("\nIngresó un identificador que no existe o no es un cliente.");
                            }
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nIngresó algo que no es un número.");
                        }
                    }
                    flag_ingreso = true;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Desea ingresar un producto al listado?");
                        System.out.println("1) Si.");
                        System.out.println("2) No.");
                        String opcion2 = sc.nextLine();
                        if(opcion2.equals("1")) 
                        {  
                            System.out.println("\nIngrese el identificador del producto.");
                            String identificador = sc.nextLine();
                            String query_productos = "SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = " + identificador + " AND TIPO IN ('PT', 'PR') AND DESCONTINUADO = false";
                            try
                            {
                                List<Map<String, Object>> prod = db.executeQuery(query_productos);
                                Map<String, Object> fila = prod.get(0);
                                String nombre_producto = (String) fila.get("NOMBRE");
                                System.out.println("\nEl nombre del producto que eligió es: " + nombre_producto);
                                System.out.println("¿Es el producto correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    int id_producto = Integer.parseInt(identificador);
                                    Producto producto = new Producto(id_producto);
                                    System.out.println("");
                                    System.out.println("¿Qué cantidad de este producto venderá?" + " Sus dimensiones son: " + producto.GetDimension());
                                    String cantidad_comprada_str = sc.nextLine();
                                    try
                                    {
                                        float cantidad_comprada = Float.parseFloat(cantidad_comprada_str);
                                        if(cantidad_comprada > 0)
                                        {
                                            productos.add(producto);
                                            cantidades.add(cantidad_comprada);
                                        }
                                        else
                                        {
                                            System.out.println("\nCantidades incorrectas.");
                                        }
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        System.out.println("\nIngresó datos incorrectos");
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("\nEl identificador que ingresó no existe o no corresponde a una Producto Terminado o Producto de Reventa o se encuentra descontinuado");
                            }
                        }
                        else if(opcion2.equals("2"))
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nIngresó una opción incorrecta.");
                        }
                    }
                    flag_ingreso = true;
                    String descripcion = null;
                    while(flag_ingreso && !productos.isEmpty())
                    {
                        System.out.println("\nIngrese un comentario acerca de la gestión (máximo 50 caracteres)");
                        descripcion = sc.nextLine();
                        if(descripcion.length() <= 50)
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nExcedió el número de carateres.");
                        }
                    }
                    if(!productos.isEmpty())
                    {
                        String query_id_max = "SELECT MAX(ID_GESTION) AS MAXIMO FROM GESTIONES";
                        List<Map<String, Object>> maximos = db.executeQuery(query_id_max);
                        Map<String, Object> filas = maximos.get(0);
                        Integer id_maximo_prueba = (Integer) filas.get("MAXIMO");
                        int id_maximo; 
                        if (id_maximo_prueba == null)
                        {
                            id_maximo = 0;
                        }
                        else
                        {
                            id_maximo = id_maximo_prueba;
                        }
                        Gestion gestion = new Gestion(id_maximo + 1, "Ventas", productos, cantidades, empleado, id_c_p, descripcion);
                        gestion.InsertarFilaGestion();
                        gestion.InsertarFilaTransaccion();
                        System.out.println("\nEl listado que se gestionó: ");
                        System.out.println(gestion.ToString(productos));
                    }
                    else
                    {
                        System.out.println("\nNo se ingresó ningún producto.");
                    }
                }

                else if (opcion.equals("3"))
                {
                    Cliente_Proveedor empresa = null;
                    int id_c_p = 1;
                    flag_ingreso = true;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Desea ingresar un producto al listado?");
                        System.out.println("1) Si.");
                        System.out.println("2) No.");
                        String opcion2 = sc.nextLine();
                        if(opcion2.equals("1")) 
                        {  
                            System.out.println("\nIngrese el identificador del producto.");
                            String identificador = sc.nextLine();
                            String query_productos = "SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = " + identificador + " AND DESCONTINUADO = false";
                            try
                            {
                                List<Map<String, Object>> prod = db.executeQuery(query_productos);
                                Map<String, Object> fila = prod.get(0);
                                String nombre_producto = (String) fila.get("NOMBRE");
                                System.out.println("\nEl nombre del producto que eligió es: " + nombre_producto);
                                System.out.println("¿Es el producto correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    int id_producto = Integer.parseInt(identificador);
                                    Producto producto = new Producto(id_producto);
                                    System.out.println("");
                                    System.out.println("¿Qué cantidad de este producto venderá?" + " Sus dimensiones son: " + producto.GetDimension());
                                    String cantidad_comprada_str = sc.nextLine();
                                    try
                                    {
                                        float cantidad_comprada = Float.parseFloat(cantidad_comprada_str);
                                        if(cantidad_comprada > 0)
                                        {
                                            productos.add(producto);
                                            cantidades.add(cantidad_comprada);
                                        }
                                        else
                                        {
                                            System.out.println("\nCantidades incorrectas.");
                                        }
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        System.out.println("\nIngresó datos incorrectos.");
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("\nEl identificador que ingresó no existe o se encuentra descontinuado.");
                            }
                        }
                        else if(opcion2.equals("2"))
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nIngresó una opción incorrecta.");
                        }
                    }
                    flag_ingreso = true;
                    String descripcion = null;
                    while(flag_ingreso && !productos.isEmpty())
                    {
                        System.out.println("\nIngrese un comentario acerca de la gestión (máximo 50 caracteres).");
                        descripcion = sc.nextLine();
                        if(descripcion.length() <= 50)
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nExcedió el número de carateres.");
                        }
                    }
                    if(!productos.isEmpty())
                    {
                        String query_id_max = "SELECT MAX(ID_GESTION) AS MAXIMO FROM GESTIONES";
                        List<Map<String, Object>> maximos = db.executeQuery(query_id_max);
                        Map<String, Object> filas = maximos.get(0);
                        Integer id_maximo_prueba = (Integer) filas.get("MAXIMO");
                        int id_maximo; 
                        if (id_maximo_prueba == null)
                        {
                            id_maximo = 0;
                        }
                        else
                        {
                            id_maximo = id_maximo_prueba;
                        }
                        Gestion gestion = new Gestion(id_maximo + 1, "Entradas", productos, cantidades, empleado, id_c_p, descripcion);
                        gestion.InsertarFilaGestion();
                        gestion.InsertarFilaTransaccion();
                        System.out.println("\nEl listado que se gestionó: ");
                        System.out.println(gestion.ToString(productos));
                    }
                    else
                    {
                        System.out.println("\nNo se ingresó ningún producto.");
                    }
                }

                else if (opcion.equals("4"))
                {
                    Cliente_Proveedor empresa = null;
                    int id_c_p = 1;
                    flag_ingreso = true;
                    while(flag_ingreso)
                    {
                        System.out.println("\n¿Desea ingresar un producto al listado?");
                        System.out.println("1) Si.");
                        System.out.println("2) No.");
                        String opcion2 = sc.nextLine();
                        if(opcion2.equals("1")) 
                        {  
                            System.out.println("\nIngrese el identificador del producto.");
                            String identificador = sc.nextLine();
                            String query_productos = "SELECT * FROM PRODUCTOS WHERE ID_PRODUCTO = " + identificador + " AND DESCONTINUADO = false";
                            try
                            {
                                List<Map<String, Object>> prod = db.executeQuery(query_productos);
                                Map<String, Object> fila = prod.get(0);
                                String nombre_producto = (String) fila.get("NOMBRE");
                                System.out.println("\nEl nombre del producto que eligió es: " + nombre_producto);
                                System.out.println("¿Es el producto correcto?");
                                System.out.println("1) Si.");
                                System.out.println("Cualquier otra tecla) No.");
                                String correcto = sc.nextLine();
                                if(correcto.equals("1"))
                                {
                                    int id_producto = Integer.parseInt(identificador);
                                    Producto producto = new Producto(id_producto);
                                    System.out.println("");
                                    System.out.println("¿Qué cantidad de este producto venderá?" + " Sus dimensiones son: " + producto.GetDimension());
                                    String cantidad_comprada_str = sc.nextLine();
                                    try
                                    {
                                        float cantidad_comprada = Float.parseFloat(cantidad_comprada_str);
                                        if(cantidad_comprada > 0)
                                        {
                                            productos.add(producto);
                                            cantidades.add(cantidad_comprada);
                                        }
                                        else
                                        {
                                            System.out.println("\nCantidades incorrectas.");
                                        }
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        System.out.println("\nIngresó datos incorrectos.");
                                    }
                                }
                            }
                            catch (Exception e)
                            {
                                System.out.println("\nEl identificador que ingresó no existe o se encuentra descontinuado.");
                            }
                        }
                        else if(opcion2.equals("2"))
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nIngresó una opción incorrecta.");
                        }
                    }
                    flag_ingreso = true;
                    String descripcion = null;
                    while(flag_ingreso && !productos.isEmpty())
                    {
                        System.out.println("\nIngrese un comentario acerca de la gestión (máximo 50 caracteres).");
                        descripcion = sc.nextLine();
                        if(descripcion.length() <= 50)
                        {
                            flag_ingreso = false;
                        }
                        else
                        {
                            System.out.println("\nExcedió el número de carateres.");
                        }
                    }
                    if(!productos.isEmpty())
                    {
                        String query_id_max = "SELECT MAX(ID_GESTION) AS MAXIMO FROM GESTIONES";
                        List<Map<String, Object>> maximos = db.executeQuery(query_id_max);
                        Map<String, Object> filas = maximos.get(0);
                        Integer id_maximo_prueba = (Integer) filas.get("MAXIMO");
                        int id_maximo; 
                        if (id_maximo_prueba == null)
                        {
                            id_maximo = 0;
                        }
                        else
                        {
                            id_maximo = id_maximo_prueba;
                        }
                        Gestion gestion = new Gestion(id_maximo + 1, "Salidas", productos, cantidades, empleado, id_c_p, descripcion);
                        gestion.InsertarFilaGestion();
                        gestion.InsertarFilaTransaccion();
                        System.out.println("\nEl listado que se gestionó: ");
                        System.out.println(gestion.ToString(productos));
                    }
                    else
                    {
                        System.out.println("\nNo se ingresó ningún producto.");
                    }
                }
                
                else
                {
                    System.out.println("Ha ingresado una opción incorrecta.");
                }

            }

            else if(menu.equals("7"))
            {
                System.out.println("\n¿Qué información desea ver?");
                System.out.println("1) Producto.");
                System.out.println("2) Categoría.");
                System.out.println("3) Ubicación.");
                System.out.println("4) Empleado.");
                System.out.println("5) Cliente/Proveedor.");
                String opcion = sc.nextLine();
                if(opcion.equals("1"))
                {
                    try 
                    {
                        System.out.println("\nIngrese el identificador del producto.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Producto producto = new Producto(id);
                        if(producto.ExisteId())
                        {
                            System.out.println(producto.Informacion());
                        }
                        else
                        {
                            System.out.println("\nEl producto no existe o está descontinuado.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nNo ha ingresado un número.");
                    }
                }
                else if(opcion.equals("2"))
                {
                    try 
                    {
                        System.out.println("\nIngrese el identificador de la categoría.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Categoria categoria = new Categoria(id);
                        if(categoria.ExisteId())
                        {
                            System.out.println(categoria.Informacion());
                        }
                        else
                        {
                            System.out.println("\nLa categoría no existe.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nNo ha ingresado un número.");
                    }
                }
                else if(opcion.equals("3"))
                {
                    try 
                    {
                        System.out.println("\nIngrese el identificador de la ubicación.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Ubicacion ubicacion = new Ubicacion(id);
                        if(ubicacion.ExisteId())
                        {
                            System.out.println(ubicacion.Informacion());
                        }
                        else
                        {
                            System.out.println("\nLa ubicación no existe.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nNo ha ingresado un número.");
                    }
                }
                else if(opcion.equals("4"))
                {
                    try 
                    {
                        System.out.println("\nIngrese el identificador del empleado.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Empleado empleado2 = new Empleado(id);
                        if(empleado2.ExisteId())
                        {
                            System.out.println(empleado2.Informacion());
                        }
                        else
                        {
                            System.out.println("\nEl empleado no existe o fue despedido.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nNo ha ingresado un número.");
                    }
                }
                else if(opcion.equals("5"))
                {
                    try 
                    {
                        System.out.println("\nIngrese el identificador del empleado.");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Cliente_Proveedor empresa = new Cliente_Proveedor(id);
                        if(empresa.ExisteId())
                        {
                            System.out.println(empresa.Informacion());
                        }
                        else
                        {
                            System.out.println("\nEl empleado no existe o fue despedido.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nNo ha ingresado un número.");
                    }
                }
                else
                {
                    System.out.println("\nHa ingresado una opción incorrecta.");
                }
            }

            else if(menu.equals("8"))
            {
                flag = false;
            }
            
            else
            {
                System.out.println("Ha ingresado una opción incorrecta.");
            }
        }
    }
}