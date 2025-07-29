/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Entity.HoaDon;
import Response.HoaDonRepose;
import Response.HoaDonChiTietRepose;
import Repository.HoaDonChiTietRepository;
import Repository.HoaDonRepository;
import Response.HoaDonChiTietRepose;
import Response.KhachHangResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ViewHoaDon extends javax.swing.JPanel {

    private HoaDonRepository hoaDonRepository;

    private HoaDonChiTietRepository hoaDonChiTietRepository;

    private DefaultTableModel dtmHoaDon;

    private DefaultTableModel dtmHoaDonChiTiet;

    private ArrayList<HoaDonRepose> list_hd = new ArrayList<>();

    private ArrayList<HoaDonChiTietRepose> list_hdct = new ArrayList<>();

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public ViewHoaDon() {
        initComponents();

        hoaDonRepository = new HoaDonRepository();
        hoaDonChiTietRepository = new HoaDonChiTietRepository();
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDonChiTiet = (DefaultTableModel) tblHoaDonCT.getModel();
        list_hd = hoaDonRepository.findAll();
        list_hdct = hoaDonChiTietRepository.findAll();
        loadTable(list_hd);
        loadTableHoaDonChiTiet(list_hdct);
    }

    private void loadTable(ArrayList<HoaDonRepose> list_hd) {
        dtmHoaDon.setRowCount(0);
        int stt = 1;
        for (HoaDonRepose hd : list_hd) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String giaVND = currencyFormat.format(hd.getThanhTien());
            dtmHoaDon.addRow(new Object[]{
                stt++,
                hd.getMaHoaDon(),
                hd.getMaNhanVien(),
                hd.getTenNhanVien(),
                hd.getTenKhachHang(),
                giaVND,
                hd.getNgayThanhToan() != null ? sdf.format(hd.getNgayThanhToan()) : "",
                getTrangThaiText(hd.getTrangThai())
            });
        }
    }

    private String getTrangThaiText(int trangThai) {
        switch (trangThai) {
            case 0:
                return "Chưa thanh toán";
            case 1:
                return "Đã thanh toán";
            default:
                return "Không xác định";
        }
    }

    private void loadTableHoaDonChiTiet(ArrayList<HoaDonChiTietRepose> list_hdct) {
        int stt = 1;
        dtmHoaDonChiTiet.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (HoaDonChiTietRepose hdct : list_hdct) {
            String giaVND = currencyFormat.format(hdct.getGiaDaThanhToan());

            // An toàn với null cho Hình thức thanh toán
            String hinhThucThanhToanText = "Không rõ";
            Boolean hinhThuc = hdct.getHinhThucThanhToan();
            if (Boolean.TRUE.equals(hinhThuc)) {
                hinhThucThanhToanText = "Tiền Mặt";
            } else if (Boolean.FALSE.equals(hinhThuc)) {
                hinhThucThanhToanText = "Chuyển Khoản";
            }

            dtmHoaDonChiTiet.addRow(new Object[]{
                stt++,
                hdct.getMaHoaDon(),
                hdct.getTenSanPham(),
                hdct.getSoluong(),
                giaVND,
                hdct.getKichThuoc(),
                hdct.getMauSac(),
                hdct.getChatLieu(),
                hdct.getMoTa(),
                hdct.getNgayThanhToan() != null ? sdf.format(hdct.getNgayThanhToan()) : "",
                hinhThucThanhToanText,
                getTrangThaiText(hdct.getTrangThai())
            });
        }
    }

    private void loadHoaDonChiTietByMaHoaDon(String maHD) {
        ArrayList<HoaDonChiTietRepose> list = hoaDonChiTietRepository.getByMaHoaDon(maHD); // gọi DAO hoặc service
        loadTableHoaDonChiTiet(list); // phương thức đã có
    }

    private void xuatExcel(JTable table) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("HoaDonChiTiet");

        TableModel model = table.getModel();

        // Header
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col));
        }

        // Dữ liệu
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Object value = model.getValueAt(row, col);
                Cell cell = excelRow.createCell(col);
                if (value != null) {
                    if (value instanceof Number) {
                        cell.setCellValue(Double.parseDouble(value.toString()));
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

        // Tự động điều chỉnh độ rộng cột
        for (int i = 0; i < model.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemHoaDonCT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonCT = new javax.swing.JTable();
        btnXuatHD = new javax.swing.JButton();

        setBackground(new java.awt.Color(239, 243, 234));
        setPreferredSize(new java.awt.Dimension(1080, 720));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tìm Kiếm");

        txtTimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Nhân Viên", "Tên Nhân Viên", "Tên Khách Hàng", "Tổng Tiền", "Ngày Thanh Toán", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setRowHeight(30);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel1);

        jPanel2.setBackground(new java.awt.Color(128, 203, 196));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tìm Kiếm");

        txtTimKiemHoaDonCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHoaDonCTActionPerformed(evt);
            }
        });

        tblHoaDonCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Kích Thước", "Màu Sắc", "Chất Liệu", "Mô Tả", "Ngày Thanh Toán", "HTTT", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCT.setRowHeight(30);
        jScrollPane2.setViewportView(tblHoaDonCT);

        btnXuatHD.setText("Xuất File Excel");
        btnXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHoaDonCT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatHD))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn Chi Tiết", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonActionPerformed
        String keyword = txtTimKiemHoaDon.getText().trim();
        System.out.println("Từ khóa tìm kiếm: '" + keyword + "'");
        ArrayList<HoaDonRepose> list_search = hoaDonRepository.search(keyword);
        loadTable(list_search);
    }//GEN-LAST:event_txtTimKiemHoaDonActionPerformed

    private void txtTimKiemHoaDonCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonCTActionPerformed
        String keyword = txtTimKiemHoaDonCT.getText().trim();
        System.out.println("Từ khóa tìm kiếm: '" + keyword + "'");
        ArrayList<HoaDonChiTietRepose> list_search = hoaDonChiTietRepository.search(keyword);
        loadTableHoaDonChiTiet(list_search);
    }//GEN-LAST:event_txtTimKiemHoaDonCTActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int selectedRow = tblHoaDon.getSelectedRow();
        if (selectedRow != -1) {
            String maHoaDon = tblHoaDon.getValueAt(selectedRow, 1).toString(); // cột 1 là Mã HĐ
            loadHoaDonChiTietByMaHoaDon(maHoaDon);
            jTabbedPane1.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHDActionPerformed
           xuatExcel(tblHoaDonCT);
    }//GEN-LAST:event_btnXuatHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCT;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimKiemHoaDonCT;
    // End of variables declaration//GEN-END:variables

}
