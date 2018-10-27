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
            FillerFW001 fw001 = new FillerFW001();
            fw001.fillForm(defender);

            FillerFW003 fw003 = new FillerFW003();
            fw003.fillForm(defender);
        }
    }
}
