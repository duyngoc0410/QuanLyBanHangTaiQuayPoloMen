/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Entity.Voucher;
import Repository.VoucherRepository;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewVoucher extends javax.swing.JPanel {

    private int index = 0;
    private DefaultTableModel dtm;

    private VoucherRepository vcRepo;

    public ViewVoucher() {
        initComponents();
        jdateNgayBD.setLocale(new Locale("vi", "VN"));
        jdateNgayKT.setLocale(new Locale("vi", "VN"));
        dtm = (DefaultTableModel) tblVoucher.getModel();
        vcRepo = new VoucherRepository();
        showDataTable(vcRepo.getAll());
    }

    private void showDataTable(ArrayList<Voucher> lists) {
        dtm.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lists.forEach(s -> dtm.addRow(new Object[]{
            index++, s.getTenVoucher(), s.getMoTa(), s.getPhanTramGiamGia(), s.getNgayBatDau(), s.getNgayKetThuc(), s.getTrangThai() == 1 ? "Còn Hạn" : "Hết Hạn"
        }));
    }

    private void detailVocher(int index) {
        Voucher vc = vcRepo.getAll().get(index);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txtNameVoucher.setText(vc.getTenVoucher());
        txtMoTa.setText(vc.getMoTa());
        txtPhamTramGiam.setText(String.valueOf(vc.getPhanTramGiamGia()));
        jdateNgayBD.setDate(vc.getNgayBatDau());
        jdateNgayKT.setDate(vc.getNgayKetThuc());
        if (vc.getTrangThai() == 1) {
            rdoConHan.setSelected(true);
            rdoHetHan.setSelected(false);
        } else {
            rdoConHan.setSelected(false);
            rdoHetHan.setSelected(true);
        }
    }

    private Voucher getFormData() {
        Date ngayBatDau = (jdateNgayBD.getDate() != null) ? new Date(jdateNgayBD.getDate().getTime()) : null;
        Date ngayKetThuc = (jdateNgayKT.getDate() != null) ? new Date(jdateNgayKT.getDate().getTime()) : null;

        Voucher vc = Voucher.builder()
                .tenVoucher(txtNameVoucher.getText())
                .moTa(txtMoTa.getText())
                .phanTramGiamGia(Integer.parseInt(txtPhamTramGiam.getText()))
                .ngayBatDau(ngayBatDau)
                .ngayKetThuc(ngayKetThuc)
                .trangThai(rdoConHan.isSelected() ? 1 : 0)
                .build();

        return vc;
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);

        ArrayList<Voucher> list = vcRepo.getAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for (Voucher vc : list) {
            String ngayBatDau = (vc.getNgayBatDau() != null) ? sdf.format(vc.getNgayBatDau()) : "N/A";
            String ngayKetThuc = (vc.getNgayKetThuc() != null) ? sdf.format(vc.getNgayKetThuc()) : "N/A";

            model.addRow(new Object[]{
                vc.getId(),
                vc.getTenVoucher(),
                vc.getMoTa(),
                vc.getPhanTramGiamGia(),
                ngayBatDau,
                ngayKetThuc,
                vc.getTrangThai() == 1 ? "Còn hạn" : "Hết hạn"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        txtTimKiemVocher = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNameVoucher = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPhamTramGiam = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jdateNgayBD = new com.toedter.calendar.JDateChooser();
        jdateNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdoConHan = new javax.swing.JRadioButton();
        rdoHetHan = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(128, 203, 196));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên VouCher", "Mô tả", "Phần Trăm Giảm Giá", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setRowHeight(25);
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);
        if (tblVoucher.getColumnModel().getColumnCount() > 0) {
            tblVoucher.getColumnModel().getColumn(0).setResizable(false);
            tblVoucher.getColumnModel().getColumn(1).setResizable(false);
            tblVoucher.getColumnModel().getColumn(2).setResizable(false);
            tblVoucher.getColumnModel().getColumn(3).setResizable(false);
            tblVoucher.getColumnModel().getColumn(4).setResizable(false);
            tblVoucher.getColumnModel().getColumn(5).setResizable(false);
            tblVoucher.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(128, 203, 196));
        btnThem.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(128, 203, 196));
        btnSua.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(128, 203, 196));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tìm Kiếm :");
        jLabel7.setName(""); // NOI18N

        btnXoa.setBackground(new java.awt.Color(128, 203, 196));
        btnXoa.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtTimKiemVocher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemVocherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(26, 26, 26)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemVocher, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTimKiemVocher, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tên Voucher :");

        txtNameVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameVoucherActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Phần Trăm Giảm :");

        txtPhamTramGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhamTramGiamActionPerformed(evt);
            }
        });

        txtMoTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mô Tả :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(txtPhamTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameVoucher))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel8, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMoTa, txtNameVoucher, txtPhamTramGiam});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNameVoucher)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhamTramGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel8, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtNameVoucher, txtPhamTramGiam});

        jdateNgayBD.setDateFormatString("dd-MM-yyyy");

        jdateNgayKT.setDateFormatString("dd-MM-yyyy");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu :");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Trạng Thái ");

        buttonGroup1.add(rdoConHan);
        rdoConHan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoConHan.setText("Còn Hạn");

        buttonGroup1.add(rdoHetHan);
        rdoHetHan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdoHetHan.setText("Hết Hạn");
        rdoHetHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetHanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoConHan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rdoConHan, rdoHetHan});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jdateNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdateNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoConHan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoHetHan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rdoConHan, rdoHetHan});

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản Lý Voucher");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblVoucher.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String tenVoucher = tblVoucher.getValueAt(row, 1).toString();

        Voucher vc = new Voucher();
        vc.setTenVoucher(tenVoucher);

        if (vcRepo.Delete(vc) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
            loadTable();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại.");
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        // TODO add your handling code here:
        tblVoucher.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblVoucher.getSelectedRow();
                if (selectedRow >= 0) {
                    // Lấy dữ liệu từ từng cột trong dòng đã chọn
                    String nameVoucher = tblVoucher.getValueAt(selectedRow, 1).toString();
                    txtNameVoucher.setText(nameVoucher != null ? nameVoucher.toString() : "");
                    String phamTram = tblVoucher.getValueAt(selectedRow, 3).toString();
                    txtPhamTramGiam.setText(phamTram != null ? phamTram.toString() : "");
                    String moTa = tblVoucher.getValueAt(selectedRow, 2).toString();
                    txtMoTa.setText(moTa != null ? moTa.toString() : "");
                    Object ngayBDObj = tblVoucher.getValueAt(selectedRow, 4);
                    Object ngayKTObj = tblVoucher.getValueAt(selectedRow, 5);
                    String trangThai = tblVoucher.getValueAt(selectedRow, 6).toString();
                    try {
                        if (ngayBDObj instanceof Date) {
                            jdateNgayBD.setDate((Date) ngayBDObj);
                        } else {
                            Date dateBD = new SimpleDateFormat("dd/MM/yyyy").parse(ngayBDObj.toString());
                            jdateNgayBD.setDate(dateBD);
                        }
                        if (ngayKTObj instanceof Date) {
                            jdateNgayKT.setDate((Date) ngayKTObj);
                        } else {
                            Date dateKT = new SimpleDateFormat("dd/MM/yyyy").parse(ngayKTObj.toString());
                            jdateNgayKT.setDate(dateKT);
                        }
                    } catch (Exception ex) {
                        jdateNgayBD.setDate(null);
                        jdateNgayKT.setDate(null);
                    }
                    if ("Còn Hạn".equalsIgnoreCase(trangThai)) {
                        rdoConHan.setSelected(true);
                        rdoHetHan.setSelected(false);
                    } else if ("Hết Hạn".equalsIgnoreCase(trangThai)) {
                        rdoConHan.setSelected(false);
                        rdoHetHan.setSelected(true);
                    } else {
                        rdoConHan.setSelected(false);
                        rdoHetHan.setSelected(false);
                    }
                }
            }
        });
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtNameVoucher.setText("");
        txtPhamTramGiam.setText("");
        txtMoTa.setText("");
        jdateNgayBD.setDate(null);
        jdateNgayKT.setDate(null);
        rdoConHan.setSelected(false);
        rdoHetHan.setSelected(false);
        JOptionPane.showMessageDialog(this, "Làm mới thành công.");

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtNameVoucher.getText().trim().isEmpty() || txtMoTa.getText().trim().isEmpty()
                || txtPhamTramGiam.getText().trim().isEmpty() || jdateNgayBD.getDate() == null
                || jdateNgayKT.getDate() == null || (!rdoConHan.isSelected() && !rdoHetHan.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            int phanTram = Integer.parseInt(txtPhamTramGiam.getText().trim());
            if (phanTram < 0 || phanTram > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm phải từ 0 đến 100.");
                return;
            }

            Voucher vc = new Voucher();
            vc.setTenVoucher(txtNameVoucher.getText().trim());
            vc.setMoTa(txtMoTa.getText().trim());
            vc.setPhanTramGiamGia(phanTram);
            vc.setNgayBatDau(jdateNgayBD.getDate());
            vc.setNgayKetThuc(jdateNgayKT.getDate());
            vc.setTrangThai(rdoConHan.isSelected() ? 1 : 0);

            if (vcRepo.add(vc)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công.");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải là số.");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        int selectedRow = tblVoucher.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa.");
            return;
        }
     
        if (txtNameVoucher.getText().trim().isEmpty() || txtMoTa.getText().trim().isEmpty()
                || txtPhamTramGiam.getText().trim().isEmpty()
                || jdateNgayBD.getDate() == null || jdateNgayKT.getDate() == null
                || (!rdoConHan.isSelected() && !rdoHetHan.isSelected())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            int phanTram = Integer.parseInt(txtPhamTramGiam.getText().trim());
            if (phanTram < 0 || phanTram > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm phải từ 0 đến 100.");
                return;
            }

            // Tạo đối tượng Voucher mới
            Voucher vc = new Voucher();
            vc.setTenVoucher(txtNameVoucher.getText().trim());
            vc.setMoTa(txtMoTa.getText().trim());
            vc.setPhanTramGiamGia(phanTram);
            vc.setNgayBatDau(jdateNgayBD.getDate());
            vc.setNgayKetThuc(jdateNgayKT.getDate());
            vc.setTrangThai(rdoConHan.isSelected() ? 1 : 0);

            // Gọi hàm sửa theo tên voucher
            vcRepo.EditByName(vc);

            JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
            loadTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải là số.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật.");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtTimKiemVocherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemVocherActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblVoucher.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblVoucher.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(txtTimKiemVocher.getText()));

    }//GEN-LAST:event_txtTimKiemVocherActionPerformed

    private void txtNameVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameVoucherActionPerformed

    private void txtMoTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTaActionPerformed

    private void rdoHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetHanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetHanActionPerformed

    private void txtPhamTramGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhamTramGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhamTramGiamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdateNgayBD;
    private com.toedter.calendar.JDateChooser jdateNgayKT;
    private javax.swing.JRadioButton rdoConHan;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNameVoucher;
    private javax.swing.JTextField txtPhamTramGiam;
    private javax.swing.JTextField txtTimKiemVocher;
    // End of variables declaration//GEN-END:variables

}
