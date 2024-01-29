package com.example.shopp;


import com.example.shopp.AlertMessage;
import com.example.shopp.Database;
import com.example.shopp.ListData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.shopp.Data.cID;

public class LoginController implements Initializable {

    @FXML
    private DatePicker birth_date;

    @FXML
    private TextField register_fullName;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField admin_cPassword;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Hyperlink admin_signin;

    @FXML
    private Button admin_signupBtn;

    @FXML
    private TextField admin_username;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorLabel1;

    @FXML
    private Label errorLabel11;

    @FXML
    private Label errorLabel111;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField login_password;

    @FXML
    private ComboBox<?> login_role;

    @FXML
    private TextField login_username;

    @FXML
    private PasswordField register_cPassword;

    @FXML
    private AnchorPane register_form;

    @FXML
    private PasswordField register_password;

    @FXML
    private Hyperlink register_signin;

    @FXML
    private Button register_signup;

    @FXML
    private TextField register_username;

    @FXML
    private PasswordField financier_cPassword;

    @FXML
    private PasswordField financier_password;

    @FXML
    private Hyperlink financier_sigin;

    @FXML
    private Button financier_signupBtn;


    @FXML
    private TextField financier_username;

    @FXML
    private AnchorPane financier_form;

    @FXML
    void setButtonLogin(ActionEvent event) {

    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private AlertMessage alert = new AlertMessage();

    public void loginAccount(){

        Data.login_username = login_username.getText();


        if(login_username.getText().isEmpty()
                || login_password.getText().isEmpty())
        {
            alert.errorMessage("Please fill all fields");
        }else{

            String selectData = "SELECT * FROM users WHERE username = ? AND password = ?";
            connect = Database.connectDB();

            String role = "";
            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    role = result.getString("role");

                    System.out.println(role);

                    Thread.sleep(1000);

                    if (role.equals("Admin")) {
                        //ADMIN
                        Parent root = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));

                        Stage stage = new Stage();
                        stage.setTitle("SHOP | Admin Portal");
                        stage.setScene(new Scene(root));

                        stage.show();

                        login_btn.getScene().getWindow().hide(); // login form

                    } else if (role.equals("Register")) {
                        String tempRegisterID = result.getString("register_id");

                        String checkData = "SELECT * FROM register WHERE register_id = '"
                                + tempRegisterID + "'";

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);

                        if (result.next()) {
                                // TO GET THE USERNAME
                                ListData.register_username = login_username.getText();

                                Parent root = FXMLLoader.load(getClass().getResource("RegisterMain.fxml"));
                                Stage stage = new Stage();

                                stage.setTitle("SHOP | Register Portal");
                                stage.setScene(new Scene(root));
                                stage.show();

                                //  HIDE
                                login_btn.getScene().getWindow().hide();

                        }
                    } else if (role.equals("Financier")) {
                        String tempFinancierID = result.getString("fin_id");

                        String checkData = "SELECT * FROM financier WHERE fin_id = '"
                                + tempFinancierID + "'";

                        statement = connect.createStatement();
                        result = statement.executeQuery(checkData);

                        if (result.next()) {

                                // TO GET THE USERNAME
                                ListData.financier_username = login_username.getText();

                                Parent root = FXMLLoader.load(getClass().getResource("FinancierMain.fxml"));
                                Stage stage = new Stage();

                                stage.setTitle("SHOP| Financier Portal");
                                stage.setScene(new Scene(root));
                                stage.show();

                                // TO HIDE YOUR LOGIN FORM
                                login_btn.getScene().getWindow().hide();

                        }

                    }
                }else {
                    alert.errorMessage("Incorrect Username/Password");
                }

            }catch (Exception e){
                e.printStackTrace();
            }



        }

    }


    private void handleException(Exception e) {
        //  show an error message to the user
        e.printStackTrace();
    }

    public void registerAdmin() {

        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty() || admin_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + admin_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(admin_username.getText() + "is already exist");
                } else if (!admin_password.getText().equals(admin_cPassword.getText())) {
                    alert.errorMessage("Password does not match");
                } else if (admin_password.getText().length() < 8)
                {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                }
                else {
                    String insertData = "INSERT INTO users (username, password, role, date) "
                            + "VALUES(?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, admin_username.getText());
                    prepare.setString(2, admin_password.getText());
                    prepare.setString(3, "Admin");
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered successfully!");

                    login_form.setVisible(true);
                    admin_form.setVisible(false);



                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void registerregister() {
        int registerID = registerIDGenerator();
        System.out.println(registerID);

        if (register_username.getText().isEmpty() || register_fullName.getText().isEmpty() ||
                birth_date.getValue() == null || register_password.getText().isEmpty() ||
                register_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            LocalDate dob = birth_date.getValue();
            LocalDate currentDate = LocalDate.now();
            LocalDate minDate = currentDate.minusYears(14);

            if (dob.isAfter(minDate)) {
                alert.errorMessage("Users under 14 years old are not allowed.");
            } else {
                try (Connection connect = Database.connectDB()) {
                    String selectData = "SELECT * FROM users WHERE LOWER(username) = LOWER(?)";
                    try (PreparedStatement checkUser = connect.prepareStatement(selectData)) {
                        checkUser.setString(1, register_username.getText().trim().toLowerCase());
                        try (ResultSet result = checkUser.executeQuery()) {
                            if (result.next()) {
                                alert.errorMessage(register_username.getText() + " is already exist");
                            } else {
                                if (!register_password.getText().equals(register_cPassword.getText())) {
                                    alert.errorMessage("Password does not match");
                                } else if (register_password.getText().length() < 8) {
                                    alert.errorMessage("Invalid password, at least 8 characters needed");
                                } else {
                                    String insertData = "INSERT INTO users (username, password, role, register_id, date) "
                                            + "VALUES(?,?,?,?,?)";

                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                    String getYear = format.format(date);
                                    String registerNum = getYear + "0000";
                                    int sNum = Integer.parseInt(registerNum) + registerID;

                                    try (PreparedStatement prepare = connect.prepareStatement(insertData)) {
                                        prepare.setString(1, register_username.getText());
                                        prepare.setString(2, register_password.getText());
                                        prepare.setString(3, "Register");
                                        prepare.setString(4, String.valueOf(sNum));
                                        prepare.setDate(5, sqlDate);

                                        prepare.executeUpdate();
                                    }

                                    String insertregister = "INSERT INTO register (username, full_name, register_id, birth_date, date_insert)"
                                            + "VALUES(?,?,?,?,?)";

                                    try (PreparedStatement prepareregister = connect.prepareStatement(insertregister)) {
                                        prepareregister.setString(1, register_username.getText());
                                        prepareregister.setString(2, register_fullName.getText());
                                        prepareregister.setString(3, String.valueOf(sNum));
                                        prepareregister.setDate(4, java.sql.Date.valueOf(birth_date.getValue()));
                                        prepareregister.setDate(5, sqlDate);

                                        prepareregister.executeUpdate();
                                    }

                                    alert.successMessage("Registered successfully!");

                                    login_form.setVisible(true);
                                    register_form.setVisible(false);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int registerID;

    public int registerIDGenerator(){

        String selectData = "SELECT MAX(id) FROM register";

        connect = Database.connectDB();

        int temp_registerID = 0;

        try{
            statement= connect.createStatement();
            result= statement.executeQuery(selectData);

            if(result.next()){
                temp_registerID= result.getInt("MAX(id)");

            }
            if(temp_registerID == 0){
                registerID = 1;
            }
            else {
                registerID = temp_registerID+1;

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return registerID;
    }
    public void registerfinancier() {
        if (financier_username.getText().isEmpty() || financier_password.getText().isEmpty() || financier_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM users WHERE username = '"
                    + financier_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.errorMessage(financier_username.getText() + " is already exist");
                } else if (!financier_password.getText().equals(financier_cPassword.getText())) {
                    alert.errorMessage("Password does not match");
                } else if (financier_password.getText().length() < 8) {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                } else {

                    String temp_financierID = "FIN-" + String.valueOf(financierIDGenerator());

                    String insertData = "INSERT INTO users "
                            + "(username, password, role, fin_id, date) "
                            + "VALUES(?,?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, financier_username.getText());
                    prepare.setString(2, financier_password.getText());
                    prepare.setString(3, "Financier");
                    prepare.setString(4, temp_financierID);
                    prepare.setString(5, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    String insertFinancier = "INSERT INTO financier"
                            + " (fin_id,username, date_insert)"
                            + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(insertFinancier);
                    prepare.setString(1, temp_financierID);
                    prepare.setString(2, financier_username.getText());
                    prepare.setString(3, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered successfully!");

                    login_form.setVisible(true);
                    financier_form.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int financierID = 0;

    public int financierIDGenerator() {
        String sql = "SELECT MAX(id) FROM financier";

        connect = Database.connectDB();
        int temp_financierID = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_financierID = result.getInt("MAX(id)");
            }

            if (temp_financierID == 0) {
                financierID = 1;
            } else {
                financierID = temp_financierID + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return financierID;
    }


    public void roleList(){
        List<String> listR= new ArrayList<>();
        for (String data : ListData.role){
            listR.add(data);
        }
        ObservableList listData =FXCollections.observableArrayList(listR);
        login_role.setItems(listData);
    }
    public void signInForm(){
        login_form.setVisible(true);
        admin_form.setVisible(false);
        register_form.setVisible(false);
        financier_form.setVisible(false);


    }

    public void switchForm(ActionEvent event ){ //Kiecial lauka

        Object selectedItem = login_role.getSelectionModel().getSelectedItem();
        if ("Admin".equals(selectedItem)) {
            login_form.setVisible(false);
            admin_form.setVisible(true);
            register_form.setVisible(false);
            financier_form.setVisible(false);

        } else if ("Register".equals(selectedItem)) {
            login_form.setVisible(false);
            admin_form.setVisible(false);
            register_form.setVisible(true);
            financier_form.setVisible(false);
        } else if ("Financier".equals(selectedItem)) {
            login_form.setVisible(false);
            admin_form.setVisible(false);
            register_form.setVisible(false);
            financier_form.setVisible(true);
        }
    }


    @FXML
    private Hyperlink anonymous_Hyperlink;
    @FXML
    protected void handleAnonymousLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Anonymous.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing RegisterMainController...");
        roleList();
    }

}