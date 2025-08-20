package Repository;

import Entity.SanPham;
import JDBCUtil.DBConnect;
import Response.SanPhamChiTietResponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SanPhamChiTietRepository {

    public ArrayList<SanPhamChiTietResponse> getAll() {
        String sql = """
                            SELECT
                                spct.Id
                                ,spct.MaSanPhamCT
                                ,sp.MaSanPham
                                ,ms.TenMauSac
                                ,kt.TenKichThuoc
                                ,cl.TenChatLieu
                                ,sp.TenSanPham
                                ,spct.Gia
                                ,spct.MoTa
                                ,spct.SoLuong
                                ,spct.NgayNhap
                                ,spct.TrangThai
                        FROM SanPhamChiTiet spct
                        JOIN SanPham sp ON spct.IdSanPham = sp.Id
                        JOIN MauSac ms ON spct.IdMauSac = ms.Id
                        JOIN KichThuoc kt ON spct.IdKichThuoc = kt.Id
                        JOIN ChatLieu cl ON spct.IdChatLieu = cl.Id  
                    """;
        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPhamChiTietResponse spr = new SanPhamChiTietResponse();
                spr.setId(rs.getInt(1));
                spr.setMaSanPhamCT(rs.getString(2));
                spr.setMaSanPham(rs.getString(3));
                spr.setTenMauSac(rs.getString(4));
                spr.setTenKichThuoc(rs.getString(5));
                spr.setTenChatLieu(rs.getString(6));
                spr.setTenSanPham(rs.getString(7));
                spr.setGia(rs.getBigDecimal(8));
                spr.setMoTa(rs.getString(9));
                spr.setSoLuong(rs.getInt(10));
                spr.setNgayNhap(rs.getDate(11));
                spr.setTrangThai(rs.getInt(12));
                list.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //              SEARCH 
    public ArrayList<SanPhamChiTietResponse> search(String keyword) {
        String sql = """
                SELECT
                        spct.Id
                        ,spct.MaSanPhamCT
                        ,sp.MaSanPham
                        ,ms.TenMauSac
                        ,kt.TenKichThuoc
                        ,cl.TenChatLieu
                        ,sp.TenSanPham
                        ,spct.Gia
                        ,spct.MoTa
                        ,spct.SoLuong
                        ,spct.NgayNhap
                        ,spct.TrangThai
                FROM SanPhamChiTiet spct
                JOIN SanPham sp ON spct.IdSanPham = sp.Id
                JOIN MauSac ms ON spct.IdMauSac = ms.Id
                JOIN KichThuoc kt ON spct.IdKichThuoc = kt.Id
                JOIN ChatLieu cl ON spct.IdChatLieu = cl.Id  
                WHERE 
                        spct.MaSanPhamCT LIKE ?
                    OR  sp.MaSanPham LIKE ?
                    OR  ms.TenMauSac LIKE ?
                    OR  kt.TenKichThuoc LIKE ?
                    OR  cl.TenChatLieu LIKE ?
                    OR sp.TenSanPham LIKE ?
                     """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (keyword.length() > 0) {
                String value = "%" + keyword + "%";
                for (int i = 1; i <= 6; i++) {
                    ps.setString(i, value);
                }
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spr = new SanPhamChiTietResponse();
                spr.setId(rs.getInt(1));
                spr.setMaSanPhamCT(rs.getString(2));
                spr.setMaSanPham(rs.getString(3));
                spr.setTenMauSac(rs.getString(4));
                spr.setTenKichThuoc(rs.getString(5));
                spr.setTenChatLieu(rs.getString(6));
                spr.setTenSanPham(rs.getString(7));
                spr.setGia(rs.getBigDecimal(8));
                spr.setMoTa(rs.getString(9));
                spr.setSoLuong(rs.getInt(10));
                spr.setNgayNhap(rs.getDate(11));
                spr.setTrangThai(rs.getInt(12));
                list.add(spr);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

//          BẮT ĐẦU UPDATE SẢN PHẨM
    public boolean updateTrangThaiByIdSanPham(int idSanPham, int trangThai) {
        String sql = "UPDATE SanPhamChiTiet SET TrangThai = ? WHERE IdSanPham = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, trangThai);
            ps.setInt(2, idSanPham);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//          KẾT THÚC

//              BẮT ĐẦU THÊM SẢN PHẨM CHI TIẾT
    public SanPhamChiTietResponse findChiTietTrungLap(int idSP, int idKichCo, int idChatLieu, int idMauSac, BigDecimal gia, String moTa) {
        String sql = "SELECT * FROM SanPhamChiTiet WHERE IdSanPham = ? AND IdKichThuoc = ? AND IdChatLieu = ? AND IdMauSac = ? AND Gia = ? AND MoTa = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSP);
            ps.setInt(2, idKichCo);
            ps.setInt(3, idChatLieu);
            ps.setInt(4, idMauSac);
            ps.setBigDecimal(5, gia);
            ps.setString(6, moTa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPhamChiTietResponse spct = new SanPhamChiTietResponse();
                spct.setId(rs.getInt("Id"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                return spct;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSoLuong(int idSPCT, int soLuongMoi) {
        String sql = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE Id = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongMoi);
            ps.setInt(2, idSPCT);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(SanPhamChiTietResponse spct) {
        String sql = """
            INSERT INTO SanPhamChiTiet 
            (IdSanPham, IdMauSac, IdKichThuoc, IdChatLieu, Gia, MoTa, SoLuong, NgayNhap, TrangThai) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdSanPham());
            ps.setInt(2, spct.getIdMauSac());
            ps.setInt(3, spct.getIdKichThuoc());
            ps.setInt(4, spct.getIdChatLieu());
            ps.setBigDecimal(5, spct.getGia());
            ps.setString(6, spct.getMoTa());
            ps.setInt(7, spct.getSoLuong());
            ps.setDate(8, new java.sql.Date(spct.getNgayNhap().getTime()));
            ps.setInt(9, spct.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//          KẾT THÚC

    //              BẮT ĐẦU UPDATE SẢN PHẨM CHI TIẾT
    public int tinhTongSoLuongTheoIdSanPham(int idSanPham) {
        String sql = "SELECT SUM(SoLuong) FROM SanPhamChiTiet WHERE IdSanPham = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // nếu null thì mặc định = 0
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean update(SanPhamChiTietResponse spct) {
        String sql = """
        UPDATE SanPhamChiTiet SET 
            IdKichThuoc = ?, IdChatLieu = ?, IdMauSac = ?, SoLuong = ?, 
            Gia = ?, MoTa = ?, NgayNhap = ?, TrangThai = ?
        WHERE Id = ?
    """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdKichThuoc());
            ps.setInt(2, spct.getIdChatLieu());
            ps.setInt(3, spct.getIdMauSac());
            ps.setInt(4, spct.getSoLuong());
            ps.setBigDecimal(5, spct.getGia());
            ps.setString(6, spct.getMoTa());
            ps.setDate(7, new java.sql.Date(spct.getNgayNhap().getTime()));
            ps.setInt(8, spct.getTrangThai());
            ps.setInt(9, spct.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTongSoLuongByMaSanPham(String maSanPham) {
        String sql = """
        SELECT SUM(SCT.SoLuong)
        FROM SanPhamChiTiet SCT
        JOIN SanPham SP ON SCT.IdSanPham = SP.Id
        WHERE SP.MaSanPham = ? AND SCT.TrangThai = 1
    """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // nếu null sẽ trả về 0
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//              KẾT THÚC
    //      BẮT ĐẦU BẮN HÀNG
    public SanPhamChiTietResponse findById(int id) {
        String sql = """
        SELECT
            spct.Id,
            spct.MaSanPhamCT,
            sp.MaSanPham,
            ms.TenMauSac,
            kt.TenKichThuoc,
            cl.TenChatLieu,
            sp.TenSanPham,
            spct.Gia,
            spct.MoTa,
            spct.SoLuong,
            spct.NgayNhap,
            spct.TrangThai
        FROM SanPhamChiTiet spct
        JOIN SanPham sp ON spct.IdSanPham = sp.Id
        JOIN MauSac ms ON spct.IdMauSac = ms.Id
        JOIN KichThuoc kt ON spct.IdKichThuoc = kt.Id
        JOIN ChatLieu cl ON spct.IdChatLieu = cl.Id
        WHERE spct.Id = ?
    """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SanPhamChiTietResponse spr = new SanPhamChiTietResponse();
                    spr.setId(rs.getInt(1));
                    spr.setMaSanPhamCT(rs.getString(2));
                    spr.setMaSanPham(rs.getString(3));
                    spr.setTenMauSac(rs.getString(4));
                    spr.setTenKichThuoc(rs.getString(5));
                    spr.setTenChatLieu(rs.getString(6));
                    spr.setTenSanPham(rs.getString(7));
                    spr.setGia(rs.getBigDecimal(8));
                    spr.setMoTa(rs.getString(9));
                    spr.setSoLuong(rs.getInt(10));
                    spr.setNgayNhap(rs.getDate(11));
                    spr.setTrangThai(rs.getInt(12));
                    return spr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSoLuong(SanPhamChiTietResponse sp) {
        String sql = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE Id = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sp.getSoLuong());
            ps.setObject(2, sp.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateSanPham(SanPhamChiTietResponse sp) {
        String sql = """
        UPDATE SanPhamChiTiet
        SET SoLuong = ?, TrangThai = ?
        WHERE Id = ?
    """;

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, sp.getSoLuong());
            ps.setInt(2, sp.getTrangThai());
            ps.setInt(3, sp.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //      KẾT THÚC
}
