package dao;

import model.Odontologo;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OdontologoDAOMap implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOMap.class);

    private Map<Integer, Odontologo> odontologoMap;

    private AtomicInteger currentId;

    public OdontologoDAOMap() {
        odontologoMap = new ConcurrentHashMap<>();
        currentId = new AtomicInteger(BigInteger.ZERO.intValue());
    }
    @Override
    public Odontologo guardar(Odontologo o) {
        String errorMsg = "Error al guardar odontologo Map";
        logger.info("Iniciando la operacion para guardar odontologo");
        AtomicInteger nextId = this.currentId;
        nextId.addAndGet(BigInteger.ONE.intValue());
        if (odontologoMap.containsKey(nextId.intValue())) {
            logger.error(errorMsg);
        }else {
            Odontologo newO = odontologoMap.computeIfAbsent(nextId.intValue(), k -> o);
            if (Objects.nonNull(newO)){
                logger.info("Odontologo guardado con éxito");
                this.currentId = nextId;
            }else{
                logger.error(errorMsg);
            }
        }
        return o;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo o) {

    }

    @Override
    public List buscarTodos() {
        logger.info("Iniciando la operacion para listar odontologos Map");
        return Arrays.asList(odontologoMap.entrySet().toArray());
    }

}
