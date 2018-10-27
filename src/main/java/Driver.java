import java.util.List;
import java.util.Iterator;
/**
 * @author: Vivian Xu
 */
public class Driver {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        String path = "Dummy Client Data.csv";
        List<DefendantInfo> InfoList = csvReader.processInputFile(path);

        Iterator<DefendantInfo> iterator = InfoList.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }

        for (DefendantInfo defender : InfoList) {

            FillingCR180 cr180 = new FillingCR180();
            cr180.fillForm(defender);

            FillingCR181 cr181 = new FillingCR181();
            cr181.fillForm(defender);

        }
    }
}
