import java.time.LocalDate;

public class Person {

    private String personalNr;
    private String name;
    private LocalDate membershipDate;
    private boolean isMembershipActive;


    public void setMembershipActive(boolean membershipActive) {
        isMembershipActive = membershipActive;
    }

    public String getPersonalNr() {
        return personalNr;
    }

    public void setPersonalNr(String personalNr) {
        this.personalNr = personalNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public boolean getIsMembershipActive() {
        return isMembershipActive;
    }

    public boolean verifyMembership() {
        boolean isMembershipActive = false;

        LocalDate dateToday = LocalDate.now();
        LocalDate expiredMembership = this.membershipDate.plusYears(1);

        if (dateToday.isBefore(expiredMembership))
            isMembershipActive = true;

        return isMembershipActive;
    }


    @Override
    public String toString() {
        String memberStatus;
        if (this.isMembershipActive)
            memberStatus = "Aktivt";
        else
            memberStatus = "Upphört";

        return "Namn: " + this.name + "\nPersonnummer: " + this.personalNr +
                "\nMedlem sedan: " + this.membershipDate + "\nMedlemsstatus: " + memberStatus;
    }

}
