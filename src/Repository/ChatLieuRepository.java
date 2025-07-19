package Repository;

import JDBCUtil.DBConnect;
import Response.ChatLieuResponse;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

public class ChatLieuRepository {

    public List<ChatLieuResponse> getAll() {
        String sql = """
                     	SELECT 
                            Id
                            ,MaChatLieu
                            ,TenChatLieu
                            ,TrangThai
                        FROM ChatLieu
                     """;
        List<ChatLieuResponse> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                ChatLieuResponse cl = new ChatLieuResponse();
                cl.setId(rs.getInt(1));
                cl.setMaChatLieu(rs.getString(2));
                cl.setTenChatLieu(rs.getString(3));
                cl.setTrangThai(rs.getInt(4));
                list.add(cl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean add(ChatLieuResponse cl) {
        int check = 0;
        String sql = """
                        INSERT INTO ChatLieu 
                            (TenChatLieu, TrangThai)
                        VALUES 
                            (?, ?)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cl.getTenChatLieu());
            ps.setInt(2, cl.getTrangThai());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(ChatLieuResponse cl) {
        int check = 0;
        String sql = """
                        UPDATE ChatLieu
                        SET 
                            TenChatLieu = ?, 
                            TrangThai = ?
                        WHERE 
                            Id = ?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cl.getTenChatLieu());
            ps.setInt(2, cl.getTrangThai());
            ps.setInt(3, cl.getId());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(int id) {
        int check = 0;
        String sql = """
                     DELETE FROM ChatLieu 
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
    
    public ArrayList<ChatLieuResponse> search(String keyword) {
        String sql = """
                    SELECT 
                        Id
                        ,MaChatLieu
                        ,TenChatLieu
                        ,TrangThai
                    FROM ChatLieu
                    WHERE
                        TenChatLieu LIKE ? OR
                        MaChatLieu LIKE ? OR
                        CAST(TrangThai AS NVARCHAR) LIKE ?
                     """;

        ArrayList<ChatLieuResponse> list = new ArrayList<>();
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
                ChatLieuResponse cl = new ChatLieuResponse();
                cl.setId(rs.getInt("Id"));
                cl.setMaChatLieu(rs.getString("MaChatLieu"));
                cl.setTenChatLieu(rs.getString("TenChatLieu"));
                cl.setTrangThai(rs.getInt("TrangThai"));
                list.add(cl);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm kích thước", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    
   public int getIdByTen(String ten) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT Id FROM ChatLieu WHERE TenChatLieu = ?";
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
