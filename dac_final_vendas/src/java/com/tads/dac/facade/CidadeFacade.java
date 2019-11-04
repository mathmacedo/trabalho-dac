package com.tads.dac.facade;

import com.tads.dac.beans.Cidade;
import com.tads.dac.dao.CidadeDAO;
import java.util.List;

public class CidadeFacade {
    public static int insertCidade(Cidade checkin){
        return CidadeDAO.insertCidade(checkin);
    }
    
    public static Cidade getCidadeById(int id){
        return CidadeDAO.getCidadeById(id);
    }
    
    public static List<Cidade> listCidades(){
        return CidadeDAO.listCidades();
    }
    
    public static boolean updateCidade(Cidade checkin){
        return CidadeDAO.updateCidade(checkin);
    }
    
    public static boolean deleteCidade(int id){
        return CidadeDAO.deleteCidade(id);
    }
}
