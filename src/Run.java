import java.util.List;

public class Run {
    public static void runApp() {
        List<Person> customer = Logic.readCustomerFromFile("src/customers.txt");
        Logic.searchCustomerFromList(customer);
    }

}
