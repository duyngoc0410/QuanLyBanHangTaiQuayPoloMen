/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Builder;

@Builder
public class HoaDonChiTietRepose {

    private int id;
    private int idHoaDon;
    private String maHoaDon;
    private String maSanPhamChitiet;
    private int idSanPhamChiTiet;
    private String mauSac;
    private String kichThuoc;
    private String tenSanPham;
    private String chatLieu;
    private int soluong;
    private String moTa;
    private BigDecimal giaDaThanhToan;
    private BigDecimal thanhTien;
    private Date ngayThanhToan;
    private Boolean hinhThucThanhToan;
    private int trangThai;

    public HoaDonChiTietRepose() {
    }

    public HoaDonChiTietRepose(int id, int idHoaDon, String maHoaDon, String maSanPhamChitiet, int idSanPhamChiTiet, String mauSac, String kichThuoc, String tenSanPham, String chatLieu, int soluong, String moTa, BigDecimal giaDaThanhToan, BigDecimal thanhTien, Date ngayThanhToan, Boolean hinhThucThanhToan, int trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.maSanPhamChitiet = maSanPhamChitiet;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.tenSanPham = tenSanPham;
        this.chatLieu = chatLieu;
        this.soluong = soluong;
        this.moTa = moTa;
        this.giaDaThanhToan = giaDaThanhToan;
        this.thanhTien = thanhTien;
        this.ngayThanhToan = ngayThanhToan;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPhamChitiet() {
        return maSanPhamChitiet;
    }

    public void setMaSanPhamChitiet(String maSanPhamChitiet) {
        this.maSanPhamChitiet = maSanPhamChitiet;
    }

    public int getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(int idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getGiaDaThanhToan() {
        return giaDaThanhToan;
    }

    public void setGiaDaThanhToan(BigDecimal giaDaThanhToan) {
        this.giaDaThanhToan = giaDaThanhToan;
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

    public Boolean getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(Boolean hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
