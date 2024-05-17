package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS VALUES(NEXT VALUE FOR ODO_SEQ,?,?,?)";

    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Guardando datos del odontologo H2");
        Connection connection= null;

        try{
            connection= BD.getConnection();
            PreparedStatement psinsert= connection.prepareStatement(SQL_INSERT);
            psinsert.setString(1,odontologo.getNumeroMatricula());
            psinsert.setString(2,odontologo.getNombre());
            psinsert.setString(3,odontologo.getApellido());

            psinsert.execute();
            logger.info("objeto guardado en la tabla");

        }catch (Exception e) {
            logger.warn(e.getMessage());

        }
        return odontologo;
    }
    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Iniciando la operacion para listar odontologos H2");
        Connection connection = null;
        Odontologo odontologo = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            connection= BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            ResultSet rs= psSelect.executeQuery();
            while (rs.next()){
                odontologo = new Odontologo (rs.getString(1), rs.getString(2), rs.getString(3));
                odontologos.add(odontologo);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologos;


    }
}