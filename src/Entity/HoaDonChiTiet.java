/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.math.BigDecimal;

/**
 *
 * @author nguyensyan
 */
public class HoaDonChiTiet {

    private int id;
    private int idHoaDon;
    private int idSanPhamChiTiet;
    private int soLuong;
    private BigDecimal giaDaThanhToan;
    private BigDecimal thanhTien;
    private Boolean hinhThucThanhToan;
    private int trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, int idHoaDon, int idSanPhamChiTiet, int soLuong, BigDecimal giaDaThanhToan, BigDecimal thanhTien, Boolean hinhThucThanhToan, int trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.soLuong = soLuong;
        this.giaDaThanhToan = giaDaThanhToan;
        this.thanhTien = thanhTien;
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

    public int getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(int idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
