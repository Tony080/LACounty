package fillers.implementations;

import fillers.AbstractPdfFiller;
import object.DefendantInfo;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;

public class FW003Filler implements AbstractPdfFiller {
    @Override
    public void fillForm(DefendantInfo info) {
        if (info == null) {
            System.out.println("Invalid info!");
        }

        try {
            // read PDF
            PDDocument pdf = PDDocument.load(new File("fw003.pdf"));



            // fill info
            // name
            setField(pdf, "topmostSubform[0].Page1[0].PersonWaivingName_ft[0]", info.getFirstName() + " " + info.getLastName());
            // caseNumber
            setField(pdf, "topmostSubform[0].Page1[0].Stamp_court_case[0].CaseNumber_ft[0]", info.getCaseNumber());
            // address
            setField(pdf, "topmostSubform[0].Page1[0].FillText23[0]", "add");
            // city
            setField(pdf, "topmostSubform[0].Page1[0].FillText21[0]", info.getCity());
            // state
            setField(pdf, "topmostSubform[0].Page1[0].FillText20[0]", info.getState());
            // zip
            setField(pdf, "topmostSubform[0].Page1[0].FillText22[0]", info.getZip());


            pdf.setAllSecurityToBeRemoved(true);
            pdf.save(FOLDER_NAME + "fw003 result.pdf");
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
//
//    public static void main(String[] args) throws IOException {
//        FW003Filler obj = new FW003Filler();
//        obj.fillForm(null);
//    }
}
