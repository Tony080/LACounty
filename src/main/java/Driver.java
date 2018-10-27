/**
 * @author: Vivian Xu
 */
public class Driver {
    public static void main(String[] args) {
        DefendantInfo defender = new DefendantInfo();
        defender.setAddress("Cherry Ln");
        defender.setCity("Fremont");
        defender.setEmail("xxx@gmail.com");
        defender.setCaseNumber("123445");
        defender.setFirstName("Vivi");
        defender.setLastName("Xu");
        defender.setState("CA");
        defender.setZip("93452");
        defender.setTelephone("9343522343");
        FillingCR180 fillingCR180 = new FillingCR180();
        fillingCR180.fillForm(defender);
    }
}
