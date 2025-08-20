package Repository;

import Entity.ThuongHieu;
import JDBCUtil.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class ThuongHieuRepository {
    public ArrayList<ThuongHieu> getAll() {
        String sql = "SELECT Id, MaThuongHieu, TenThuongHieu, TrangThai FROM ThuongHieu";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(ThuongHieu.builder()
                        .idThuongHieu(rs.getInt("Id"))
                        .maThuongHieu(rs.getString("MaThuongHieu"))
                        .tenThuongHieu(rs.getString("TenThuongHieu"))
                        .trangThai(rs.getInt("TrangThai"))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
