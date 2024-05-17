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
    private static iDao<Odontologo> odontologoDAOMap = new OdontologoDAOMap();
    private static OdontologoService odontologoServiceMap = new OdontologoService(odontologoDAOMap);

    @BeforeAll
    public static void setUp() {
        odontologoDAOMap = new OdontologoDAOMap();
        odontologoServiceMap = new OdontologoService(odontologoDAOMap);
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
}
