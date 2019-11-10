package com.tads.dac.facade;

import com.tads.dac.beans.Assento;
import com.tads.dac.dao.AssentoDAO;
import java.util.List;

public class AssentoFacade {
    public static int insertAssento(Assento assento){
        return AssentoDAO.insertAssento(assento);
    }
    
    public static Assento getAssentoById(int id){
        return AssentoDAO.getAssentoById(id);
    }
    
    public static List<Assento> getAssentosByVoo(int id){
        return AssentoDAO.getAssentosByVoo(id);
    }
    
    public static List<Assento> listAssentos(){
        return AssentoDAO.listAssentos();
    }
    
    public static boolean updateAssento(Assento assento){
        return AssentoDAO.updateAssento(assento);
    }
    
    public static boolean deleteAssento(int id){
        return AssentoDAO.deleteAssento(id);
    }
}
