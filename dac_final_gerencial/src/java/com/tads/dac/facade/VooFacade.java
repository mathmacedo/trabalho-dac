package com.tads.dac.facade;

import com.tads.dac.beans.Voo;
import com.tads.dac.dao.VooDAO;
import java.util.List;

public class VooFacade {
    public static int insertVoo(Voo voo){
        return VooDAO.insertVoo(voo);
    }
    
    public static Voo getVooById(int id){
        return VooDAO.getVooById(id);
    }
    
    public static List<Voo> listVoos(){
        return VooDAO.listVoos();
    }
    
    public static boolean updateVoo(Voo voo){
        return VooDAO.updateVoo(voo);
    }
    
    public static boolean deleteVoo(int id){
        return VooDAO.deleteVoo(id);
    }
}
