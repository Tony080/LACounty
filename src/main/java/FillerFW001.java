import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;

public class FillerFW001 implements AbstractPdfFiller {
    @Override
    public void fillForm(DefendantInfo info) {
        if (info == null) {
            System.out.println("Invalid info!");
        }

        try {
            // read PDF
            PDDocument pdf = PDDocument.load(new File("fw001.pdf"));

            // fill info

            // name
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerName_ft[0]", info.getFirstName() + " " + info.getLastName());
            // caseNumber
            setField(pdf, "topmostSubform[0].Page1[0].RightCaption_sf[0].CaseNumber_ft[0]", info.getCaseNumber());
            // address
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerStrAddress_ft[0]", info.getAddress());
            // city
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerCity_ft[0]", info.getCity());
            // state
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerState_ft[0]", info.getState());
            // zip
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerZip_ft[0]", info.getZip());
            // telephone
            setField(pdf, "topmostSubform[0].Page1[0].PetitionerTel_ft[0]", info.getTelephone());


            pdf.setAllSecurityToBeRemoved(true);
            pdf.save("fw001 result.pdf");
            pdf.close();

            // save PDF to folder
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setField(PDDocument pdf, String name, String value) throws IOException {
        PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
        PDAcroForm acroForm = documentCatalog.getAcroForm();
        //Acquire all possible names
//        PDFieldTree tree = acroForm.getFieldTree();
//        for (PDField aTree : tree) {
//            System.out.println(aTree);
//        }

        PDField field = acroForm.getField(name);
        field.setValue(value);
    }

    public static void main(String[] args) throws IOException {
//        FillerFW001 obj = new FillerFW001();
//        obj.fillForm(null);
    }
}
