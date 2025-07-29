/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;

@Builder
public class HoaDon {

    private int id;
    private int idKhachHang;
    private int idNhanVien;
    private int idVoucher;
    private String maHoaDon;
    private BigDecimal tienDua;
    private BigDecimal tienThua;
    private BigDecimal ThanhTien;
    private Date ngayThanhToan;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(int id, int idKhachHang, int idNhanVien, int idVoucher, String maHoaDon, BigDecimal tienDua, BigDecimal tienThua, BigDecimal ThanhTien, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maHoaDon = maHoaDon;
        this.tienDua = tienDua;
        this.tienThua = tienThua;
        this.ThanhTien = ThanhTien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public BigDecimal getTienDua() {
        return tienDua;
    }

    public void setTienDua(BigDecimal tienDua) {
        this.tienDua = tienDua;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public BigDecimal getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(BigDecimal ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
