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

public class Pos040Filler implements AbstractPdfFiller {
    public static final String FILENAME = "pos040.pdf";
    private PDDocument pdf;

    @Override
    public void fillForm(DefendantInfo individualInfo) {
        try {
            PDAcroForm acroForm = openPdf();
            fillInfo(acroForm, individualInfo);
            pdf.save(FOLDER_NAME + "pos040 result.pdf");
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
            PDField name = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].AttyName_ft[0]");
            name.setValue(individualInfo.getFirstName() + " " + individualInfo.getLastName());
        }
        //Fill Case Num
        if (individualInfo.getCaseNumber() != null) {
            PDField caseNum = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].CaseNumber[0].CaseNumber_ft[0]");
            caseNum.setValue(individualInfo.getCaseNumber());
        }
        //Fill Address
        if (individualInfo.getAddress() != null) {
            PDField street = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].AttyStreet_ft[0]");
            street.setValue(individualInfo.getAddress());
        }
        //Fill City
        if (individualInfo.getCity() != null) {
            PDField city = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].AttyCity_ft[0]");
            city.setValue(individualInfo.getCity());
        }
        //Fill State
        if (individualInfo.getState() != null) {
            PDField state = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].AttyState_ft[0]");
            state.setValue(individualInfo.getState());
        }
        //Fill Zip
        if (individualInfo.getZip() != null) {
            PDField zip = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].AttyZip_ft[0]");
            zip.setValue(individualInfo.getZip());
        }
        //Fill phone
        if (individualInfo.getTelephone() != null) {
            PDField phone = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].Phone_ft[0]");
            phone.setValue(individualInfo.getTelephone());
        }
        //Fill email
        if (individualInfo.getEmail() != null) {
            PDField email = acroForm.getField("topmostSubform[0].Page1[0].StdP1Header_sf[0].AttyInfo[0].Email_ft[0]");
            email.setValue(individualInfo.getEmail());
        }

    }
}
