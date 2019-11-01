package com.tads.dac.facade;

import com.tads.dac.beans.Checkin;
import com.tads.dac.dao.CheckinDAO;
import java.util.List;

public class CheckinFacade {
    public static int insertCheckin(Checkin checkin){
        return CheckinDAO.insertCheckin(checkin);
    }
    
    public static Checkin getCheckinById(int id){
        return CheckinDAO.getCheckinById(id);
    }
    
    public static List<Checkin> listCheckins(){
        return CheckinDAO.listCheckins();
    }
    
    public static boolean updateCheckin(Checkin checkin){
        return CheckinDAO.updateCheckin(checkin);
    }
    
    public static boolean deleteCheckin(int id){
        return CheckinDAO.deleteCheckin(id);
    }
}
