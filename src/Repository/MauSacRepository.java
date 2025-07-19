package Repository;

import JDBCUtil.DBConnect;
import Response.ChatLieuResponse;
import Response.MauSacResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MauSacRepository {

    public List<MauSacResponse> getAll() {
        String sql = """
                     	SELECT 
                            Id
                            ,MaMauSac
                            ,TenMauSac
                            ,TrangThai
                        FROM MauSac
                     """;
        List<MauSacResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                MauSacResponse ms = new MauSacResponse();
                ms.setId(rs.getInt(1));
                ms.setMaMauSac(rs.getString(2));
                ms.setTenMauSac(rs.getString(3));
                ms.setTrangThai(rs.getInt(4));
                list.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(MauSacResponse ms) {
        int check = 0;
        String sql = """
                        INSERT INTO MauSac 
                            (TenMauSac, TrangThai)
                        VALUES 
                            (?, ?)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ms.getTenMauSac());
            ps.setInt(2, ms.getTrangThai());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(MauSacResponse ms) {
        int check = 0;
        String sql = """
                        UPDATE MauSac
                        SET 
                            TenMauSac = ?, 
                            TrangThai = ?
                        WHERE 
                            Id = ?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ms.getTenMauSac());
            ps.setInt(2, ms.getTrangThai());
            ps.setInt(3, ms.getId());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(int id) {
        int check = 0;
        String sql = """
                     DELETE FROM MauSac 
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

    public ArrayList<MauSacResponse> search(String keyword) {
        String sql = """
                    SELECT 
                        Id
                        ,MaMauSac
                        ,TenMauSac
                        ,TrangThai
                    FROM MauSac
                    WHERE
                        TenMauSac LIKE ? OR
                        MaMauSac LIKE ? OR
                        CAST(TrangThai AS NVARCHAR) LIKE ?
                     """;

        ArrayList<MauSacResponse> list = new ArrayList<>();
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
                MauSacResponse ms = new MauSacResponse();
                ms.setId(rs.getInt("Id"));
                ms.setMaMauSac(rs.getString("MaMauSac"));
                ms.setTenMauSac(rs.getString("TenMauSac"));
                ms.setTrangThai(rs.getInt("TrangThai"));
                list.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm kích thước", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public int getIdByTen(String ten) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT Id FROM MauSac WHERE TenMauSac = ?";
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
