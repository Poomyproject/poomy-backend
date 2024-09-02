package com.poomy.mainserver.util.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ChangeFormat {

    public static String reviewFormat(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(timestamp);
    }

}
