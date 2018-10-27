import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvReader {

  public static List<DefendantInfo> processInputFile(String inputFilePath) {
    List<DefendantInfo> inputList = new ArrayList<DefendantInfo>();
    try{
      File inputF = new File(inputFilePath);
      InputStream inputFS = new FileInputStream(inputF);
      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
      // skip the header of the csv
      inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
      br.close();
    } catch (IOException e) {
      System.err.println("Fail to open " + inputFilePath);
    }
    return inputList ;
  }

  private static Function<String, DefendantInfo> mapToItem = (line) -> {
    String[] p = line.split(",");   // a CSV has comma separated lines
    DefendantInfo info = new DefendantInfo();

    info.setFirstName(p[0]);
    info.setLastName(p[1]);
    info.setCaseNumber(p[2]);
    info.setAddress(p[6]);
    info.setCity(p[7]);
    info.setState(p[8]);
    info.setZip(p[9]);
    info.setTelephone(p[10]);
    info.setEmail(p[12]);

    return info;
  };


}
