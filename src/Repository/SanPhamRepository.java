package Repository;

import Entity.SanPham;
import JDBCUtil.DBConnect;
import Response.SanPhamResponse;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

public class SanPhamRepository {

    public ArrayList<SanPham> getAll() {
        String sql = """
                        SELECT
                                sp.Id,
                                th.TenThuongHieu,
                                sp.MaSanPham,
                                sp.TenSanPham,
                                SUM(spct.SoLuong) AS SoLuongTong,
                                sp.TrangThai
                        FROM
                                SanPham sp	
                        JOIN SanPhamChiTiet spct ON sp.Id = spct.IdSanPham
                        JOIN ThuongHieu th ON sp.IdThuongHieu = th.Id
                        GROUP BY 
                                sp.Id,
                                sp.IdThuongHieu,
                                th.TenThuongHieu,
                                sp.MaSanPham,
                                sp.TenSanPham,
                                sp.TrangThai
                     """;

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("Id"));
                sanPham.setTenThuongHieu(rs.getString("TenThuongHieu"));
                sanPham.setMaSanPham(rs.getString("MaSanPham"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setSoLuong(rs.getInt("SoLuongTong"));
                sanPham.setTrangThai(rs.getInt("TrangThai"));
                listSanPham.add(sanPham);
            }
            return listSanPham;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<SanPham> search(String keyword) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        String sql = """
                SELECT
                        sp.Id,
                        th.TenThuongHieu,
                        sp.MaSanPham,
                        sp.TenSanPham,
                        SUM(spct.SoLuong) AS SoLuongTong,
                        sp.TrangThai
                FROM
                        SanPham sp	
                JOIN    
                        SanPhamChiTiet spct ON sp.Id = spct.IdSanPham
                JOIN 
                        ThuongHieu th ON sp.IdThuongHieu = th.Id
                """ + (hasKeyword ? """
                WHERE
                        sp.TenSanPham LIKE ?
                    OR 
                        sp.MaSanPham LIKE ?
                """ : "") + """
                GROUP BY 
                        sp.Id,
                        sp.IdThuongHieu,
                        th.TenThuongHieu,
                        sp.MaSanPham,
                        sp.TenSanPham,
                        spct.SoLuong,
                        sp.TrangThai
                """;

        ArrayList<SanPham> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (hasKeyword) {
                String value = "%" + keyword.trim() + "%";
                ps.setString(1, value);
                ps.setString(2, value);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("Id"));
                sanPham.setTenThuongHieu(rs.getString("TenThuongHieu"));
                sanPham.setMaSanPham(rs.getString("MaSanPham"));
                sanPham.setTenSanPham(rs.getString("TenSanPham"));
                sanPham.setSoLuong(rs.getInt("SoLuongTong"));
                sanPham.setTrangThai(rs.getInt("TrangThai"));

                list.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
//                      BẮT ĐẦU UPDATE BẢNG SẢN PHẨM

    public SanPham findByMaSanPham(String maSP) {
        String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            System.out.println("Tìm kiếm SP với mã: [" + maSP + "]");

            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("Id"));
                sp.setIdThuongHieu(rs.getInt("IdThuongHieu"));
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                return sp;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateTenVaTrangThaiByMa(String maSP, String tenMoi, int trangThai) {
        String sql = "UPDATE SanPham SET TenSanPham = ?, TrangThai = ? WHERE MaSanPham = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenMoi);
            ps.setInt(2, trangThai);
            ps.setString(3, maSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//                  KẾT THÚC

//                  BẮT ĐẦU THÊM SẢN PHẨM CHI TIẾT
    public String generateMaSanPhamMoi() {
        String sql = "SELECT MAX(MaSanPham) AS MaxMaSP FROM SanPham";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String maxMa = rs.getString("MaxMaSP");
                if (maxMa == null) {
                    return "SP001";
                }

                // Bóc tách phần số phía sau SP
                int so = Integer.parseInt(maxMa.substring(2));
                return String.format("SP%03d", so + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SP001";
    }

    public SanPham findByTenSanPham(String tenSP) {
        String sql = """
                    SELECT Id, IdThuongHieu, MaSanPham, TenSanPham, TrangThai 
                    FROM SanPham WHERE TenSanPham = ?
                     """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Map trực tiếp từng trường vào constructor hoặc setter
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("Id"));
                sp.setIdThuongHieu(rs.getInt("IdThuongHieu"));
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(SanPham sp) {
        String sql = """
                        INSERT INTO SanPham (IdThuongHieu, TenSanPham, TrangThai)
                        VALUES (?, ?, ?)
                """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 1);       // nếu bạn chưa có IdThuongHieu, set = 0
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, sp.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//                  KẾT THÚC

    //          BẮT ĐẦU UPDATE SẢN PHẨM CHI TIẾT
    public ArrayList<SanPham> getAllWithSoLuong() {
        String sql = """
        SELECT sp.*, ISNULL(SUM(spct.SoLuong), 0) AS SoLuong
        FROM SanPham sp
        LEFT JOIN SanPhamChiTiet spct ON sp.Id = spct.IdSanPham
        GROUP BY sp.Id, sp.IdThuongHieu, sp.MaSanPham, sp.TenSanPham, sp.TrangThai
    """;

        ArrayList<SanPham> list = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("Id"));
                sp.setIdThuongHieu(rs.getInt("IdThuongHieu"));
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                sp.setSoLuong(rs.getInt("SoLuong")); // cần có setSoLuong(int) trong lớp SanPham
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //              KẾT THÚC
}
