package com.tads.dac.facade;

import com.tads.dac.beans.CidadeAeroporto;
import com.tads.dac.dao.CidadeAeroportoDAO;
import java.util.List;

public class CidadeAeroportoFacade {
    public static int insertCidadeAeroporto(CidadeAeroporto cidadeAeroporto){
        return CidadeAeroportoDAO.insertCidadeAeroporto(cidadeAeroporto);
    }
    
    public static CidadeAeroporto getCidadeAeroportoById(int id){
        return CidadeAeroportoDAO.getCidadeAeroportoById(id);
    }
    
    public static List<CidadeAeroporto> listCidadeAeroportos(){
        return CidadeAeroportoDAO.listCidadeAeroportos();
    }
    
    public static boolean updateCidadeAeroporto(CidadeAeroporto cidadeAeroporto){
        return CidadeAeroportoDAO.updateCidadeAeroporto(cidadeAeroporto);
    }
    
    public static boolean deleteCidadeAeroporto(int id){
        return CidadeAeroportoDAO.deleteCidadeAeroporto(id);
    }
}
