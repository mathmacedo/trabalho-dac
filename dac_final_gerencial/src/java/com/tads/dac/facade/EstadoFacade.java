        package com.tads.dac.facade;

import com.tads.dac.beans.Estado;
import com.tads.dac.dao.EstadoDAO;
import java.util.List;

public class EstadoFacade {
    public static int insertEstado(Estado estado){
        return EstadoDAO.insertEstado(estado);
    }
    
    public static Estado getEstadoById(int id){
        return EstadoDAO.getEstadoById(id);
    }
    
    public static List<Estado> listEstados(){
        return EstadoDAO.listEstados();
    }
    
    public static boolean updateEstado(Estado estado){
        return EstadoDAO.updateEstado(estado);
    }
    
    public static boolean deleteEstado(int id){
        return EstadoDAO.deleteEstado(id);
    }

    public static Estado getEstadoBySigla(String sigla) {
        return EstadoDAO.getEstadoBySigla(sigla);
    }
}
