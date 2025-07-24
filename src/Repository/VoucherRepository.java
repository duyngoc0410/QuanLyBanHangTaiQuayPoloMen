/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.Voucher;
import JDBCUtil.DBConnect;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author nguyensyan
 */
public class VoucherRepository {

    public ArrayList<Voucher> getAll() {
        ArrayList<Voucher> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM Voucher");
            while (r.next()) {
                Voucher vc = new Voucher();
                vc.setId(r.getInt(1));
                vc.setTenVoucher(r.getString(2));
                vc.setMoTa(r.getString(3));
                vc.setPhanTramGiamGia(r.getInt(4));
                vc.setNgayBatDau(r.getDate(5));
                vc.setNgayKetThuc(r.getDate(6));
                vc.setTrangThai(r.getInt(7));
                list.add(vc);
            }
            return list;
        } catch (Exception e) {

        }
        return null;
    }

    public boolean add(Voucher vc) {
        PreparedStatement ps = null;
        Connection c = null;
        try {
            c = DBConnect.getConnection();
            ps = c.prepareStatement("INSERT INTO Voucher (TenVoucher, MoTa, PhanTramGiamGia, NgayBatDau, NgayKetThuc, TrangThai) VALUES(?,?,?,?,?,?)");
            ps.setString(1, vc.getTenVoucher());
            ps.setString(2, vc.getMoTa());
            ps.setInt(3, vc.getPhanTramGiamGia());
            java.sql.Date sqlDate = new java.sql.Date(vc.getNgayBatDau().getTime());
            ps.setDate(4, sqlDate);
            java.sql.Date sqlData = new java.sql.Date(vc.getNgayKetThuc().getTime());
            ps.setDate(5, sqlData);
            ps.setInt(6, vc.getTrangThai());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public int Delete(Voucher vc) {
        try {
            Connection c = DBConnect.getConnection();
            PreparedStatement p = c.prepareStatement("DELETE FROM Voucher WHERE TenVoucher = ?");
            p.setString(1, vc.getTenVoucher()); // Đặt đúng kiểu dữ liệu
            return p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void EditByName(Voucher vc) {
        String sql = "UPDATE Voucher SET MoTa = ?, PhanTramGiamGia = ?, NgayBatDau = ?, NgayKetThuc = ?, TrangThai = ? WHERE TenVoucher = ?";

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, vc.getMoTa());
            ps.setInt(2, vc.getPhanTramGiamGia());
            ps.setDate(3, new java.sql.Date(vc.getNgayBatDau().getTime()));
            ps.setDate(4, new java.sql.Date(vc.getNgayKetThuc().getTime()));
            ps.setInt(5, vc.getTrangThai());
            ps.setString(6, vc.getTenVoucher());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Không tìm thấy voucher để cập nhật theo tên: " + vc.getTenVoucher());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//          TẠO HÓA ĐƠN

    public Integer getIdByTen(String tenVoucher) {
        String sql = "SELECT Id FROM Voucher WHERE Ten = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenVoucher);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//              KẾT THÚC
}
