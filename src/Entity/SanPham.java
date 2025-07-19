package Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
public class SanPham {

    private int id;
    private int idSanPhamCT;
    private int idThuongHieu;
    private String tenThuongHieu;
    private String maSanPham;
    private String tenSanPham;
    private String tenSanPhamCT;
    private int soLuong;
    private int trangThai;
    private int trangThaiCT;

    public SanPham() {
    }

    public SanPham(int id, int idSanPhamCT, int idThuongHieu, String tenThuongHieu, String maSanPham, String tenSanPham, String tenSanPhamCT, int soLuong, int trangThai, int trangThaiCT) {
        this.id = id;
        this.idSanPhamCT = idSanPhamCT;
        this.idThuongHieu = idThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenSanPhamCT = tenSanPhamCT;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.trangThaiCT = trangThaiCT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSanPhamCT() {
        return idSanPhamCT;
    }

    public void setIdSanPhamCT(int idSanPhamCT) {
        this.idSanPhamCT = idSanPhamCT;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenSanPhamCT() {
        return tenSanPhamCT;
    }

    public void setTenSanPhamCT(String tenSanPhamCT) {
        this.tenSanPhamCT = tenSanPhamCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThaiCT() {
        return trangThaiCT;
    }

    public void setTrangThaiCT(int trangThaiCT) {
        this.trangThaiCT = trangThaiCT;
    }

}
