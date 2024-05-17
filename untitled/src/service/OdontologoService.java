package service;

import dao.iDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoIDao;

    public OdontologoService(iDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar(Odontologo odontologo) {
        return this.odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> buscarTodos() {
        return this.odontologoIDao.buscarTodos();
    }
}
