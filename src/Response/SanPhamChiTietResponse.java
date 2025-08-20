/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ChungPC
 */
public class SanPhamChiTietResponse {

    private int id;
    private int idSanPham;
    private int idMauSac;
    private int idKichThuoc;
    private int idChatLieu;
    private String maSanPham;
    private String maSanPhamCT;
    private String tenSanPham;
    private String tenMauSac;
    private String tenKichThuoc;
    private String tenChatLieu;
    private BigDecimal gia;
    private String moTa;
    private int soLuong;
    private Date ngayNhap;
    private int trangThai;

    public SanPhamChiTietResponse() {
    }

    public SanPhamChiTietResponse(int id, int idSanPham, int idMauSac, int idKichThuoc, int idChatLieu, String maSanPham, String maSanPhamCT, String tenSanPham, String tenMauSac, String tenKichThuoc, String tenChatLieu, BigDecimal gia, String moTa, int soLuong, Date ngayNhap, int trangThai) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.idMauSac = idMauSac;
        this.idKichThuoc = idKichThuoc;
        this.idChatLieu = idChatLieu;
        this.maSanPham = maSanPham;
        this.maSanPhamCT = maSanPhamCT;
        this.tenSanPham = tenSanPham;
        this.tenMauSac = tenMauSac;
        this.tenKichThuoc = tenKichThuoc;
        this.tenChatLieu = tenChatLieu;
        this.gia = gia;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(int idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaSanPhamCT() {
        return maSanPhamCT;
    }

    public void setMaSanPhamCT(String maSanPhamCT) {
        this.maSanPhamCT = maSanPhamCT;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


}
