package com.example.planalife.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateManager {
    Calendar mCalendar;

    public DateManager(){
        mCalendar = Calendar.getInstance();
    }

    //Obtenez les éléments du mois
    public List<Date> getDays(){
        //Garder l'état actuel
        Date startDate = mCalendar.getTime();

        //Calculer le nombre total de cellules affichées dans GridView
        int count = getWeeks() * 7 ;

        //Calculer le nombre de jours du mois précédent affiché sur le calendrier du mois en cours
        mCalendar.set(Calendar.DATE, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        mCalendar.add(Calendar.DATE, -dayOfWeek);

        List<Date> days = new ArrayList<>();

        for (int i = 0; i < count; i ++){
            days.add(mCalendar.getTime());
            mCalendar.add(Calendar.DATE, 1);
        }

        //Restaurer l'état
        mCalendar.setTime(startDate);

        return days;
    }

    //Vérifiez si c'est ce mois-ci
    public boolean isCurrentMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.US);
        String currentMonth = format.format(mCalendar.getTime());
        if (currentMonth.equals(format.format(date))){
            return true;
        }else {
            return false;
        }
    }

    //Obtenez le nombre de semaines
    public int getWeeks(){
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    //Obtenez la journée
    public int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //Au mois suivant
    public void nextMonth(){
        mCalendar.add(Calendar.MONTH, 1);
    }

    //Au mois précédent
    public void prevMonth(){
        mCalendar.add(Calendar.MONTH, -1);
    }
}