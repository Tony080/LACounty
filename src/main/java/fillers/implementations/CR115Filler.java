package fillers.implementations;

import fillers.AbstractPdfFiller;
import object.DefendantInfo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;

public class CR115Filler implements AbstractPdfFiller {
    public static final String FILENAME = "cr115.pdf";
    private PDDocument pdf;
    @Override
    public void fillForm(DefendantInfo individualInfo) {
        try {
            PDAcroForm acroForm = openPdf();
            fillInfo(acroForm, individualInfo);
            pdf.save(FOLDER_NAME + "cr115 result.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private PDAcroForm openPdf() throws IOException {
        pdf = PDDocument.load(new File(FILENAME));

        pdf.setAllSecurityToBeRemoved(true);
        PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
        return documentCatalog.getAcroForm();
    }
    private void fillInfo(PDAcroForm acroForm, DefendantInfo individualInfo) throws IOException {
        PDFieldTree tree = acroForm.getFieldTree();
        for (PDField aTree : tree) {
            System.out.println(aTree);
        }

        //Fill name
        if (individualInfo.getFirstName() != null || individualInfo.getLastName() != null) {
            PDField name = acroForm.getField("TEXT.1.0");
            name.setValue(individualInfo.getFirstName() + " " + individualInfo.getLastName());
            PDField name2 = acroForm.getField("TEXT.1.8");
            name2.setValue(individualInfo.getFirstName() + " " + individualInfo.getLastName());
        }
//        //Fill Case Num
        if (individualInfo.getCaseNumber() != null) {
            PDField name = acroForm.getField("TEXT.1.7");
            name.setValue(individualInfo.getCaseNumber());
        }
        //Fill Address
        if (individualInfo.getAddress() != null) {
            PDField home = acroForm.getField("TEXT.1.2");
            home.setValue(individualInfo.getAddress());
            PDField home2=acroForm.getField("TEXT.1.15");
            home2.setValue(individualInfo.getAddress());
        }
        //Fill City+zip
        if (individualInfo.getCity() != null || individualInfo.getZip() != null) {
            PDField name = acroForm.getField("TEXT.1.4");
            name.setValue(individualInfo.getCity() + " " + individualInfo.getZip());
        }
        //Fill Telephone
        if (individualInfo.getTelephone() != null) {
            PDField phone = acroForm.getField("TEXT.1.16");
            phone.setValue(individualInfo.getTelephone());
        }
    }

}
