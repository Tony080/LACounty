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
            String fullName = individualInfo.getFirstName() + " " + individualInfo.getLastName();
            PDField name1 = acroForm.getField("TEXT.1.6");
            name1.setValue(fullName);
            PDField name2 = acroForm.getField("TEXT.1.8");
            name2.setValue(fullName);
        }
//        //Fill Case Num
        if (individualInfo.getCaseNumber() != null) {
            PDField caseNum = acroForm.getField("TEXT.1.7");
            caseNum.setValue(individualInfo.getCaseNumber());
        }
        //Fill Address
        if (individualInfo.getAddress() != null) {
            PDField home = acroForm.getField("TEXT.1.15");
            home.setValue(individualInfo.getAddress() + ", " + individualInfo.getCity() + ", " + individualInfo.getZip());
        }

        if (individualInfo.getBirthday() != null) {
            PDField name = acroForm.getField("TEXT.1.10");
            name.setValue(individualInfo.getBirthday());
        }
        //Fill Telephone
        if (individualInfo.getTelephone() != null) {
            PDField phone = acroForm.getField("TEXT.1.16");
            phone.setValue(individualInfo.getTelephone());
        }
    }

}
