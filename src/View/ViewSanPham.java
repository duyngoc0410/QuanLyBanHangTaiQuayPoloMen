package View;

import Repository.SanPhamRepository;
import Response.SanPhamResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Entity.SanPham;
import Entity.ThuongHieu;
import JDBCUtil.DBConnect;
import Repository.ChatLieuRepository;
import Repository.KichThuocRepository;
import Repository.MauSacRepository;
import Repository.SanPhamChiTietRepository;
import Repository.ThuongHieuRepository;
import Response.ChatLieuResponse;
import Response.KichThuocResponse;
import Response.MauSacResponse;
import Response.SanPhamChiTietResponse;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.SwingUtilities;

public class ViewSanPham extends javax.swing.JPanel {

    private SanPhamRepository sanPhamRepo;
    private SanPhamChiTietRepository sanPhamChiTietRepo;
    private ChatLieuRepository chatLieuRepo;
    private MauSacRepository mauSacRepo;
    private KichThuocRepository kichThuocRepo;
    private DefaultTableModel dtmSanPham;
    private DefaultTableModel dtmSanPhamCT;
    private ThuongHieuRepository thuongHieuRepo = new ThuongHieuRepository();
    private List<ThuongHieu> listTH = new ArrayList<>();
    private ArrayList<SanPhamChiTietResponse> list_spct = new ArrayList<>();
    private ArrayList<SanPham> list_sp = new ArrayList<>();
    private ArrayList<MauSacResponse> list_mau = new ArrayList<>();
    private ArrayList<ChatLieuResponse> list_chatLieu = new ArrayList<>();
    private ArrayList<KichThuocResponse> list_kichThuoc = new ArrayList<>();

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private int index = 0;

    public ViewSanPham() {
        initComponents();
        sanPhamRepo = new SanPhamRepository();
        sanPhamChiTietRepo = new SanPhamChiTietRepository();
        chatLieuRepo = new ChatLieuRepository();
        kichThuocRepo = new KichThuocRepository();
        mauSacRepo = new MauSacRepository();
        dtmSanPham = (DefaultTableModel) tblSanPham.getModel();
        dtmSanPhamCT = (DefaultTableModel) tblSanPhamCT.getModel();
        list_sp = sanPhamRepo.getAll();
        list_spct = sanPhamChiTietRepo.getAll();
        dateNgayNhap.setLocale(new Locale("vi", "VN"));
        fillToTableCT(list_spct);
        fillToTable(list_sp);
        loadComboBoxData();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSanPham = new javax.swing.JTextField();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi0 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblTenSanPham = new javax.swing.JLabel();
        txtTenSanPhamCT = new javax.swing.JTextField();
        rdoConHangCT = new javax.swing.JRadioButton();
        rdoHetHangCT = new javax.swing.JRadioButton();
        lblTrangThai = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        lblChatLieu = new javax.swing.JLabel();
        lblTenSanPham1 = new javax.swing.JLabel();
        txtSoLuongCT = new javax.swing.JTextField();
        lblTenSanPham2 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        lblNgayNhap = new javax.swing.JLabel();
        dateNgayNhap = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        btnThemCT = new javax.swing.JButton();
        btnSuaCT = new javax.swing.JButton();
        btnLamMoiCT = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        txtTimKiemCT = new javax.swing.JTextField();
        lblTimKiem = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanelThuocTinh = new javax.swing.JPanel();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(128, 203, 196));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");

        txtMaSP.setEditable(false);

        buttonGroup1.add(rdoConHang);
        rdoConHang.setText("Còn Hàng");

        buttonGroup1.add(rdoHetHang);
        rdoHetHang.setText("Hết Hàng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Trạng Thái");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCapNhat.setBackground(new java.awt.Color(255, 204, 0));
        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi0.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi0.setText("Làm Mới");
        btnLamMoi0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(btnLamMoi0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCapNhat, btnLamMoi0});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamMoi0, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCapNhat, btnLamMoi0});

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(89, 89, 89)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdoConHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoHetHang))
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoConHang)
                            .addComponent(rdoHetHang))))
                .addGap(75, 75, 75))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel5.setBackground(new java.awt.Color(128, 203, 196));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thương Hiệu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(30);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(128, 203, 196));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 265));

        lblTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham.setText("Tên Sản Phẩm");

        buttonGroup1.add(rdoConHangCT);
        rdoConHangCT.setText("Còn Hàng");

        buttonGroup1.add(rdoHetHangCT);
        rdoHetHangCT.setText("Hết Hàng");
        rdoHetHangCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetHangCTActionPerformed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai.setText("Trạng Thái");

        lblGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblGiaBan.setText("Giá Bán");

        lblMauSac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMauSac.setText("Màu Sắc");

        lblKichThuoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblKichThuoc.setText("Kích Thước");

        lblChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChatLieu.setText("Chất Liệu");

        lblTenSanPham1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham1.setText("Số Lượng");

        lblTenSanPham2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenSanPham2.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKichThuocActionPerformed(evt);
            }
        });

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblNgayNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgayNhap.setText("Ngày Nhập");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemCT.setBackground(new java.awt.Color(0, 204, 51));
        btnThemCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemCT.setText("Thêm");
        btnThemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTActionPerformed(evt);
            }
        });

        btnSuaCT.setBackground(new java.awt.Color(255, 204, 0));
        btnSuaCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaCT.setText("Sửa");
        btnSuaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTActionPerformed(evt);
            }
        });

        btnLamMoiCT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoiCT.setText("Làm Mới");
        btnLamMoiCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoiCT, btnSuaCT, btnThemCT});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThemCT)
                .addGap(18, 18, 18)
                .addComponent(btnSuaCT)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoiCT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoiCT, btnSuaCT, btnThemCT});

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblTenSanPham2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblTenSanPham1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSoLuongCT, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblGiaBan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblTenSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenSanPhamCT, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(lblChatLieu)
                            .addGap(18, 18, 18)
                            .addComponent(cboChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(lblKichThuoc)
                            .addGap(18, 18, 18)
                            .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblMauSac)
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoConHangCT)
                                    .addComponent(cboMauSac, 0, 223, Short.MAX_VALUE))
                                .addComponent(rdoHetHangCT, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(40, 40, 40)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblChatLieu, lblGiaBan, lblKichThuoc, lblMauSac, lblNgayNhap, lblTenSanPham, lblTenSanPham1, lblTenSanPham2, lblTrangThai});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane3, txtGiaBan, txtSoLuongCT, txtTenSanPhamCT});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboChatLieu, cboKichThuoc, cboMauSac, dateNgayNhap});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNgayNhap)
                                    .addComponent(dateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKichThuoc))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblChatLieu)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSanPhamCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenSanPham, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblGiaBan)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuongCT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenSanPham1))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMauSac))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTrangThai)
                                    .addComponent(rdoConHangCT)
                                    .addComponent(rdoHetHangCT)))
                            .addComponent(lblTenSanPham2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblChatLieu, lblGiaBan, lblKichThuoc, lblMauSac, lblNgayNhap, lblTenSanPham, lblTenSanPham1, lblTenSanPham2, lblTrangThai});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboChatLieu, cboKichThuoc, cboMauSac, dateNgayNhap});

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGiaBan, txtTenSanPhamCT});

        jPanel11.setBackground(new java.awt.Color(128, 203, 196));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SPCT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Kích Thước", "Chất Liệu", "Giá", "Mô Tả", "Số Lượng", "Ngày Nhập", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamCT.setRowHeight(30);
        tblSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamCT);

        txtTimKiemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemCTActionPerformed(evt);
            }
        });

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTimKiem.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTimKiem))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel7);

        javax.swing.GroupLayout jPanelThuocTinhLayout = new javax.swing.GroupLayout(jPanelThuocTinh);
        jPanelThuocTinh.setLayout(jPanelThuocTinhLayout);
        jPanelThuocTinhLayout.setHorizontalGroup(
            jPanelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
        );
        jPanelThuocTinhLayout.setVerticalGroup(
            jPanelThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanelThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc Tính", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        ThuocTinh thuocTinhFrame = new ThuocTinh();
        JPanel thuocTinhPanel = thuocTinhFrame.getMainPanel();

        jPanelThuocTinh.setLayout(new BorderLayout());
        jPanelThuocTinh.removeAll();
        jPanelThuocTinh.add(thuocTinhPanel, BorderLayout.CENTER);
        jPanelThuocTinh.revalidate();
        jPanelThuocTinh.repaint();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void txtTimKiemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemCTActionPerformed
        String keyword = txtTimKiemCT.getText();
        ArrayList<SanPhamChiTietResponse> ketQuaTimKiem = sanPhamChiTietRepo.search(keyword);
        if (ketQuaTimKiem != null) {
            fillToTableCT(ketQuaTimKiem);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp.");
        }
    }//GEN-LAST:event_txtTimKiemCTActionPerformed

    private void tblSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTMouseClicked
        int row = tblSanPhamCT.getSelectedRow();
        if (row == -1) {
            return;
        }

        SanPhamChiTietResponse spct = sanPhamChiTietRepo.getAll().get(row);
        String giaVND = currencyFormat.format(spct.getGia());
        txtTenSanPhamCT.setText(spct.getTenSanPham());
        txtGiaBan.setText(giaVND);
        txtSoLuongCT.setText(String.valueOf(spct.getSoLuong()));
        txtMoTa.setText(spct.getMoTa());
        boolean trangThai = spct.getTrangThai() == 1; // true = còn hàng, false = hết hàng
        rdoConHangCT.setSelected(trangThai);
        rdoHetHangCT.setSelected(!trangThai);
        cboChatLieu.setSelectedItem(spct.getTenChatLieu());
        cboKichThuoc.setSelectedItem(spct.getTenKichThuoc()); // Set kích thước
        cboMauSac.setSelectedItem(spct.getTenMauSac());
        dateNgayNhap.setDate(spct.getNgayNhap());
    }//GEN-LAST:event_tblSanPhamCTMouseClicked

    private void btnLamMoiCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiCTActionPerformed
        clearCT();
    }//GEN-LAST:event_btnLamMoiCTActionPerformed

    private void btnSuaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTActionPerformed
        try {
            int row = tblSanPhamCT.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm chi tiết để sửa.");
                return;
            }

            // Lấy SPCT gốc
            SanPhamChiTietResponse spctOld = list_spct.get(row);
            int idSPCT = spctOld.getId();
            int idSanPham = spctOld.getIdSanPham();
            int trangThaiCu = spctOld.getTrangThai();
            int soLuongCu = spctOld.getSoLuong();

            // Không cho sửa tên sản phẩm
            String tenSPForm = txtTenSanPhamCT.getText().trim();
            String tenSPGoc = spctOld.getTenSanPham().trim();
            if (!tenSPForm.equalsIgnoreCase(tenSPGoc)) {
                JOptionPane.showMessageDialog(this, "Không được phép sửa Tên sản phẩm!");
                txtTenSanPhamCT.setText(tenSPGoc);
                return;
            }

            // Lấy dữ liệu mới
            String kichCo = cboKichThuoc.getSelectedItem().toString();
            String chatLieu = cboChatLieu.getSelectedItem().toString();
            String mauSac = cboMauSac.getSelectedItem().toString();

            int soLuongMoi = Integer.parseInt(txtSoLuongCT.getText().trim());
            if (soLuongMoi < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không được âm!");
                return;
            }

            String giaBanText = txtGiaBan.getText().replaceAll("[^\\d]", "");
            if (giaBanText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ!");
                return;
            }

            BigDecimal gia = new BigDecimal(giaBanText);
            if (gia.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Giá bán không được âm!");
                return;
            }

            String moTa = txtMoTa.getText().trim();
            Date ngayNhap = dateNgayNhap.getDate();
            int trangThaiMoi = rdoConHangCT.isSelected() ? 1 : 0;

            // Lấy ID các thuộc tính
            int idKichThuoc = new KichThuocRepository().getIdByTen(kichCo);
            int idChatLieu = new ChatLieuRepository().getIdByTen(chatLieu);
            int idMauSac = new MauSacRepository().getIdByTen(mauSac);

            // Gán lại
            SanPhamChiTietResponse spctNew = new SanPhamChiTietResponse();
            spctNew.setId(idSPCT);
            spctNew.setIdSanPham(idSanPham);
            spctNew.setIdKichThuoc(idKichThuoc);
            spctNew.setIdChatLieu(idChatLieu);
            spctNew.setIdMauSac(idMauSac);
            spctNew.setSoLuong(soLuongMoi);
            spctNew.setGia(gia);
            spctNew.setMoTa(moTa);
            spctNew.setNgayNhap(ngayNhap);
            spctNew.setTrangThai(trangThaiMoi);

            // Cập nhật
            SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
            boolean updated = spctRepo.update(spctNew);

            if (updated) {
                int tongSoLuong = spctRepo.tinhTongSoLuongTheoIdSanPham(idSanPham);
                list_sp = new SanPhamRepository().getAllWithSoLuong();
                list_spct = spctRepo.getAll();
                fillToTable(list_sp);
                fillToTableCT(list_spct);
                clearCT();

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán hoặc số lượng không hợp lệ!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSuaCTActionPerformed

    private void btnThemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTActionPerformed
        try {
            String tenSP = txtTenSanPhamCT.getText().trim();
            String kichCo = cboKichThuoc.getSelectedItem().toString();
            String chatLieu = cboChatLieu.getSelectedItem().toString();
            String mauSac = cboMauSac.getSelectedItem().toString();
            String soLuongStr = txtSoLuongCT.getText().trim();
            String giaBanText = txtGiaBan.getText().replaceAll("[^\\d]", ""); // chỉ giữ số
            String moTa = txtMoTa.getText().trim();
            Date ngayNhap = dateNgayNhap.getDate();
            int trangThai = rdoConHangCT.isSelected() ? 1 : 0;

            // ==== VALIDATE ====
            if (tenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm!");
                return;
            }

            if (kichCo.equals("Chọn") || chatLieu.equals("Chọn") || mauSac.equals("Chọn")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ Kích thước, Chất liệu và Màu sắc!");
                return;
            }

            if (soLuongStr.isEmpty() || !soLuongStr.matches("\\d+") || Integer.parseInt(soLuongStr) <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương!");
                return;
            }

            if (giaBanText.isEmpty() || new BigDecimal(giaBanText).compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
                return;
            }

            if (ngayNhap == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhập!");
                return;
            }

            int soLuong = Integer.parseInt(soLuongStr);
            BigDecimal giaBan = new BigDecimal(giaBanText);

            // ==== Tiếp tục xử lý ====
            SanPhamRepository spRepo = new SanPhamRepository();
            SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();

            // 1. Kiểm tra sản phẩm theo tên
            SanPham sp = spRepo.findByTenSanPham(tenSP);
            String maSP;
            int idSanPham;
            if (sp == null) {
                maSP = spRepo.generateMaSanPhamMoi();
                sp = new SanPham();
                sp.setMaSanPham(maSP);
                sp.setTenSanPham(tenSP);
                sp.setTrangThai(trangThai);
                spRepo.insert(sp);
                sp = spRepo.findByMaSanPham(maSP);
                if (sp == null) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi tạo sản phẩm mới!");
                    return;
                }
                list_sp = spRepo.getAll();
                fillToTable(list_sp);
            }
            idSanPham = sp.getId();

            // 2. Lấy id các thuộc tính
            int idKichThuoc = new KichThuocRepository().getIdByTen(kichCo);
            int idChatLieu = new ChatLieuRepository().getIdByTen(chatLieu);
            int idMauSac = new MauSacRepository().getIdByTen(mauSac);

            // 3. Kiểm tra chi tiết trùng
            SanPhamChiTietResponse spctTonTai = spctRepo.findChiTietTrungLap(
                idSanPham, idKichThuoc, idChatLieu, idMauSac, giaBan, moTa);

            if (spctTonTai != null) {
                int soLuongMoi = spctTonTai.getSoLuong() + soLuong;
                spctRepo.updateSoLuong(spctTonTai.getId(), soLuongMoi);
            } else {
                SanPhamChiTietResponse spct = new SanPhamChiTietResponse();
                spct.setIdSanPham(idSanPham);
                spct.setIdKichThuoc(idKichThuoc);
                spct.setIdChatLieu(idChatLieu);
                spct.setIdMauSac(idMauSac);
                spct.setSoLuong(soLuong);
                spct.setGia(giaBan);
                spct.setMoTa(moTa);
                spct.setNgayNhap(ngayNhap);
                spct.setTrangThai(trangThai);
                spctRepo.insert(spct);
            }

            JOptionPane.showMessageDialog(this, "Thêm sản phẩm chi tiết thành công.");
            list_sp = spRepo.getAll();
            list_spct = spctRepo.getAll();
            fillToTable(list_sp);
            fillToTableCT(list_spct);
            clearCT();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng hoặc giá bán không hợp lệ!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnThemCTActionPerformed

    private void rdoHetHangCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetHangCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetHangCTActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String keyword = txtTimKiem.getText().trim();
        ArrayList<SanPham> ketQuaTimKiem = sanPhamRepo.search(keyword);
        if (ketQuaTimKiem != null && !ketQuaTimKiem.isEmpty()) {
            fillToTable(ketQuaTimKiem);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp.");
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }

        // Lấy sản phẩm từ danh sách đã đổ vào bảng
        SanPham sp = list_sp.get(row);

        // Tính tổng số lượng từ các chi tiết sản phẩm có trạng thái còn hàng
        int tongSoLuong = new SanPhamChiTietRepository().getTongSoLuongByMaSanPham(sp.getMaSanPham());

        // Gán dữ liệu lên form
        txtMaSP.setText(sp.getMaSanPham());
        txtTenSanPham.setText(sp.getTenSanPham());
        boolean trangThai = sp.getTrangThai() == 1;
        rdoConHang.setSelected(trangThai);
        rdoHetHang.setSelected(!trangThai);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnLamMoi0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi0ActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoi0ActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần cập nhật.");
            return;
        }

        // Lấy dữ liệu mới từ giao diện
        String tenMoi = txtTenSanPham.getText().trim();
        if (tenMoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống.");
            return;
        }
        int trangThaiMoi = rdoConHang.isSelected() ? 1 : 0;

        String maSP = tblSanPham.getValueAt(selectedRow, 1).toString(); // Cột 1 mới là mã SP
        System.out.println("Giá trị lấy từ bảng tại cột 1: [" + maSP + "]");

        SanPhamRepository sanPhamRepo = new SanPhamRepository();
        SanPham sp = sanPhamRepo.findByMaSanPham(maSP);
        if (sp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.");
            return;
        }

        // Cập nhật bảng SanPham
        boolean capNhatSP = sanPhamRepo.updateTenVaTrangThaiByMa(maSP, tenMoi, trangThaiMoi);

        // Cập nhật bảng SanPhamChiTiet theo IdSanPham
        SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
        boolean capNhatSPCT = spctRepo.updateTrangThaiByIdSanPham(sp.getId(), trangThaiMoi);
        if (capNhatSP && capNhatSPCT) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
            clear();
            list_sp = sanPhamRepo.getAll();  // <- Tải lại danh sách mới từ DB
            fillToTable(list_sp);
            list_spct = spctRepo.getAll();  // <- Cập nhật bảng chi tiết
            fillToTableCT(list_spct);

        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cboKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKichThuocActionPerformed

    private boolean isDialogOpen = false;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi0;
    private javax.swing.JButton btnLamMoiCT;
    private javax.swing.JButton btnSuaCT;
    private javax.swing.JButton btnThemCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private com.toedter.calendar.JDateChooser dateNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelThuocTinh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblNgayNhap;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTenSanPham1;
    private javax.swing.JLabel lblTenSanPham2;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoConHangCT;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoHetHangCT;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamCT;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongCT;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTenSanPhamCT;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemCT;
    // End of variables declaration//GEN-END:variables

    private boolean isValidCouponCode(String str) {
        // Biểu thức chính quy cho phép các ký tự chữ và số
        String regex = "^[$,^,&,*,<,>,|,!,;,:,  ,#,'',+,=,{}]+$";
        return str.matches(regex);
    }

    private boolean checkFormCreateProduct() {

        if (txtTenSanPham.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            return false;
        }
        if (txtTenSanPham.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được quá 20 ký tự!");
            return false;
        }
        if (!rdoConHang.isSelected() && !rdoHetHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái cho sản phẩm!");
            return false;
        }
        if (isValidCouponCode(txtMaSP.getText())) {
            JOptionPane.showMessageDialog(this, "Tên mã sản phẩm chỉ được chứa chữ và số.");
            return false;
        }
        if (isValidCouponCode(txtTenSanPham.getText())) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm chỉ được chứa chữ và số.");
            return false;
        }

        return true;
    }

    private void fillToTableCT(ArrayList<SanPhamChiTietResponse> list_spct) {
        dtmSanPhamCT.setRowCount(0);
        index = 1;
        for (SanPhamChiTietResponse spct : list_spct) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String giaVND = currencyFormat.format(spct.getGia());
            dtmSanPhamCT.addRow(new Object[]{
                index++,
                spct.getMaSanPhamCT(),
                spct.getMaSanPham(),
                spct.getTenSanPham(),
                spct.getTenMauSac(),
                spct.getTenKichThuoc(),
                spct.getTenChatLieu(),
                giaVND,
                spct.getMoTa(),
                spct.getSoLuong(),
                spct.getNgayNhap() != null ? sdf.format(spct.getNgayNhap()) : "",
                spct.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    private void loadComboBoxData() {
        try {
            // Xóa toàn bộ dữ liệu trước khi load mới
            cboChatLieu.removeAllItems();
            cboKichThuoc.removeAllItems();
            cboMauSac.removeAllItems();

            // Lấy danh sách từ DB
            List<ChatLieuResponse> listChatLieu = chatLieuRepo.getAll();
            List<KichThuocResponse> listKichThuoc = kichThuocRepo.getAll();
            List<MauSacResponse> listMauSac = mauSacRepo.getAll();

            // Kiểm tra danh sách trước khi thêm vào ComboBox
            if (listChatLieu != null) {
                for (ChatLieuResponse cl : listChatLieu) {
                    cboChatLieu.addItem(cl.getTenChatLieu()); // Chỉ lấy tên loại
                }
            }
            if (listKichThuoc != null) {
                for (KichThuocResponse kt : listKichThuoc) {
                    cboKichThuoc.addItem(kt.getTenKichThuoc()); // Chỉ lấy kích thước
                }
            }
            if (listMauSac != null) {
                for (MauSacResponse ms : listMauSac) {
                    cboMauSac.addItem(ms.getTenMauSac()); // Chỉ lấy tên màu
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu ComboBox: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void clear() {
        txtTenSanPham.setText("");
        txtMaSP.setText("");
        buttonGroup1.clearSelection();
        fillToTable(list_sp);

    }

    private void fillToTable(ArrayList<SanPham> list_sp) {
        dtmSanPham.setRowCount(0);
        index = 1;
        for (SanPham sp : list_sp) {
            int tongSoLuong = new SanPhamChiTietRepository().getTongSoLuongByMaSanPham(sp.getMaSanPham());
            dtmSanPham.addRow(new Object[]{
                index++,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                tongSoLuong,
                sp.getTenThuongHieu(),
                sp.getTrangThai() == 1 ? "Còn hàng" : "Hết hàng"
            });
        }
    }

    private void clearCT() {
        txtTenSanPhamCT.setText("");
        txtGiaBan.setText("");
        txtSoLuongCT.setText("");
        txtMoTa.setText("");
        dateNgayNhap.setDate(null);
        cboKichThuoc.setSelectedIndex(0);
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        fillToTableCT(list_spct);
    }

}
