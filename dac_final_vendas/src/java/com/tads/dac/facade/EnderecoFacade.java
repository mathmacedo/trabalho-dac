package com.tads.dac.facade;

import com.tads.dac.beans.Endereco;
import com.tads.dac.dao.EnderecoDAO;
import java.util.List;

public class EnderecoFacade {
    public static int insertEndereco(Endereco checkin){
        return EnderecoDAO.insertEndereco(checkin);
    }
    
    public static Endereco getEnderecoById(int id){
        return EnderecoDAO.getEnderecoById(id);
    }
    
    public static List<Endereco> listEnderecos(){
        return EnderecoDAO.listEnderecos();
    }
    
    public static boolean updateEndereco(Endereco checkin){
        return EnderecoDAO.updateEndereco(checkin);
    }
    
    public static boolean deleteEndereco(int id){
        return EnderecoDAO.deleteEndereco(id);
    }
}
