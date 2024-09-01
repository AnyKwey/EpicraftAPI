package eu.epicraft.com.data.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IDate {

    public static Date getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        return date;
    }
}
