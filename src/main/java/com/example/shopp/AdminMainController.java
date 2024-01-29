package com.example.shopp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import java.util.List;

import static com.example.shopp.Data.cID;

public class AdminMainController implements Initializable {


    @FXML
    private Button addregister_btn;

    @FXML
    private TableColumn<RegisterData, String> addregister_fullname;

    @FXML
    private TableColumn<RegisterData, String> addregister_dateInsert;

    @FXML
    private Button addregister_deleteBtn;

    @FXML
    private AnchorPane addregister_form;

    @FXML
    private TableColumn<RegisterData, String> addregister_name;


    @FXML
    private TableColumn<RegisterData, String> addregister_registerNumber;

    @FXML
    private TableView<RegisterData> addregister_tableView;

    @FXML
    private Button addregister_updateBtn;


    @FXML
    private Button addfinancier_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Label greet_username;

    ///financier


    @FXML
    private TableColumn<FinancierData, String> addfinancier_col_dateInsert;


    @FXML
    private TableColumn<FinancierData, String> addfinancier_col_name;

    @FXML
    private TableColumn<FinancierData, String> addfinancier_col_financierID;


    @FXML
    private Button addfinancier_deleteBtn;

    @FXML
    private TextField addfinancier_username;


    @FXML
    private TableView<FinancierData> addfinancier_tableView;

    @FXML
    private TextField addfinancier_financierID;

    @FXML
    private Button addfinancier_updateBtn;

    @FXML
    private AnchorPane addfinancier_form;

    //dashboard
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private Label dashboard_TS;

    @FXML
    private Label dashboard_TT;
    //password
    @FXML
    private PasswordField np_confirmPassword;

    @FXML
    private PasswordField np_newPassword;
    @FXML
    private PasswordField cp_Password;
    @FXML
    private Button changePassBtn;
    @FXML
    private TextField fp_username;
    @FXML
    private Button addchange_btn;
    @FXML
    private AnchorPane password_form;
    //invenory

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private TableColumn<ProductData, String> inventory_col_date;

    @FXML
    private TableColumn<ProductData, String> inventory_col_price;

    @FXML
    private TableColumn<ProductData, String> inventory_col_productID;

    @FXML
    private TableColumn<ProductData, String> inventory_col_productName;

    @FXML
    private TableColumn<ProductData, String> inventory_col_status;

    @FXML
    private TableColumn<ProductData, String> inventory_col_stock;

    @FXML
    private TableColumn<ProductData, String> inventory_col_type;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private TableView<ProductData> inventory_tableView;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField inventory_price;

    @FXML
    private TextField inventory_productID;

    @FXML
    private TextField inventory_productName;

    @FXML
    private ComboBox<?> inventory_status;

    @FXML
    private TextField inventory_stock;

    @FXML
    private ComboBox<?> inventory_type;

    //Menu

    @FXML
    private Button menu_btn;

    @FXML
    private TableColumn<ProductData, String> menu_col_price;

    @FXML
    private TableColumn<ProductData, String> menu_col_productName;

    @FXML
    private TableColumn<ProductData, String> menu_col_quantity;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_payBtn;

    @FXML
    private Button menu_receiptBtn;

    @FXML
    private Button menu_removeBtn;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private TableView<ProductData> menu_tableView;

    @FXML
    private Label menu_total;
    //////////customer

    @FXML
    private Button customers_btn;

    @FXML
    private TableColumn<CustomersData, Integer> customers_col_customerID;

    @FXML
    private TableColumn<CustomersData, Double> customers_col_total;

    @FXML
    private TableColumn<CustomersData, Date> customers_col_date;

    @FXML
    private TableColumn<CustomersData, String> customers_col_cashier;


    @FXML
    private AnchorPane customers_form;

    @FXML
    protected TableView<CustomersData> customers_tableView;//for register

    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();

    public void dashboardDisplayTS() {
        String sql = "SELECT COUNT(id) FROM register WHERE date_delete IS NULL";
        connect = Database.connectDB();
        int tempTS = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTS = result.getInt("COUNT(id)");
            }
            dashboard_TS.setText("" + tempTS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dashboardDisplayTT() {
        String sql = "SELECT COUNT(id) FROM financier WHERE date_delete IS NULL";
        connect = Database.connectDB();
        int tempTT = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTT = result.getInt("COUNT(id)");
            }
            dashboard_TT.setText(String.valueOf(tempTT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassBtn() {
        if (np_newPassword.getText().isEmpty() || np_confirmPassword.getText().isEmpty() || cp_Password.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
            return;
        }

        connect = Database.connectDB();

        try {
            // getpassword
            String getCurrentPasswordQuery = "SELECT password FROM users WHERE username = ?";
            prepare = connect.prepareStatement(getCurrentPasswordQuery);
            prepare.setString(1, fp_username.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                String currentPasswordInDatabase = result.getString("password");

                // ==
                if (cp_Password.getText().equals(currentPasswordInDatabase)) {


                    if (np_newPassword.getText().equals(np_confirmPassword.getText())) {

                        // update
                        String updatePass = "UPDATE users SET password = ? WHERE username = ?";
                        prepare = connect.prepareStatement(updatePass);
                        prepare.setString(1, np_newPassword.getText());
                        prepare.setString(2, fp_username.getText());
                        prepare.executeUpdate();

                        alert.successMessage("Successfully changed Password!");

                        np_confirmPassword.setText("");
                        np_newPassword.setText("");
                        fp_username.setText("");
                        cp_Password.setText("");

                    } else {
                        alert.errorMessage("New Password and Confirm Password do not match");
                    }
                } else {
                    alert.errorMessage("Incorrect current password");
                }
            } else {
                alert.errorMessage("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public ObservableList<RegisterData> addregisterGetData() {

        ObservableList<RegisterData> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM register WHERE date_delete IS NULL ";

        connect = Database.connectDB();

        RegisterData sData;

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {

                sData = new RegisterData(result.getInt("id"), result.getString("register_id")
                        , result.getString("username"), result.getString("full_name")
                        , result.getDate("date_insert"));
                listData.add(sData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<RegisterData> addregisterListData;

    public void addregisterDisplayData() {


        addregisterListData = addregisterGetData();
        addregister_registerNumber.setCellValueFactory(new PropertyValueFactory<>("registerID"));
        addregister_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        addregister_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        addregister_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));

        addregister_tableView.setItems(addregisterListData);

    }

    public void addregisterUpdateBtn() {

        RegisterData sData = addregister_tableView.getSelectionModel().getSelectedItem();
        int num = addregister_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            alert.errorMessage("Please select the item first");
            return;
        } else {

            ListData.temp_registerNumber = sData.getRegisterID();
            ListData.temp_registerName = sData.getUsername();
            ListData.temp_registerBirthDate = sData.getBirthDate();


            try {
                Parent root = FXMLLoader.load(getClass().getResource("Addregister.fxml"));
                Stage stage = new Stage();


                stage.setTitle("Update register");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setAddregisterDeleteBtn() {

        RegisterData sData = addregister_tableView.getSelectionModel().getSelectedItem();
        int num = addregister_tableView.getSelectionModel().getFocusedIndex();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if ((num - 1) < -1) {
            alert.errorMessage("Please select item first");
            return;
        } else {
            if (alert.confirmMessage("Are you sure u want to Delete register ID: "
                    + sData.getRegisterID() + "?")) {
                String deleteData = "UPDATE register SET date_delete = ? WHERE register_id = ?";
                connect = Database.connectDB();


                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, sData.getRegisterID());
                    prepare.executeUpdate();
                    alert.successMessage("Deleted successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
        addregisterDisplayData();
    }

    public ObservableList<FinancierData> addfinancierGetData() {

        ObservableList<FinancierData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM financier WHERE date_delete IS NULL";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            FinancierData tData;
            while (result.next()) {

                tData = new FinancierData(result.getInt("id"), result.getString("fin_id")
                        , result.getString("username")
                        , result.getDate("date_insert"), result.getDate("date_update")
                        , result.getDate("date_delete"));
                listData.add(tData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<FinancierData> addfinancierListData;

    public void addfinancierDisplayData() {
        addfinancierListData = addfinancierGetData();

        addfinancier_col_financierID.setCellValueFactory(new PropertyValueFactory<>("financierID"));
        addfinancier_col_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        addfinancier_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("dateInsert"));


        addfinancier_tableView.setItems(addfinancierListData);
    }

    public void addfinancierSelectItems() {
        FinancierData tData = addfinancier_tableView.getSelectionModel().getSelectedItem();
        int num = addregister_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) return;
        addfinancier_financierID.setText(tData.getFinancierID());
        addfinancier_username.setText(tData.getUsername());


    }


    private String financierID;

    public void generatefinancierID() {

        String sql = "SELECT MAX(id) FROM financier";

        connect = Database.connectDB();
        String temp_financierID = "TID-";
        int temp_count = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_count = result.getInt("MAX(id)");
            }

            if (temp_count == 0) {
                temp_count += 1;
                financierID = temp_financierID + temp_count;
            } else {
                financierID = temp_financierID + (temp_count + 1);
            }


        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void addfinancierDisplayfinancierID() {
        generatefinancierID();
        addfinancier_financierID.setText(financierID);
    }


    public void setAddfinancierDeleteBtn() {


        FinancierData sData = addfinancier_tableView.getSelectionModel().getSelectedItem();
        int num = addfinancier_tableView.getSelectionModel().getFocusedIndex();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        if ((num - 1) < -1) {
            alert.errorMessage("Please select item first");
            return;
        } else {
            if (alert.confirmMessage("Are you sure u want to Delete financier ID: "
                    + sData.getFinancierID() + "?")) {
                String deleteData = "UPDATE financier SET date_delete = ? WHERE fin_id = ?";
                connect = Database.connectDB();


                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setString(2, sData.getFinancierID());
                    prepare.executeUpdate();
                    alert.successMessage("Deleted successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
        addfinancierDisplayData();
    }
    public void inventorySelectData() {

        ProductData prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        inventory_productID.setText(prodData.getProductId());
        inventory_productName.setText(prodData.getProductName());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrice()));
   

        Data.path = prodData.getImage();

        String path = "File:" + prodData.getImage();
        Data.date = String.valueOf(prodData.getDate());
        Data.id = prodData.getId();

        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }
    private String[] typeList = {"Phones", "Sport items"};

    public void inventoryTypeList() {

        List<String> typeL = new ArrayList<>();

        for (String data : typeList) {
            typeL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(typeL);
        inventory_type.setItems(listData);
    }

    private String[] statusList = {"Available", "Unavailable"};

    public void inventoryStatusList() {

        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusL);
        inventory_status.setItems(listData);

    }



    public ObservableList<ProductData> inventoryDataList() {

        ObservableList<ProductData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prodData;

            while (result.next()) {

                prodData = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(prodData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<ProductData> inventoryListData;

    //add
    public void inventoryAddBtn() {

        if (inventory_productID.getText().isEmpty()
                || inventory_productName.getText().isEmpty()
                || inventory_type.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || Data.path == null) {

            alert.errorMessage("Please fill all blank fields");

        } else {

            // CHECK PRODUCT ID
            String checkProdID = "SELECT prod_id FROM product WHERE prod_id = '"
                    + inventory_productID.getText() + "'";

            connect = Database.connectDB();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next()) {
                    alert.errorMessage(inventory_productID.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO product "
                            + "(prod_id, prod_name, type, stock, price, status, image, date) "
                            + "VALUES(?,?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, inventory_productID.getText());
                    prepare.setString(2, inventory_productName.getText());
                    prepare.setString(3, (String) inventory_type.getSelectionModel().getSelectedItem());
                    prepare.setString(4, inventory_stock.getText());
                    prepare.setString(5, inventory_price.getText());
                    prepare.setString(6, (String) inventory_status.getSelectionModel().getSelectedItem());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(7, path);

                    // TO GET CURRENT DATE
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Successfully Added!");

                    inventoryShowData();
                    inventoryClearBtn();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryClearBtn() {

        inventory_productID.setText("");
        inventory_productName.setText("");
        inventory_type.getSelectionModel().clearSelection();
        inventory_stock.setText("");
        inventory_price.setText("");
        inventory_status.getSelectionModel().clearSelection();
        Data.path = "";
        Data.id = 0;
        inventory_imageView.setImage(null);

    }

    public void inventoryUpdateBtn() {

        if (inventory_productID.getText().isEmpty()
                || inventory_productName.getText().isEmpty()
                || inventory_type.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || Data.path == null || Data.id == 0) {

            alert.errorMessage("Please fill all blank fields");

        } else {

            String path = Data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE product SET "
                    + "prod_id = '" + inventory_productID.getText() + "', prod_name = '"
                    + inventory_productName.getText() + "', type = '"
                    + inventory_type.getSelectionModel().getSelectedItem() + "', stock = '"
                    + inventory_stock.getText() + "', price = '"
                    + inventory_price.getText() + "', status = '"
                    + inventory_status.getSelectionModel().getSelectedItem() + "', image = '"
                    + path + "', date = '"
                    + Data.date + "' WHERE id = " + Data.id;

            connect = Database.connectDB();

            try {


                if (alert.confirmMessage("Are you sure you want to UPDATE PRoduct ID: " + inventory_productID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert.successMessage("Successfully Updated!");


                    inventoryShowData();

                    inventoryClearBtn();
                } else {
                    alert.errorMessage("Cancelled");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryDeleteBtn() {

        Integer id = com.example.shopp.Data.id;
        if (id != null) {
            int idValue = id.intValue();  // Integer -> int

        } else {

            alert.errorMessage("ID is null. Please select a product.");
            return;  // exit
        }


        if (Data.id == 0) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            if (alert.confirmMessage("Are you sure you want to DELETE Product ID: " + inventory_productID.getText() + "?")) {
                String deleteData = "DELETE FROM product WHERE id = " + Data.id;
                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert.errorMessage("Successfully Deleted!");


                    inventoryShowData();

                    inventoryClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();

                    alert.errorMessage("Error while deleting the product.");
                }
            } else {
                alert.errorMessage("Cancelled");
            }
        }
    }

    public void inventoryShowData() {
        inventoryListData = inventoryDataList();

        inventory_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        inventory_tableView.setItems(inventoryListData);

    }

    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg", "*jpeg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }

    private Image image;
///////////////////////////////////////////////////////////////////////////////////////////
    public ObservableList<ProductData> menuGetData() {

        String sql = "SELECT * FROM product";

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while (result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    public void menuDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int i = 0; i < cardListData.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("CardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();

                // AdminMainController
                cardC.setAdminMainController(this);

                cardC.setData(cardListData.get(i));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void customerID() {

        String sql = "SELECT MAX(customer_id) FROM customer";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }

            Data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductData> menuGetOrder() {
        customerID();
        ObservableList<ProductData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while (result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<ProductData> menuOrderListData;

    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();

        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        menu_tableView.setItems(menuOrderListData);
    }
    private int getid;

    public void menuSelectOrder() {
        ProductData prod = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId();

    }

    private double totalP;

    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + cID;

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("€" + totalP);
    }
    public void menuPayBtn() {

            menuGetTotal();
            String insertPay = "INSERT INTO receipt (customer_id, total, date, em_username) "
                    + "VALUES(?,?,?,?)";

            connect = Database.connectDB();

            try {
                    boolean confirmed = alert.confirmMessage("Are you sure?");

                    if (confirmed) {
                        menuGetTotal();
                        prepare = connect.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(cID));
                        prepare.setString(2, String.valueOf(totalP));

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        prepare.setString(3, String.valueOf(sqlDate));
                        prepare.setString(4, Data.login_username);

                        prepare.executeUpdate();

                        alert.successMessage("Successful.");

                        menuShowOrderData();
                    } else {
                        alert.errorMessage("Cancelled.");
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    public void menuRemoveBtn() {
        if (getid == 0) {
            alert.errorMessage("Please select the order you want to remove");
        } else {
            String deleteData = "DELETE FROM customer WHERE id = " + getid;
            connect = Database.connectDB();
            try {

                if (alert.confirmMessage("Are you sure you want to delete this order?")) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();


                    menuShowOrderData();
                    menuDisplayCard();
                    menuDisplayTotal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void menuRestart() {
        totalP = 0;
        menu_total.setText("€0.0");
    }

    public ObservableList<CustomersData> CustomersDataList() {

        ObservableList<CustomersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomersData cData;

            while (result.next()) {
                cData = new CustomersData(result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("em_username"));

                listData.add(cData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<CustomersData> customersListData;
    // metodas to table
    public TableView<CustomersData> getCustomersTableView() {
        return customers_tableView;}
    public void customersShowData() {
        customersListData = CustomersDataList();

        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));

        customers_tableView.setItems(customersListData);
    }

    public void updateMenuData() {
        menuDisplayCard();
        menuDisplayTotal();
        menuShowOrderData();
    }


@FXML
private Button exportToJsonButton;


    @FXML
    void exportToJson() {
        //is duomenu
        ObservableList<CustomersData> dataList = CustomersDataList();

        // sauga
        String filePath = "C:\\Users\\aleks\\Desktop\\exportToJson\\exportedData.json";

        // metod
        JsonExporter.exportToJson(dataList, filePath);

        // create
        File file = new File(filePath);

        // deriktorii
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            // create
            if (file.createNewFile()) {
                System.out.println("failas sukurtas " + filePath);
            } else {
                System.out.println("failas jau yra " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchForm(ActionEvent event) {


        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(false);
            password_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);


        } else if (event.getSource() == addregister_btn) {
            dashboard_form.setVisible(false);
            addregister_form.setVisible(true);
            addfinancier_form.setVisible(false);
            password_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);


            addregisterDisplayData();
        } else if (event.getSource() == addfinancier_btn) {
            dashboard_form.setVisible(false);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(true);
            password_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

            addfinancierDisplayData();
        } else if (event.getSource() == addchange_btn) {
            dashboard_form.setVisible(false);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(false);
            password_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(false);
            password_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

            inventoryShowData();
        } else  if (event.getSource() ==menu_btn){
            dashboard_form.setVisible(false);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(false);
            password_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
            customers_form.setVisible(false);


            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();
        } else if (event.getSource()==customers_btn){
            dashboard_form.setVisible(false);
            addregister_form.setVisible(false);
            addfinancier_form.setVisible(false);
            password_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(true);

            customersShowData();
        }


    }
    @FXML
    void handleInventoryBtn(ActionEvent event) {

        inventoryShowData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardDisplayTS();
        dashboardDisplayTT();

        inventoryStatusList();
        inventoryTypeList();
        menuDisplayCard();
        menuGetOrder();
        inventoryShowData();

        menuGetOrder();
        menuShowOrderData();

        customersShowData();


        menuDisplayTotal();

    }

}