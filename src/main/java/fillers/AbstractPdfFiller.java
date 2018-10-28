package fillers;

import object.DefendantInfo;

public interface AbstractPdfFiller {
  String FOLDER_NAME = "outputs/";
  void fillForm(DefendantInfo individualInfo);
}
