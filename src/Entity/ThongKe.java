/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author OS
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongKe {
     private String tenSanPham;
     private double gia;
     private int soLuong;
     private double thanhTien;
     private int trangThai; // 0 = Không hoạt động, 1 = Hoạt động
     private Date ngayThanhToan;
}
