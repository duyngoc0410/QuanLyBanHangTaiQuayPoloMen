package Entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamChiTiet {

    private int id;
    private int idSanPham;
    private int idMauSac;
    private int idKichThuoc;
    private int idChatLieu;
    private String maSanPham;
    private String tenSanPham;
    private BigDecimal gia;
    private String moTa;
    private int soLuong;
    private Date ngayNhap;
    private int trangThai;
}
