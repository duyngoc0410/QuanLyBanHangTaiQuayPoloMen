package View;

import Repository.ChatLieuRepository;
import Repository.KichThuocRepository;
import Repository.MauSacRepository;
import Response.ChatLieuResponse;
import Response.KichThuocResponse;
import Response.MauSacResponse;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ThuocTinh extends javax.swing.JFrame {

    private DefaultTableModel dtmMauSac;
    private DefaultTableModel dtmKichThuoc;
    private DefaultTableModel dtmChatLieu;
    private ArrayList<MauSacResponse> list_mau = new ArrayList<>();
    private ArrayList<ChatLieuResponse> list_cl = new ArrayList<>();
    private ArrayList<KichThuocResponse> list_kt = new ArrayList<>();
    private MauSacRepository mauSacRepo;
    private KichThuocRepository kichThuocRepo;
    private ChatLieuRepository chatLieuRepo;
    private int index = 0;

    public ThuocTinh() {
        initComponents();
        setLocationRelativeTo(null);
        dtmMauSac = (DefaultTableModel) this.tblMauSac.getModel();
        dtmKichThuoc = (DefaultTableModel) this.tblKichThuoc.getModel();
        dtmChatLieu = (DefaultTableModel) this.tblChatLieu.getModel();
        mauSacRepo = new MauSacRepository();
        kichThuocRepo = new KichThuocRepository();
        chatLieuRepo = new ChatLieuRepository();
        list_cl = (ArrayList<ChatLieuResponse>) chatLieuRepo.getAll();
        list_kt = (ArrayList<KichThuocResponse>) kichThuocRepo.getAll();
        list_mau = (ArrayList<MauSacResponse>) mauSacRepo.getAll();
        showDataTableSP(list_kt);
        showDataTableSPCL(list_cl);
        showDataTableSPMS(list_mau);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Ẩn cửa sổ thuộc tính
                dispose(); // hoặc setVisible(false);

                // Quay lại giao diện chính (MainForm, TrangChu...)
                new ViewSanPham().setVisible(true); //
            }
        });
        

    }
    
    public JPanel getMainPanel() {
        return jPanelThuocTinh;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanelThuocTinh = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panMau = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenMau = new javax.swing.JTextField();
        rdoHetMauSac = new javax.swing.JRadioButton();
        rdoConMauSac = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnThemMau = new javax.swing.JButton();
        btnSuaMau = new javax.swing.JButton();
        btnXoaMau = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimMauSac = new javax.swing.JTextField();
        panChatLieu = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenChatLieu = new javax.swing.JTextField();
        rdoHetChatLieu = new javax.swing.JRadioButton();
        rdoConChatLieu = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnThemChatLieu = new javax.swing.JButton();
        btnSuaChatLieu = new javax.swing.JButton();
        btnXoaChatLieu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTimChatLieu = new javax.swing.JTextField();
        panKichThuoc = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKichThuoc = new javax.swing.JTextField();
        rdoHetKichThuoc = new javax.swing.JRadioButton();
        rdoConKichThuoc = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        btnThemKichThuoc = new javax.swing.JButton();
        btnSuaKichThuoc = new javax.swing.JButton();
        btnXoaKichThuoc = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKichThuoc = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTimKichThuoc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        jPanelThuocTinh.setPreferredSize(new java.awt.Dimension(900, 500));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        panMau.setBackground(new java.awt.Color(128, 203, 196));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên Màu Sắc");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Trạng Thái");

        txtTenMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenMauActionPerformed(evt);
            }
        });

        buttonGroup7.add(rdoHetMauSac);
        rdoHetMauSac.setText("Hết hàng");

        buttonGroup7.add(rdoConMauSac);
        rdoConMauSac.setText("Còn hàng");
        rdoConMauSac.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnThemMau.setBackground(new java.awt.Color(0, 153, 255));
        btnThemMau.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMau.setText("Thêm");
        btnThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauActionPerformed(evt);
            }
        });

        btnSuaMau.setBackground(new java.awt.Color(255, 255, 0));
        btnSuaMau.setText("Sửa");
        btnSuaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMauActionPerformed(evt);
            }
        });

        btnXoaMau.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaMau.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaMau.setText("Xóa");
        btnXoaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaMau)
                    .addComponent(btnSuaMau)
                    .addComponent(btnThemMau))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaMau, btnThemMau, btnXoaMau});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaMau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaMau, btnThemMau, btnXoaMau});

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Màu", "Tên Màu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.setRowHeight(25);
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMauSac);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tìm Kiếm");

        txtTimMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panMauLayout = new javax.swing.GroupLayout(panMau);
        panMau.setLayout(panMauLayout);
        panMauLayout.setHorizontalGroup(
            panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMauLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panMauLayout.createSequentialGroup()
                        .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panMauLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panMauLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHetMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoConMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panMauLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(25, 25, 25))
        );

        panMauLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        panMauLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtTenMau, txtTimMauSac});

        panMauLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoConMauSac, rdoHetMauSac});

        panMauLayout.setVerticalGroup(
            panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMauLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panMauLayout.createSequentialGroup()
                        .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panMauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(rdoHetMauSac)
                            .addComponent(rdoConMauSac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMauLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panMauLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, rdoConMauSac, rdoHetMauSac});

        panMauLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTenMau, txtTimMauSac});

        jTabbedPane2.addTab("Màu Sắc", panMau);

        panChatLieu.setBackground(new java.awt.Color(128, 203, 196));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên Chất Liệu");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Trạng Thái");

        txtTenChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenChatLieuActionPerformed(evt);
            }
        });

        buttonGroup6.add(rdoHetChatLieu);
        rdoHetChatLieu.setText("Hết hàng");

        buttonGroup6.add(rdoConChatLieu);
        rdoConChatLieu.setText("Còn hàng");
        rdoConChatLieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnThemChatLieu.setBackground(new java.awt.Color(0, 153, 255));
        btnThemChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnThemChatLieu.setText("Thêm");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });

        btnSuaChatLieu.setBackground(new java.awt.Color(255, 255, 0));
        btnSuaChatLieu.setText("Sửa");
        btnSuaChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChatLieuActionPerformed(evt);
            }
        });

        btnXoaChatLieu.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaChatLieu.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaChatLieu.setText("Xóa");
        btnXoaChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaChatLieu)
                    .addComponent(btnSuaChatLieu)
                    .addComponent(btnThemChatLieu))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaChatLieu, btnThemChatLieu, btnXoaChatLieu});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemChatLieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuaChatLieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaChatLieu, btnThemChatLieu, btnXoaChatLieu});

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Chất Liệu", "Tên Chất Liệu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChatLieu.setRowHeight(25);
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChatLieu);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Tìm Kiếm");

        txtTimChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimChatLieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panChatLieuLayout = new javax.swing.GroupLayout(panChatLieu);
        panChatLieu.setLayout(panChatLieuLayout);
        panChatLieuLayout.setHorizontalGroup(
            panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panChatLieuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                    .addGroup(panChatLieuLayout.createSequentialGroup()
                        .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panChatLieuLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHetChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(rdoConChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panChatLieuLayout.createSequentialGroup()
                                .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenChatLieu)
                                    .addComponent(txtTimChatLieu))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(25, 25, 25))
        );

        panChatLieuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

        panChatLieuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoConChatLieu, rdoHetChatLieu});

        panChatLieuLayout.setVerticalGroup(
            panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panChatLieuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panChatLieuLayout.createSequentialGroup()
                        .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdoHetChatLieu)
                            .addComponent(rdoConChatLieu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panChatLieuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panChatLieuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6, rdoConChatLieu, rdoHetChatLieu});

        panChatLieuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtTenChatLieu, txtTimChatLieu});

        jTabbedPane2.addTab("Chất Liệu", panChatLieu);

        panKichThuoc.setBackground(new java.awt.Color(128, 203, 196));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tên Kích Cỡ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Trạng Thái");

        txtKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKichThuocActionPerformed(evt);
            }
        });

        buttonGroup5.add(rdoHetKichThuoc);
        rdoHetKichThuoc.setText("Hết hàng");

        buttonGroup5.add(rdoConKichThuoc);
        rdoConKichThuoc.setText("Còn hàng");
        rdoConKichThuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnThemKichThuoc.setBackground(new java.awt.Color(0, 153, 255));
        btnThemKichThuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKichThuoc.setText("Thêm");
        btnThemKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKichThuocActionPerformed(evt);
            }
        });

        btnSuaKichThuoc.setBackground(new java.awt.Color(255, 255, 0));
        btnSuaKichThuoc.setText("Sửa");
        btnSuaKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKichThuocActionPerformed(evt);
            }
        });

        btnXoaKichThuoc.setBackground(new java.awt.Color(255, 0, 0));
        btnXoaKichThuoc.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKichThuoc.setText("Xóa");
        btnXoaKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaKichThuoc)
                    .addComponent(btnSuaKichThuoc)
                    .addComponent(btnThemKichThuoc))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaKichThuoc, btnThemKichThuoc, btnXoaKichThuoc});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemKichThuoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuaKichThuoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaKichThuoc, btnThemKichThuoc, btnXoaKichThuoc});

        tblKichThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Kích Thước", "Tên Kích Thước", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKichThuoc.setRowHeight(25);
        tblKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKichThuocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKichThuoc);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        txtTimKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panKichThuocLayout = new javax.swing.GroupLayout(panKichThuoc);
        panKichThuoc.setLayout(panKichThuocLayout);
        panKichThuocLayout.setHorizontalGroup(
            panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKichThuocLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(panKichThuocLayout.createSequentialGroup()
                        .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panKichThuocLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoHetKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoConKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panKichThuocLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panKichThuocLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(25, 25, 25))
        );

        panKichThuocLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8, jLabel9});

        panKichThuocLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtKichThuoc, txtTimKichThuoc});

        panKichThuocLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoConKichThuoc, rdoHetKichThuoc});

        panKichThuocLayout.setVerticalGroup(
            panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKichThuocLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panKichThuocLayout.createSequentialGroup()
                        .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panKichThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rdoHetKichThuoc)
                            .addComponent(rdoConKichThuoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panKichThuocLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        panKichThuocLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel7, jLabel8, jLabel9, rdoConKichThuoc, rdoHetKichThuoc});

        panKichThuocLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtKichThuoc, txtTimKichThuoc});

        jTabbedPane2.addTab("Kích Thước", panKichThuoc);

        javax.swing.GroupLayout jPanelThuocTinhLayout = new javax.swing.GroupLayout(jPanelThuocTinh);
        jPanelThuocTinh.setLayout(jPanelThuocTinhLayout);
        jPanelThuocTinhLayout.setHorizontalGroup(
            jPanelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThuocTinhLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanelThuocTinhLayout.setVerticalGroup(
            jPanelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThuocTinhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMauActionPerformed

        int selectedRow = tblMauSac.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa màu sắc này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            MauSacResponse ms = list_mau.get(selectedRow);
            if (mauSacRepo.delete(ms.getId())) {
                list_mau.remove(selectedRow);
                showDataTableSPMS(list_mau);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaMauActionPerformed

    private void txtTenMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenMauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenMauActionPerformed

    private void txtTenChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenChatLieuActionPerformed

    private void btnXoaChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChatLieuActionPerformed

        int selectedRow = tblChatLieu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chất liệu này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ChatLieuResponse cl = list_cl.get(selectedRow);
            if (chatLieuRepo.delete(cl.getId())) {
                list_cl.remove(selectedRow);
                showDataTableSPCL(list_cl);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaChatLieuActionPerformed

    private void txtKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKichThuocActionPerformed

    private void btnXoaKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKichThuocActionPerformed

        int selectedRow = tblKichThuoc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa kích thước này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            KichThuocResponse kt = list_kt.get(selectedRow);
            if (kichThuocRepo.delete(kt.getId())) {
                list_kt.remove(selectedRow);
                showDataTableSP(list_kt);
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaKichThuocActionPerformed

    private void txtTimKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKichThuocActionPerformed
        String keyword = txtTimKichThuoc.getText().trim();
        list_kt = kichThuocRepo.search(keyword); // Bạn cần khai báo chatLieuService
        showDataTableSP(list_kt);
    }//GEN-LAST:event_txtTimKichThuocActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        try {
            int row = tblMauSac.getSelectedRow();
            if (row == -1) {
                return;
            }
            MauSacResponse ms = mauSacRepo.getAll().get(row);
            txtTenMau.setText(ms.getTenMauSac());
            boolean trangThai = ms.getTrangThai() == 1; // true = còn hàng, false = hết hàng
            rdoConMauSac.setSelected(trangThai);
            rdoHetMauSac.setSelected(!trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        try {
            int row = tblChatLieu.getSelectedRow();
            if (row == -1) {
                return;
            }
            ChatLieuResponse cl = chatLieuRepo.getAll().get(row);
            txtTenChatLieu.setText(cl.getTenChatLieu());
            boolean trangThai = cl.getTrangThai() == 1; // true = còn hàng, false = hết hàng
            rdoConChatLieu.setSelected(trangThai);
            rdoHetChatLieu.setSelected(!trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void tblKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKichThuocMouseClicked
        try {
            int row = tblKichThuoc.getSelectedRow();
            if (row == -1) {
                return;
            }
            KichThuocResponse kt = kichThuocRepo.getAll().get(row);
            txtKichThuoc.setText(kt.getTenKichThuoc());
            boolean trangThai = kt.getTrangThai() == 1; // true = còn hàng, false = hết hàng
            rdoConKichThuoc.setSelected(trangThai);
            rdoHetKichThuoc.setSelected(!trangThai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblKichThuocMouseClicked

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        MauSacResponse ms = getFormDataMS();
        if (ms != null) {
            if (checkFormCreateProductMS()) {
                if (isDuplicateMauSacName(ms.getTenMauSac())) {
                    JOptionPane.showMessageDialog(this, "Màu sắc đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mauSacRepo.add(ms)) {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc thành công!");
                    list_mau = (ArrayList<MauSacResponse>) mauSacRepo.getAll();
                    showDataTableSPMS(list_mau);
                    this.clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm màu sắc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void btnThemKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKichThuocActionPerformed
        KichThuocResponse newKichThuoc = getFormData();
        if (newKichThuoc != null) {
            if (checkFormCreateProduct()) {
                if (isDuplicateKichThuocName(newKichThuoc.getTenKichThuoc())) {
                    JOptionPane.showMessageDialog(this, "Kích thước đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (kichThuocRepo.add(newKichThuoc)) {
                    JOptionPane.showMessageDialog(this, "Thêm kích thước thành công!");
                    list_kt = kichThuocRepo.getAll();
                    showDataTableSP(list_kt);
                    this.clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm kích thước thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnThemKichThuocActionPerformed

    private void btnSuaKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKichThuocActionPerformed
        int index = tblKichThuoc.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một kích thước để sửa.");
            return;
        }
        KichThuocResponse kichThuocResponse = kichThuocRepo.getAll().get(index);
        KichThuocResponse newKichThuoc = getFormData();
        newKichThuoc.setId(kichThuocResponse.getId());
        if (!txtKichThuoc.getText().isBlank()) {
            if (kichThuocRepo.update(newKichThuoc)) {
                showDataTableSP(kichThuocRepo.getAll());
                JOptionPane.showMessageDialog(this, "Cập nhật kích thước thành công!");
                this.clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật kích thước thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên kích thước không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnSuaKichThuocActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        ChatLieuResponse chatLieu = getFormDataCL();
        if (chatLieu != null) {
            if (checkFormCreateProductCL()) {
                if (isDuplicateChatLieuName(chatLieu.getTenChatLieu())) {
                    JOptionPane.showMessageDialog(this, "Chất liệu đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (chatLieuRepo.add(chatLieu)) {
                    JOptionPane.showMessageDialog(this, "Thêm chất liệu thành công!");
                    list_cl = (ArrayList<ChatLieuResponse>) chatLieuRepo.getAll();
                    showDataTableSPCL(list_cl);
                    this.clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm chất liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void btnSuaChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChatLieuActionPerformed
        int index = tblChatLieu.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chất liệu để sửa.");
            return;
        }
        ChatLieuResponse cl = chatLieuRepo.getAll().get(index);
        ChatLieuResponse chatLieu = getFormDataCL();
        chatLieu.setId(cl.getId());
        if (!txtTenChatLieu.getText().isBlank()) {
            if (chatLieuRepo.update(chatLieu)) {
                showDataTableSPCL((ArrayList<ChatLieuResponse>) chatLieuRepo.getAll());
                JOptionPane.showMessageDialog(this, "Cập nhật chất liệu thành công!");
                this.clearFormCL();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật chất liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnSuaChatLieuActionPerformed

    private void btnSuaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMauActionPerformed
        int index = tblMauSac.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một màu sắc để sửa.");
            return;
        }
        MauSacResponse ms = mauSacRepo.getAll().get(index);
        MauSacResponse msMau = getFormDataMS();
        msMau.setId(ms.getId());
        if (!txtTenMau.getText().isBlank()) {
            if (mauSacRepo.update(msMau)) {
                showDataTableSPMS((ArrayList<MauSacResponse>) mauSacRepo.getAll());
                JOptionPane.showMessageDialog(this, "Cập nhật màu sắc thành công!");
                this.clearFormMS();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật màu sắc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnSuaMauActionPerformed

    private void txtTimChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimChatLieuActionPerformed
        String keyword = txtTimChatLieu.getText().trim();
        list_cl = chatLieuRepo.search(keyword); // Bạn cần khai báo chatLieuService
        showDataTableSPCL(list_cl);
    }//GEN-LAST:event_txtTimChatLieuActionPerformed

    private void txtTimMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimMauSacActionPerformed
        String keyword = txtTimMauSac.getText().trim();
        list_mau = mauSacRepo.search(keyword); // Bạn cần khai báo chatLieuService
        showDataTableSPMS(list_mau);
    }//GEN-LAST:event_txtTimMauSacActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThuocTinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaChatLieu;
    private javax.swing.JButton btnSuaKichThuoc;
    private javax.swing.JButton btnSuaMau;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemKichThuoc;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnXoaChatLieu;
    private javax.swing.JButton btnXoaKichThuoc;
    private javax.swing.JButton btnXoaMau;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelThuocTinh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panChatLieu;
    private javax.swing.JPanel panKichThuoc;
    private javax.swing.JPanel panMau;
    private javax.swing.JRadioButton rdoConChatLieu;
    private javax.swing.JRadioButton rdoConKichThuoc;
    private javax.swing.JRadioButton rdoConMauSac;
    private javax.swing.JRadioButton rdoHetChatLieu;
    private javax.swing.JRadioButton rdoHetKichThuoc;
    private javax.swing.JRadioButton rdoHetMauSac;
    private javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblKichThuoc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtTenChatLieu;
    private javax.swing.JTextField txtTenMau;
    private javax.swing.JTextField txtTimChatLieu;
    private javax.swing.JTextField txtTimKichThuoc;
    private javax.swing.JTextField txtTimMauSac;
    // End of variables declaration//GEN-END:variables

    private boolean isDuplicateKichThuocName(String tenKichThuoc) {
        for (KichThuocResponse kt : kichThuocRepo.getAll()) {
            if (kt.getTenKichThuoc().equalsIgnoreCase(tenKichThuoc.trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateChatLieuName(String tenChatLieu) {
        for (ChatLieuResponse cl : chatLieuRepo.getAll()) {
            if (cl.getTenChatLieu().equalsIgnoreCase(tenChatLieu.trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateMauSacName(String tenMauSac) {
        for (MauSacResponse ms : mauSacRepo.getAll()) {
            if (ms.getTenMauSac().equalsIgnoreCase(tenMauSac.trim())) {
                return true;
            }
        }
        return false;
    }

    public void showDataTableSP(ArrayList<KichThuocResponse> list) {
        dtmKichThuoc = (DefaultTableModel) tblKichThuoc.getModel();
        dtmKichThuoc.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);

        for (KichThuocResponse kt : list) {

            dtmKichThuoc.addRow(new Object[]{
                index.getAndIncrement(),
                kt.getMaKichThuoc(),
                kt.getTenKichThuoc(),
                kt.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void showDataTableSPMS(ArrayList<MauSacResponse> list) {
        dtmMauSac = (DefaultTableModel) tblMauSac.getModel();
        dtmMauSac.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);

        for (MauSacResponse ms : list) {

            dtmMauSac.addRow(new Object[]{
                index.getAndIncrement(),
                ms.getMaMauSac(),
                ms.getTenMauSac(),
                ms.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    public void showDataTableSPCL(ArrayList<ChatLieuResponse> list) {
        dtmChatLieu = (DefaultTableModel) tblChatLieu.getModel();
        dtmChatLieu.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);

        for (ChatLieuResponse cl : list) {

            dtmChatLieu.addRow(new Object[]{
                index.getAndIncrement(),
                cl.getMaChatLieu(),
                cl.getTenChatLieu(),
                cl.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    private KichThuocResponse getFormData() {
        try {
            return KichThuocResponse.builder()
                    .tenKichThuoc(txtKichThuoc.getText())
                    .trangThai(rdoConKichThuoc.isSelected() ? 1 : 0)
                    .build();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private ChatLieuResponse getFormDataCL() {
        try {
            return ChatLieuResponse.builder()
                    .tenChatLieu(txtTenChatLieu.getText())
                    .trangThai(rdoConChatLieu.isSelected() ? 1 : 0)
                    .build();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private MauSacResponse getFormDataMS() {
        try {
            return MauSacResponse.builder()
                    .tenMauSac(txtTenMau.getText())
                    .trangThai(rdoConMauSac.isSelected() ? 1 : 0)
                    .build();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean checkFormCreateProduct() {

        if (txtKichThuoc.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Tên kích thước không được để trống!");
            return false;
        }
        if (txtKichThuoc.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên kích thước không được quá 20 ký tự!");
            return false;
        }
        if (!rdoConKichThuoc.isSelected() && !rdoHetKichThuoc.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái cho kích thước!");
            return false;
        }
        if (isValidCouponCode(txtKichThuoc.getText())) {
            JOptionPane.showMessageDialog(this, "Tên kích thước chỉ được chứa chữ và số.");
            return false;
        }

        return true;
    }

    private boolean checkFormCreateProductCL() {

        if (txtTenChatLieu.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được để trống!");
            return false;
        }
        if (txtTenChatLieu.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu không được quá 20 ký tự!");
            return false;
        }
        if (!rdoConChatLieu.isSelected() && !rdoHetChatLieu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái cho chất liệu!");
            return false;
        }
        if (isValidCouponCode(txtTenChatLieu.getText())) {
            JOptionPane.showMessageDialog(this, "Tên chất liệu chỉ được chứa chữ và số.");
            return false;
        }

        return true;
    }

    private boolean checkFormCreateProductMS() {

        if (txtTenMau.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được để trống!");
            return false;
        }
        if (txtTenMau.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc không được quá 20 ký tự!");
            return false;
        }
        if (!rdoConMauSac.isSelected() && !rdoHetMauSac.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái cho màu sắc!");
            return false;
        }
        if (isValidCouponCode(txtTenMau.getText())) {
            JOptionPane.showMessageDialog(this, "Tên màu sắc chỉ được chứa chữ và số.");
            return false;
        }

        return true;
    }

    private boolean isValidCouponCode(String str) {
        // Biểu thức chính quy cho phép các ký tự chữ và số
        String regex = "^[$,^,&,*,<,>,|,!,;,:,  ,#,'',+,=,{}]+$";
        return str.matches(regex);
    }

    private void clearForm() {
        txtKichThuoc.setText("");
        txtTimKichThuoc.setText("");
        buttonGroup5.clearSelection();
        this.showDataTableSP(kichThuocRepo.getAll());
    }

    private void clearFormCL() {
        txtTenChatLieu.setText("");
        txtTimChatLieu.setText("");
        buttonGroup5.clearSelection();
        this.showDataTableSPCL((ArrayList<ChatLieuResponse>) chatLieuRepo.getAll());
    }

    private void clearFormMS() {
        txtTenMau.setText("");
        txtTimMauSac.setText("");
        buttonGroup5.clearSelection();
        this.showDataTableSPMS((ArrayList<MauSacResponse>) mauSacRepo.getAll());
    }
}
