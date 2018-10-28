import fillers.AbstractPdfFiller;
import fillers.implementations.CR115Filler;
import fillers.implementations.CR180Filler;
import fillers.implementations.CR181Filler;
import fillers.implementations.FW001Filler;
import fillers.implementations.FW003Filler;
import fillers.implementations.Pos040Filler;
import object.DefendantInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        String path = "Dummy Client Data.csv";
        List<DefendantInfo> infos = CsvReader.processInputFile(path);

        File outDir = new File(AbstractPdfFiller.FOLDER_NAME.substring(0, AbstractPdfFiller.FOLDER_NAME.length() - 1));
        if (!outDir.exists()) {
            outDir.mkdir();
        }
        List<AbstractPdfFiller> pdfFillers = initializePdfFillers();

        for (AbstractPdfFiller filler : pdfFillers) {
            filler.fillForm(infos.get(0));
        }
    }

    public static List<AbstractPdfFiller> initializePdfFillers() {
        List<AbstractPdfFiller> result = new ArrayList<>();
        AbstractPdfFiller cr115Filler = new CR115Filler();
        AbstractPdfFiller cr180Filler = new CR180Filler();
        AbstractPdfFiller cr181Filler = new CR181Filler();
        AbstractPdfFiller fw003Filler = new FW003Filler();
        AbstractPdfFiller fw001Filler = new FW001Filler();
        AbstractPdfFiller pos040Filler = new Pos040Filler();

        result.add(cr115Filler);
        result.add(cr180Filler);
        result.add(cr181Filler);
        result.add(fw001Filler);
        result.add(fw003Filler);
        result.add(pos040Filler);
        return result;
    }
}
