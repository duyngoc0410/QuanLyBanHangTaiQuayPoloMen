package Repository;

import Entity.KhachHang;
import JDBCUtil.DBConnect;
import Response.KhachHangResponse;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangRepository {

    public ArrayList<KhachHangResponse> getAll() {
        String sql = """
                        SELECT Id
                              ,MaKhachHang
                              ,TenKhachHang
                              ,GioiTinh
                              ,SoDienThoai
                              ,DiaChi
                              ,NgayTao
                              ,TrangThai
                          FROM KhachHang
                     """;
        ArrayList<KhachHangResponse> listKhachHang = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHangResponse kh = new KhachHangResponse();
                kh.setId(rs.getInt(1));
                kh.setMaKhachHang(rs.getString(2));
                kh.setTenKhachHang(rs.getString(3));
                kh.setGioiTinh(rs.getBoolean(4));
                kh.setSoDienThoai(rs.getString(5));
                kh.setDiaChi(rs.getString(6));
                kh.setNgayTao(rs.getDate(7));
                kh.setTrangThai(rs.getInt(8));
                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listKhachHang;
    }

    public boolean addKhachHang(KhachHangResponse kh) {
        String sql = """
                            INSERT INTO [dbo].[KhachHang]
                                       ([TenKhachHang]
                                       ,[GioiTinh]
                                       ,[SoDienThoai]
                                       ,[DiaChi]
                                       ,[NgayTao]
                                       ,[TrangThai])
                                 VALUES
                                       (?,?,?,?,?,1)
                            """;
        ArrayList<KhachHangResponse> listKhachHang = new ArrayList<>();
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getTenKhachHang());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getDiaChi());
            java.sql.Date sqlDate = new java.sql.Date(kh.getNgayTao().getTime());
            ps.setDate(5, sqlDate);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return check > 0;
    }

    public boolean sua(Integer id, KhachHangResponse kh) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[KhachHang]
                       SET [TenKhachHang] = ?
                          ,[GioiTinh] = ?
                          ,[SoDienThoai] = ?
                          ,[DiaChi] = ?
                          ,[NgayTao] = ?
                     WHERE Id = ?
                    """;
//        ArrayList<KhachHangResponse> listKhachHang = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, kh.getTenKhachHang());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getDiaChi());
            java.sql.Date sqlDate = new java.sql.Date(kh.getNgayTao().getTime());
            ps.setDate(5, sqlDate);
            ps.setInt(6, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<KhachHangResponse> search(String maKhachHang, String tenKhachHang, String soDienThoai) {
        String sql = """
                     SELECT Id,
                            MaKhachHang,
                            TenKhachHang,
                            GioiTinh,
                            SoDienThoai,
                            DiaChi,
                            NgayTao
                     FROM KhachHang
                     WHERE 
                           MaKhachHang LIKE ?
                        OR TenKhachHang LIKE ? 
                        OR SoDienThoai LIKE ? 
                    """;
        ArrayList<KhachHangResponse> list_search = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + maKhachHang + "%");
            ps.setString(2, "%" + tenKhachHang + "%");
            ps.setString(3, "%" + soDienThoai + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachHangResponse kh = new KhachHangResponse();
                    kh.setId(rs.getInt(1));
                    kh.setMaKhachHang(rs.getString(2));
                    kh.setTenKhachHang(rs.getString(3));
                    kh.setGioiTinh(rs.getBoolean(4));
                    kh.setSoDienThoai(rs.getString(5));
                    kh.setDiaChi(rs.getString(6));
                    kh.setNgayTao(rs.getDate(7));
                    list_search.add(kh);
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } catch (Exception e) {
        }
        return list_search;

    }

    //              TẠO HÓA ĐƠN
    public Integer insertAndGetId(KhachHang kh) {
        String sqlInsert = "INSERT INTO KhachHang (TenKhachHang, SoDienThoai) VALUES (?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sqlInsert)) {
            ps.setString(1, kh.getTenKhachHang());
            ps.setString(2, kh.getSoDienThoai());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Sau khi thêm thành công, tìm lại ID theo SĐT
                return getIdBySdt(kh.getSoDienThoai());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getIdBySdt(String sdt) {
        String sql = "SELECT Id FROM KhachHang WHERE SoDienThoai = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

//              KẾT THÚC
}
