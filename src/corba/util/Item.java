package corba.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by channel on 2017/5/31.
 */
public class Item {
    Long id;
    Date start;
    Date end;
    String label;

    public Item() {
        setStart(new Date());
        setEnd(new Date());
        setLabel("");
    }

    public Item(String begin, String end, String label) throws ParseException {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

        Date date1 = sdf.parse(begin);
        Date date2 = sdf.parse(end);
        setStart(date1);
        setEnd(date2);
        setLabel(label);
        Calendar now = Calendar.getInstance();
        setId(now.getTimeInMillis());
    }

    public void setId(Long i) {
        id = i;
    }

    public Long getId() {
        return id;
    }

    public int compareBegin(Item another) {
        return getStart().compareTo(another.getStart());
    }

    public int compareEnd(Item another) {
        return getEnd().compareTo(another.getEnd());
    }

    public int compare(Date start,Date end) {
        if(getStart().compareTo(start) >= 0 && getEnd().compareTo(end) <= 0) {
            return 1;
        }
        return -1;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void print() {
        System.out.println("id: " + getId() + " Start date: " + getStart().toString() + " End date: " + getEnd().toString() + " Label: " + getLabel());
    }
}
