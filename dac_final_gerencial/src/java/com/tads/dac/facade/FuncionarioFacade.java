package com.tads.dac.facade;

import com.tads.dac.beans.Funcionario;
import com.tads.dac.dao.FuncionarioDAO;
import java.util.List;

public class FuncionarioFacade {
    public static int insertFuncionario(Funcionario funcionario){
        return FuncionarioDAO.insertFuncionario(funcionario);
    }
    
    public static Funcionario getFuncionarioById(int id){
        return FuncionarioDAO.getFuncionarioById(id);
    }
    
    public static List<Funcionario> listFuncionarios(){
        return FuncionarioDAO.listFuncionarios();
    }
    
    public static boolean updateFuncionario(Funcionario funcionario){
        return FuncionarioDAO.updateFuncionario(funcionario);
    }
    
    public static boolean deleteFuncionario(int id){
        return FuncionarioDAO.deleteFuncionario(id);
    }
    
    public static Funcionario authenticate(String name, String password){
        return FuncionarioDAO.authenticate(name, password);
    }
}
