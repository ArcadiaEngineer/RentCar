package GUI;


import Business.RentCarSystem;
import Entities.Abstract.User;
import Entities.Concrete.Car;
import Entities.Concrete.Customer;
import Entities.Concrete.Gallery;
import Entities.Concrete.GalleryOwner;
import Entities.Concrete.Mail;
import Entities.Concrete.Order;
import Entities.Concrete.PromotionCode;
import Entities.Concrete.Visitor;
import Entities.Interface.Registerable;
import Helper.HelperMethods;
import Helper.MyCellRenderer;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Asus
 */
public final class CustomerWindow extends javax.swing.JFrame {

    Customer customer;
    Visitor visitor;
    
    Car currentCar;
    int currentCarIndex = 0;
    Order currentOrder;
    int currentOrderIndex = 0;
    
    boolean isCarsFiltered = false;
    boolean isOrdersFiltered = false;
    boolean isChecked = false;
    
    private String profPictPath;
    
    public CustomerWindow(Customer customer) {
        this.customer = customer;
        RentCarSystem.getOrdersFromDatabase(customer.getCustomerId());
        initComponents();
        initializeFields();
        designColorsAndComponents();
    }
    
    public CustomerWindow(Registerable visitor) {
        this.visitor = (Visitor) visitor;
        initComponents();
        setCarInformation( RentCarSystem.getCars().get( 0 ) );
        lblProfilePic.setIcon( new ImageIcon(getClass().getResource( "/images/visitor125.png" )));
        lblTheUserName.setText(((Visitor)visitor).getFullName() );
        designColorsAndComponents();
        pnlProfile.setVisible( false );
        pnlLayeredProfile.setVisible( false );
        lblCurrentCash.setVisible( false );
        lblTotalPayment1.setVisible( false );
        lblTotalCashAfterRental.setVisible( false );
        
        lblRent.addMouseListener( new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                visitorNotPermitedAction();
            }
            
        });
        
        btnApprove.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visitorNotPermitedAction();
            }
            
        });
        
        //tableCar.getTableHeader().setDefaultRenderer( new Helper.HeaderColor());
        //tableCar.setDefaultRenderer(Object.class, centerRenderer);

    }
    
    public void designColorsAndComponents() {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        centerRenderer.setBackground( new Color(51, 51, 51));
        
        pick_upDate_JDatechooser.setLocale(Locale.ENGLISH);
        pick_upDate_JDatechooser.setBackground( new Color(51, 51, 51));
        pick_upDate_JDatechooser.getCalendarButton().setBackground( new Color(51, 51, 51));
        pick_upDate_JDatechooser.setSelectableDateRange(new Date(), new Date(new Date().getTime() + 2_592_000_000L));
        
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setBackground( new Color(51, 51, 51));
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setBorder( new CompoundBorder(new LineBorder(new Color(122, 72, 255), 1, true), new EmptyBorder(1, 4, 1, 1) ));
        
        returnDate_JDatechooser.setLocale(Locale.ENGLISH);
        
        returnDate_JDatechooser.setBackground( new Color(51, 51, 51));
        returnDate_JDatechooser.getCalendarButton().setBackground( new Color(51, 51, 51));
        returnDate_JDatechooser.getCalendarButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date start = pick_upDate_JDatechooser.getDate();
                try {
                    returnDate_JDatechooser.setSelectableDateRange(new Date(start.getTime() + 86_400_000L), new Date(start.getTime() + 2_592_000_000L));
                } catch( NullPointerException ex ) {
                    HelperMethods.showErrorMessage("You must choose first Pick-Up Date", "Error");
                }
            }
            
        });
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setBackground( new Color(51, 51, 51));
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setBorder( new CompoundBorder(new LineBorder(new Color(122, 72, 255), 1, true), new EmptyBorder(1, 4, 1, 1) ));
        
        
        // https://stackoverflow.com/questions/1720482/resize-problem-with-jlist
        listOrders.setPrototypeCellValue("----------------------------------");
        
        // https://stackoverflow.com/questions/21029653/java-jlist-text-center-align
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) listOrders.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        renderAllComboBoxes();
        
    }
    
    public void initializeFields() {
        lblProfilePic.setIcon( new ImageIcon(getClass().getResource( customer.getImgPath() )));
        lblTheUserName.setText(customer.getUsername());
        lblTheUserMail.setText(customer.getMailAdress().getName());
        lblCurrentCashOfUser.setText("$" + customer.getTotalCash() );
        tbxUserName.setText( customer.getUsername() );
        tbxUserFullName.setText( customer.getFullName() );
        tbxUserGender.setText( customer.getGender() );
        tbxUserAge.setText( String.valueOf( customer.getAge() ) );
        tbxUserEmail.setText( customer.getMailAdress().getName() );
        tbxNewPhoneNum.setText( customer.getPhoneNumber() ); 
        lblUserCurrentCashValue.setText("$"  + customer.getTotalCash() );
        tbxHomeAddr.setText( customer.getHomeAddress() );
        fulfillData();
        
    }
    
    private void renderAllComboBoxes() {
        cbxGallery.setRenderer( new MyCellRenderer() );
        cbxPrice.setRenderer( new MyCellRenderer() );
        cbxOrderGalery.setRenderer( new MyCellRenderer() );
        cbxOrderPrice.setRenderer( new MyCellRenderer() );
    }
    
    public void visitorNotPermitedAction() {
        int choice = JOptionPane.showConfirmDialog(null, "You must register to the application. \nDo you want to register?", "Unsopperdet Operation", JOptionPane.YES_NO_OPTION);
                
                if ( choice == 0){
                    dispose();
                    visitor.register();
                }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlParent = new javax.swing.JPanel();
        PnlLeftSide = new javax.swing.JPanel();
        pnlProfileInfo = new javax.swing.JPanel();
        lblProfilePic = new javax.swing.JLabel();
        lblTheUserMail = new javax.swing.JLabel();
        lblTheUserName = new javax.swing.JLabel();
        pnlHomePage = new javax.swing.JPanel();
        lblHomeIcon = new javax.swing.JLabel();
        lblHomeText = new javax.swing.JLabel();
        lblHomeColor = new javax.swing.JLabel();
        pnlProfile = new javax.swing.JPanel();
        lblProfileText = new javax.swing.JLabel();
        lblProfileIcon = new javax.swing.JLabel();
        lblProfileColor = new javax.swing.JLabel();
        pnlExit = new javax.swing.JPanel();
        lblExitIcon = new javax.swing.JLabel();
        lblExitText = new javax.swing.JLabel();
        lblExitColor = new javax.swing.JLabel();
        pnlLayered = new javax.swing.JLayeredPane();
        pnlLayeredHomePage = new javax.swing.JPanel();
        pnlInPnlLayerdHomePagefilter = new javax.swing.JPanel();
        lblPrice = new javax.swing.JLabel();
        cbxPrice = new javax.swing.JComboBox<>();
        lblGallery = new javax.swing.JLabel();
        cbxGallery = new javax.swing.JComboBox<>();
        lblRange = new javax.swing.JLabel();
        tbxMin = new javax.swing.JTextField();
        tbxMax = new javax.swing.JTextField();
        btnFilter = new javax.swing.JButton();
        btnClearFilter = new javax.swing.JButton();
        pnlInLayeredHomePageCarInfo = new javax.swing.JPanel();
        lblCarImage = new javax.swing.JLabel();
        lblPrevious = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblRent = new javax.swing.JLabel();
        lblCarTitle = new javax.swing.JLabel();
        lblFuelType = new javax.swing.JLabel();
        lblFuelTypeValue = new javax.swing.JLabel();
        lblTransmissionType = new javax.swing.JLabel();
        lblTransmissionTypeValue = new javax.swing.JLabel();
        lblDailyPrice = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblYearValue = new javax.swing.JLabel();
        lblDailyPriceValue = new javax.swing.JLabel();
        lblKilometerValue = new javax.swing.JLabel();
        lblKilometer = new javax.swing.JLabel();
        lblFuelType6 = new javax.swing.JLabel();
        lblFuelCapacityValue = new javax.swing.JLabel();
        lblFuelType8 = new javax.swing.JLabel();
        lblTrunkVolumeValue = new javax.swing.JLabel();
        pnlApproval = new javax.swing.JPanel();
        btnApprove = new javax.swing.JButton();
        lblTotalCashAfterRental = new javax.swing.JLabel();
        lblCurrentCash = new javax.swing.JLabel();
        lblTotalCashAfterRentalOfUser = new javax.swing.JLabel();
        lblCurrentCashOfUser = new javax.swing.JLabel();
        lblTotalPaymentOfUser = new javax.swing.JLabel();
        lblTotalPayment1 = new javax.swing.JLabel();
        lblPickUpDate = new javax.swing.JLabel();
        lblReturnDate = new javax.swing.JLabel();
        lblPromotionCode = new javax.swing.JLabel();
        textPromotionCode = new javax.swing.JTextField();
        pick_upDate_JDatechooser = new com.toedter.calendar.JDateChooser();
        returnDate_JDatechooser = new com.toedter.calendar.JDateChooser();
        btnCheckPromotion = new javax.swing.JButton();
        pnlLayeredProfile = new javax.swing.JPanel();
        pnlInPnlLayerdProfileFilter = new javax.swing.JPanel();
        lblOrderPrice = new javax.swing.JLabel();
        cbxOrderPrice = new javax.swing.JComboBox<>();
        lblOrderGallery = new javax.swing.JLabel();
        cbxOrderGalery = new javax.swing.JComboBox<>();
        lblRange1 = new javax.swing.JLabel();
        tbxMinPrice = new javax.swing.JTextField();
        tbxMaxPrice = new javax.swing.JTextField();
        btnFilterOrders = new javax.swing.JButton();
        btnClearFilterOfOrder = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listOrders = new javax.swing.JList<>();
        pnlInLayeredHomePageOrderInfo = new javax.swing.JPanel();
        lblOrderedCarBrand = new javax.swing.JLabel();
        lblOrderedCarPickUpDate = new javax.swing.JLabel();
        lblOrderedCarReturnDate = new javax.swing.JLabel();
        lblOrderedCarMoedl = new javax.swing.JLabel();
        lblOrderedCarUsedPromotionCode = new javax.swing.JLabel();
        lblOrderedCarGallery = new javax.swing.JLabel();
        lblOrderedCharge = new javax.swing.JLabel();
        lblOrderCarImage = new javax.swing.JLabel();
        lblOrderCarBrandVal = new javax.swing.JLabel();
        lblOrderCarModelVal = new javax.swing.JLabel();
        lblOrderCarGalleryVal = new javax.swing.JLabel();
        lblOrderChargeVal = new javax.swing.JLabel();
        lblOrderPromotionCode = new javax.swing.JLabel();
        lblOrderReturnVal = new javax.swing.JLabel();
        lblOrderPickupDate = new javax.swing.JLabel();
        lblOrderPhoneNum = new javax.swing.JLabel();
        lblOrderPhoneNumVal = new javax.swing.JLabel();
        lblPrintIcon = new javax.swing.JLabel();
        pnlUserInformation = new javax.swing.JPanel();
        pnlUserBankInformation = new javax.swing.JPanel();
        lblUserCurrentCash = new javax.swing.JLabel();
        lblUserCurrentCashValue = new javax.swing.JLabel();
        lblUserCurrentCash1 = new javax.swing.JLabel();
        tbxDepositedCash = new javax.swing.JTextField();
        lblUserCurrentCash2 = new javax.swing.JLabel();
        lblUserTotalCash = new javax.swing.JLabel();
        btnUserDepositCash = new javax.swing.JButton();
        btnSaveChanges = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        lblUserName = new javax.swing.JLabel();
        tbxUserName = new javax.swing.JTextField();
        lblHomeAdd = new javax.swing.JLabel();
        tbxHomeAddr = new javax.swing.JTextArea();
        lblChangePic = new javax.swing.JLabel();
        lblChangePassword = new javax.swing.JLabel();
        tbxNewPhoneNum = new javax.swing.JTextField();
        lblNewPhoNum = new javax.swing.JLabel();
        lblUserEmail = new javax.swing.JLabel();
        tbxUserEmail = new javax.swing.JTextField();
        tbxUserAge = new javax.swing.JTextField();
        lblUserAge = new javax.swing.JLabel();
        lblUserGender = new javax.swing.JLabel();
        tbxUserGender = new javax.swing.JTextField();
        tbxUserFullName = new javax.swing.JTextField();
        lblUserFullName = new javax.swing.JLabel();
        jTogg_btn_modifyAcc = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PnlParent.setBackground(new java.awt.Color(51, 51, 51));

        PnlLeftSide.setBackground(new java.awt.Color(51, 51, 51));

        pnlProfileInfo.setBackground(new java.awt.Color(51, 51, 51));
        pnlProfileInfo.setForeground(new java.awt.Color(51, 51, 51));

        lblProfilePic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTheUserMail.setBackground(new java.awt.Color(242, 243, 244));
        lblTheUserMail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTheUserMail.setForeground(new java.awt.Color(204, 204, 204));
        lblTheUserMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTheUserName.setBackground(new java.awt.Color(242, 243, 244));
        lblTheUserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTheUserName.setForeground(new java.awt.Color(204, 204, 204));
        lblTheUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlProfileInfoLayout = new javax.swing.GroupLayout(pnlProfileInfo);
        pnlProfileInfo.setLayout(pnlProfileInfoLayout);
        pnlProfileInfoLayout.setHorizontalGroup(
            pnlProfileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProfileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfilePic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlProfileInfoLayout.createSequentialGroup()
                        .addGroup(pnlProfileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTheUserMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTheUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlProfileInfoLayout.setVerticalGroup(
            pnlProfileInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileInfoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTheUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTheUserMail, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pnlHomePage.setBackground(new java.awt.Color(255, 255, 255));
        pnlHomePage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlHomePage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHomeIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHomeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home48.png"))); // NOI18N
        pnlHomePage.add(lblHomeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 60, 60));

        lblHomeText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHomeText.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHomeText.setText("Home");
        pnlHomePage.add(lblHomeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 80, 60));

        lblHomeColor.setBackground(new java.awt.Color(51, 51, 51));
        lblHomeColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color2.png"))); // NOI18N
        lblHomeColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeColorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHomeColorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHomeColorMouseExited(evt);
            }
        });
        pnlHomePage.add(lblHomeColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 60));

        pnlProfile.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblProfileText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblProfileText.setForeground(new java.awt.Color(204, 204, 204));
        lblProfileText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfileText.setText("Profile");
        pnlProfile.add(lblProfileText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 80, 60));

        lblProfileIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile48.png"))); // NOI18N
        pnlProfile.add(lblProfileIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 60, 60));

        lblProfileColor.setBackground(new java.awt.Color(51, 51, 51));
        lblProfileColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color2.png"))); // NOI18N
        lblProfileColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProfileColorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblProfileColorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblProfileColorMouseExited(evt);
            }
        });
        pnlProfile.add(lblProfileColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 60));

        pnlExit.setBackground(new java.awt.Color(255, 255, 255));
        pnlExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlExit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblExitIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExitIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit48.png"))); // NOI18N
        pnlExit.add(lblExitIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 60, 60));

        lblExitText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblExitText.setForeground(new java.awt.Color(204, 204, 204));
        lblExitText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExitText.setText("Sign Out");
        pnlExit.add(lblExitText, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 90, 60));

        lblExitColor.setBackground(new java.awt.Color(51, 51, 51));
        lblExitColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color2.png"))); // NOI18N
        lblExitColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitColorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitColorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitColorMouseExited(evt);
            }
        });
        pnlExit.add(lblExitColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 60));

        javax.swing.GroupLayout PnlLeftSideLayout = new javax.swing.GroupLayout(PnlLeftSide);
        PnlLeftSide.setLayout(PnlLeftSideLayout);
        PnlLeftSideLayout.setHorizontalGroup(
            PnlLeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlProfileInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlHomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PnlLeftSideLayout.setVerticalGroup(
            PnlLeftSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlLeftSideLayout.createSequentialGroup()
                .addComponent(pnlProfileInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(pnlHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlExit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pnlLayered.setLayout(new java.awt.CardLayout());

        pnlLayeredHomePage.setBackground(new java.awt.Color(51, 51, 51));

        pnlInPnlLayerdHomePagefilter.setBackground(new java.awt.Color(51, 51, 51));
        pnlInPnlLayerdHomePagefilter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrice.setForeground(new java.awt.Color(204, 204, 204));
        lblPrice.setText("Sort:");
        lblPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1));

        cbxPrice.setBackground(new java.awt.Color(51, 51, 51));
        cbxPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxPrice.setForeground(new java.awt.Color(204, 204, 204));
        cbxPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kilometer", "Price", "Year", "Brand" }));

        lblGallery.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGallery.setForeground(new java.awt.Color(204, 204, 204));
        lblGallery.setText("Gallery:");
        lblGallery.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1));

        cbxGallery.setBackground(new java.awt.Color(51, 51, 51));
        cbxGallery.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbxGallery.setForeground(new java.awt.Color(204, 204, 204));
        cbxGallery.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        lblRange.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRange.setForeground(new java.awt.Color(204, 204, 204));
        lblRange.setText("Range:");
        lblRange.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1));

        tbxMin.setBackground(new java.awt.Color(51, 51, 51));
        tbxMin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMin.setForeground(new java.awt.Color(204, 204, 204));
        tbxMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxMin.setText("0");
        tbxMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxMinKeyTyped(evt);
            }
        });

        tbxMax.setBackground(new java.awt.Color(51, 51, 51));
        tbxMax.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMax.setForeground(new java.awt.Color(204, 204, 204));
        tbxMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxMax.setText("100");
        tbxMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxMaxKeyTyped(evt);
            }
        });

        btnFilter.setBackground(new java.awt.Color(0, 0, 0));
        btnFilter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFilter.setForeground(new java.awt.Color(122, 72, 255));
        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnClearFilter.setBackground(new java.awt.Color(0, 0, 0));
        btnClearFilter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearFilter.setForeground(new java.awt.Color(122, 72, 255));
        btnClearFilter.setText("Clear Filter");
        btnClearFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInPnlLayerdHomePagefilterLayout = new javax.swing.GroupLayout(pnlInPnlLayerdHomePagefilter);
        pnlInPnlLayerdHomePagefilter.setLayout(pnlInPnlLayerdHomePagefilterLayout);
        pnlInPnlLayerdHomePagefilterLayout.setHorizontalGroup(
            pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                        .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41)
                        .addComponent(btnClearFilter)
                        .addGap(90, 90, 90))
                    .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                        .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxPrice, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                                .addComponent(lblGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxGallery, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                                .addComponent(lblRange, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(tbxMin, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(tbxMax, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(125, Short.MAX_VALUE))))
        );
        pnlInPnlLayerdHomePagefilterLayout.setVerticalGroup(
            pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRange, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbxMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbxMax, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGallery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pnlInLayeredHomePageCarInfo.setBackground(new java.awt.Color(51, 51, 51));
        pnlInLayeredHomePageCarInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Car Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(122, 72, 255))); // NOI18N

        lblPrevious.setBackground(new java.awt.Color(51, 51, 51));
        lblPrevious.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/previous32.png"))); // NOI18N
        lblPrevious.setOpaque(true);
        lblPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPreviousMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPreviousMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPreviousMouseExited(evt);
            }
        });

        lblNext.setBackground(new java.awt.Color(51, 51, 51));
        lblNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/next32.png"))); // NOI18N
        lblNext.setOpaque(true);
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNextMouseExited(evt);
            }
        });

        lblRent.setBackground(new java.awt.Color(51, 51, 51));
        lblRent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/car32.png"))); // NOI18N
        lblRent.setOpaque(true);
        lblRent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRentMouseExited(evt);
            }
        });

        lblCarTitle.setBackground(new java.awt.Color(242, 243, 244));
        lblCarTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblCarTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblCarTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarTitle.setText("AUDI A3 Sedan");

        lblFuelType.setBackground(new java.awt.Color(51, 51, 51));
        lblFuelType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuelType.setForeground(new java.awt.Color(204, 204, 204));
        lblFuelType.setText("Fuel Type:");

        lblFuelTypeValue.setBackground(new java.awt.Color(51, 51, 51));
        lblFuelTypeValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuelTypeValue.setForeground(new java.awt.Color(204, 204, 204));
        lblFuelTypeValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTransmissionType.setBackground(new java.awt.Color(242, 243, 244));
        lblTransmissionType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTransmissionType.setForeground(new java.awt.Color(204, 204, 204));
        lblTransmissionType.setText("Transmission Type:");

        lblTransmissionTypeValue.setBackground(new java.awt.Color(242, 243, 244));
        lblTransmissionTypeValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTransmissionTypeValue.setForeground(new java.awt.Color(204, 204, 204));
        lblTransmissionTypeValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblDailyPrice.setBackground(new java.awt.Color(242, 243, 244));
        lblDailyPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDailyPrice.setForeground(new java.awt.Color(204, 204, 204));
        lblDailyPrice.setText("Daily Price:");

        lblYear.setBackground(new java.awt.Color(51, 51, 51));
        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblYear.setForeground(new java.awt.Color(204, 204, 204));
        lblYear.setText("Year:");

        lblYearValue.setBackground(new java.awt.Color(51, 51, 51));
        lblYearValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblYearValue.setForeground(new java.awt.Color(204, 204, 204));
        lblYearValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblDailyPriceValue.setBackground(new java.awt.Color(242, 243, 244));
        lblDailyPriceValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDailyPriceValue.setForeground(new java.awt.Color(204, 204, 204));
        lblDailyPriceValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblKilometerValue.setBackground(new java.awt.Color(51, 51, 51));
        lblKilometerValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKilometerValue.setForeground(new java.awt.Color(204, 204, 204));

        lblKilometer.setBackground(new java.awt.Color(51, 51, 51));
        lblKilometer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKilometer.setForeground(new java.awt.Color(204, 204, 204));
        lblKilometer.setText("Kilometer:");

        lblFuelType6.setBackground(new java.awt.Color(51, 51, 51));
        lblFuelType6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuelType6.setForeground(new java.awt.Color(204, 204, 204));
        lblFuelType6.setText("Fuel Capacity:");

        lblFuelCapacityValue.setBackground(new java.awt.Color(51, 51, 51));
        lblFuelCapacityValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuelCapacityValue.setForeground(new java.awt.Color(204, 204, 204));

        lblFuelType8.setBackground(new java.awt.Color(51, 51, 51));
        lblFuelType8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFuelType8.setForeground(new java.awt.Color(204, 204, 204));
        lblFuelType8.setText("Trunk Volume:");

        lblTrunkVolumeValue.setBackground(new java.awt.Color(51, 51, 51));
        lblTrunkVolumeValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrunkVolumeValue.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlInLayeredHomePageCarInfoLayout = new javax.swing.GroupLayout(pnlInLayeredHomePageCarInfo);
        pnlInLayeredHomePageCarInfo.setLayout(pnlInLayeredHomePageCarInfoLayout);
        pnlInLayeredHomePageCarInfoLayout.setHorizontalGroup(
            pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblCarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCarTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                        .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTransmissionType, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(lblFuelType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuelTypeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTransmissionTypeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDailyPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblYearValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDailyPriceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(62, 62, 62)
                        .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addComponent(lblFuelType8, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrunkVolumeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuelType6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKilometer, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblKilometerValue, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuelCapacityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRent, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNext, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        pnlInLayeredHomePageCarInfoLayout.setVerticalGroup(
            pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                        .addComponent(lblCarTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblKilometer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKilometerValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuelCapacityValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuelType6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTrunkVolumeValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuelType8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuelTypeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFuelType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTransmissionTypeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTransmissionType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblYearValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDailyPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDailyPriceValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(6, 6, 6)
                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlApproval.setBackground(new java.awt.Color(51, 51, 51));
        pnlApproval.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Approval", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        btnApprove.setBackground(new java.awt.Color(0, 0, 0));
        btnApprove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnApprove.setForeground(new java.awt.Color(122, 72, 255));
        btnApprove.setText("Approve");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        lblTotalCashAfterRental.setBackground(new java.awt.Color(242, 243, 244));
        lblTotalCashAfterRental.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalCashAfterRental.setForeground(new java.awt.Color(204, 204, 204));
        lblTotalCashAfterRental.setText("Total Cash After Rental");

        lblCurrentCash.setBackground(new java.awt.Color(242, 243, 244));
        lblCurrentCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCurrentCash.setForeground(new java.awt.Color(204, 204, 204));
        lblCurrentCash.setText("Your Current Cash");

        lblTotalCashAfterRentalOfUser.setBackground(new java.awt.Color(51, 51, 51));
        lblTotalCashAfterRentalOfUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalCashAfterRentalOfUser.setForeground(new java.awt.Color(204, 204, 204));

        lblCurrentCashOfUser.setBackground(new java.awt.Color(51, 51, 51));
        lblCurrentCashOfUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCurrentCashOfUser.setForeground(new java.awt.Color(51, 153, 0));
        lblCurrentCashOfUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblTotalPaymentOfUser.setBackground(new java.awt.Color(51, 51, 51));
        lblTotalPaymentOfUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalPaymentOfUser.setForeground(new java.awt.Color(204, 204, 204));

        lblTotalPayment1.setBackground(new java.awt.Color(242, 243, 244));
        lblTotalPayment1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalPayment1.setForeground(new java.awt.Color(204, 204, 204));
        lblTotalPayment1.setText("Total Payment");

        lblPickUpDate.setBackground(new java.awt.Color(242, 243, 244));
        lblPickUpDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPickUpDate.setForeground(new java.awt.Color(204, 204, 204));
        lblPickUpDate.setText("Pick-Up Date");

        lblReturnDate.setBackground(new java.awt.Color(242, 243, 244));
        lblReturnDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblReturnDate.setForeground(new java.awt.Color(204, 204, 204));
        lblReturnDate.setText("Return Date");

        lblPromotionCode.setBackground(new java.awt.Color(242, 243, 244));
        lblPromotionCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPromotionCode.setForeground(new java.awt.Color(204, 204, 204));
        lblPromotionCode.setText("Promotion Code");

        textPromotionCode.setBackground(new java.awt.Color(51, 51, 51));
        textPromotionCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textPromotionCode.setForeground(new java.awt.Color(204, 204, 204));
        textPromotionCode.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        textPromotionCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textPromotionCodeKeyTyped(evt);
            }
        });

        pick_upDate_JDatechooser.setBackground(new java.awt.Color(51, 51, 51));
        pick_upDate_JDatechooser.setForeground(new java.awt.Color(204, 204, 204));
        pick_upDate_JDatechooser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pick_upDate_JDatechooser.setOpaque(false);

        returnDate_JDatechooser.setBackground(new java.awt.Color(25, 25, 25));
        returnDate_JDatechooser.setForeground(new java.awt.Color(204, 204, 204));
        returnDate_JDatechooser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        returnDate_JDatechooser.setOpaque(false);

        btnCheckPromotion.setBackground(new java.awt.Color(0, 0, 0));
        btnCheckPromotion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCheckPromotion.setForeground(new java.awt.Color(122, 72, 255));
        btnCheckPromotion.setText("Ok");
        btnCheckPromotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckPromotionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlApprovalLayout = new javax.swing.GroupLayout(pnlApproval);
        pnlApproval.setLayout(pnlApprovalLayout);
        pnlApprovalLayout.setHorizontalGroup(
            pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlApprovalLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblReturnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPickUpDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPromotionCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalCashAfterRental, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPayment1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalCashAfterRentalOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentCashOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPaymentOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pick_upDate_JDatechooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnDate_JDatechooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlApprovalLayout.createSequentialGroup()
                        .addComponent(textPromotionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCheckPromotion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlApprovalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        pnlApprovalLayout.setVerticalGroup(
            pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlApprovalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurrentCashOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurrentCash, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPickUpDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pick_upDate_JDatechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblReturnDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnDate_JDatechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPromotionCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textPromotionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCheckPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalPaymentOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalCashAfterRentalOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalCashAfterRental, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlLayeredHomePageLayout = new javax.swing.GroupLayout(pnlLayeredHomePage);
        pnlLayeredHomePage.setLayout(pnlLayeredHomePageLayout);
        pnlLayeredHomePageLayout.setHorizontalGroup(
            pnlLayeredHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredHomePageLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlLayeredHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInLayeredHomePageCarInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLayeredHomePageLayout.createSequentialGroup()
                        .addComponent(pnlInPnlLayerdHomePagefilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlApproval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlLayeredHomePageLayout.setVerticalGroup(
            pnlLayeredHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredHomePageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInLayeredHomePageCarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLayeredHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInPnlLayerdHomePagefilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlApproval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlLayered.add(pnlLayeredHomePage, "card3");

        pnlLayeredProfile.setBackground(new java.awt.Color(51, 51, 51));

        pnlInPnlLayerdProfileFilter.setBackground(new java.awt.Color(51, 51, 51));
        pnlInPnlLayerdProfileFilter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblOrderPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderPrice.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderPrice.setText("Sort:");

        cbxOrderPrice.setBackground(new java.awt.Color(0, 0, 0));
        cbxOrderPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxOrderPrice.setForeground(new java.awt.Color(122, 72, 255));
        cbxOrderPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cbxOrderPrice.setToolTipText("");

        lblOrderGallery.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderGallery.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderGallery.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderGallery.setText("  Gallery:");

        cbxOrderGalery.setBackground(new java.awt.Color(0, 0, 0));
        cbxOrderGalery.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxOrderGalery.setForeground(new java.awt.Color(122, 72, 255));
        cbxOrderGalery.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbxOrderGalery.setToolTipText("");

        lblRange1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRange1.setForeground(new java.awt.Color(204, 204, 204));
        lblRange1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRange1.setText("Range:");

        tbxMinPrice.setBackground(new java.awt.Color(51, 51, 51));
        tbxMinPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMinPrice.setForeground(new java.awt.Color(204, 204, 204));
        tbxMinPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxMinPrice.setText("0");
        tbxMinPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbxMinPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxMinPriceKeyTyped(evt);
            }
        });

        tbxMaxPrice.setBackground(new java.awt.Color(51, 51, 51));
        tbxMaxPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMaxPrice.setForeground(new java.awt.Color(204, 204, 204));
        tbxMaxPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxMaxPrice.setText("10000");
        tbxMaxPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tbxMaxPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxMaxPriceKeyTyped(evt);
            }
        });

        btnFilterOrders.setBackground(new java.awt.Color(0, 0, 0));
        btnFilterOrders.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFilterOrders.setForeground(new java.awt.Color(122, 72, 255));
        btnFilterOrders.setText("Filter");
        btnFilterOrders.setToolTipText("");
        btnFilterOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterOrdersActionPerformed(evt);
            }
        });

        btnClearFilterOfOrder.setBackground(new java.awt.Color(0, 0, 0));
        btnClearFilterOfOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClearFilterOfOrder.setForeground(new java.awt.Color(122, 72, 255));
        btnClearFilterOfOrder.setText("Clear");
        btnClearFilterOfOrder.setToolTipText("");
        btnClearFilterOfOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFilterOfOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInPnlLayerdProfileFilterLayout = new javax.swing.GroupLayout(pnlInPnlLayerdProfileFilter);
        pnlInPnlLayerdProfileFilter.setLayout(pnlInPnlLayerdProfileFilterLayout);
        pnlInPnlLayerdProfileFilterLayout.setHorizontalGroup(
            pnlInPnlLayerdProfileFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInPnlLayerdProfileFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOrderPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxOrderPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRange1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblOrderGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxOrderGalery, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnFilterOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearFilterOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        pnlInPnlLayerdProfileFilterLayout.setVerticalGroup(
            pnlInPnlLayerdProfileFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInPnlLayerdProfileFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInPnlLayerdProfileFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOrderGallery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxOrderPrice)
                    .addComponent(tbxMinPrice)
                    .addComponent(tbxMaxPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInPnlLayerdProfileFilterLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblOrderPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInPnlLayerdProfileFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxOrderGalery, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(btnClearFilterOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFilterOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblRange1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        listOrders.setBackground(new java.awt.Color(51, 51, 51));
        listOrders.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orders", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N
        listOrders.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        listOrders.setForeground(new java.awt.Color(204, 204, 204));
        listOrders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listOrders.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listOrdersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listOrders);

        pnlInLayeredHomePageOrderInfo.setBackground(new java.awt.Color(51, 51, 51));
        pnlInLayeredHomePageOrderInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N
        pnlInLayeredHomePageOrderInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOrderedCarBrand.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarBrand.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarBrand.setText("Brand:");
        lblOrderedCarBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 32, 73, -1));

        lblOrderedCarPickUpDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarPickUpDate.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarPickUpDate.setText("Pick-up Date:");
        lblOrderedCarPickUpDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarPickUpDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 120, -1));

        lblOrderedCarReturnDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarReturnDate.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarReturnDate.setText("Return Date:");
        lblOrderedCarReturnDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 120, -1));

        lblOrderedCarMoedl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarMoedl.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarMoedl.setText("Model:");
        lblOrderedCarMoedl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarMoedl, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 64, 73, -1));

        lblOrderedCarUsedPromotionCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarUsedPromotionCode.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarUsedPromotionCode.setText("Promotion Code:");
        lblOrderedCarUsedPromotionCode.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarUsedPromotionCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        lblOrderedCarGallery.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarGallery.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarGallery.setText("Gallery:");
        lblOrderedCarGallery.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCarGallery, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 128, 73, -1));

        lblOrderedCharge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCharge.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCharge.setText("Charge:");
        lblOrderedCharge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderedCharge, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 160, 73, -1));
        pnlInLayeredHomePageOrderInfo.add(lblOrderCarImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 203, 166));

        lblOrderCarBrandVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderCarBrandVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderCarBrandVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderCarBrandVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderCarBrandVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 32, 140, 20));

        lblOrderCarModelVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderCarModelVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderCarModelVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderCarModelVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderCarModelVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 64, 140, 20));

        lblOrderCarGalleryVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderCarGalleryVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderCarGalleryVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderCarGalleryVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderCarGalleryVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 128, 140, 20));

        lblOrderChargeVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderChargeVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderChargeVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderChargeVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderChargeVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 140, 20));

        lblOrderPromotionCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderPromotionCode.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderPromotionCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderPromotionCode.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderPromotionCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 140, 20));

        lblOrderReturnVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderReturnVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderReturnVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderReturnVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderReturnVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 140, 20));

        lblOrderPickupDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderPickupDate.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderPickupDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderPickupDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderPickupDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 140, 20));

        lblOrderPhoneNum.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderPhoneNum.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderPhoneNum.setText("Phone:");
        lblOrderPhoneNum.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 96, 73, -1));

        lblOrderPhoneNumVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderPhoneNumVal.setForeground(new java.awt.Color(224, 224, 224));
        lblOrderPhoneNumVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderPhoneNumVal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        pnlInLayeredHomePageOrderInfo.add(lblOrderPhoneNumVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 96, 140, 20));

        lblPrintIcon.setBackground(new java.awt.Color(51, 51, 51));
        lblPrintIcon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrintIcon.setForeground(new java.awt.Color(204, 204, 204));
        lblPrintIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrintIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print48.png"))); // NOI18N
        lblPrintIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPrintIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPrintIcon.setOpaque(true);
        lblPrintIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintIconMouseClicked(evt);
            }
        });
        pnlInLayeredHomePageOrderInfo.add(lblPrintIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 84, -1));

        pnlUserInformation.setBackground(new java.awt.Color(51, 51, 51));
        pnlUserInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N
        pnlUserInformation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlUserBankInformation.setBackground(new java.awt.Color(51, 51, 51));
        pnlUserBankInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bank Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblUserCurrentCash.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash.setText("Current Cash:");

        lblUserCurrentCashValue.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCashValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCashValue.setForeground(new java.awt.Color(51, 153, 0));
        lblUserCurrentCashValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserCurrentCashValue.setText("$");

        lblUserCurrentCash1.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash1.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash1.setText("Deposited Cash:");

        tbxDepositedCash.setBackground(new java.awt.Color(51, 51, 51));
        tbxDepositedCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbxDepositedCash.setForeground(new java.awt.Color(204, 204, 204));
        tbxDepositedCash.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxDepositedCash.setText("0");
        tbxDepositedCash.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxDepositedCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxDepositedCashKeyTyped(evt);
            }
        });

        lblUserCurrentCash2.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash2.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash2.setText("Total Cash: ");

        lblUserTotalCash.setBackground(new java.awt.Color(51, 51, 51));
        lblUserTotalCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserTotalCash.setForeground(new java.awt.Color(204, 204, 204));
        lblUserTotalCash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnUserDepositCash.setBackground(new java.awt.Color(0, 0, 0));
        btnUserDepositCash.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnUserDepositCash.setForeground(new java.awt.Color(122, 72, 255));
        btnUserDepositCash.setText("Deposit Cash");
        btnUserDepositCash.setToolTipText("");
        btnUserDepositCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserDepositCashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserBankInformationLayout = new javax.swing.GroupLayout(pnlUserBankInformation);
        pnlUserBankInformation.setLayout(pnlUserBankInformationLayout);
        pnlUserBankInformationLayout.setHorizontalGroup(
            pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserCurrentCash, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserCurrentCash1)
                    .addComponent(lblUserCurrentCash2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUserTotalCash, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(tbxDepositedCash, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(lblUserCurrentCashValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserBankInformationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUserDepositCash, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlUserBankInformationLayout.setVerticalGroup(
            pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserCurrentCashValue, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserCurrentCash, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUserCurrentCash1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(tbxDepositedCash))
                .addGap(18, 18, 18)
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUserTotalCash, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblUserCurrentCash2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnUserDepositCash, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pnlUserInformation.add(pnlUserBankInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 270));

        btnSaveChanges.setBackground(new java.awt.Color(0, 0, 0));
        btnSaveChanges.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnSaveChanges.setForeground(new java.awt.Color(122, 72, 255));
        btnSaveChanges.setText("Save Changes");
        btnSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesActionPerformed(evt);
            }
        });
        pnlUserInformation.add(btnSaveChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 267, -1, 46));

        btnDeleteUser.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteUser.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnDeleteUser.setForeground(new java.awt.Color(122, 72, 255));
        btnDeleteUser.setText("Delete Account");
        pnlUserInformation.add(btnDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 267, 155, 46));

        lblUserName.setBackground(new java.awt.Color(242, 243, 244));
        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(204, 204, 204));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserName.setText("User Name:");
        pnlUserInformation.add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 90, 30));

        tbxUserName.setEditable(false);
        tbxUserName.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxUserName.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserName.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserName.setDisabledTextColor(new java.awt.Color(250, 250, 250));
        pnlUserInformation.add(tbxUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 150, 30));

        lblHomeAdd.setBackground(new java.awt.Color(255, 255, 255));
        lblHomeAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHomeAdd.setForeground(new java.awt.Color(204, 204, 204));
        lblHomeAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHomeAdd.setText("Home Address:");
        lblHomeAdd.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlUserInformation.add(lblHomeAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 270, -1));

        tbxHomeAddr.setEditable(false);
        tbxHomeAddr.setBackground(new java.awt.Color(58, 50, 67));
        tbxHomeAddr.setColumns(20);
        tbxHomeAddr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbxHomeAddr.setForeground(new java.awt.Color(204, 204, 204));
        tbxHomeAddr.setLineWrap(true);
        tbxHomeAddr.setRows(5);
        tbxHomeAddr.setText("Anafartalar Mah. Şehit Teğmen Kalmaz Cad. No:2 / 301, Altındağ / Ankara, Türkiye");
        tbxHomeAddr.setWrapStyleWord(true);
        tbxHomeAddr.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(3, 6, 3, 6)));
        pnlUserInformation.add(tbxHomeAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 270, 80));

        lblChangePic.setBackground(new java.awt.Color(242, 243, 244));
        lblChangePic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChangePic.setForeground(new java.awt.Color(204, 204, 204));
        lblChangePic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChangePic.setText("Change Picture");
        lblChangePic.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true));
        lblChangePic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChangePic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChangePicMouseClicked(evt);
            }
        });
        pnlUserInformation.add(lblChangePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 160, 40));

        lblChangePassword.setBackground(new java.awt.Color(242, 243, 244));
        lblChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChangePassword.setForeground(new java.awt.Color(204, 204, 204));
        lblChangePassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChangePassword.setText("Change Password");
        lblChangePassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true));
        lblChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChangePasswordMouseClicked(evt);
            }
        });
        pnlUserInformation.add(lblChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 160, 40));

        tbxNewPhoneNum.setEditable(false);
        tbxNewPhoneNum.setBackground(new java.awt.Color(51, 51, 51));
        tbxNewPhoneNum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxNewPhoneNum.setForeground(new java.awt.Color(204, 204, 204));
        tbxNewPhoneNum.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxNewPhoneNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbxNewPhoneNumKeyTyped(evt);
            }
        });
        pnlUserInformation.add(tbxNewPhoneNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 150, 30));

        lblNewPhoNum.setBackground(new java.awt.Color(242, 243, 244));
        lblNewPhoNum.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNewPhoNum.setForeground(new java.awt.Color(204, 204, 204));
        lblNewPhoNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewPhoNum.setText("Phone Num. :");
        pnlUserInformation.add(lblNewPhoNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 90, 31));

        lblUserEmail.setBackground(new java.awt.Color(242, 243, 244));
        lblUserEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblUserEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserEmail.setText("Email Adr. :");
        pnlUserInformation.add(lblUserEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 90, 30));

        tbxUserEmail.setEditable(false);
        tbxUserEmail.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxUserEmail.setForeground(new java.awt.Color(204, 204, 204));
        tbxUserEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        pnlUserInformation.add(tbxUserEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 150, 30));

        tbxUserAge.setEditable(false);
        tbxUserAge.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserAge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxUserAge.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserAge.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserAge.setDisabledTextColor(new java.awt.Color(250, 250, 250));
        pnlUserInformation.add(tbxUserAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 150, 30));

        lblUserAge.setBackground(new java.awt.Color(242, 243, 244));
        lblUserAge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserAge.setForeground(new java.awt.Color(204, 204, 204));
        lblUserAge.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserAge.setText("Age:");
        pnlUserInformation.add(lblUserAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 90, 30));

        lblUserGender.setBackground(new java.awt.Color(242, 243, 244));
        lblUserGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserGender.setForeground(new java.awt.Color(204, 204, 204));
        lblUserGender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserGender.setText("Gender:");
        pnlUserInformation.add(lblUserGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, 30));

        tbxUserGender.setEditable(false);
        tbxUserGender.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserGender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxUserGender.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserGender.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserGender.setDisabledTextColor(new java.awt.Color(250, 250, 250));
        pnlUserInformation.add(tbxUserGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 150, 30));

        tbxUserFullName.setEditable(false);
        tbxUserFullName.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserFullName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxUserFullName.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserFullName.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserFullName.setDisabledTextColor(new java.awt.Color(250, 250, 250));
        pnlUserInformation.add(tbxUserFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 150, 30));

        lblUserFullName.setBackground(new java.awt.Color(242, 243, 244));
        lblUserFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserFullName.setForeground(new java.awt.Color(204, 204, 204));
        lblUserFullName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserFullName.setText("Full Name:");
        pnlUserInformation.add(lblUserFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, 30));

        jTogg_btn_modifyAcc.setBackground(new java.awt.Color(0, 0, 0));
        jTogg_btn_modifyAcc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTogg_btn_modifyAcc.setForeground(new java.awt.Color(122, 72, 255));
        jTogg_btn_modifyAcc.setText("Modify Account");
        jTogg_btn_modifyAcc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jTogg_btn_modifyAccİtemStateChanged(evt);
            }
        });
        pnlUserInformation.add(jTogg_btn_modifyAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 267, -1, 46));

        javax.swing.GroupLayout pnlLayeredProfileLayout = new javax.swing.GroupLayout(pnlLayeredProfile);
        pnlLayeredProfile.setLayout(pnlLayeredProfileLayout);
        pnlLayeredProfileLayout.setHorizontalGroup(
            pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredProfileLayout.createSequentialGroup()
                .addGroup(pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayeredProfileLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInLayeredHomePageOrderInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlUserInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addComponent(pnlInPnlLayerdProfileFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLayeredProfileLayout.setVerticalGroup(
            pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInPnlLayerdProfileFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInLayeredHomePageOrderInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUserInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlLayered.add(pnlLayeredProfile, "card2");

        javax.swing.GroupLayout PnlParentLayout = new javax.swing.GroupLayout(PnlParent);
        PnlParent.setLayout(PnlParentLayout);
        PnlParentLayout.setHorizontalGroup(
            PnlParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlParentLayout.createSequentialGroup()
                .addComponent(PnlLeftSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLayered))
        );
        PnlParentLayout.setVerticalGroup(
            PnlParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlLeftSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlLayered)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlParent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHomeColorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeColorMouseEntered
        // TODO add your handling code here:
        lblHomeColor.setIcon(new ImageIcon(getClass().getResource("/images/color3.png")));
    }//GEN-LAST:event_lblHomeColorMouseEntered

    private void lblHomeColorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeColorMouseExited
        // TODO add your handling code here:
        lblHomeColor.setIcon( new ImageIcon(getClass().getResource("/images/color2.png")) );
    }//GEN-LAST:event_lblHomeColorMouseExited

    private void lblHomeColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeColorMouseClicked
        // TODO add your handling code here:
        HelperMethods.changePage(pnlLayeredHomePage, pnlLayered);
    }//GEN-LAST:event_lblHomeColorMouseClicked

    private void lblProfileColorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileColorMouseEntered
        // TODO add your handling code here:
        lblProfileColor.setIcon( new ImageIcon(getClass().getResource("/images/color3.png")));
    }//GEN-LAST:event_lblProfileColorMouseEntered

    private void lblProfileColorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileColorMouseExited
        // TODO add your handling code here:
        lblProfileColor.setIcon( new ImageIcon(getClass().getResource("/images/color2.png")) );
    }//GEN-LAST:event_lblProfileColorMouseExited

    private void lblProfileColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileColorMouseClicked
        // TODO add your handling code here:
        HelperMethods.changePage(pnlLayeredProfile, pnlLayered);
        //RentCarSystem.getOrdersFromDatabase();
    }//GEN-LAST:event_lblProfileColorMouseClicked

    private void lblExitColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitColorMouseClicked
        // TODO add your handling code here:
        System.exit( 0 );
    }//GEN-LAST:event_lblExitColorMouseClicked

    private void lblExitColorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitColorMouseEntered
        // TODO add your handling code here:
        lblExitColor.setIcon( new ImageIcon(getClass().getResource("/images/color3.png")));
    }//GEN-LAST:event_lblExitColorMouseEntered

    private void lblExitColorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitColorMouseExited
        // TODO add your handling code here:
        lblExitColor.setIcon( new ImageIcon(getClass().getResource("/images/color2.png")) );
    }//GEN-LAST:event_lblExitColorMouseExited

    private void lblPreviousMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviousMouseEntered
        // TODO add your handling code here:
        lblPrevious.setBackground( new Color(55, 55, 55) );
    }//GEN-LAST:event_lblPreviousMouseEntered

    private void lblPreviousMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviousMouseExited
        // TODO add your handling code here:
        lblPrevious.setBackground( new Color(51, 51, 51) );
    }//GEN-LAST:event_lblPreviousMouseExited

    private void lblNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseEntered
        // TODO add your handling code here:
        lblNext.setBackground( new Color(55, 55, 55) );
    }//GEN-LAST:event_lblNextMouseEntered

    private void lblNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseExited
        // TODO add your handling code here:
        lblNext.setBackground( new Color(51, 51, 51) );
    }//GEN-LAST:event_lblNextMouseExited

    private void lblRentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRentMouseEntered
        // TODO add your handling code here:
        lblRent.setBackground( new Color(55, 55, 55) );
    }//GEN-LAST:event_lblRentMouseEntered

    private void lblRentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRentMouseExited
        // TODO add your handling code here:
        lblRent.setBackground( new Color(51, 51, 51) );
    }//GEN-LAST:event_lblRentMouseExited

    private void textPromotionCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPromotionCodeKeyTyped
        lblTotalPaymentOfUser.setText(String.valueOf(currentCar.getPrice() *(int)(returnDate_JDatechooser.getDate().getTime() - pick_upDate_JDatechooser.getDate().getTime()) / 86_400_000));
        isChecked = false;
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setForeground(new Color(204, 204, 204));
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setForeground(new Color(204, 204, 204));
    }//GEN-LAST:event_textPromotionCodeKeyTyped

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        
        currentCarIndex++;
        if(isCarsFiltered){
            if ( currentCarIndex == RentCarSystem.getFilteredCarList().size())
                currentCarIndex = 0;
            currentCar = RentCarSystem.getFilteredCarList().get(currentCarIndex);
            setCarInformation(currentCar);
        }
        else{
            if ( currentCarIndex == RentCarSystem.getCars().size())
                currentCarIndex = 0;
            currentCar = RentCarSystem.getCars().get(currentCarIndex);
            setCarInformation(currentCar);
        }
    }//GEN-LAST:event_lblNextMouseClicked

    private void lblPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviousMouseClicked
        
        currentCarIndex--;
        if(isCarsFiltered){
            if ( currentCarIndex == -1)
                currentCarIndex = RentCarSystem.getFilteredCarList().size() - 1;
            currentCar = RentCarSystem.getFilteredCarList().get(currentCarIndex);
            setCarInformation(currentCar);
        }
        else{
            //currentCarIndex--;
            if ( currentCarIndex == -1)
                currentCarIndex = RentCarSystem.getCars().size() - 1;
            currentCar = RentCarSystem.getCars().get(currentCarIndex);
            setCarInformation(currentCar);
        }
    }//GEN-LAST:event_lblPreviousMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        
        String comparetorType = null;
        int min = -1, max= -1 , galleryId = -1;
        
        if(!((String)cbxPrice.getSelectedItem() == null) && !cbxPrice.getSelectedItem().equals("") ){
            comparetorType = (String)cbxPrice.getSelectedItem();
        }
        if(!((String)cbxGallery.getSelectedItem() == null) && !(cbxGallery.getSelectedItem().equals("")) && !((String)cbxGallery.getSelectedItem()).equals("All")){
             //galleryId = Integer.parseInt((String)cbxGallery.getSelectedItem());
             galleryId = RentCarSystem.getGalleryByName( cbxGallery.getSelectedItem().toString() ).getId();
        }
        max = checkValidInput(tbxMax,max);
        min = checkValidInput(tbxMin, min);
        
        if(comparetorType != null){
            RentCarSystem.sortCarList(RentCarSystem.getCars(), comparetorType);
        }
        RentCarSystem.getFilteredCarList().clear();
        RentCarSystem.getFilteredCarList().addAll(RentCarSystem.getCars());
        
        if(min != -1){
            sortByMin(min);
        }
        if(max != -1){
            sortByMax(max);
        }
        if(galleryId != -1){
            sortByGalleryId(galleryId);
        }
        
        if(RentCarSystem.getFilteredCarList().size()>0){
            isCarsFiltered = true;
            currentCarIndex = 0;
            currentCar = RentCarSystem.getFilteredCarList().get(currentCarIndex);
            setCarInformation(currentCar);
        }else{
            isCarsFiltered = false;
            currentCarIndex = 0;
            isCarsFiltered = false;
            currentCar = RentCarSystem.getCars().get(currentCarIndex);
            setCarInformation(currentCar);
        }
        
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        isCarsFiltered = false;
        RentCarSystem.getFilteredCarList().clear();
        RentCarSystem.getFilteredCarList().addAll(RentCarSystem.getCars());
        currentCarIndex = 0;
        currentCar = RentCarSystem.getCars().get(currentCarIndex);
        setCarInformation(currentCar);
        tbxMax.setText("");
        tbxMin.setText("");
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private void lblRentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRentMouseClicked
        
        try {
            lblCurrentCashOfUser.setText("$" + String.valueOf(customer.getTotalCash()));
            if ( pick_upDate_JDatechooser.getDate() == null || returnDate_JDatechooser.getDate() == null ) {
                System.out.println(""+ pick_upDate_JDatechooser.getDate() );
                System.out.println(""+ returnDate_JDatechooser.getDate());
                throw new NullPointerException();
            }
            
            if(  ((JTextField)pick_upDate_JDatechooser.getDateEditor()).getText().equalsIgnoreCase( ((JTextField)returnDate_JDatechooser.getDateEditor()).getText())  ) {
                throw new IllegalArgumentException("Pick-Up Date and Return Date cannot be same!!");
            } else if ( pick_upDate_JDatechooser.getDate().getTime() > returnDate_JDatechooser.getDate().getTime()  ){
                throw new IllegalArgumentException( "Pick-Up Date cannot be later than Return Date!!" );
            }
            
            double totalPayment = ((returnDate_JDatechooser.getDate().getTime() - pick_upDate_JDatechooser.getDate().getTime()) / 86_400_000) * currentCar.getPrice();
            lblTotalPaymentOfUser.setText(String.valueOf(totalPayment));
            lblTotalCashAfterRentalOfUser.setText(String.valueOf(customer.getTotalCash() - totalPayment));
            
            
        } catch( NullPointerException ex ) {
            HelperMethods.showErrorMessage("One of the Date is not choosen...", "Date Confusion");
        } catch ( IllegalArgumentException ex ) {
            HelperMethods.showErrorMessage(ex.getMessage(), "Date Confusion");
        }
    }//GEN-LAST:event_lblRentMouseClicked

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        //var totalDay = (int)(returnDate_JDatechooser.getDate().getTime() - pick_upDate_JDatechooser.getDate().getTime()) / 86_400_000;
        try {
            int totalDay = (int) ((returnDate_JDatechooser.getDate().getTime() - pick_upDate_JDatechooser.getDate().getTime()) / 86_400_000L);
            double totalCashAfterRental = Double.parseDouble( lblTotalCashAfterRentalOfUser.getText() );

            if ( totalCashAfterRental <= 0 )
                throw new IllegalArgumentException("Sir, you don't have enough money...");

            int galleyOwner_id = 0;
                Gallery gallery = RentCarSystem.getGalleryById( currentCar.getGalleryId() );
                for ( User user : RentCarSystem.getUserList() ) {
                    if ( user instanceof GalleryOwner ) {
                        if (  ((GalleryOwner)(user)).getGalleries().contains( gallery ) )
                            galleyOwner_id = ((GalleryOwner)(user)).getGalleryOwner_id();
                    }
                }
            
            int id = Order.getTotal_id();
                String promotionCode = textPromotionCode.getText();
                String fullName = customer.getFullName();
                String galOwnPhoNum = RentCarSystem.getGalleryOwnerById( galleyOwner_id ).getPhoneNumber();
                String custPhoneNum = customer.getPhoneNumber();
                String galOwnHomeAddr = RentCarSystem.getGalleryOwnerById( galleyOwner_id ).getHomeAddress();
                String custHomeAddr = customer.getHomeAddress();
                String brand = currentCar.getBrand();
                String model = currentCar.getModel();
                String rentDate = pick_upDate_JDatechooser.getDate().toString();
                String returnDate = returnDate_JDatechooser.getDate().toString();
                double amountPaid = Double.parseDouble(lblTotalPaymentOfUser.getText());
                int galleryId = currentCar.getGalleryId();
                int customerId = customer.getCustomerId();
                int carId = currentCar.getId();
                String imgPath = currentCar.getSmall_imgPath();
                double dailyPrice = currentCar.getPrice();
                Order order = null;
            
            if(!textPromotionCode.getText().isBlank() && !textPromotionCode.getText().isEmpty()){
                order = new Order(id, promotionCode, fullName, galOwnPhoNum, custPhoneNum, galOwnHomeAddr, custHomeAddr, brand, model, 
                                        rentDate, returnDate, amountPaid, galleryId, customerId, carId, imgPath, totalDay, dailyPrice);

                boolean result = RentCarSystem.createOrder( order );
                if(!result){
                    //Promotion exception
                }else{
                    setOrderList(RentCarSystem.getOrders());
                    
                }
            }else{
                order = new Order(id, fullName, galOwnPhoNum, custPhoneNum, galOwnHomeAddr, custHomeAddr, brand, model, 
                                        rentDate, returnDate, amountPaid, galleryId, customerId, carId, imgPath, totalDay, dailyPrice);
                RentCarSystem.createOrderNoPromotion( order );
                setOrderList(RentCarSystem.getOrders());
                
            }
            
            customer.setTotalCash( customer.getTotalCash() - amountPaid );
            RentCarSystem.updateUserInDatabase(customer, "customer");
            lblCurrentCashOfUser.setText( "$" + customer.getTotalCash() );
            lblUserCurrentCashValue.setText( "$" + customer.getTotalCash() );
            HelperMethods.showSuccessfulMessage("Renting the car is successful...", "Successful Rent");
        } catch( IllegalArgumentException ex ) {
            HelperMethods.showErrorMessage( ex.getMessage(), "Insufficient Balance");
        } catch ( NullPointerException ex ) {
            HelperMethods.showErrorMessage(ex.getMessage(), "Order couldn't be created...");
        } catch (SQLException ex) {
            HelperMethods.showErrorMessage("Order couldn't be created..", "Databse Error");
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnCheckPromotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckPromotionActionPerformed
        if(!isChecked){
            var totalDay = (int)(returnDate_JDatechooser.getDate().getTime() - pick_upDate_JDatechooser.getDate().getTime()) / 86_400_000;
            if(!textPromotionCode.getText().isBlank() && !textPromotionCode.getText().isEmpty()){
            PromotionCode pc = RentCarSystem.getPromotionCodeByCode(textPromotionCode.getText());
            if(pc!=null && RentCarSystem.isUsed(pc)==false && !lblTotalPaymentOfUser.getText().isEmpty()){
                double totalPayment = totalDay * currentCar.getPrice() - totalDay *  pc.getDiscount() * currentCar.getPrice() ;
                lblTotalPaymentOfUser.setText(String.valueOf(totalPayment));
                isChecked = true;
            }
        }
        }
    }//GEN-LAST:event_btnCheckPromotionActionPerformed

    private void listOrdersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listOrdersValueChanged
        
        if(isOrdersFiltered){
            currentOrderIndex = listOrders.getSelectedIndex()==-1?0:listOrders.getSelectedIndex();
            currentOrder = RentCarSystem.getFilteredOrderList().get(currentOrderIndex);
            setOrderDetails(currentOrder);
        }else{
            currentOrderIndex = listOrders.getSelectedIndex()==-1?0:listOrders.getSelectedIndex();
            currentOrder = RentCarSystem.getOrders().get(currentOrderIndex);
            setOrderDetails(currentOrder);
        }
        
        
    }//GEN-LAST:event_listOrdersValueChanged

    private void btnFilterOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterOrdersActionPerformed
        String comparetorType = null;
        int min = -1, max= -1 , galleryId = -1;
        
        if(!(!((String)cbxOrderPrice.getSelectedItem() == null) && cbxOrderPrice.getSelectedItem().equals("")) ){
            comparetorType = (String)cbxOrderPrice.getSelectedItem();
        }
        if(!((String)cbxOrderGalery.getSelectedItem() == null) && !(cbxOrderGalery.getSelectedItem().equals("")) && !((String)cbxOrderGalery.getSelectedItem()).equals("All")){
             //galleryId = Integer.parseInt((String)cbxOrderGalery.getSelectedItem());
             galleryId = RentCarSystem.getGalleryByName( cbxGallery.getSelectedItem().toString() ).getId();
        }
        
        max = checkValidInput(tbxMaxPrice,max);
        min = checkValidInput(tbxMinPrice, min);
        
        RentCarSystem.getFilteredOrderList().clear();
        RentCarSystem.getFilteredOrderList().addAll(RentCarSystem.getOrders());
        
        sortOrders(comparetorType);
        
        if(min != -1){
            sortByMinOrder(min);
        }
        if(max != -1){
            sortByMaxOrder(max);
        }
        if(galleryId != -1){
            sortByGalleryIdOrder(galleryId);
        }
        
        if(RentCarSystem.getFilteredOrderList().size()>0){
            isOrdersFiltered = true;
            currentOrderIndex = 0;
            currentOrder = RentCarSystem.getFilteredOrderList().get(currentOrderIndex);
            setOrderDetails(currentOrder);
            setOrderList(RentCarSystem.getFilteredOrderList());
        }else if(RentCarSystem.getOrders().size()>0){
            isOrdersFiltered = false;
            currentOrderIndex = 0;
            currentOrder = RentCarSystem.getOrders().get(currentOrderIndex);
            setOrderDetails(currentOrder);
            setOrderList(RentCarSystem.getOrders());
        }else{
            HelperMethods.showErrorMessage("You dont have any orders", "Empty order list");
        }
    }//GEN-LAST:event_btnFilterOrdersActionPerformed

    private void btnClearFilterOfOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterOfOrderActionPerformed
        isOrdersFiltered = false;
        RentCarSystem.getFilteredOrderList().clear();
        RentCarSystem.getFilteredOrderList().addAll(RentCarSystem.getOrders());
        currentOrderIndex = 0;
        currentOrder = RentCarSystem.getOrders().get(currentOrderIndex);
        setOrderDetails(currentOrder);
        setOrderList(RentCarSystem.getFilteredOrderList());
        tbxMaxPrice.setText("0");
        tbxMinPrice.setText("10000");
    }//GEN-LAST:event_btnClearFilterOfOrderActionPerformed

    private void lblChangePicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangePicMouseClicked
        // TODO add your handling code here:
        if ( jTogg_btn_modifyAcc.isSelected() ) {
            try {
                JFileChooser chooser = new JFileChooser( new File("").getAbsolutePath() + "\\src\\images\\" );
                chooser.showOpenDialog( null );
                File f = chooser.getSelectedFile();
                profPictPath = f.getName();
                profPictPath = "/images/" + profPictPath;
                System.out.println( profPictPath );
            } catch ( NullPointerException ex ) {
                HelperMethods.showErrorMessage("You didn't choose a picture", "Not Selected Picture");
                profPictPath = customer.getImgPath();
            }
        }

    }//GEN-LAST:event_lblChangePicMouseClicked

    private void lblChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChangePasswordMouseClicked
        // TODO add your handling code here:
        if ( jTogg_btn_modifyAcc.isSelected() ) {
            ResetPasswordPanel resetPasswordPanel = new ResetPasswordPanel( customer );
            resetPasswordPanel.setVisible( true );
            resetPasswordPanel.setLocationRelativeTo( null );
        }
    }//GEN-LAST:event_lblChangePasswordMouseClicked

    private void tbxNewPhoneNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxNewPhoneNumKeyTyped
        // TODO add your handling code here:
        if ( (evt.getKeyChar() >= '0' &&  evt.getKeyChar() <= '9') || evt.getKeyChar() == ' ' ) {

        } else {
            evt.consume();
        }

        if ( tbxNewPhoneNum.getText().isBlank() )
        tbxNewPhoneNum.setText( customer.getPhoneNumber() );
    }//GEN-LAST:event_tbxNewPhoneNumKeyTyped

    private void btnSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangesActionPerformed
        // TODO add your handling code here:
        if ( profPictPath == null )
            profPictPath = customer.getImgPath();
        
        try{
            Mail mail = RentCarSystem.getMailByName( tbxUserEmail.getText() );
            String phoneNum = tbxNewPhoneNum.getText();
            String homeAddress = tbxHomeAddr.getText();
            
            if( !HelperMethods.checkHomeAddress( tbxHomeAddr.getText() ) ) {
                throw new Exception("The home address must innclude just one \", \" regex");
            }
            
            HelperMethods.controlPhoneNum(phoneNum, customer.getUsername());
            
            if ( RentCarSystem.isMailUsedAnyOtherUser( mail.getMail_id(), customer ) )
                throw new Exception("This mail is already in use!!!");
            
            if ( customer.updateInformation(phoneNum, profPictPath, homeAddress, "customer", mail) ) {
                HelperMethods.showSuccessfulMessage("Updating profile information is successful", "Successful Update");
            }
                
            
        } catch( NullPointerException ex ) {
            HelperMethods.showErrorMessage("Unvalid mail name!!", "Not Found Mail");
            ex.printStackTrace();
        } catch( SQLException ex ) {
            HelperMethods.showErrorMessage("We couldn't update in the database... ", "Database Error");
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            HelperMethods.showErrorMessage(ex.getMessage(), "In use Mail or Phone Number");
        } catch (Exception ex) {
            HelperMethods.showErrorMessage(ex.getMessage(), "In use Mail or Phone Number");
        }
        
        jTogg_btn_modifyAcc.setSelected( false );
        initializeFields();
    }//GEN-LAST:event_btnSaveChangesActionPerformed

    private void jTogg_btn_modifyAccİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jTogg_btn_modifyAccİtemStateChanged
        // TODO add your handling code here:
        if ( evt.getStateChange() == evt.SELECTED ) {
            tbxUserEmail.setEditable( true );
            tbxNewPhoneNum.setEditable( true );
            tbxHomeAddr.setEditable( true );
        } else if ( evt.getStateChange() == evt.DESELECTED ) {
            tbxUserEmail.setEditable( false );
            tbxNewPhoneNum.setEditable( false );
            tbxHomeAddr.setEditable( false );
        }

    }//GEN-LAST:event_jTogg_btn_modifyAccİtemStateChanged

    private void lblPrintIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintIconMouseClicked
        // TODO add your handling code here:
        if ( currentOrder != null ) 
            customer.printOrder(currentOrder);
    }//GEN-LAST:event_lblPrintIconMouseClicked

    private void tbxMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMinKeyTyped
        // TODO add your handling code here:
        HelperMethods.wirteOnlyNumber(evt, tbxMin);
    }//GEN-LAST:event_tbxMinKeyTyped

    private void tbxMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMaxKeyTyped
        // TODO add your handling code here:
        HelperMethods.wirteOnlyNumber(evt, tbxMax);
    }//GEN-LAST:event_tbxMaxKeyTyped

    private void tbxMinPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMinPriceKeyTyped
        // TODO add your handling code here:
        HelperMethods.wirteOnlyNumber(evt, tbxMinPrice);
    }//GEN-LAST:event_tbxMinPriceKeyTyped

    private void tbxMaxPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxMaxPriceKeyTyped
        // TODO add your handling code here:
        HelperMethods.wirteOnlyNumber(evt, tbxMaxPrice);
    }//GEN-LAST:event_tbxMaxPriceKeyTyped

    private void tbxDepositedCashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbxDepositedCashKeyTyped
        // TODO add your handling code here:
        HelperMethods.wirteOnlyNumber(evt, tbxDepositedCash);
    }//GEN-LAST:event_tbxDepositedCashKeyTyped

    private void btnUserDepositCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserDepositCashActionPerformed
        double toBeAddedCash = Double.parseDouble(tbxDepositedCash.getText());
        customer.setTotalCash(toBeAddedCash + customer.getTotalCash());
        try {
            RentCarSystem.updateUserInDatabase(customer,"customer");
        } catch (SQLException ex) {
            //Logger.getLogger(CustomerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblUserCurrentCashValue.setText("$" + String.valueOf(customer.getTotalCash()));
        lblCurrentCashOfUser.setText("$" + String.valueOf(customer.getTotalCash()));
        
    }//GEN-LAST:event_btnUserDepositCashActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlLeftSide;
    private javax.swing.JPanel PnlParent;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnCheckPromotion;
    private javax.swing.JButton btnClearFilter;
    private javax.swing.JButton btnClearFilterOfOrder;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFilterOrders;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JButton btnUserDepositCash;
    private javax.swing.JComboBox<String> cbxGallery;
    private javax.swing.JComboBox<String> cbxOrderGalery;
    private javax.swing.JComboBox<String> cbxOrderPrice;
    private javax.swing.JComboBox<String> cbxPrice;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jTogg_btn_modifyAcc;
    private javax.swing.JLabel lblCarImage;
    private javax.swing.JLabel lblCarTitle;
    private javax.swing.JLabel lblChangePassword;
    private javax.swing.JLabel lblChangePic;
    private javax.swing.JLabel lblCurrentCash;
    private javax.swing.JLabel lblCurrentCashOfUser;
    private javax.swing.JLabel lblDailyPrice;
    private javax.swing.JLabel lblDailyPriceValue;
    private javax.swing.JLabel lblExitColor;
    private javax.swing.JLabel lblExitIcon;
    private javax.swing.JLabel lblExitText;
    private javax.swing.JLabel lblFuelCapacityValue;
    private javax.swing.JLabel lblFuelType;
    private javax.swing.JLabel lblFuelType6;
    private javax.swing.JLabel lblFuelType8;
    private javax.swing.JLabel lblFuelTypeValue;
    private javax.swing.JLabel lblGallery;
    private javax.swing.JLabel lblHomeAdd;
    private javax.swing.JLabel lblHomeColor;
    private javax.swing.JLabel lblHomeIcon;
    private javax.swing.JLabel lblHomeText;
    private javax.swing.JLabel lblKilometer;
    private javax.swing.JLabel lblKilometerValue;
    private javax.swing.JLabel lblNewPhoNum;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblOrderCarBrandVal;
    private javax.swing.JLabel lblOrderCarGalleryVal;
    private javax.swing.JLabel lblOrderCarImage;
    private javax.swing.JLabel lblOrderCarModelVal;
    private javax.swing.JLabel lblOrderChargeVal;
    private javax.swing.JLabel lblOrderGallery;
    private javax.swing.JLabel lblOrderPhoneNum;
    private javax.swing.JLabel lblOrderPhoneNumVal;
    private javax.swing.JLabel lblOrderPickupDate;
    private javax.swing.JLabel lblOrderPrice;
    private javax.swing.JLabel lblOrderPromotionCode;
    private javax.swing.JLabel lblOrderReturnVal;
    private javax.swing.JLabel lblOrderedCarBrand;
    private javax.swing.JLabel lblOrderedCarGallery;
    private javax.swing.JLabel lblOrderedCarMoedl;
    private javax.swing.JLabel lblOrderedCarPickUpDate;
    private javax.swing.JLabel lblOrderedCarReturnDate;
    private javax.swing.JLabel lblOrderedCarUsedPromotionCode;
    private javax.swing.JLabel lblOrderedCharge;
    private javax.swing.JLabel lblPickUpDate;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblPrintIcon;
    private javax.swing.JLabel lblProfileColor;
    private javax.swing.JLabel lblProfileIcon;
    private javax.swing.JLabel lblProfilePic;
    private javax.swing.JLabel lblProfileText;
    private javax.swing.JLabel lblPromotionCode;
    private javax.swing.JLabel lblRange;
    private javax.swing.JLabel lblRange1;
    private javax.swing.JLabel lblRent;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JLabel lblTheUserMail;
    private javax.swing.JLabel lblTheUserName;
    private javax.swing.JLabel lblTotalCashAfterRental;
    private javax.swing.JLabel lblTotalCashAfterRentalOfUser;
    private javax.swing.JLabel lblTotalPayment1;
    private javax.swing.JLabel lblTotalPaymentOfUser;
    private javax.swing.JLabel lblTransmissionType;
    private javax.swing.JLabel lblTransmissionTypeValue;
    private javax.swing.JLabel lblTrunkVolumeValue;
    private javax.swing.JLabel lblUserAge;
    private javax.swing.JLabel lblUserCurrentCash;
    private javax.swing.JLabel lblUserCurrentCash1;
    private javax.swing.JLabel lblUserCurrentCash2;
    private javax.swing.JLabel lblUserCurrentCashValue;
    private javax.swing.JLabel lblUserEmail;
    private javax.swing.JLabel lblUserFullName;
    private javax.swing.JLabel lblUserGender;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserTotalCash;
    private javax.swing.JLabel lblYear;
    private javax.swing.JLabel lblYearValue;
    private javax.swing.JList<String> listOrders;
    private com.toedter.calendar.JDateChooser pick_upDate_JDatechooser;
    private javax.swing.JPanel pnlApproval;
    private javax.swing.JPanel pnlExit;
    private javax.swing.JPanel pnlHomePage;
    private javax.swing.JPanel pnlInLayeredHomePageCarInfo;
    private javax.swing.JPanel pnlInLayeredHomePageOrderInfo;
    private javax.swing.JPanel pnlInPnlLayerdHomePagefilter;
    private javax.swing.JPanel pnlInPnlLayerdProfileFilter;
    private javax.swing.JLayeredPane pnlLayered;
    private javax.swing.JPanel pnlLayeredHomePage;
    private javax.swing.JPanel pnlLayeredProfile;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlProfileInfo;
    private javax.swing.JPanel pnlUserBankInformation;
    private javax.swing.JPanel pnlUserInformation;
    private com.toedter.calendar.JDateChooser returnDate_JDatechooser;
    private javax.swing.JTextField tbxDepositedCash;
    private javax.swing.JTextArea tbxHomeAddr;
    private javax.swing.JTextField tbxMax;
    private javax.swing.JTextField tbxMaxPrice;
    private javax.swing.JTextField tbxMin;
    private javax.swing.JTextField tbxMinPrice;
    private javax.swing.JTextField tbxNewPhoneNum;
    private javax.swing.JTextField tbxUserAge;
    private javax.swing.JTextField tbxUserEmail;
    private javax.swing.JTextField tbxUserFullName;
    private javax.swing.JTextField tbxUserGender;
    private javax.swing.JTextField tbxUserName;
    private javax.swing.JTextField textPromotionCode;
    // End of variables declaration//GEN-END:variables

    private void fulfillData() {
        
        initCarFields();
        initOrderFields();
        fulfillGalleryCbx();
        //fulfillGalleryOrderedCbx();
        
    }
    
    private void setCarInformation(Car currentCar) {
        
        lblCarTitle.setText( currentCar.getBrand() + " " + currentCar.getModel() + " " + currentCar.getType() );
        lblCarImage.setIcon(new ImageIcon(getClass().getResource(currentCar.getLarge_imgPath())));
        lblFuelTypeValue.setText(currentCar.getFuelType());
        lblTransmissionTypeValue.setText(currentCar.getTransmissionType());
        lblYearValue.setText(String.valueOf(currentCar.getYear()));
        lblDailyPriceValue.setText(String.valueOf(currentCar.getPrice()));
        lblKilometerValue.setText(String.valueOf(currentCar.getKm()));
        lblFuelCapacityValue.setText(String.valueOf(currentCar.getFuelCapacity()));
        lblTrunkVolumeValue.setText(String.valueOf(currentCar.getTrunkVolume()));
        
    }
    private void sortByMin(int min) {
        ArrayList<Car> toBeRemoved = new ArrayList<>();
        for (Car car : RentCarSystem.getFilteredCarList()) {
            if(car.getPrice() < min){
                toBeRemoved.add(car);
            }
        }
        RentCarSystem.getFilteredCarList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    private void sortByMax(int max) {
        ArrayList<Car> toBeRemoved = new ArrayList<>();
        for (Car car : RentCarSystem.getFilteredCarList()) {
            if(car.getPrice() > max){
                toBeRemoved.add(car);
            }
        }
        RentCarSystem.getFilteredCarList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    private void sortByGalleryId(int galleryIdf) {
        ArrayList<Car> toBeRemoved = new ArrayList<>();
        for (Car car : RentCarSystem.getFilteredCarList()) {
            if(car.getGalleryId() != galleryIdf){
                toBeRemoved.add(car);
            }
        }
        RentCarSystem.getFilteredCarList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    
    private void sortByMinOrder(int min){
        ArrayList<Order> toBeRemoved = new ArrayList<>();
        for (Order order : RentCarSystem.getFilteredOrderList()) {
            if(order.getAmountPaid() < min){
                toBeRemoved.add(order);
            }
        }
        RentCarSystem.getFilteredOrderList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    private void sortByMaxOrder(int max){
        ArrayList<Order> toBeRemoved = new ArrayList<>();
        for (Order order : RentCarSystem.getFilteredOrderList()) {
            if(order.getAmountPaid() > max){
                toBeRemoved.add(order);
            }
        }
        RentCarSystem.getFilteredOrderList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    private void sortByGalleryIdOrder(int id){
        ArrayList<Order> toBeRemoved = new ArrayList<>();
        for (Order order : RentCarSystem.getFilteredOrderList()) {
            if(order.getGalleryId() != id){
                toBeRemoved.add(order);
            }
        }
        RentCarSystem.getFilteredOrderList().removeAll(toBeRemoved);
        toBeRemoved = null;
    }
    private void sortOrders(String comparetorType) {
        if(comparetorType.equals("Ascending")){
            Collections.sort(RentCarSystem.getFilteredOrderList(),new Comparator<Order>(){
                @Override
                public int compare(Order o1, Order o2) {
                    if(o1.getAmountPaid()<o2.getAmountPaid()){
                        return -1;
                    }
                    if(o1.getAmountPaid()>o2.getAmountPaid()){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            });
        }
        else{
            Collections.sort(RentCarSystem.getFilteredOrderList(),new Comparator<Order>(){
                @Override
                public int compare(Order o1, Order o2) {
                    if(o1.getAmountPaid()<o2.getAmountPaid()){
                        return 1;
                    }
                    if(o1.getAmountPaid()>o2.getAmountPaid()){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            });
        }
    }
    private void setOrderDetails(Order currentOrderForSet) {
        lblOrderCarBrandVal.setText( currentOrderForSet.getBrand() );
            lblOrderCarModelVal.setText( currentOrderForSet.getModel() );
            lblOrderPhoneNumVal.setText( currentOrderForSet.getPhoneNumber() );
            lblOrderCarGalleryVal.setText( RentCarSystem.getGalleryById( currentOrderForSet.getGalleryId() ).getName() );
            lblOrderPickupDate.setText( currentOrderForSet.getRentDate().substring(0, 11 ) + currentOrderForSet.getRentDate().substring( currentOrderForSet.getRentDate().length() - 4));
            lblOrderReturnVal.setText( currentOrderForSet.getReturnDate().substring(0, 11) + currentOrderForSet.getReturnDate().substring( currentOrderForSet.getReturnDate().length() - 4));
            lblOrderPromotionCode.setText( currentOrderForSet.getPromotionCodeId() );
            lblOrderChargeVal.setText( String.format("$%.2f", currentOrderForSet.getAmountPaid() ) );
            lblOrderCarImage.setIcon( new ImageIcon( getClass().getResource( currentOrderForSet.getImgCarPath() ) ) );
        
    }
    private void setOrderList(ArrayList<Order> orders){
        
        DefaultListModel defaultListModel = new DefaultListModel();
        for (Order order : orders) {
            if(order.getCustomerId() == customer.getCustomerId()){
                defaultListModel.addElement(order.getBrand() + " " + order.getModel());
            }
        }
        currentOrder = orders.get(currentOrderIndex);
        listOrders.setModel(defaultListModel);
        setOrderDetails(currentOrder);
        System.out.println("" + orders);
    }
    
    private void fulfillGalleryCbx() {
        
        ArrayList<Gallery> galleries = new ArrayList<>( RentCarSystem.getGalleries() );
        for ( Gallery gallery : galleries ) {
            cbxOrderGalery.addItem( gallery.getName() );
            cbxGallery.addItem( gallery.getName() );
        }
    }
    private void fulfillGalleryOrderedCbx(){
        String [] galleryIds =  new String[RentCarSystem.getOrders().size()+ 1];
        galleryIds[0] = "All";
        for (int i = 1; i < RentCarSystem.getOrders().size(); i++) {
            galleryIds[i] = String.valueOf(RentCarSystem.getOrders().get(i).getGalleryId());
        }
        cbxOrderGalery.setModel(new javax.swing.DefaultComboBoxModel<>(galleryIds));
    }
    
    private int checkValidInput(JTextField tbxMax,int intValue) {
        
        
        if(!tbxMax.getText().isBlank() && !tbxMax.getText().isEmpty()){
            try {
                intValue = Integer.parseInt(tbxMax.getText());
                if(intValue<0){
                    intValue = -1;
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();//Entered not digit
            } catch (Exception ex) {
                ex.printStackTrace();//Invalid input
            }
        }
        return intValue;
    }

    private void initCarFields() {
        RentCarSystem.getFilteredCarList().addAll(RentCarSystem.getCars());
        currentCar = RentCarSystem.getCars().get(currentCarIndex);
        setCarInformation(currentCar);
        currentCarIndex++;
    }
    
    // Bu fonksiyonu yeniden yaz cunku customer'da herhangi bir order olmamasina ragmen order goruntuleyebiliyor.
    private void initOrderFields() {
        if(RentCarSystem.getOrders().size()>0){
            RentCarSystem.getFilteredOrderList().addAll(RentCarSystem.getOrders());
            currentOrder = RentCarSystem.getOrders().get(currentOrderIndex);
            setOrderDetails(currentOrder);
            setOrderList(RentCarSystem.getOrders());
        }
    }
    
}
