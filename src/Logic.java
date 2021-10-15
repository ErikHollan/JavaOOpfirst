import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {


    public static List<Person> readCustomerFromFile(String Path) {  //Läser given fil
        List<Person> customerInfo = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(Path))) {  //löper igenom fil
            while (sc.hasNext()) {
                Person customer = new Person();
                String readCustomer = sc.nextLine();

                customer.setPersonalNr(readCustomer.substring(0, readCustomer.indexOf(',')));
                customer.setName(readCustomer.substring(readCustomer.indexOf(',') + 2));
                readCustomer = sc.nextLine();
                customer.setMembershipDate(LocalDate.parse(readCustomer, DateTimeFormatter.ISO_LOCAL_DATE));
                customer.setMembershipActive(customer.verifyMembership());
                customerInfo.add(customer);  //Lägger till objekt till lista
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customerInfo;
    }

    public static void searchCustomerFromList(List<Person> customerInfo) { //läser igenom listan med objekt
        while (true) {
            boolean doesCustomerExist = false;
            String searchInput = JOptionPane.showInputDialog("Sök kund");
            if (searchInput.trim().isEmpty()) { //Ej skrivit något
                JOptionPane.showMessageDialog(null, "Du har inte skrivit något");
                continue;
            }

            for (Person customer : customerInfo)  //Finns sökordet i listan?
                if (searchInput.equalsIgnoreCase(customer.getName()) || searchInput.equals(customer.getPersonalNr())) { //om ja
                    JOptionPane.showMessageDialog(null, customer.toString());
                    doesCustomerExist = true;
                    customerActions(customer); //skicka customer till nästa metod

                }
            if (!doesCustomerExist) { //om nej
                JOptionPane.showMessageDialog(null, searchInput + " är inte medlem hos oss!");
            }
        }
    }

    public static boolean customerActions(Person person) {  //Actions för kund
        boolean b = false;
//är medlemskapet aktivt?
        if (person.getIsMembershipActive()) {  //om ja
            b = true;
            int confirmAlternative = JOptionPane.showConfirmDialog(null, "Stämpla in?", "Stämpla in", JOptionPane.YES_NO_OPTION);
            if (confirmAlternative == JOptionPane.YES_OPTION) {
                writeCustomerToFile(person); //skicka person till nästa metod som skriver ut till ny fil
                JOptionPane.showMessageDialog(null, person.getName() + " är nu instämplad");
            }
        } else {//om nej
            int confirmAlternative = JOptionPane.showConfirmDialog(null, "Vill kund förnya medlemskap?", "Förnya medlemskap", JOptionPane.YES_NO_OPTION);
            if (confirmAlternative == JOptionPane.YES_OPTION) { //kund vill förnya medlemskap
                person.setMembershipDate(LocalDate.now());
                person.setMembershipActive(true);  //nytt datum för medlemskap: idag
                /*String memberShipDate = person.getMembershipDate().toString();
                String dateNow = LocalDate.now().toString();
                updateMembership("src/customers.txt", dateNow, memberShipDate);
                */
                JOptionPane.showMessageDialog(null, person.getName() + ", ditt medlemskap är nu förnyat!\nFrån: " + LocalDate.now()
                        + "\nTill: " + LocalDate.now().plusYears(1));
                writeCustomerToFile(person); //skriv till ny fil
            }
        }

        return b;
    }

    public static void writeCustomerToFile(Person printPerson) { //skriver till ny fil
        try (PrintWriter writeToFile = new PrintWriter(new BufferedWriter(new FileWriter("src/instämpling.txt", true)))) {

            // skriver ut objektets data + dagens datum och klockslag
            String timeStamp = "\nSenaste aktivitet: " + LocalDateTime.now().withNano(0);
            writeToFile.write(String.valueOf(printPerson)); //objekt till string
            writeToFile.write(timeStamp.replace('T', ' ') + "\n");
            writeToFile.write("\n");


        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fel! Uppgifterna sparades inte");
        }
    }

 /*   public static void updateMembership(String filePath, String oldString, String newString) {
        File fileToBeModified = new File("src/customers.txt");

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fel!");
        } finally {
            try {

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

  */
}


