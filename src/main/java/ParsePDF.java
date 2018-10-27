import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;

public class ParsePDF {

  public static void main(String[] args) throws IOException {
    ParsePDF parsePDF = new ParsePDF();
    parsePDF.openPdf("cr180.pdf");
  }

  public void openPdf(String fileName) throws IOException {
    PDDocument pdf = PDDocument.load(new File(fileName));
    setField(pdf, "Name", "dummy");
  }

  public void setField(PDDocument pdf, String name, String value) throws IOException {
    PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
    PDAcroForm acroForm = documentCatalog.getAcroForm();
    PDField field = acroForm.getField(name);
    if (field != null) {
      field.setValue(value);
    } else {
      System.out.println("No such field!");
    }
  }
}
