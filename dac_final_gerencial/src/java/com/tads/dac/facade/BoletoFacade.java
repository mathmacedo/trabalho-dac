package com.tads.dac.facade;

import com.tads.dac.beans.Boleto;
import com.tads.dac.dao.BoletoDAO;
import java.util.List;

public class BoletoFacade {
    public static int insertBoleto(Boleto boleto){
        return BoletoDAO.insertBoleto(boleto);
    }
    
    public static Boleto getBoletoById(int id){
        return BoletoDAO.getBoletoById(id);
    }
    
    public static Boleto getBoletosByReserva(int id){
        return BoletoDAO.getBoletoByReserva(id);
    }
    
    public static List<Boleto> listBoletos(){
        return BoletoDAO.listBoletos();
    }
    
    public static boolean updateBoleto(Boleto boleto){
        return BoletoDAO.updateBoleto(boleto);
    }
    
    public static boolean deleteBoleto(int id){
        return BoletoDAO.deleteBoleto(id);
    }
}
