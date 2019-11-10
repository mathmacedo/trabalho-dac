package com.tads.dac.facade;

import com.tads.dac.beans.Endereco;
import com.tads.dac.dao.EnderecoDAO;
import java.util.List;

public class EnderecoFacade {
    public static int insertEndereco(Endereco endereco){
        return EnderecoDAO.insertEndereco(endereco);
    }
    
    public static Endereco getEnderecoById(int id){
        return EnderecoDAO.getEnderecoById(id);
    }
    
    public static List<Endereco> listEnderecos(){
        return EnderecoDAO.listEnderecos();
    }
    
    public static boolean updateEndereco(Endereco endereco){
        return EnderecoDAO.updateEndereco(endereco);
    }
    
    public static boolean deleteEndereco(int id){
        return EnderecoDAO.deleteEndereco(id);
    }
}
