/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import View.ViewBanHang;

/**
 *
 * @author ChungPC
 */
public class ViewQuanLy extends javax.swing.JFrame {
    private JPanel childPanel;
    public ViewQuanLy() {
        initComponents();
        setLocationRelativeTo(null);
        setpanel(new ViewBanHang());
        color();
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnBanHang};
        new Thread(() -> {
            for (JButton btn11 : btn1) {
                btn11.setBackground(new Color(128,203,196));
            }
            for (JButton btn22 : btn2) {
                btn22.setBackground(new Color(128,203,196));
            }
        }).start();

    }
    private void setpanel(JPanel panel) {
        JPanel childPanel = panel;
        panelMain.removeAll();
        panelMain.add(childPanel);
        panelMain.validate();
    }

    private void color() {
        JButton[] btns = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        for (JButton btn : btns) {
            btn.setBackground(new Color(9, 107, 104));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelMain = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(128, 203, 196));
        jPanel1.setPreferredSize(new java.awt.Dimension(980, 700));
        jPanel1.setLayout(null);

        panelMain.setBackground(new java.awt.Color(239, 243, 234));
        panelMain.setMaximumSize(new java.awt.Dimension(1920, 1080));
        panelMain.setMinimumSize(new java.awt.Dimension(0, 0));
        panelMain.setPreferredSize(new java.awt.Dimension(1080, 720));
        panelMain.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelMain);
        panelMain.setBounds(200, 0, 1080, 720);

        btnBanHang.setBackground(new java.awt.Color(128, 203, 196));
        btnBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 253, 246));
        btnBanHang.setText("Bán Hàng");
        btnBanHang.setAlignmentY(0.0F);
        btnBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBanHangMouseClicked(evt);
            }
        });
        jPanel1.add(btnBanHang);
        btnBanHang.setBounds(0, 0, 200, 60);

        btnSanPham.setBackground(new java.awt.Color(128, 203, 196));
        btnSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 253, 246));
        btnSanPham.setText("Sản Phẩm");
        btnSanPham.setAlignmentY(0.0F);
        btnSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseClicked(evt);
            }
        });
        jPanel1.add(btnSanPham);
        btnSanPham.setBounds(0, 60, 200, 60);

        btnHoaDon.setBackground(new java.awt.Color(128, 203, 196));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(255, 253, 246));
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setAlignmentY(0.0F);
        btnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
        });
        jPanel1.add(btnHoaDon);
        btnHoaDon.setBounds(0, 120, 200, 60);

        btnThongKe.setBackground(new java.awt.Color(128, 203, 196));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 253, 246));
        btnThongKe.setText("Thống Kê");
        btnThongKe.setAlignmentY(0.0F);
        btnThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });
        jPanel1.add(btnThongKe);
        btnThongKe.setBounds(0, 180, 200, 60);

        btnKhachHang.setBackground(new java.awt.Color(128, 203, 196));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 253, 246));
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.setAlignmentY(0.0F);
        btnKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
        });
        jPanel1.add(btnKhachHang);
        btnKhachHang.setBounds(0, 240, 200, 60);

        btnNhanVien.setBackground(new java.awt.Color(128, 203, 196));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 253, 246));
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setAlignmentY(0.0F);
        btnNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseClicked(evt);
            }
        });
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jPanel1.add(btnNhanVien);
        btnNhanVien.setBounds(0, 360, 200, 60);

        btnVoucher.setBackground(new java.awt.Color(128, 203, 196));
        btnVoucher.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(255, 253, 246));
        btnVoucher.setText("Voucher");
        btnVoucher.setAlignmentY(0.0F);
        btnVoucher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoucherMouseClicked(evt);
            }
        });
        jPanel1.add(btnVoucher);
        btnVoucher.setBounds(0, 300, 200, 60);

        btnThoat.setBackground(new java.awt.Color(128, 203, 196));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 253, 246));
        btnThoat.setText("Thoát");
        btnThoat.setAlignmentY(0.0F);
        btnThoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 246, 233)));
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });
        jPanel1.add(btnThoat);
        btnThoat.setBounds(0, 420, 200, 60);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewSanPham());
        JButton[] btn1 = {btnBanHang, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnSanPham};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnSanPhamMouseClicked

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewHoaDon());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnHoaDon};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewThongKe());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnKhachHang, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnThongKe};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        // TODO add your handling code here:
        setpanel(new ViewKhachHang());
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnVoucher, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnKhachHang};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseClicked
//
//        // TODO add yowNhur handling code here:
//        setpanel(new ViewNhanVien());
//
//        // TODO add your handling code here:
//        setpanel(new ViewNhanVien(this, rootPaneCheckingEnabled));
//        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnThoat};
//        JButton[] btn2 = {btnNhanVien};
//        for (JButton btn22 : btn2) {
//            btn22.setBackground(new Color(9, 107, 104));
//        }
//        for (JButton btn11 : btn1) {
//            btn11.setBackground(new Color(128,203,196));
//        }
    }//GEN-LAST:event_btnNhanVienMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        // TODO add your handling code here:
        JButton[] btn1 = {btnBanHang, btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnNhanVien};
        JButton[] btn2 = {btnThoat};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
        setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_btnThoatMouseClicked

    private void btnBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseClicked

        setpanel(new ViewBanHang());
        JButton[] btn1 = {btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnBanHang};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnBanHangMouseClicked

    private void btnVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoucherMouseClicked
     setpanel(new ViewVoucher());
        JButton[] btn1 = {btnBanHang,btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnNhanVien, btnThoat};
        JButton[] btn2 = {btnVoucher};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnVoucherMouseClicked

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        setpanel(new ViewNhanVien());
        JButton[] btn1 = {btnBanHang,btnSanPham, btnHoaDon, btnThongKe, btnKhachHang, btnVoucher, btnThoat};
        JButton[] btn2 = {btnNhanVien};
        for (JButton btn22 : btn2) {
            btn22.setBackground(new Color(9, 107, 104));
        }
        for (JButton btn11 : btn1) {
            btn11.setBackground(new Color(128,203,196));
        }
    }//GEN-LAST:event_btnNhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewQuanLy().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
