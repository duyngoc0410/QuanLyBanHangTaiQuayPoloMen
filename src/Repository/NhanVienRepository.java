package Repository;

import JDBCUtil.DBConnect;
import Response.KhachHangResponse;
import Response.NhanVienResponse;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhanVienRepository {

    public ArrayList<NhanVienResponse> getAll() {
        String sql = """
                     SELECT Id
                           ,MaNhanVien
                           ,TenNhanVien
                           ,DiaChi
                           ,MatKhau
                           ,GioiTinh
                           ,Email
                           ,SoDienThoai
                           ,ChucVu
                           ,NgayTao
                           ,NgaySua
                           ,TrangThai
                       FROM NhanVien
                     """;
        ArrayList<NhanVienResponse> list_nv = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVienResponse nv = new NhanVienResponse();
                nv.setId(rs.getInt(1));
                nv.setMaNhanVien(rs.getString(2));
                nv.setTenNhanVien(rs.getString(3));
                nv.setDiaChi(rs.getString(4));
                nv.setMatKhau(rs.getString(5));
                nv.setGioiTinh(rs.getBoolean(6));
                nv.setEmail(rs.getString(7));
                nv.setSoDienThoai(rs.getString(8));
                nv.setChucVu(rs.getString(9));
                nv.setNgayTao(rs.getDate(10));
                nv.setNgaySua(rs.getDate(11));
                nv.setTrangThai(rs.getInt(12));
                list_nv.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_nv;
    }

    public boolean addNhanVien(NhanVienResponse nv) {
        String sql = """
                            INSERT INTO [dbo].[NhanVien]
                                            ([TenNhanVien]
                                            ,[DiaChi]
                                            ,[MatKhau]
                                            ,[GioiTinh]
                                            ,[Email]
                                            ,[SoDienThoai]
                                            ,[ChucVu]
                                            ,[NgayTao]
                                            ,[NgaySua]
                                            ,[TrangThai])
                                      VALUES
                                       (?,?,?,?,?,?,?,?,?,?)
                            """;
        ArrayList<NhanVienResponse> listNhanVien = new ArrayList<>();
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getDiaChi());
            ps.setString(3, nv.getMatKhau());
            ps.setBoolean(4, nv.isGioiTinh());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getChucVu());
            java.sql.Date sqlDate = new java.sql.Date(nv.getNgayTao().getTime());
            ps.setDate(8, sqlDate);
            ps.setDate(9, sqlDate);
            ps.setInt(10, nv.getTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);

        }
        return check > 0;
    }

    public boolean sua(Integer id, NhanVienResponse nv) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[NhanVien]
                        SET [TenNhanVien] = ?
                           ,[DiaChi] = ?
                           ,[MatKhau] = ?
                           ,[GioiTinh] = ?
                           ,[Email] = ?
                           ,[SoDienThoai] = ?
                           ,[ChucVu] = ?
                           ,[NgayTao] = ?
                           ,[NgaySua] = ?
                           ,[TrangThai] = ?
                      WHERE MaNhanVien = ?
                    """;
//        ArrayList<KhachHangResponse> listKhachHang = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, nv.getTenNhanVien());
            ps.setString(2, nv.getDiaChi());
            ps.setString(3, nv.getMatKhau());
            ps.setBoolean(4, nv.isGioiTinh());
            ps.setString(5, nv.getEmail());
            ps.setString(6, nv.getSoDienThoai());
            ps.setString(7, nv.getChucVu());
            java.sql.Date sqlDate = new java.sql.Date(nv.getNgayTao().getTime());
            ps.setDate(8, sqlDate);
            java.sql.Date sqlDate1 = new java.sql.Date(nv.getNgaySua().getTime());
            ps.setDate(9, sqlDate1);
            ps.setInt(10, nv.getTrangThai());
            ps.setInt(11, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<NhanVienResponse> search(String maNhanVien, String tenNhanVien, String soDienThoai) {
        String sql = """
                     SELECT Id,
                            TenNhanVien,
                            MaNhanVien,
                            GioiTinh,
                            SoDienThoai,
                            DiaChi,
                            NgayTao
                     FROM NhanVien
                     WHERE 
                           TenNhanVien LIKE ?
                        OR MaNhanVien LIKE ? 
                        OR SoDienThoai LIKE ? 
                    """;
        ArrayList<NhanVienResponse> list_search = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + maNhanVien + "%");
            ps.setString(2, "%" + tenNhanVien + "%");
            ps.setString(3, "%" + soDienThoai + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVienResponse kh = new NhanVienResponse();
                    kh.setId(rs.getInt(1));
                    kh.setMaNhanVien(rs.getString(3));
                    kh.setTenNhanVien(rs.getString(2));
                    kh.setDiaChi(rs.getString(6));
                    kh.setGioiTinh(rs.getBoolean(4));
                    kh.setSoDienThoai(rs.getString(5));
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

    public NhanVienResponse login(String email, String passWord) {
        String sql = """
                SELECT Id
                    ,MaNhanVien
                    ,TenNhanVien
                    ,DiaChi
                    ,MatKhau
                    ,GioiTinh
                    ,Email
                    ,SoDienThoai
                    ,ChucVu
                    ,NgayTao
                    ,NgaySua
                    ,TrangThai
                FROM NhanVien
                WHERE Email = ?
                AND MatKhau = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVienResponse nv = new NhanVienResponse();
                nv.setId(rs.getInt("Id"));
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setNgayTao(rs.getDate("NgayTao"));
                nv.setNgaySua(rs.getDate("NgaySua"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                return nv; // Đăng nhập thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
//              TẠO HÓA ĐƠN

    public int getIdByTen(String tenNV) {
        String sql = "SELECT Id FROM NhanVien WHERE TenNhanVien = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Không tìm thấy
    }

    public String generateNewMaNhanVien() {
        String sql = "SELECT MAX(MaNhanVien) AS MaCuoi FROM NhanVien";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String maCu = rs.getString("MaCuoi"); // ví dụ: HD0012
                if (maCu != null) {
                    int so = Integer.parseInt(maCu.replace("NV", ""));
                    return String.format("NV%04d", so + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "NV0001";
    }

}
