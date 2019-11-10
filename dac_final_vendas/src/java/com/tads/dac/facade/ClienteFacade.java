package com.tads.dac.facade;

import com.tads.dac.beans.Cliente;
import com.tads.dac.dao.ClienteDAO;
import java.util.List;

public class ClienteFacade {
    public static int insertCliente(Cliente cliente){
        return ClienteDAO.insertCliente(cliente);
    }
    
    public static Cliente getClienteById(int id){
        return ClienteDAO.getClienteById(id);
    }
    
    public static List<Cliente> listClientes(){
        return ClienteDAO.listClientes();
    }
    
    public static boolean updateCliente(Cliente cliente){
        return ClienteDAO.updateCliente(cliente);
    }
    
    public static boolean deleteCliente(int id){
        return ClienteDAO.deleteCliente(id);
    }
}
