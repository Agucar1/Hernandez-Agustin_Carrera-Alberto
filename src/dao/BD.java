package dao;

import model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class BD {

    private static final Logger logger= Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE_PAC="DROP TABLE IF EXISTS PACIENTES; CREATE TABLE PACIENTES (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, " +
            "CEDULA VARCHAR(100) NOT NULL, FECHA_INGRESO DATE NOT NULL, DOMICILIO_ID INT NOT NULL )"; //<<-- FK
    private static final String SQL_DROP_CREATE_DOM="DROP TABLE IF EXISTS DOMICILIOS; " +
            "CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(100) NOT NULL, NUMERO INT NOT NULL, LOCALIDAD VARCHAR(100) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL)";
    private static final  String SQL_DROP_CREATE_ODO= "DROP TABLE IF EXISTS ODONTOLOGOS;" + "CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY, CEDULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL)";

    private static final String SQL_DROP_CREATE_ODO_SEQ = """
            DROP TABLE IF EXISTS ODO_SEQ; CREATE SEQUENCE ODO_SEQ\s
            MINVALUE 1\s
            MAXVALUE 999999999\s
            INCREMENT BY 1\s
            START WITH 202700\s
            NOCACHE\s
            NOCYCLE;""";

    private static final String SQL_PRUEBA="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID) VALUES ('Jorgito','Pereyra','111111','2024-05-16', 1), ('German','Fraire','22222','2024-05-10',2); " +
            "INSERT INTO DOMICILIOS  (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES ('Siempre Viva',742,'Springfield','USA'),('Av. Uruguay',345,'Punta del Este','Uruguay')" +
            ";INSERT INTO ODONTOLOGOS (ID, CEDULA, NOMBRE, APELLIDO) VALUES (1, '999999','Rodolfo','Perez')";
    public static void crearTablas(){
        Connection connection= null;
        try{
            connection= getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE_DOM);
            statement.execute(SQL_DROP_CREATE_PAC);
            statement.execute(SQL_DROP_CREATE_ODO);
            statement.execute(SQL_DROP_CREATE_ODO_SEQ);

            statement.execute(SQL_PRUEBA);



        }catch (Exception e){
            logger.warn(e.getMessage());
        }

    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/clinicaOdontologica","sa","sa");
    }
}
