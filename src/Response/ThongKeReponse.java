/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import java.util.Date;

/**
 *
 * @author OS
 */
public class ThongKeReponse {

    private String tenSanPham;
    private String maSanPhamChiTiet;
    private double gia;
    private int soLuong;
    private double thanhTien;
    private int trangThai;
    private Date NgayThanhToan;

    public ThongKeReponse() {
    }

    public ThongKeReponse(String tenSanPham, String maSanPhamChiTiet, double gia, int soLuong, double thanhTien, int trangThai, Date NgayThanhToan) {
        this.tenSanPham = tenSanPham;
        this.maSanPhamChiTiet = maSanPhamChiTiet;
        this.gia = gia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPhamChiTiet() {
        return maSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(String maSanPhamChiTiet) {
        this.maSanPhamChiTiet = maSanPhamChiTiet;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

}
