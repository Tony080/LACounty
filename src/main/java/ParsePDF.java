import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;

public class ParsePDF {

  public static void main(String[] args) throws IOException {
    ParsePDF parsePDF = new ParsePDF();
    parsePDF.openPdf("cr180.pdf");
  }

  public void openPdf(String fileName) throws IOException {
    PDDocument pdf = PDDocument.load(new File(fileName));
    setField(pdf, "", "dummy");
    pdf.setAllSecurityToBeRemoved(true);
    pdf.save("cr180 result.pdf");
  }

  public void setField(PDDocument pdf, String name, String value) throws IOException {
    PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
    PDAcroForm acroForm = documentCatalog.getAcroForm();
    //Acquire all possible names
    PDFieldTree tree = acroForm.getFieldTree();
    for (PDField aTree : tree) {
      System.out.println(aTree);
    }
    //Set specified field with given name
    //Firm name
    PDField field = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].AttyInfo[0].AttyFirm_ft[0]");
    //Case number
    PDField field2 = acroForm.getField("topmostSubform[0].Page1[0].Caption_sf[0].CaseNumber[0].CaseNumber_ft[0]");
    field2.setValue("99998");

    if (field != null) {
      field.setValue(value);
    } else {
      System.out.println("No such field!");
    }
  }
}
