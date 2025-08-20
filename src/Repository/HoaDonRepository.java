/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.Connection;
import Entity.HoaDon;
import JDBCUtil.DBConnect;
import Response.HoaDonChiTietRepose;
import Response.HoaDonRepose;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author nguyensyan
 */
public class HoaDonRepository {

    public ArrayList<HoaDonRepose> findAll() {

        String sql = """
                SELECT	
                             hd.Id,
                             hd.MaHoaDon,
                             nv.MaNhanVien,
                             nv.TenNhanVien,
                             kh.MaKhachHang,
                             kh.TenKhachHang,
                             SUM(ISNULL(hdct.GiaDaThanhToan * hdct.SoLuong, 0)) AS ThanhTien, 
                             hd.NgayThanhToan, 
                             hd.TrangThai
                         FROM HoaDon hd
                         JOIN KhachHang kh ON hd.IdKhachHang = kh.Id
                         JOIN NhanVien nv ON hd.IdNhanVien = nv.Id
                         LEFT JOIN HoaDonChiTiet hdct ON hd.Id = hdct.IdHoaDon
                         GROUP BY 
                             hd.MaHoaDon,
                             hd.Id, 
                             nv.MaNhanVien,
                             nv.TenNhanVien,
                             kh.MaKhachHang,
                             kh.TenKhachHang,
                             hd.NgayThanhToan,
                             hd.TrangThai
        """;
        ArrayList<HoaDonRepose> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonRepose hdr = new HoaDonRepose();
                hdr.setId(rs.getInt(1));
                hdr.setMaHoaDon(rs.getString(2));
                hdr.setMaNhanVien(rs.getString(3));
                hdr.setTenNhanVien(rs.getString(4));
                hdr.setMaKhachHang(rs.getString(5));
                hdr.setTenKhachHang(rs.getString(6));
                hdr.setThanhTien(rs.getBigDecimal(7));
                hdr.setNgayThanhToan(rs.getDate(8));
                hdr.setTrangThai(rs.getInt(9));
                list.add(hdr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<HoaDonRepose> getAllByTrangThai() {

        String sql = """
               SELECT	
                    hd.Id,
                    hd.MaHoaDon,
                    nv.MaNhanVien,
                    nv.TenNhanVien,
                    kh.MaKhachHang,
                    kh.TenKhachHang,
                    kh.SoDienThoai, 
                    ISNULL(SUM(hdct.GiaDaThanhToan * hdct.SoLuong), 0) AS ThanhTien,
                    hd.NgayThanhToan, 
                    hd.TrangThai
                FROM HoaDon hd
                JOIN KhachHang kh ON hd.IdKhachHang = kh.Id
                JOIN NhanVien nv ON hd.IdNhanVien = nv.Id
                LEFT JOIN HoaDonChiTiet hdct ON hd.Id = hdct.IdHoaDon  
                WHERE hd.TrangThai = 0
                GROUP BY hd.Id, hd.MaHoaDon, nv.MaNhanVien, nv.TenNhanVien, kh.MaKhachHang, kh.TenKhachHang, kh.SoDienThoai, hd.NgayThanhToan, hd.TrangThai
        """;
        ArrayList<HoaDonRepose> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonRepose hdr = new HoaDonRepose();
                hdr.setId(rs.getInt(1));
                hdr.setMaHoaDon(rs.getString(2));
                hdr.setMaNhanVien(rs.getString(3));
                hdr.setTenNhanVien(rs.getString(4));
                hdr.setMaKhachHang(rs.getString(5));
                hdr.setTenKhachHang(rs.getString(6));
                hdr.setSoDienThoai(rs.getString(7));
                Object thanhTienObj = rs.getObject(8);
                BigDecimal thanhTien = BigDecimal.ZERO;

                if (thanhTienObj != null) {
                    if (thanhTienObj instanceof BigDecimal) {
                        thanhTien = (BigDecimal) thanhTienObj;
                    } else if (thanhTienObj instanceof Double) {
                        thanhTien = BigDecimal.valueOf((Double) thanhTienObj);
                    } else if (thanhTienObj instanceof Integer) {
                        thanhTien = BigDecimal.valueOf((Integer) thanhTienObj);
                    }
//                    System.out.println("Converted value: " + thanhTien);
                }
                hdr.setThanhTien(thanhTien);
                hdr.setNgayThanhToan(rs.getDate(9));
                hdr.setTrangThai(rs.getInt(10));
                list.add(hdr);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<HoaDonRepose> search(String keyword) {
        String sql = """
                SELECT	
                	hd.Id,
                	hd.MaHoaDon,
                	nv.MaNhanVien,
                	nv.TenNhanVien,
                	kh.TenKhachHang,
                        (hdct.GiaDaThanhToan * hdct.SoLuong) AS ThanhTien , 
                	hd.NgayThanhToan, 
                	hd.TrangThai
                FROM HoaDon hd
                JOIN KhachHang kh ON hd.IdKhachHang = kh.Id
                JOIN NhanVien nv ON hd.IdNhanVien = nv.Id
                JOIN HoaDonChiTiet hdct ON hd.Id = hdct.IdHoaDon
        """;
        if (!keyword.isEmpty()) {
            sql += """
                WHERE (
                    hd.MaHoaDon LIKE ?
                    OR nv.MaNhanVien LIKE ?
                    OR nv.TenNhanVien LIKE ?
                    OR kh.TenKhachHang LIKE ?
                    OR (
                        CASE hd.TrangThai 
                        WHEN 1 THEN N'Đã thanh toán'            
                        WHEN 0 THEN N'Chưa thanh toán'
                        ELSE N'Không xác định'
                        END 
                        ) 
                    LIKE ?
                    )
                  """;
        }
        ArrayList<HoaDonRepose> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            if (keyword.length() > 0) {
                String value = "%" + keyword + "%";
                for (int i = 1; i <= 5; i++) {
                    ps.setString(i, value);
                }
            }
            System.out.println("SQL: " + ps.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonRepose hd = new HoaDonRepose();
                    hd.setId(rs.getInt(1));
                    hd.setMaHoaDon(rs.getString(2));
                    hd.setMaNhanVien(rs.getString(3));
                    hd.setTenNhanVien(rs.getString(4));
                    hd.setTenKhachHang(rs.getString(5));
                    String raw = rs.getString(6);
                    raw = raw.replace(" đ", "");
                    BigDecimal thanhTien = new BigDecimal(raw);
                    hd.setThanhTien(thanhTien);
                    hd.setNgayThanhToan(rs.getDate(7));
                    hd.setTrangThai(rs.getInt(8));
                    list.add(hd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean add(HoaDon hd) {
        int check = 0;

        String sql = """
                       INSERT INTO HoaDon
                                 (IdKhachHang
                                 ,IdNhanVien
                                 ,IdVoucher
                                 ,TienDua
                                 ,ThanhTien
                                 ,NgayThanhToan
                                 ,TrangThai)
                           VALUES
                                 (?,?,?,?,?,?,?)
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKhachHang());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getIdVoucher());
            ps.setObject(4, 0);
            ps.setObject(5, 0);
            ps.setObject(6, new Date());
            ps.setObject(7, 0);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return check > 0;
    }

    public String generateNewMaHD() {
        String sql = "SELECT MAX(MaHoaDon) AS MaCuoi FROM HoaDon";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String maCu = rs.getString("MaCuoi"); // ví dụ: HD0012
                if (maCu != null) {
                    int so = Integer.parseInt(maCu.replace("HD", ""));
                    return String.format("HD%05d", so + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "HD00001";
    }

    public boolean insert(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (IdNhanVien, IdKhachHang, NgayThanhToan, TrangThai) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hd.getIdNhanVien());
            ps.setInt(2, hd.getIdKhachHang());
            java.sql.Date ngayTaoSql = new java.sql.Date(hd.getNgayThanhToan().getTime());
            ps.setDate(3, ngayTaoSql);
            ps.setInt(4, hd.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateTongTien(int idHoaDon, BigDecimal tongTien) {
        String sql = "UPDATE HoaDon SET ThanhTien = ? WHERE Id = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBigDecimal(1, tongTien);
            ps.setObject(2, idHoaDon);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateHoaDon(HoaDonRepose hd) {
        String sql = """
        UPDATE HoaDon
        SET 
            ThanhTien = ?, 
            TienDua = ?, 
            IdVoucher = ?, 
            TrangThai = ?
        WHERE Id = ?
    """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, hd.getThanhTien() != null ? hd.getThanhTien() : BigDecimal.ZERO);
            ps.setBigDecimal(2, hd.getTienDua() != null ? hd.getTienDua() : BigDecimal.ZERO);

            Integer voucherId = hd.getIdVoucher();
            if (voucherId == null || voucherId == 0) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, voucherId);
            }

            ps.setInt(4, hd.getTrangThai());
            ps.setInt(5, hd.getId());
            System.out.println("Đang cập nhật hóa đơn ID = " + hd.getId());
            int rows = ps.executeUpdate();
            System.out.println("Cập nhật hóa đơn thành công" + rows);
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật hóa đơn:");
            e.printStackTrace();
            return false;
        }
    }
}
