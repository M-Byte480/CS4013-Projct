package reservation;

import java.util.ArrayList;

public class Timetable {

    private ArrayList<Timetable> appointments = new ArrayList<Timetable>();

    public void add(Timetable a) {
        appointments.add(a);
    }

    public ArrayList<Appointment> getAppointmentsForDay(Timetable day) {
        ArrayList<Appointment> appointmentsToday = new ArrayList<Appointment>();
        for (Appointment appt : appointments) {
            if (appt.getDay().eqauls(day)) {
                appointmentsToday.add(appt);
            }
        }
        return appointmentsToday;
    }
    public void cancel(Appointment a) {
        appointments.remove(a);
    }

    @Override
    public String toString() {
        return "AppointmentCalendar{" +
                "appointments=" + appointments +
                '}';
}
