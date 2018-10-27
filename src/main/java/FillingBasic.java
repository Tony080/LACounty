import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;
/**
 * @author: Vivian Xu
 */
public class FillingBasic implements AbstractPdfFiller {

    protected String FILENAME;

        @Override
        public void fillForm(DefendantInfo individualInfo) {
            // filling pdf
            try {
            PDDocument pdf = PDDocument.load(new File(FILENAME));
                pdf.setAllSecurityToBeRemoved(true);
                PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
                PDAcroForm acroForm = documentCatalog.getAcroForm();
                //Acquire all possible names
                PDFieldTree tree = acroForm.getFieldTree();
                //Firm name
                PDField firmName = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyFirm_ft[0]");
                //firmName.setValue(individualInfo.getF());

                PDField name = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyName_ft[0]");
                //name.setValue(individualInfo.getFirstName() + " " +  individualInfo.getLastName());
                if (name != null) {
                    name.setValue(individualInfo.getFirstName() + " " +  individualInfo.getLastName());
                } else {
                    System.out.println("No such field : "+ name);
                }

                //Case number
                PDField caseNum = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].CaseNumber[0].CaseNumber_ft[0]");
                if (caseNum != null) {
                    caseNum.setValue(individualInfo.getCaseNumber());
                } else {
                    System.out.println("No such field : "+ caseNum);
                }

                // Street Address
                PDField streetAddress = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyStreet_ft[0]");
                if (streetAddress != null) {
                    streetAddress.setValue(individualInfo.getAddress());
                } else {
                    System.out.println("No such field : "+ streetAddress);
                }

                // Phone Number
                PDField phoneNumber = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].Phone_ft[0]");
                if (phoneNumber != null) {
                    phoneNumber.setValue(individualInfo.getTelephone());
                } else {
                    System.out.println("No such field : "+ phoneNumber);
                }

                // Email
                PDField email = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].Email_ft[0]");
                if (email != null) {
                    email.setValue(individualInfo.getEmail());
                } else {
                    System.out.println("No such field : "+ email);
                }

                // City
                PDField city = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyCity_ft[0]");
                if (email != null) {
                    city.setValue(individualInfo.getCity());
                } else {
                    System.out.println("No such field : "+ city);
                }

                // State
                PDField state = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyState_ft[0]");
                if (email != null) {
                    state.setValue(individualInfo.getState());
                } else {
                    System.out.println("No such field : "+ state);
                }

                // Zip
                PDField zip = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyZip_ft[0]");
                if (zip != null) {
                    zip.setValue(individualInfo.getZip());
                } else {
                    System.out.println("No such field : "+ zip);
                }
                pdf.save(FILENAME + "_" + individualInfo.getLastName() + "_" + individualInfo.getCaseNumber());
                pdf.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
}
