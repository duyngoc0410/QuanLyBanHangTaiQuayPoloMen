/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import JDBCUtil.DBConnect;
import java.util.ArrayList;
import Response.ThongKeReponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author OS
 */
public class ThongKeRepository {

    public ArrayList<ThongKeReponse> getAllThongKe() {
        String sql = """
        SELECT 
            s.TenSanPham,
            spct.MaSanPhamCT,
            hdct.GiaDaThanhToan,
            hdct.SoLuong,
            hdct.ThanhTien,
            hd.TrangThai,
            hd.NgayThanhToan
        FROM HoaDonChiTiet hdct
        JOIN HoaDon hd ON hdct.IdHoaDon = hd.Id
        JOIN SanPhamChiTiet spct ON hdct.IdSanPhamChiTiet = spct.IdSanPham
        JOIN SanPham s ON spct.IdSanPham = s.Id
        WHERE hd.NgayThanhToan <= GETDATE() 
        ORDER BY hd.NgayThanhToan DESC; 
        """;

        ArrayList<ThongKeReponse> listThongKe = new ArrayList<>();
        try (
                Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ThongKeReponse tk = new ThongKeReponse();
                tk.setTenSanPham(rs.getString(1));
                tk.setMaSanPhamChiTiet(rs.getString(2));
                tk.setGia(rs.getDouble(3));
                tk.setSoLuong(rs.getInt(4));
                tk.setThanhTien(rs.getDouble(5));
                tk.setTrangThai(rs.getInt(6));
                tk.setNgayThanhToan(rs.getDate(7));
                listThongKe.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThongKe;
    }

}
