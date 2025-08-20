/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author nguyensyan
 */
public class HoaDonRepose {

    private Integer id;
    private String idHoaDon;
    private String maHoaDon;
    private int idKhachHang;
    private int idNhanVien;
    private int idVoucher;
    private String maKhachHang;
    private String tenKhachHang;
    private String maNhanVien;
    private String TenNhanVien;
    private String tenVoucher;
    private String soDienThoai;
    private BigDecimal tienDua;
    private BigDecimal thanhTien;
    private Date ngayThanhToan;
    private int trangThai;

    public HoaDonRepose() {
    }

    public HoaDonRepose(Integer id, String idHoaDon, String maHoaDon, int idKhachHang, int idNhanVien, int idVoucher, String maKhachHang, String tenKhachHang, String maNhanVien, String TenNhanVien, String tenVoucher, String soDienThoai, BigDecimal tienDua, BigDecimal thanhTien, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.maNhanVien = maNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.tenVoucher = tenVoucher;
        this.soDienThoai = soDienThoai;
        this.tienDua = tienDua;
        this.thanhTien = thanhTien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
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

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public BigDecimal getTienDua() {
        return tienDua;
    }

    public void setTienDua(BigDecimal tienDua) {
        this.tienDua = tienDua;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
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
