package com.tads.dac.facade;

import com.tads.dac.beans.Piloto;
import com.tads.dac.dao.PilotoDAO;
import java.util.List;

public class PilotoFacade {
    public static int insertPiloto(Piloto checkin){
        return PilotoDAO.insertPiloto(checkin);
    }
    
    public static Piloto getPilotoById(int id){
        return PilotoDAO.getPilotoById(id);
    }
    
    public static List<Piloto> listPilotos(){
        return PilotoDAO.listPilotos();
    }
    
    public static boolean updatePiloto(Piloto checkin){
        return PilotoDAO.updatePiloto(checkin);
    }
    
    public static boolean deletePiloto(int id){
        return PilotoDAO.deletePiloto(id);
    }
}
