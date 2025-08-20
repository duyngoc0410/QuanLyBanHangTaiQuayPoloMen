package View;

import Entity.HoaDon;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.Voucher;
import Repository.HoaDonChiTietRepository;
import Repository.HoaDonRepository;
import Repository.KhachHangRepository;
import Repository.NhanVienRepository;
import Repository.SanPhamChiTietRepository;
import Repository.VoucherRepository;
import Response.HoaDonChiTietRepose;
import Response.HoaDonRepose;
import Response.NhanVienResponse;
import Response.SanPhamChiTietResponse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class ViewBanHang extends javax.swing.JPanel {

    private HoaDonRepository hoaDonRepository;
    private NhanVienRepository nhanVienRepository;
    private SanPhamChiTietRepository sanPhamChiTietRepository;
    private SanPhamChiTietResponse sanPhamChiTietResponse;
    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmSanPham;
    private DefaultTableModel dtmHoaDonChiTiet;
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    private VoucherRepository voucherRepository;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private ArrayList<HoaDonRepose> list_hd = new ArrayList<>();
    private ArrayList<Voucher> listVoucher; 
    private ArrayList<SanPhamChiTietResponse> list_spct = new ArrayList<>();
    private Integer index = 0;
    private ArrayList<HoaDonChiTietRepose> gioHangTam = new ArrayList<>();
    private HoaDonRepose hoaDonDangChon = null;

    public ViewBanHang() {
        initComponents();
        hoaDonRepository = new HoaDonRepository();
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
        sanPhamChiTietRepository = new SanPhamChiTietRepository();
        nhanVienRepository = new NhanVienRepository();
        voucherRepository = new VoucherRepository();
        dtmHoaDon = (DefaultTableModel) tblHoaDonBan.getModel();
        dtmSanPham = (DefaultTableModel) tblSanPhamBan.getModel();
        dtmHoaDonChiTiet = (DefaultTableModel) tblGioHang.getModel();
        showTableHoaDon(hoaDonRepository.getAllByTrangThai());
        showTableSanPham(sanPhamChiTietRepository.getAll());
        showTableGioHang((ArrayList<HoaDonChiTietRepose>) gioHangTam);
        loadComboBoxData();
        loadVoucherToComboBox();
        setupComboBoxListener();
        clock();

    }

    private void showTableSanPham(ArrayList<SanPhamChiTietResponse> all) {
        dtmSanPham.setRowCount(0);
        int index = 1;
        for (SanPhamChiTietResponse sp : all) {
            String giaVND = sp.getGia() != null ? currencyFormat.format(sp.getGia()) : "0 ₫";
            dtmSanPham.addRow(new Object[]{
                index++,
                sp.getMaSanPhamCT(),
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getTenMauSac(),
                sp.getTenChatLieu(),
                sp.getTenKichThuoc(),
                giaVND,
                sp.getSoLuong(),
                sp.getTrangThai() == 1 ? "Còn Hàng" : "Hết Hàng"
            });
        }
    }

    private void showTableHoaDon(ArrayList<HoaDonRepose> allByTrangThai) {
        dtmHoaDon.setRowCount(0);
        int index = 1;
        SimpleDateFormat sim = new SimpleDateFormat("dd/MM/yyyy");
        for (HoaDonRepose hd : allByTrangThai) {
            String giaVND = hd.getThanhTien() != null ? currencyFormat.format(hd.getThanhTien()) : "0 ₫";
            dtmHoaDon.addRow(new Object[]{
                index++,
                hd.getMaHoaDon(),
                hd.getMaNhanVien(),
                hd.getTenNhanVien(),
                hd.getTenKhachHang(),
                giaVND,
                hd.getNgayThanhToan() != null ? sim.format(hd.getNgayThanhToan()) : "",
                hd.getTrangThai() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán"
            });
        }
    }

    private void showTableGioHang(ArrayList<HoaDonChiTietRepose> list) {
        dtmHoaDonChiTiet.setRowCount(0);
        int index = 1;
        for (HoaDonChiTietRepose hdct : list) {
            BigDecimal gia = hdct.getGiaDaThanhToan() != null ? hdct.getGiaDaThanhToan() : BigDecimal.ZERO;
            BigDecimal thanhTien = hdct.getThanhTien() != null ? hdct.getThanhTien() : BigDecimal.ZERO;
            dtmHoaDonChiTiet.addRow(new Object[]{
                index++,
                hdct.getMaSanPhamChitiet(),
                hdct.getTenSanPham(),
                hdct.getSoluong(),
                currencyFormat.format(gia),
                currencyFormat.format(thanhTien)
            });
        }
    }

    private void loadComboBoxData() {
        try {
            // Xóa dữ liệu cũ
            cboNguoiTao.removeAllItems();
            cboHinhThuc.removeAllItems();

            // Load nhân viên
            List<NhanVienResponse> nhanVienList = nhanVienRepository.getAll();
            if (nhanVienList != null) {
                for (NhanVienResponse nv : nhanVienList) {
                    cboNguoiTao.addItem(nv.getTenNhanVien());
                }
            }

            // Hình thức thanh toán
            cboHinhThuc.addItem("Tiền mặt");
            cboHinhThuc.addItem("Chuyển khoản");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi tải dữ liệu ComboBox: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateTongTienTam() {
        BigDecimal tongTien = BigDecimal.ZERO;
        for (HoaDonChiTietRepose hdct : gioHangTam) {
            if (hdct.getThanhTien() != null) {
                tongTien = tongTien.add(hdct.getThanhTien());
            }
        }

        txtTongTien.setText(tongTien.toString());
    }

    private void tinhThanhTienSauGiam() {
        try {
            // Lấy và kiểm tra chuỗi tổng tiền
            String tongTienStr = txtTongTien.getText().replaceAll("[^\\d]", "").trim();
            if (tongTienStr.isEmpty()) {
                return;
            }
            int tongTien = Integer.parseInt(tongTienStr);

            // Lấy và xử lý chuỗi giảm giá, tách bỏ ký tự %
            String giamStr = txtGiaTriGiam.getText().replaceAll("[^\\d]", "").trim();
            if (giamStr.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Giá trị giảm đang trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int phanTramGiam = Integer.parseInt(giamStr);

            // Tính thành tiền
            int thanhTien = tongTien - (tongTien * phanTramGiam / 100);

            // Hiển thị
            DecimalFormat df = new DecimalFormat("#,###");
            txtThanhTien.setText(df.format(thanhTien) + " ₫");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tính Thành tiền: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList<Voucher> dsVoucher;

    private void loadVoucherToComboBox() {
        dsVoucher = voucherRepository.getAll(); // gán list vào biến toàn cục
        if (dsVoucher != null) {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Voucher v : dsVoucher) {
                model.addElement(v.getTenVoucher() + " ( " + v.getMoTa() + " ) "); // chỉ đưa tên vào combo
            }
            cboVoucher.setModel(model);
        }
    }

    private void setupComboBoxListener() {
        cboVoucher.addActionListener(e -> {
            int index = cboVoucher.getSelectedIndex();
            if (index >= 0 && index < dsVoucher.size()) {
                Voucher selected = dsVoucher.get(index);
                txtGiaTriGiam.setText(selected.getPhanTramGiamGia() + " %"); // hiển thị có dấu %
                tinhThanhTienSauGiam(); // 👉 tự tính lại thành tiền
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonBan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPhamBan = new javax.swing.JTable();
        lblTimKiem = new javax.swing.JLabel();
        txtTimKiemGioHang = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        cboNguoiTao = new javax.swing.JComboBox<>();
        txtKhachHang = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        cboVoucher = new javax.swing.JComboBox<>();
        txtGiaTriGiam = new javax.swing.JTextField();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        txtTienDua = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        cboHinhThuc = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtTienTraLai = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoaDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Nhân Viên", "Tên Nhân Viên", "Tên Khách Hàng", "Tổng Tiền", "Ngày Tạo", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonBan.setRowHeight(25);
        tblHoaDonBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonBan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(128, 203, 196));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPhamBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SPCT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Chất Liệu", "Kích Thước", "Giá ", "Số Lượng", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamBan.setRowHeight(25);
        tblSanPhamBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamBanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPhamBan);

        lblTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiem.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(128, 203, 196));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SPCT", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setRowHeight(25);
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(128, 203, 196));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Ngày Tạo");

        jLabel2.setText("Người Tạo");

        jLabel3.setText("Khách Hàng");

        jLabel4.setText("Số Điện Thoại");

        jLabel5.setText("Mã Hóa Đơn");

        jLabel6.setText("Voucher");

        jLabel7.setText("Giá Trị Giảm");

        jLabel8.setText("Tổng Tiền");

        jLabel9.setText("Hình Thức ");

        jLabel10.setText("Thành Tiền");

        jLabel11.setText("Tiền Khách Đưa");

        lblNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgayTao.setForeground(new java.awt.Color(255, 0, 0));
        lblNgayTao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayTao.setText("___________________________");

        txtMaHoaDon.setEditable(false);

        txtTongTien.setEditable(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        cboVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVoucherActionPerformed(evt);
            }
        });

        txtGiaTriGiam.setEditable(false);

        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setText("Làm Mới");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtThanhTien.setEditable(false);

        jLabel13.setText("Tiền Trả Lại");

        txtTienTraLai.setEditable(false);

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel13, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboHinhThuc, cboNguoiTao, cboVoucher, lblNgayTao, txtGiaTriGiam, txtKhachHang, txtMaHoaDon, txtSoDienThoai, txtThanhTien, txtTienDua, txtTienTraLai, txtTongTien});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnTaoHoaDon, btnThanhToan});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboHinhThuc, cboNguoiTao, cboVoucher, jLabel1, jLabel10, jLabel11, jLabel13, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, lblNgayTao, txtGiaTriGiam, txtKhachHang, txtMaHoaDon, txtSoDienThoai, txtThanhTien, txtTienDua, txtTienTraLai, txtTongTien});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnTaoHoaDon, btnThanhToan});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        try {
            // Lấy dữ liệu từ form
            String tenNhanVien = cboNguoiTao.getSelectedItem().toString();
            String tenKhachHang = txtKhachHang.getText().trim();
            String sdt = txtSoDienThoai.getText().trim();
            String voucherTen = cboVoucher.getSelectedItem().toString();

            // Validate bắt buộc
            if (tenKhachHang.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khách hàng!");
                return;
            }

            // Validate số điện thoại
            if (!sdt.matches("0\\d{9}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
                return;
            }

            // Lấy ngày hiện tại (chỉ ngày tháng năm)
            Date utilDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date ngayTao = cal.getTime();

            // Lấy ID nhân viên
            int idNhanVien = new NhanVienRepository().getIdByTen(tenNhanVien);

            // Xử lý Khách Hàng
            KhachHangRepository khRepo = new KhachHangRepository();
            Integer idKhachHang = khRepo.getIdBySdt(sdt);
            if (idKhachHang == null) {
                // Thêm mới nếu chưa có
                KhachHang kh = new KhachHang();
                kh.setTenKhachHang(tenKhachHang);
                kh.setSoDienThoai(sdt);
                idKhachHang = khRepo.insertAndGetId(kh);
            }
            // Sinh mã hóa đơn theo thứ tự tăng dần
            String maHoaDon = hoaDonRepository.generateNewMaHD(); // ví dụ: HD00101

            // Tạo đối tượng hóa đơn
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(maHoaDon);
            hd.setIdNhanVien(idNhanVien);
            hd.setIdKhachHang(idKhachHang);
            hd.setNgayThanhToan(ngayTao);
            hd.setThanhTien(BigDecimal.ZERO); // vì chưa có sản phẩm
            hd.setTrangThai(0); // 0 = chưa thanh toán

            // Thêm vào DB
            boolean taoThanhCong = hoaDonRepository.insert(hd);
            if (taoThanhCong) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!");
                showTableHoaDon(hoaDonRepository.getAllByTrangThai());
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo hóa đơn: " + e.getMessage());
        }

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblSanPhamBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamBanMouseClicked
        int row = tblSanPhamBan.getSelectedRow();
        if (row < 0 || hoaDonDangChon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn và sản phẩm!");
            return;
        }

        Object idRaw = tblSanPhamBan.getValueAt(row, 0);
        int id;
        try {
            id = Integer.parseInt(idRaw.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID sản phẩm không hợp lệ!");
            return;
        }

        SanPhamChiTietResponse spctre = sanPhamChiTietRepository.findById(id);
        if (spctre == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm trong hệ thống!");
            return;
        }

        if (spctre.getSoLuong() <= 0) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng!");
            return;
        }

        BigDecimal giaSanPham = spctre.getGia();
        if (giaSanPham == null) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm không được để trống!");
            return;
        }

        String soLuongInput = JOptionPane.showInputDialog(this, "Nhập số lượng:", "1");
        if (soLuongInput == null || soLuongInput.trim().isEmpty()) {
            return;
        }

        int number;
        try {
            number = Integer.parseInt(soLuongInput.trim());
            if (number <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
            return;
        }

        // Kiểm tra tồn kho thực tế sau khi cộng dồn trong giỏ
        int tongSoLuongTrongGio = number;
        for (HoaDonChiTietRepose hdct : gioHangTam) {
            if (hdct.getIdSanPhamChiTiet() == spctre.getId()) {
                tongSoLuongTrongGio += hdct.getSoluong();
                break;
            }
        }

        if (tongSoLuongTrongGio > spctre.getSoLuong()) {
            JOptionPane.showMessageDialog(this, "Không đủ hàng! Tồn kho: " + spctre.getSoLuong());
            return;
        }

        // Thêm hoặc cộng dồn
        boolean daCo = false;
        for (HoaDonChiTietRepose hdct : gioHangTam) {
            if (hdct.getIdSanPhamChiTiet() == spctre.getId()) {
                hdct.setSoluong(hdct.getSoluong() + number);
                BigDecimal gia = hdct.getGiaDaThanhToan() != null ? hdct.getGiaDaThanhToan() : giaSanPham;
                hdct.setGiaDaThanhToan(gia);
                hdct.setThanhTien(gia.multiply(BigDecimal.valueOf(hdct.getSoluong())));
                daCo = true;
                break;
            }
        }

        if (!daCo) {
            HoaDonChiTietRepose hdct = new HoaDonChiTietRepose();
            hdct.setIdHoaDon(hoaDonDangChon.getId());
            hdct.setMaHoaDon(hoaDonDangChon.getMaHoaDon());
            hdct.setMaSanPhamChitiet(spctre.getMaSanPhamCT());
            hdct.setIdSanPhamChiTiet(spctre.getId());
            hdct.setTenSanPham(spctre.getTenSanPham());
            hdct.setChatLieu(spctre.getTenChatLieu());
            hdct.setKichThuoc(spctre.getTenKichThuoc());
            hdct.setMauSac(spctre.getTenMauSac());
            hdct.setSoluong(number);
            hdct.setGiaDaThanhToan(giaSanPham);
            hdct.setThanhTien(giaSanPham.multiply(BigDecimal.valueOf(number)));
            hdct.setTrangThai(0);
            gioHangTam.add(hdct);
        }

        // Cập nhật tổng tiền
        BigDecimal tongTien = BigDecimal.ZERO;
        for (HoaDonChiTietRepose item : gioHangTam) {
            if (item.getThanhTien() != null) {
                tongTien = tongTien.add(item.getThanhTien());
            }
        }

        hoaDonDangChon.setThanhTien(tongTien);
        hoaDonRepository.updateTongTien(hoaDonDangChon.getId(), tongTien);

        // Hiển thị lại bảng
        showTableGioHang((ArrayList<HoaDonChiTietRepose>) gioHangTam);
        showTableHoaDon(list_hd);
        showTableSanPham(sanPhamChiTietRepository.getAll());

        txtTongTien.setText(currencyFormat.format(tongTien));
        txtThanhTien.setText(currencyFormat.format(tongTien));
    }//GEN-LAST:event_tblSanPhamBanMouseClicked

    private void tblHoaDonBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonBanMouseClicked
        int indexSelected = tblHoaDonBan.getSelectedRow();
        if (indexSelected < 0) {
            return;
        }

        String maHoaDon = tblHoaDonBan.getValueAt(indexSelected, 1).toString().trim();
        list_hd = hoaDonRepository.getAllByTrangThai();
        for (HoaDonRepose hd : list_hd) {
            if (hd.getMaHoaDon().equalsIgnoreCase(maHoaDon)) {
                hoaDonDangChon = hd;

                // Gán dữ liệu
                cboNguoiTao.setSelectedItem(hd.getTenNhanVien());
                txtKhachHang.setText(hd.getTenKhachHang());
                txtSoDienThoai.setText(hd.getSoDienThoai());
                txtMaHoaDon.setText(hd.getMaHoaDon());

                // Tổng tiền
                if (hd.getThanhTien() != null) {
                    txtTongTien.setText(currencyFormat.format(hd.getThanhTien()));
                } else {
                    txtTongTien.setText(currencyFormat.format(BigDecimal.ZERO));
                }

                // Load giỏ hàng đúng theo hóa đơn
                gioHangTam.clear();
                ArrayList<HoaDonChiTietRepose> chiTietList = hoaDonChiTietRepository.getAllById(hd.getId());
                tinhThanhTienSauGiam();

                break;
            }
        }

        if (hoaDonDangChon == null || hoaDonDangChon.getTrangThai() != 0) {
            JOptionPane.showMessageDialog(this, "Chỉ xử lý hóa đơn chưa thanh toán!");
            return;
        }
    }//GEN-LAST:event_tblHoaDonBanMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void cboVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVoucherActionPerformed
        setupComboBoxListener();
    }//GEN-LAST:event_cboVoucherActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (gioHangTam.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng đang trống!");
            return;
        }

        // 1. Lấy hình thức thanh toán
        String hinhThucThanhToan = cboHinhThuc.getSelectedItem().toString();
        boolean isTienMat = hinhThucThanhToan.equalsIgnoreCase("Tiền mặt");

        // 2. Parse tiền khách đưa & thành tiền
        BigDecimal tienKhachDua;
        BigDecimal thanhTienSauGiam;

        try {
            tienKhachDua = new BigDecimal(txtTienDua.getText().trim());
            String thanhTienStr = txtThanhTien.getText().replaceAll("[^\\d]", "").trim();
            if (thanhTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Thành tiền đang trống!");
                return;
            }
            thanhTienSauGiam = new BigDecimal(thanhTienStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số tiền không hợp lệ!");
            return;
        }

        // 3. Tính tiền trả lại
        BigDecimal tienTraLai = tienKhachDua.subtract(thanhTienSauGiam);
        if (tienTraLai.compareTo(BigDecimal.ZERO) < 0) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ!");
            return;
        }
        txtTienTraLai.setText(new DecimalFormat("#,###").format(tienTraLai) + " ₫");
        
        // 4. Xác nhận thanh toán
        int confirm = JOptionPane.showConfirmDialog(this,
                "Xác nhận thanh toán:\nHình thức: " + hinhThucThanhToan
                + "\nTổng tiền: " + thanhTienSauGiam + " ₫"
                + "\nTiền khách đưa: " + tienKhachDua + " ₫"
                + "\nTiền trả lại: " + tienTraLai + " ₫",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // 5. Cập nhật từng hóa đơn chi tiết
        for (HoaDonChiTietRepose hdct : gioHangTam) {
            hdct.setNgayThanhToan(new Date());
            hdct.setHinhThucThanhToan(isTienMat);
            hdct.setTrangThai(1);

            if (hdct.getId() != 0) {
                hoaDonChiTietRepository.updateHoaDonCT(hdct);
            } else {
                hoaDonChiTietRepository.add(hdct);
            }

            SanPhamChiTietResponse sp = sanPhamChiTietRepository.findById(hdct.getIdSanPhamChiTiet());
            int soLuongConLai = sp.getSoLuong() - hdct.getSoluong();

            if (soLuongConLai < 0) {
                JOptionPane.showMessageDialog(this, "Không đủ hàng cho sản phẩm: " + sp.getTenSanPham());
                return;
            }

            sp.setSoLuong(soLuongConLai);

            // ✅ Nếu hết hàng, chuyển trạng thái = 0 (tức là hết hàng)
            if (soLuongConLai == 0) {
                sp.setTrangThai(0); // Giả định: 1 = còn hàng, 0 = hết hàng
            }

            sanPhamChiTietRepository.updateSanPham(sp);
        }

        // 7. Cập nhật hóa đơn
        if (hoaDonDangChon == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn đang chọn!");
            return;
        }

        hoaDonDangChon.setThanhTien(thanhTienSauGiam);
        hoaDonDangChon.setTienDua(tienKhachDua);
        hoaDonDangChon.setTrangThai(1);

        // 8. Xử lý voucher
        if (cboVoucher.getSelectedIndex() > 0 && cboVoucher.getSelectedIndex() < dsVoucher.size()) {
            Voucher selected = dsVoucher.get(cboVoucher.getSelectedIndex());
            hoaDonDangChon.setIdVoucher(selected.getId());
        } else {
            hoaDonDangChon.setIdVoucher(0); // hoặc 0 nếu DB không cho null
        }

        hoaDonRepository.updateHoaDon(hoaDonDangChon);

        // 9. Dọn dẹp
        gioHangTam.clear();
        showTableGioHang(gioHangTam);

        hoaDonDangChon = null;

        // 10. Cập nhật lại giao diện
        showTableSanPham(sanPhamChiTietRepository.getAll());
        showTableHoaDon(hoaDonRepository.getAllByTrangThai());
        clearForm();

        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed

        // Xác nhận hủy
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn hủy hóa đơn và xóa giỏ hàng không?",
                "Xác nhận hủy", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        gioHangTam.clear();
        showTableGioHang(gioHangTam);

        hoaDonDangChon = null;
        clearForm();
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cboHinhThuc;
    private javax.swing.JComboBox<String> cboNguoiTao;
    private javax.swing.JComboBox<String> cboVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonBan;
    private javax.swing.JTable tblSanPhamBan;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTienTraLai;
    private javax.swing.JTextField txtTimKiemGioHang;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    private void clearForm() {
        txtKhachHang.setText("");
        txtSoDienThoai.setText("");
        txtMaHoaDon.setText("");
        txtGiaTriGiam.setText("");
        txtTienDua.setText("");
        txtTienTraLai.setText("");
        txtTongTien.setText("");
        txtThanhTien.setText("");
        // ComboBox - đặt lại về lựa chọn đầu tiên
        if (cboNguoiTao.getItemCount() > 0) {
            cboNguoiTao.setSelectedIndex(0);
        }
        if (cboVoucher.getItemCount() > 0) {
            cboVoucher.setSelectedItem("NONE"); // nếu bạn có item "NONE"
        }
        if (cboHinhThuc.getItemCount() > 0) {
            cboHinhThuc.setSelectedIndex(0);
        }
    }

    private void clock() {
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));

            @Override
            public void actionPerformed(ActionEvent e) {
                lblNgayTao.setText(format.format(new Date()));
            }
        }).start();
    }

}
