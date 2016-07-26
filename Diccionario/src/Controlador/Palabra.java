package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;

public class Palabra {

    ResultSet rs = null;
    String consultaSql;
    Connection con;
    Statement cm = null;
    Conexion mconexion = new Conexion();
    String pal;
    PreparedStatement ps;

    String idpalabra, palabra, definicion, ejemplos, code;

    DefaultListModel model = new DefaultListModel();
    
    //Set and Get de las variables idpalabra, palabra, definicion, ejemplos, code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdpalabra() {
        return idpalabra;
    }

    public void setIdpalabra(String idpalabra) {
        this.idpalabra = idpalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getEjemplos() {
        return ejemplos;
    }

    public void setEjemplos(String ejemplos) {
        this.ejemplos = ejemplos;
    }

    //Metodo para buscar la palabra
    public void buscarPalabra(String Palabra) throws SQLException {
        //agregamos a la variable 
        pal = Palabra;
        //definimos la consulta que haremos
        consultaSql = "select * from palabra where Palabra='" + pal + "'";
        //inicializamos la conexion
        con = mconexion.getConexion();
        //creamos el statement
        cm = con.createStatement();
        //Ejecutamos el query
        rs = cm.executeQuery(consultaSql);
        //Agregamos en variables lo que llamamos en la base de datos
        if (rs.next()) {
            this.idpalabra = String.valueOf(rs.getInt(1));
            this.palabra = rs.getString(2);
            this.definicion = rs.getString(3);
            this.ejemplos = rs.getString(4);
            this.code = "1";
        } else {
            this.code = "-1";
        }

    }
    //Metodo para insertar la palabra nueva
    public void insertarPalabra(String Pal, String Def, String Eje) throws SQLException {
        //definimos la consulta
        consultaSql = "insert into palabra(Palabra,Definicion,Ejemplos) "
                + "values('" + Pal + "', '" + Def + "','" + Eje + "')";
        //conectamos a la conexion
        con = mconexion.getConexion();
        //creamos el statement
        cm = con.createStatement();
        //ejectutamos la consulta
        cm.execute(consultaSql);
    }
    //Metodo para actualizar palabra
    public void actualizarPalabra(String Pal, String Def, String Eje) throws SQLException {
        //definimos la consulta
        consultaSql = "update palabra set Definicion='" + Def + "',Ejemplos='" + Eje + "' where Palabra='" + Pal + "'";
        //conectamos a la conexion
        con = mconexion.getConexion();
        //creamos el statement
        cm = con.createStatement();
        //ejectutamos la consulta
        cm.execute(consultaSql);
    }
    //Metodo para borrar la palabra
    public void borrarPalabra(String Pal) throws SQLException {
        //definimos la consulta
        consultaSql = "delete from palabra where Palabra='" + Pal + "'";
        //conectamos a la conexion
        con = mconexion.getConexion();
        //creamos el statement
        cm = con.createStatement();
        //ejectutamos la consulta
        cm.execute(consultaSql);
    }
    public void Pal(String cad) throws SQLException, ClassNotFoundException {
        //Conexion directa hacia base de datos porque usaremos el postgresql
        Class.forName("org.postgresql.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mr104110_parcial2","root","wualdo");
        //preparamos la consulta que haremos
        ps= con.prepareStatement("select Palabra from palabra where Palabra like'"+cad+"%'");
        //ejecutamos el query
        rs = ps.executeQuery();
        //almacenamos en la variable que en este caso sera una lista para poder mostrarlo en el jlist
        while(rs.next())
        {
            model.addElement(rs.getObject(1));
        }     
    }
    //Retorna los valores encontrados al jlist
    public DefaultListModel getmodel(){
        return model;
    }

}
