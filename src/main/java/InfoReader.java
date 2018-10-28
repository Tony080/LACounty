import fillers.AbstractPdfFiller;
import object.DefendantInfo;

import java.awt.*;
import java.io.File;
import javax.swing.*;


public class InfoReader {

    JFrame frame;
    JLabel firstName, lastName, caseNumber, address, city, state, zip, telephone, email, birthday;
    JPanel buttonPane, fieldsPanel;

    JTextField firstNameField, lastNameField, caseNumberField, addressField, cityField,
                stateField, zipField, telephoneField, emailField, birthdayField;

    JButton okButton, clearButton;

    public static void main(String args[]) {
        File outputDir = new File(AbstractPdfFiller.FOLDER_NAME.substring(0, AbstractPdfFiller.FOLDER_NAME.length() - 1));
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
        new InfoReader();
    }

    InfoReader() {
        frame = new JFrame("Client Information");

        buttonPane = new JPanel();
        fieldsPanel = new JPanel();

        firstName = new JLabel("First Name: ");
        lastName = new JLabel("Last Name: ");
        caseNumber = new JLabel("Case Number: ");
        address = new JLabel("Address: ");
        city = new JLabel("City: ");
        state = new JLabel("State: ");
        zip = new JLabel("Zip: ");
        telephone = new JLabel("Telephone: ");
        email = new JLabel("Email: ");
        birthday = new JLabel("Birthday: ");

        firstNameField = new JTextField("");
        lastNameField = new JTextField("");
        caseNumberField = new JTextField("");
        addressField = new JTextField("");
        cityField = new JTextField("");
        stateField = new JTextField("");
        zipField = new JTextField("");
        telephoneField = new JTextField("");
        emailField = new JTextField("");
        birthdayField = new JTextField("");

        okButton = new JButton("OK");
        clearButton = new JButton("CLEAR");

        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPane.setLayout(new FlowLayout());

        fieldsPanel.add(firstName);
        fieldsPanel.add(firstNameField);
        fieldsPanel.add(lastName);
        fieldsPanel.add(lastNameField);
        fieldsPanel.add(caseNumber);
        fieldsPanel.add(caseNumberField);
        fieldsPanel.add(address);
        fieldsPanel.add(addressField);
        fieldsPanel.add(city);
        fieldsPanel.add(cityField);
        fieldsPanel.add(state);
        fieldsPanel.add(stateField);
        fieldsPanel.add(zip);
        fieldsPanel.add(zipField);
        fieldsPanel.add(telephone);
        fieldsPanel.add(telephoneField);
        fieldsPanel.add(email);
        fieldsPanel.add(emailField);
        fieldsPanel.add(birthday);
        fieldsPanel.add(birthdayField);

        buttonPane.add(okButton);
        buttonPane.add(clearButton);

        frame.setPreferredSize(new Dimension(800, 500));
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPane, BorderLayout.PAGE_END);

        // click OK to create a DefendantInfo object
        okButton.addActionListener((e) -> {
            DefendantInfo info = getDefendantInfo(firstNameField.getText(), lastNameField.getText(), caseNumberField.getText(),
                    addressField.getText(), cityField.getText(), stateField.getText(), zipField.getText(),
                    telephoneField.getText(), emailField.getText(), birthdayField.getText());
//            System.out.println(info.toString());
            for (AbstractPdfFiller filler : Driver.initializePdfFillers()) {
                filler.fillForm(info);
            }
            JOptionPane.showMessageDialog( null, "Your information are all recorded in PDFs!", "Congradulations", JOptionPane.INFORMATION_MESSAGE);

        });

        // click CLEAR to clear all textfields
        clearButton.addActionListener((e) -> {
            firstNameField.setText("");
            lastNameField.setText("");
            caseNumberField.setText("");
            addressField.setText("");
            cityField.setText("");
            stateField.setText("");
            zipField.setText("");
            telephoneField.setText("");
            emailField.setText("");
            birthdayField.setText("");
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private static DefendantInfo getDefendantInfo (String firstName, String lastName, String caseNumber, String address, String city,
                                                   String state, String zip, String telephone, String email, String birthday) {
        DefendantInfo info = new DefendantInfo();

        info.setFirstName(firstName);
        info.setLastName(lastName);
        info.setCaseNumber(caseNumber);
        info.setAddress(address);
        info.setCity(city);
        info.setState(state);
        info.setZip(zip);
        info.setTelephone(telephone);
        info.setEmail(email);
        info.setBirthday(birthday);

        return info;
    };
}
