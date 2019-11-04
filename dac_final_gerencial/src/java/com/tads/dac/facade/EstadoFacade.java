package com.tads.dac.facade;

import com.tads.dac.beans.Estado;
import com.tads.dac.dao.EstadoDAO;
import java.util.List;

public class EstadoFacade {
    public static int insertEstado(Estado checkin){
        return EstadoDAO.insertEstado(checkin);
    }
    
    public static Estado getEstadoById(int id){
        return EstadoDAO.getEstadoById(id);
    }
    
    public static List<Estado> listEstados(){
        return EstadoDAO.listEstados();
    }
    
    public static boolean updateEstado(Estado checkin){
        return EstadoDAO.updateEstado(checkin);
    }
    
    public static boolean deleteEstado(int id){
        return EstadoDAO.deleteEstado(id);
    }
}
