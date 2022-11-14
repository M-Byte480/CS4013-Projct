package people;

public class Chef extends Person{
    /*
    Will deduce when an order is ready to be brought to a table
    Will need to read one of the CSV files that stores orders and when
    the status is 'ready' then it is 'brought' to the table
     */
    public Chef(String name, String phoneNumber, String id) {
        super(name, phoneNumber, id);
    }


}
