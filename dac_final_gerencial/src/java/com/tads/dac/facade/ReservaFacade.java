package com.tads.dac.facade;

import com.tads.dac.beans.Reserva;
import com.tads.dac.dao.ReservaDAO;
import java.util.List;

public class ReservaFacade {
    public static int insertReserva(Reserva reserva){
        return ReservaDAO.insertReserva(reserva);
    }
    
    public static Reserva getReservaById(int id){
        return ReservaDAO.getReservaById(id);
    }
    
    public static List<Reserva> getReservasByCliente(int id){
        return ReservaDAO.getReservasByCliente(id);
    }
    
    public static List<Reserva> getCompletedReservasByCliente(int id){
        return ReservaDAO.getCompletedReservasByCliente(id);
    }
    
    public static List<Reserva> listReservas(){
        return ReservaDAO.listReservas();
    }
    
    public static boolean updateReserva(Reserva reserva){
        return ReservaDAO.updateReserva(reserva);
    }
    
    public static boolean deleteReserva(int id){
        return ReservaDAO.deleteReserva(id);
    }
}
