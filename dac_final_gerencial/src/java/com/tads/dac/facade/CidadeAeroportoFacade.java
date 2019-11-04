package com.tads.dac.facade;

import com.tads.dac.beans.CidadeAeroporto;
import com.tads.dac.dao.CidadeAeroportoDAO;
import java.util.List;

public class CidadeAeroportoFacade {
    public static int insertCidadeAeroporto(CidadeAeroporto checkin){
        return CidadeAeroportoDAO.insertCidadeAeroporto(checkin);
    }
    
    public static CidadeAeroporto getCidadeAeroportoById(int id){
        return CidadeAeroportoDAO.getCidadeAeroportoById(id);
    }
    
    public static List<CidadeAeroporto> listCidadeAeroportos(){
        return CidadeAeroportoDAO.listCidadeAeroportos();
    }
    
    public static boolean updateCidadeAeroporto(CidadeAeroporto checkin){
        return CidadeAeroportoDAO.updateCidadeAeroporto(checkin);
    }
    
    public static boolean deleteCidadeAeroporto(int id){
        return CidadeAeroportoDAO.deleteCidadeAeroporto(id);
    }
}
