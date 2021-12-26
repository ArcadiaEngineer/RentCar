package GUI;


import Business.RentCarSystem;
import Entities.Concrete.Car;
import Entities.Concrete.Customer;
import Entities.Concrete.Order;
import Entities.Concrete.PromotionCode;
import Entities.Concrete.Visitor;
import Entities.Interface.Registerable;
import Helper.HelperMethods;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Order currentOrder;
    int currentOrderIndex = 0;
    DefaultListModel defaultListModel;
    
    
    public CustomerWindow(Customer customer) {
        this.customer = customer;
        initComponents();
        initializeFields();
        designColorsAndComponents();
        
    }
    
    public CustomerWindow(Registerable visitor) {
        this.visitor = (Visitor) visitor;
        initComponents();
        lblProfilePic.setIcon( new ImageIcon(getClass().getResource( "/images/visitor125.png" )));
        lblTheUserName.setText( ((Visitor)visitor).getFullName() );
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
        
        
        pick_upDate_JDatechooser.setBackground( new Color(51, 51, 51));
        pick_upDate_JDatechooser.getCalendarButton().setBackground( new Color(51, 51, 51));
        pick_upDate_JDatechooser.setSelectableDateRange(new Date(), new Date(new Date().getTime() + 2_592_000_000L));
        
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setBackground( new Color(51, 51, 51));
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setBorder( new CompoundBorder(new LineBorder(new Color(122, 72, 255), 1, true), new EmptyBorder(1, 4, 1, 1) ));
        
        
        
        returnDate_JDatechooser.setBackground( new Color(51, 51, 51));
        returnDate_JDatechooser.getCalendarButton().setBackground( new Color(51, 51, 51));
        returnDate_JDatechooser.getCalendarButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date start = pick_upDate_JDatechooser.getDate();
                returnDate_JDatechooser.setSelectableDateRange(start, new Date(start.getTime() + 2_592_000_000L));
            }
            
        });
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setBackground( new Color(51, 51, 51));
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setBorder( new CompoundBorder(new LineBorder(new Color(122, 72, 255), 1, true), new EmptyBorder(1, 4, 1, 1) ));
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
        tbxUserEmail.setEditable( false );
        tbxCurrentPass.setEchoChar((char)'\u2022');
        tbxNewPass.setEchoChar((char)'\u2022');
        tbxConfNewPass.setEchoChar((char)'\u2022');
        
        fulfillData();
        
    }
    
    public void visitorNotPermitedAction() {
        int choice = JOptionPane.showConfirmDialog(null, "You must register to the application. Do you want to register?", "Unsopperdet Operation", JOptionPane.YES_NO_OPTION);
                System.out.println("" + choice);
                
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
        pnlInLayeredHomePageCarInfo1 = new javax.swing.JPanel();
        lblOrderedCarBrand = new javax.swing.JLabel();
        lblOrderedCarMoedl = new javax.swing.JLabel();
        lblOrderedCarType = new javax.swing.JLabel();
        lblOrderedCharge = new javax.swing.JLabel();
        lblOrderedCarGallery = new javax.swing.JLabel();
        lblOrderedCarBrandValue = new javax.swing.JLabel();
        lblOrderedCarModelValue = new javax.swing.JLabel();
        lblOrderedCarPhoneValue = new javax.swing.JLabel();
        lblOrderedCarRentDateValue = new javax.swing.JLabel();
        btnDeleteOrder = new javax.swing.JButton();
        lblOrderedCarPickUpDate = new javax.swing.JLabel();
        lblOrderedCarGalleryIdValue = new javax.swing.JLabel();
        lblOrderedCarReturnDateValue = new javax.swing.JLabel();
        lblOrderedCarReturnDate = new javax.swing.JLabel();
        lblOrderedCarChargeValue = new javax.swing.JLabel();
        lblOrderDetailCarImg = new javax.swing.JLabel();
        lblOrderedCarPromotionCodeValue = new javax.swing.JLabel();
        lblOrderedCarUsedPromotionCode = new javax.swing.JLabel();
        btnDeleteOrder1 = new javax.swing.JButton();
        pnlUserInformation = new javax.swing.JPanel();
        lblUserName1 = new javax.swing.JLabel();
        lblUserGender = new javax.swing.JLabel();
        lblUserAge = new javax.swing.JLabel();
        lblUserEmail = new javax.swing.JLabel();
        lblUserFullName = new javax.swing.JLabel();
        tbxUserName = new javax.swing.JTextField();
        tbxUserFullName = new javax.swing.JTextField();
        tbxUserGender = new javax.swing.JTextField();
        tbxUserAge = new javax.swing.JTextField();
        tbxUserEmail = new javax.swing.JTextField();
        pnlUserBankInformation = new javax.swing.JPanel();
        lblUserCurrentCash = new javax.swing.JLabel();
        lblUserCurrentCashValue = new javax.swing.JLabel();
        lblUserCurrentCash1 = new javax.swing.JLabel();
        tbxDepositedCash = new javax.swing.JTextField();
        lblUserCurrentCash2 = new javax.swing.JLabel();
        lblUserTotalCash = new javax.swing.JLabel();
        btnUserDepositCash = new javax.swing.JButton();
        btnSaveChanges = new javax.swing.JButton();
        btnModifyUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        lblCurrentPassword = new javax.swing.JLabel();
        lblNewPassword = new javax.swing.JLabel();
        lblConfNewPassword = new javax.swing.JLabel();
        tbxCurrentPass = new javax.swing.JPasswordField();
        tbxNewPass = new javax.swing.JPasswordField();
        tbxConfNewPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        cbxPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kilometer", "Price", "Year" }));
        cbxPrice.setSelectedIndex(-1);

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

        tbxMax.setBackground(new java.awt.Color(51, 51, 51));
        tbxMax.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMax.setForeground(new java.awt.Color(204, 204, 204));
        tbxMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInPnlLayerdHomePagefilterLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cbxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRange, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbxMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbxMax, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInPnlLayerdHomePagefilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGallery, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cbxGallery, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
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
                                    .addComponent(lblTransmissionType, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
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
                                .addComponent(lblFuelType8, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTrunkVolumeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInLayeredHomePageCarInfoLayout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuelType6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKilometer, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(pnlApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalCashAfterRentalOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCurrentCashOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPaymentOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pick_upDate_JDatechooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
        tbxMinPrice.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        tbxMaxPrice.setBackground(new java.awt.Color(51, 51, 51));
        tbxMaxPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbxMaxPrice.setForeground(new java.awt.Color(204, 204, 204));
        tbxMaxPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tbxMaxPrice.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

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
                .addComponent(cbxOrderPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lblRange1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbxMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblOrderGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxOrderGalery, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btnFilterOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClearFilterOfOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(cbxOrderGalery)
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

        pnlInLayeredHomePageCarInfo1.setBackground(new java.awt.Color(51, 51, 51));
        pnlInLayeredHomePageCarInfo1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblOrderedCarBrand.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarBrand.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarBrand.setText("Brand:");
        lblOrderedCarBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarMoedl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarMoedl.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarMoedl.setText("Model:");
        lblOrderedCarMoedl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarType.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarType.setText("Phone N.");
        lblOrderedCarType.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCharge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCharge.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCharge.setText("Return Date:");
        lblOrderedCharge.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarGallery.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarGallery.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarGallery.setText("Rent Date:");
        lblOrderedCarGallery.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarBrandValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarBrandValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarModelValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarModelValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarPhoneValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarPhoneValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarRentDateValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarRentDateValue.setForeground(new java.awt.Color(204, 204, 204));

        btnDeleteOrder.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteOrder.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnDeleteOrder.setForeground(new java.awt.Color(122, 72, 255));
        btnDeleteOrder.setText("Delete");
        btnDeleteOrder.setToolTipText("");

        lblOrderedCarPickUpDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarPickUpDate.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarPickUpDate.setText("Gallery Id:");
        lblOrderedCarPickUpDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarGalleryIdValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarGalleryIdValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarReturnDateValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarReturnDateValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarReturnDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarReturnDate.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarReturnDate.setText("Charge:");
        lblOrderedCarReturnDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        lblOrderedCarChargeValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarChargeValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarPromotionCodeValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarPromotionCodeValue.setForeground(new java.awt.Color(204, 204, 204));

        lblOrderedCarUsedPromotionCode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderedCarUsedPromotionCode.setForeground(new java.awt.Color(204, 204, 204));
        lblOrderedCarUsedPromotionCode.setText("Prom. Code:");
        lblOrderedCarUsedPromotionCode.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));

        btnDeleteOrder1.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteOrder1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnDeleteOrder1.setForeground(new java.awt.Color(122, 72, 255));
        btnDeleteOrder1.setText("Print");
        btnDeleteOrder1.setToolTipText("");

        javax.swing.GroupLayout pnlInLayeredHomePageCarInfo1Layout = new javax.swing.GroupLayout(pnlInLayeredHomePageCarInfo1);
        pnlInLayeredHomePageCarInfo1.setLayout(pnlInLayeredHomePageCarInfo1Layout);
        pnlInLayeredHomePageCarInfo1Layout.setHorizontalGroup(
            pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblOrderedCarGallery, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOrderedCharge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOrderedCarType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOrderedCarMoedl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOrderedCarBrand, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDeleteOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblOrderedCarBrandValue, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                .addComponent(lblOrderedCarModelValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOrderedCarPhoneValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(btnDeleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblOrderedCarPickUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOrderedCarReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOrderedCarUsedPromotionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblOrderedCarChargeValue, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                    .addComponent(lblOrderedCarGalleryIdValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrderedCarPromotionCodeValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblOrderDetailCarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrderedCarRentDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOrderedCarReturnDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInLayeredHomePageCarInfo1Layout.setVerticalGroup(
            pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrderedCarBrandValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOrderedCarBrand)
                            .addComponent(lblOrderedCarPickUpDate)
                            .addComponent(lblOrderedCarGalleryIdValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addComponent(lblOrderedCarReturnDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblOrderedCarUsedPromotionCode))
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblOrderedCarMoedl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOrderedCarModelValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblOrderedCarType)
                                    .addComponent(lblOrderedCarPhoneValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlInLayeredHomePageCarInfo1Layout.createSequentialGroup()
                                .addComponent(lblOrderedCarChargeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblOrderedCarPromotionCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrderedCarGallery)
                            .addComponent(lblOrderedCarRentDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOrderedCharge)
                            .addComponent(lblOrderedCarReturnDateValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblOrderDetailCarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlInLayeredHomePageCarInfo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlUserInformation.setBackground(new java.awt.Color(51, 51, 51));
        pnlUserInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblUserName1.setBackground(new java.awt.Color(242, 243, 244));
        lblUserName1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserName1.setForeground(new java.awt.Color(204, 204, 204));
        lblUserName1.setText("User Name:");

        lblUserGender.setBackground(new java.awt.Color(242, 243, 244));
        lblUserGender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserGender.setForeground(new java.awt.Color(204, 204, 204));
        lblUserGender.setText("Gender:");

        lblUserAge.setBackground(new java.awt.Color(242, 243, 244));
        lblUserAge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserAge.setForeground(new java.awt.Color(204, 204, 204));
        lblUserAge.setText("Age");

        lblUserEmail.setBackground(new java.awt.Color(242, 243, 244));
        lblUserEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserEmail.setForeground(new java.awt.Color(204, 204, 204));
        lblUserEmail.setText("Email");

        lblUserFullName.setBackground(new java.awt.Color(242, 243, 244));
        lblUserFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserFullName.setForeground(new java.awt.Color(204, 204, 204));
        lblUserFullName.setText("Full Name");

        tbxUserName.setEditable(false);
        tbxUserName.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserName.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserName.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserName.setDisabledTextColor(new java.awt.Color(250, 250, 250));

        tbxUserFullName.setEditable(false);
        tbxUserFullName.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserFullName.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserFullName.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserFullName.setDisabledTextColor(new java.awt.Color(250, 250, 250));

        tbxUserGender.setEditable(false);
        tbxUserGender.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserGender.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserGender.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserGender.setDisabledTextColor(new java.awt.Color(250, 250, 250));

        tbxUserAge.setEditable(false);
        tbxUserAge.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserAge.setForeground(new java.awt.Color(102, 102, 102));
        tbxUserAge.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));
        tbxUserAge.setDisabledTextColor(new java.awt.Color(250, 250, 250));

        tbxUserEmail.setBackground(new java.awt.Color(51, 51, 51));
        tbxUserEmail.setForeground(new java.awt.Color(204, 204, 204));
        tbxUserEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        pnlUserBankInformation.setBackground(new java.awt.Color(51, 51, 51));
        pnlUserBankInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bank Account Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15), new java.awt.Color(122, 72, 255))); // NOI18N

        lblUserCurrentCash.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash.setText("Current Cash:");

        lblUserCurrentCashValue.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCashValue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCashValue.setForeground(new java.awt.Color(204, 204, 204));

        lblUserCurrentCash1.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash1.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash1.setText("Deposited Cash:");

        tbxDepositedCash.setBackground(new java.awt.Color(51, 51, 51));
        tbxDepositedCash.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        lblUserCurrentCash2.setBackground(new java.awt.Color(51, 51, 51));
        lblUserCurrentCash2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserCurrentCash2.setForeground(new java.awt.Color(204, 204, 204));
        lblUserCurrentCash2.setText("Total Cash: ");

        lblUserTotalCash.setBackground(new java.awt.Color(51, 51, 51));
        lblUserTotalCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserTotalCash.setForeground(new java.awt.Color(204, 204, 204));

        btnUserDepositCash.setBackground(new java.awt.Color(0, 0, 0));
        btnUserDepositCash.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnUserDepositCash.setForeground(new java.awt.Color(122, 72, 255));
        btnUserDepositCash.setText("Deposit Cash");
        btnUserDepositCash.setToolTipText("");

        javax.swing.GroupLayout pnlUserBankInformationLayout = new javax.swing.GroupLayout(pnlUserBankInformation);
        pnlUserBankInformation.setLayout(pnlUserBankInformationLayout);
        pnlUserBankInformationLayout.setHorizontalGroup(
            pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                        .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserCurrentCash, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserCurrentCash1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbxDepositedCash, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(lblUserCurrentCashValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                        .addComponent(lblUserCurrentCash2)
                        .addGap(62, 62, 62)
                        .addComponent(lblUserTotalCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(pnlUserBankInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                        .addComponent(lblUserCurrentCash2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(pnlUserBankInformationLayout.createSequentialGroup()
                        .addComponent(lblUserTotalCash, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(btnUserDepositCash, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSaveChanges.setBackground(new java.awt.Color(0, 0, 0));
        btnSaveChanges.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnSaveChanges.setForeground(new java.awt.Color(122, 72, 255));
        btnSaveChanges.setText("Save Changes");

        btnModifyUser.setBackground(new java.awt.Color(0, 0, 0));
        btnModifyUser.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnModifyUser.setForeground(new java.awt.Color(122, 72, 255));
        btnModifyUser.setText("Modify Account");
        btnModifyUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setBackground(new java.awt.Color(0, 0, 0));
        btnDeleteUser.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnDeleteUser.setForeground(new java.awt.Color(122, 72, 255));
        btnDeleteUser.setText("Delete Account");

        lblCurrentPassword.setBackground(new java.awt.Color(242, 243, 244));
        lblCurrentPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCurrentPassword.setForeground(new java.awt.Color(204, 204, 204));
        lblCurrentPassword.setText("Current Password:");

        lblNewPassword.setBackground(new java.awt.Color(242, 243, 244));
        lblNewPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNewPassword.setForeground(new java.awt.Color(204, 204, 204));
        lblNewPassword.setText("New Password:");

        lblConfNewPassword.setBackground(new java.awt.Color(242, 243, 244));
        lblConfNewPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblConfNewPassword.setForeground(new java.awt.Color(204, 204, 204));
        lblConfNewPassword.setText("Confirm New Password:");

        tbxCurrentPass.setEditable(false);
        tbxCurrentPass.setBackground(new java.awt.Color(51, 51, 51));
        tbxCurrentPass.setForeground(new java.awt.Color(204, 204, 204));
        tbxCurrentPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        tbxNewPass.setEditable(false);
        tbxNewPass.setBackground(new java.awt.Color(51, 51, 51));
        tbxNewPass.setForeground(new java.awt.Color(204, 204, 204));
        tbxNewPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        tbxConfNewPass.setEditable(false);
        tbxConfNewPass.setBackground(new java.awt.Color(51, 51, 51));
        tbxConfNewPass.setForeground(new java.awt.Color(204, 204, 204));
        tbxConfNewPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 72, 255), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 1)));

        javax.swing.GroupLayout pnlUserInformationLayout = new javax.swing.GroupLayout(pnlUserInformation);
        pnlUserInformation.setLayout(pnlUserInformationLayout);
        pnlUserInformationLayout.setHorizontalGroup(
            pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblUserEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUserName1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tbxUserEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(tbxUserAge, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tbxUserGender, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(tbxUserFullName)
                                    .addComponent(tbxUserName))
                                .addGap(18, 18, 18)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                        .addComponent(lblConfNewPassword)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tbxConfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCurrentPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNewPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(41, 41, 41)
                                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tbxNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tbxCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE))
                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                        .addGap(0, 106, Short.MAX_VALUE)
                        .addComponent(btnModifyUser, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveChanges)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)))
                .addComponent(pnlUserBankInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlUserInformationLayout.setVerticalGroup(
            pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlUserBankInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblUserName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tbxUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tbxUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tbxUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUserGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tbxUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tbxUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblUserAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlUserInformationLayout.createSequentialGroup()
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tbxCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCurrentPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tbxNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNewPassword))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblConfNewPassword)
                                    .addComponent(tbxConfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(104, 104, 104)))
                        .addGroup(pnlUserInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModifyUser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout pnlLayeredProfileLayout = new javax.swing.GroupLayout(pnlLayeredProfile);
        pnlLayeredProfile.setLayout(pnlLayeredProfileLayout);
        pnlLayeredProfileLayout.setHorizontalGroup(
            pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredProfileLayout.createSequentialGroup()
                .addGroup(pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayeredProfileLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInLayeredHomePageCarInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlUserInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInPnlLayerdProfileFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlLayeredProfileLayout.setVerticalGroup(
            pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayeredProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInPnlLayerdProfileFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLayeredProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(pnlInLayeredHomePageCarInfo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUserInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnModifyUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyUserActionPerformed
        // TODO add your handling code here:
        tbxCurrentPass.setEditable( true );
        tbxNewPass.setEditable( true );
        tbxConfNewPass.setEditable( true );
        tbxUserEmail.setEditable( true );
        
    }//GEN-LAST:event_btnModifyUserActionPerformed

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
        // TODO add your handling code here:
        ((JTextFieldDateEditor)pick_upDate_JDatechooser.getDateEditor()).setForeground(new Color(204, 204, 204));
        ((JTextFieldDateEditor)returnDate_JDatechooser.getDateEditor()).setForeground(new Color(204, 204, 204));
    }//GEN-LAST:event_textPromotionCodeKeyTyped

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        if(currentCarIndex<cars.size() && currentCarIndex > 0){
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
            currentCarIndex++;
        }else{
            currentCarIndex = 0;
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
            currentCarIndex++;
        }
    }//GEN-LAST:event_lblNextMouseClicked

    private void lblPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPreviousMouseClicked
        if(currentCarIndex>-1 && currentCarIndex < cars.size()){
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
            currentCarIndex--;
        }else{
            currentCarIndex = cars.size()-1;
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
            currentCarIndex--;
        }
    }//GEN-LAST:event_lblPreviousMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        
        String comparetorType = null;
        int min = -1, max= -1;
        int galleryId = -1;
        
        if(!(!((String)cbxPrice.getSelectedItem() == null) && cbxPrice.getSelectedItem().equals("")) ){
            comparetorType = (String)cbxPrice.getSelectedItem();
        }
        if(!((String)cbxGallery.getSelectedItem() == null) && !(cbxGallery.getSelectedItem().equals("")) && !((String)cbxGallery.getSelectedItem()).equals("All")){
             galleryId = Integer.parseInt((String)cbxGallery.getSelectedItem());
        }
        if(!tbxMax.getText().isBlank() && !tbxMax.getText().isEmpty()){
            try {
                max = Integer.parseInt(tbxMax.getText());
                if(max<0){
                    max = -1;
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();//Entered not digit
            } catch (Exception ex) {
                ex.printStackTrace();//Invalid input
            }
        }
        if(!tbxMin.getText().isBlank() && !tbxMin.getText().isEmpty()){
            try {
                min = Integer.parseInt(tbxMin.getText());
                if(min<0){
                    min = -1;
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();//Entered not digit
            } catch (Exception ex) {
                ex.printStackTrace();//Invalid input
            }
        }
        if(comparetorType != null){
            RentCarSystem.sortCarList(cars, comparetorType);
        }
        
        if(min != -1){
            sortByMin(min);
        }
        if(max != -1){
            sortByMax(max);
        }
        if(galleryId != -1){
            sortByGalleryId(galleryId);
        }
        
        if(cars.size()>0){
            currentCarIndex = 0;
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
        }else{
            //There is no car to suitable for the filtration message
            cars = RentCarSystem.getCars();
            currentCarIndex = 0;
            currentCar = cars.get(currentCarIndex);
            setCarInformation(currentCar);
        }
        
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterActionPerformed
        cars.clear();
        cars.addAll(RentCarSystem.getCars());
        currentCarIndex = 0;
        currentCar = cars.get(currentCarIndex);
        setCarInformation(currentCar);
        tbxMax.setText("");
        tbxMin.setText("");
    }//GEN-LAST:event_btnClearFilterActionPerformed

    private void lblRentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRentMouseClicked
        lblCurrentCashOfUser.setText("$" + String.valueOf(customer.getTotalCash()));
        if(pick_upDate_JDatechooser.getDate()!=null && returnDate_JDatechooser.getDate()!=null){
            var totalPayment = computeDiff(pick_upDate_JDatechooser.getDate(),returnDate_JDatechooser.getDate()).get(TimeUnit.DAYS) * currentCar.getPrice();
            lblTotalPaymentOfUser.setText(String.valueOf(totalPayment));
            lblTotalCashAfterRentalOfUser.setText(String.valueOf(customer.getTotalCash() - totalPayment));
        }
        else{
            //Dates can not be null
        }
    }//GEN-LAST:event_lblRentMouseClicked

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        
        if(!textPromotionCode.getText().isBlank() && !textPromotionCode.getText().isEmpty()){
            System.out.println("customerWindow");
            var result = RentCarSystem.createOrder(textPromotionCode.getText(), customer.getFullName(), customer.getPhoneNumber(), currentCar.getBrand(), currentCar.getModel(),
                    pick_upDate_JDatechooser.getDate().toString(), returnDate_JDatechooser.getDate().toString(), Double.parseDouble(lblTotalPaymentOfUser.getText()),
                    currentCar.getGalleryId(), currentCar.getId(),currentCar.getSmall_imgPath());
            if(!result){
                //Promotion exception
            }
        }else{
            RentCarSystem.createOrder(customer.getFullName(), customer.getPhoneNumber(), currentCar.getBrand(), currentCar.getModel(),
                    pick_upDate_JDatechooser.getDate().toString(), returnDate_JDatechooser.getDate().toString(), Double.parseDouble(lblTotalPaymentOfUser.getText()),
                    currentCar.getGalleryId(), currentCar.getId(), currentCar.getSmall_imgPath());
        }
        
        
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnCheckPromotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckPromotionActionPerformed
        if(!textPromotionCode.getText().isBlank() && !textPromotionCode.getText().isEmpty()){
            PromotionCode pc = RentCarSystem.getPromotionCodeByCode(textPromotionCode.getText());
            if(pc!=null && RentCarSystem.isUsed(pc)==false){
                double totalPayment = Double.parseDouble(lblTotalPaymentOfUser.getText()) - Double.parseDouble(lblTotalPaymentOfUser.getText())*pc.getDiscount();
                lblTotalPaymentOfUser.setText(String.valueOf(totalPayment));
            }
        }
    }//GEN-LAST:event_btnCheckPromotionActionPerformed

    private void listOrdersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listOrdersValueChanged
        currentOrderIndex = listOrders.getSelectedIndex()==-1?0:listOrders.getSelectedIndex();
        currentOrder = orders.get(currentOrderIndex);
        setOrderDetails(currentOrder);
    }//GEN-LAST:event_listOrdersValueChanged

    private void btnFilterOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterOrdersActionPerformed
        String comparetorType = null;
        int min = -1, max= -1;
        int galleryId = -1;
        
        if(!(!((String)cbxOrderPrice.getSelectedItem() == null) && cbxOrderPrice.getSelectedItem().equals("")) ){
            comparetorType = (String)cbxOrderPrice.getSelectedItem();
        }
        if(!((String)cbxOrderGalery.getSelectedItem() == null) && !(cbxOrderGalery.getSelectedItem().equals("")) && !((String)cbxOrderGalery.getSelectedItem()).equals("All")){
             galleryId = Integer.parseInt((String)cbxOrderGalery.getSelectedItem());
        }
        if(!tbxMaxPrice.getText().isBlank() && !tbxMaxPrice.getText().isEmpty()){
            try {
                max = Integer.parseInt(tbxMaxPrice.getText());
                if(max<0){
                    max = -1;
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();//Entered not digit
            } catch (Exception ex) {
                ex.printStackTrace();//Invalid input
            }
        }
        if(!tbxMinPrice.getText().isBlank() && !tbxMinPrice.getText().isEmpty()){
            try {
                min = Integer.parseInt(tbxMinPrice.getText());
                if(min<0){
                    min = -1;
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();//Entered not digit
            } catch (Exception ex) {
                ex.printStackTrace();//Invalid input
            }
        }
        if(comparetorType.equals("Ascending")){
            Collections.sort(orders,new Comparator<Order>(){
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
            Collections.sort(orders,new Comparator<Order>(){
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
        if(min != -1){
            SortByMinOrder(min);
        }
        if(max != -1){
            SortByMaxOrder(max);
        }
        if(galleryId != -1){
            SortByGalleryIdOrder(galleryId);
        }
        setOrderList();
        currentOrderIndex = 0;
        currentOrder = orders.get(currentOrderIndex);
        setOrderDetails(currentOrder);
    }//GEN-LAST:event_btnFilterOrdersActionPerformed

    private void btnClearFilterOfOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFilterOfOrderActionPerformed
        orders.clear();
        orders.addAll(RentCarSystem.getOrders());
        setOrderList();
        currentOrderIndex = 0;
        currentOrder = orders.get(currentOrderIndex);
        setOrderDetails(currentOrder);
    }//GEN-LAST:event_btnClearFilterOfOrderActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlLeftSide;
    private javax.swing.JPanel PnlParent;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnCheckPromotion;
    private javax.swing.JButton btnClearFilter;
    private javax.swing.JButton btnClearFilterOfOrder;
    private javax.swing.JButton btnDeleteOrder;
    private javax.swing.JButton btnDeleteOrder1;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnFilterOrders;
    private javax.swing.JButton btnModifyUser;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JButton btnUserDepositCash;
    private javax.swing.JComboBox<String> cbxGallery;
    private javax.swing.JComboBox<String> cbxOrderGalery;
    private javax.swing.JComboBox<String> cbxOrderPrice;
    private javax.swing.JComboBox<String> cbxPrice;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCarImage;
    private javax.swing.JLabel lblCarTitle;
    private javax.swing.JLabel lblConfNewPassword;
    private javax.swing.JLabel lblCurrentCash;
    private javax.swing.JLabel lblCurrentCashOfUser;
    private javax.swing.JLabel lblCurrentPassword;
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
    private javax.swing.JLabel lblHomeColor;
    private javax.swing.JLabel lblHomeIcon;
    private javax.swing.JLabel lblHomeText;
    private javax.swing.JLabel lblKilometer;
    private javax.swing.JLabel lblKilometerValue;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblOrderDetailCarImg;
    private javax.swing.JLabel lblOrderGallery;
    private javax.swing.JLabel lblOrderPrice;
    private javax.swing.JLabel lblOrderedCarBrand;
    private javax.swing.JLabel lblOrderedCarBrandValue;
    private javax.swing.JLabel lblOrderedCarChargeValue;
    private javax.swing.JLabel lblOrderedCarGallery;
    private javax.swing.JLabel lblOrderedCarGalleryIdValue;
    private javax.swing.JLabel lblOrderedCarModelValue;
    private javax.swing.JLabel lblOrderedCarMoedl;
    private javax.swing.JLabel lblOrderedCarPhoneValue;
    private javax.swing.JLabel lblOrderedCarPickUpDate;
    private javax.swing.JLabel lblOrderedCarPromotionCodeValue;
    private javax.swing.JLabel lblOrderedCarRentDateValue;
    private javax.swing.JLabel lblOrderedCarReturnDate;
    private javax.swing.JLabel lblOrderedCarReturnDateValue;
    private javax.swing.JLabel lblOrderedCarType;
    private javax.swing.JLabel lblOrderedCarUsedPromotionCode;
    private javax.swing.JLabel lblOrderedCharge;
    private javax.swing.JLabel lblPickUpDate;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblPrice;
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
    private javax.swing.JLabel lblUserName1;
    private javax.swing.JLabel lblUserTotalCash;
    private javax.swing.JLabel lblYear;
    private javax.swing.JLabel lblYearValue;
    private javax.swing.JList<String> listOrders;
    private com.toedter.calendar.JDateChooser pick_upDate_JDatechooser;
    private javax.swing.JPanel pnlApproval;
    private javax.swing.JPanel pnlExit;
    private javax.swing.JPanel pnlHomePage;
    private javax.swing.JPanel pnlInLayeredHomePageCarInfo;
    private javax.swing.JPanel pnlInLayeredHomePageCarInfo1;
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
    private javax.swing.JPasswordField tbxConfNewPass;
    private javax.swing.JPasswordField tbxCurrentPass;
    private javax.swing.JTextField tbxDepositedCash;
    private javax.swing.JTextField tbxMax;
    private javax.swing.JTextField tbxMaxPrice;
    private javax.swing.JTextField tbxMin;
    private javax.swing.JTextField tbxMinPrice;
    private javax.swing.JPasswordField tbxNewPass;
    private javax.swing.JTextField tbxUserAge;
    private javax.swing.JTextField tbxUserEmail;
    private javax.swing.JTextField tbxUserFullName;
    private javax.swing.JTextField tbxUserGender;
    private javax.swing.JTextField tbxUserName;
    private javax.swing.JTextField textPromotionCode;
    // End of variables declaration//GEN-END:variables

    private void fulfillData() {
        
        /////////////////////////CAR INFORMATION/////////////////////////////////
        cars.addAll(RentCarSystem.getCars());
        currentCar = cars.get(currentCarIndex);
        setCarInformation(currentCar);
        currentCarIndex++;
        //////////////////////////////////////////////////////////////////////////
        /////////////////////////GALLERY COMBOBOX/////////////////////////////////
        fulfillGalleryCbx();
        fulfillGalleryOrderedCbx();
        //////////////////////////////////////////////////////////////////////////
        /////////////////////////ORDER LIST///////////////////////////////////////
        orders.addAll(RentCarSystem.getOrders());
        defaultListModel = new DefaultListModel();
        for (Order order : orders) {
            if(order.getCustomerId()==customer.getCustomerId()){
                defaultListModel.addElement(order);
            }
        }
        currentOrder = orders.get(currentOrderIndex);
        listOrders.setModel(defaultListModel);
        setOrderDetails(currentOrder);
    }
    private void setCarInformation(Car currentCar) {
        
        lblCarTitle.setText(currentCar.getBrand());
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
        
        ArrayList<Car> filteredCars = new ArrayList<>();
        
        for (Car car : cars) {
            if(car.getPrice() > min){
                filteredCars.add(car);
            }
        }
        cars.clear();
        cars.addAll(filteredCars);
        
    }
    private void sortByMax(int max) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        
        for (Car car : cars) {
            if(car.getPrice() < max){
                filteredCars.add(car);
            }
        }
        cars.clear();
        cars.addAll(filteredCars);
    }
    private void sortByGalleryId(int galleryIdf) {
        ArrayList<Car> filteredCars = new ArrayList<>();
        
        for (Car car : cars) {
            if(car.getGalleryId()== galleryIdf){
                filteredCars.add(car);
            }
        }
        cars.clear();
        cars.addAll(filteredCars);
    }
    private void SortByMinOrder(int min){
        ArrayList<Order> filteredOrder = new ArrayList<>();
        
        for (Order order : orders) {
            if(order.getAmountPaid() > min){
                filteredOrder.add(order);
            }
        }
        orders.clear();
        orders.addAll(filteredOrder);
    }
    private void SortByMaxOrder(int max){
        ArrayList<Order> filteredOrder = new ArrayList<>();
        
        for (Order order : orders) {
            if(order.getAmountPaid() < max){
                filteredOrder.add(order);
            }
        }
        orders.clear();
        orders.addAll(filteredOrder);
    }
    private void SortByGalleryIdOrder(int id){
        ArrayList<Order> filteredOrder = new ArrayList<>();
        
        for (Order order : orders) {
            if(order.getGalleryId() == id){
                filteredOrder.add(order);
            }
        }
        orders.clear();
        orders.addAll(filteredOrder);
    }
    public static Map<TimeUnit,Long> computeDiff(Date date1, Date date2) {

        long diffInMillies = date2.getTime() - date1.getTime();

        //create the list
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);

        //create the result map of TimeUnit and difference
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
        long milliesRest = diffInMillies;

        for ( TimeUnit unit : units ) {

            //calculate difference in millisecond 
            long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;

            //put the result in the map
            result.put(unit,diff);
        }

        return result;
    }
    private void fulfillGalleryCbx() {
        String [] galleryIds =  new String[RentCarSystem.getGalleries().size()+ 1];
        galleryIds[0] = "All";
        for (int i = 1; i < RentCarSystem.getGalleries().size(); i++) {
            galleryIds[i] = String.valueOf(RentCarSystem.getGalleryById(i).getId());
        }
        cbxGallery.setModel(new javax.swing.DefaultComboBoxModel<>(galleryIds));
    }
    private void fulfillGalleryOrderedCbx(){
        String [] galleryIds =  new String[RentCarSystem.getOrders().size()+ 1];
        galleryIds[0] = "All";
        for (int i = 1; i < RentCarSystem.getOrders().size(); i++) {
            galleryIds[i] = String.valueOf(RentCarSystem.getOrders().get(i).getGalleryId());
        }
        cbxOrderGalery.setModel(new javax.swing.DefaultComboBoxModel<>(galleryIds));
    }
    private void setOrderDetails(Order currentOrderForSet) {
        lblOrderDetailCarImg.setIcon(new ImageIcon(getClass().getResource(currentOrderForSet.getImgCarPath())));
        lblOrderedCarBrandValue.setText(currentOrderForSet.getBrand());
        lblOrderedCarModelValue.setText(currentOrderForSet.getModel());
        lblOrderedCarPhoneValue.setText(currentOrderForSet.getPhoneNumber());
        lblOrderedCarRentDateValue.setText(currentOrderForSet.getRentDate());
        lblOrderedCarReturnDateValue.setText(currentOrderForSet.getReturnDate());
        lblOrderedCarPromotionCodeValue.setText((currentOrderForSet.getPromotionCodeId()==null?"No Promotion":currentOrderForSet.getPromotionCodeId()));
        lblOrderedCarGalleryIdValue.setText(String.valueOf(currentOrderForSet.getGalleryId()));
        lblOrderedCarChargeValue.setText(String.valueOf(currentOrderForSet.getAmountPaid()));
        
    }
    private void setOrderList(){
        defaultListModel = new DefaultListModel();
        for (Order order : orders) {
            if(order.getCustomerId()==customer.getCustomerId()){
                defaultListModel.addElement(order);
            }
        }
        currentOrder = orders.get(currentOrderIndex);
        listOrders.setModel(defaultListModel);
        setOrderDetails(currentOrder);
    }
}
