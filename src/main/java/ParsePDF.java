import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParsePDF {

  public static void main(String[] args) throws IOException {
    ParsePDF parsePDF = new ParsePDF();
    parsePDF.openPdf("cr180.pdf");
  }

  public void openPdf(String fileName) throws IOException {
    PDDocument pdf = PDDocument.load(new File(fileName));
    setField(pdf, "NAME", "dummy");
  }

  public void setField(PDDocument pdf, String name, String value) throws IOException {
    PDDocumentCatalog documentCatalog = pdf.getDocumentCatalog();
    PDAcroForm acroForm = documentCatalog.getAcroForm();
    PDFieldTree tree = acroForm.getFieldTree();

//    if (field != null) {
//      field.setValue(value);
//    } else {
//      System.out.println("No such field!");
//    }
  }
}
