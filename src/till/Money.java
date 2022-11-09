package till;

/**
 * Class format inspired by lab assignment
 */
public class Money {
    private double value;
    private String name;

    /**
     * Constructor to create a Money object with a specified value and a specified name
     * @param aValue
     * @param aName
     */
    public Money(double aValue, String aName)
    {
        value = aValue;
        name = aName;
    }

    /**
     * Initializing an array of type Money with all money objects needed.
     * Adds every value used in the Euro Currency to the array.
     */
    private static Money[] money = {
            new Money(0.05, "5 cent"),
            new Money(0.10,"10 cent"),
            new Money(0.20,"20 cent"),
            new Money(0.50,"50 cent"),
            new Money(1.0,"1 euro"),
            new Money(2.0,"2 euro"),
            new Money(5.0,"5 euro"),
            new Money(10.0,"10 euro"),
            new Money(20.0,"20 euro"),
            new Money(50.0,"50 euro"),
            new Money(100.0,"100 euro"),
            new Money(200.0,"200 euro"),
            new Money(500.0, "500 euro"),
    };
}
