package Repository;

import JDBCUtil.DBConnect;
import Response.KichThuocResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class KichThuocRepository {

    public ArrayList<KichThuocResponse> getAll() {
        String sql = """
                     	SELECT 
                            Id
                            ,MaKichThuoc
                            ,TenKichThuoc
                            ,TrangThai
                        FROM KichThuoc
                     """;
        ArrayList<KichThuocResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                KichThuocResponse kt = new KichThuocResponse();
                kt.setId(rs.getInt(1));
                kt.setMaKichThuoc(rs.getString(2));
                kt.setTenKichThuoc(rs.getString(3));
                kt.setTrangThai(rs.getInt(4));
                list.add(kt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(KichThuocResponse kt) {
        int check = 0;
        String sql = """
                        INSERT INTO KichThuoc 
                            (TenKichThuoc, TrangThai)
                        VALUES 
                            (?, ?)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kt.getTenKichThuoc());
            ps.setInt(2, kt.getTrangThai());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KichThuocResponse kt) {
        int check = 0;
        String sql = """
                        UPDATE KichThuoc
                        SET 
                            TenKichThuoc = ?, 
                            TrangThai = ?
                        WHERE 
                            Id = ?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kt.getTenKichThuoc());
            ps.setInt(2, kt.getTrangThai());
            ps.setInt(3, kt.getId());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(int id) {
        int check = 0;
        String sql = """
                     DELETE FROM KichThuoc 
                     WHERE Id = ?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
    public ArrayList<KichThuocResponse> search(String keyword) {
        String sql = """
                     SELECT
                        kt.Id,
                        kt.MaKichThuoc,
                        kt.TenKichThuoc,
                        kt.TrangThai
                     FROM
                        KichThuoc kt
                     WHERE
                        kt.TenKichThuoc LIKE ? OR
                        kt.MaKichThuoc LIKE ? OR
                        CAST(kt.TrangThai AS NVARCHAR) LIKE ?
                     """;

        ArrayList<KichThuocResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            String statusSearch = keyword.trim().toLowerCase();
            
            if (statusSearch.contains("Còn hàng")) {
                statusSearch = "1";
            } else if (statusSearch.contains("Hết hàng")) {
                statusSearch = "0";
            }

            ps.setString(1, pattern);             
            ps.setString(2, pattern);               
            ps.setString(3, "%" + statusSearch + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KichThuocResponse kt = new KichThuocResponse();
                kt.setId(rs.getInt("Id"));
                kt.setMaKichThuoc(rs.getString("MaKichThuoc"));
                kt.setTenKichThuoc(rs.getString("TenKichThuoc"));
                kt.setTrangThai(rs.getInt("TrangThai"));
                list.add(kt);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm kích thước", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public int getIdByTen(String ten) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT Id FROM KichThuoc WHERE TenKichThuoc = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
