package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection cn ;
    
    public Conexion()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mr104110_parcial2","root","wualdo");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error no se puede conectar a la BD");
        }
        catch(SQLException e)
        {
           System.out.println("Error"+e.getMessage());
        }   
    }
    public Connection getConexion()
    {
        return cn;
    }
}
