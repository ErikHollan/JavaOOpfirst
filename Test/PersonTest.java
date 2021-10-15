import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private LocalDate membershipDate;


    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    @Test
    public void personSetterGetterTest(){
        Person p = new Person();
        p.setName("Bear Belle");
        p.setPersonalNr("8104021234");
        p.setMembershipActive(false);

        assertTrue(p.getName() == "Bear Belle");
        assertTrue(p.getPersonalNr() == "8104021234");
        assertTrue(p.getIsMembershipActive() == false);
    }
    @Test
    public void verifyMembershipTest() {
        Person p = new Person();
        p.setMembershipDate(LocalDate.of(2020, 11, 15));
        assertTrue(p.verifyMembership());

    }

}


