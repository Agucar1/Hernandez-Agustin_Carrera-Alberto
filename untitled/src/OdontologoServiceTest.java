import dao.BD;
import dao.OdontologoDAOH2;
import dao.OdontologoDAOMap;
import dao.iDao;
import model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.math.BigInteger;
import java.util.List;

public class OdontologoServiceTest {
    private static iDao<Odontologo> odontologoDAOMap;
    private static iDao<Odontologo> odontologoDAOH2;
    private static OdontologoService odontologoServiceMap;
    private static OdontologoService odontologoServiceH2;

    @BeforeAll
    public static void setUp() {
        odontologoDAOMap = new OdontologoDAOMap();
        odontologoServiceMap = new OdontologoService(odontologoDAOMap);

        odontologoDAOH2 = new OdontologoDAOH2();
        odontologoServiceH2 = new OdontologoService(odontologoDAOH2);
        BD.crearTablas();
    }

    @Test
    public void guardarOdontologoMap(){
        Odontologo odontologo = new Odontologo("1", "Alberto", "Carrera");
        Odontologo odontologoNuevo = odontologoServiceMap.guardar(odontologo);
        Assertions.assertEquals(odontologo, odontologoNuevo);
    }

    @Test
    public void ListarOdontologoMap(){
        List<Odontologo> odontologosList = odontologoServiceMap.buscarTodos();
        Assertions.assertEquals(BigInteger.ONE.intValue(), odontologosList.size());
    }

    @Test
    public void guardarOdontologoH2(){
        Odontologo odontologo = new Odontologo("1", "Agustin", "Hernandez");
        Odontologo odontologoNuevo = odontologoServiceH2.guardar(odontologo);
        Assertions.assertEquals(odontologo, odontologoNuevo);
    }

    @Test
    public void ListarOdontologoH2(){
        List<Odontologo> odontologosList = odontologoServiceH2.buscarTodos();
        Assertions.assertEquals(BigInteger.ONE.intValue(), odontologosList.size());
    }
}
