package Response;

import lombok.Builder;

@Builder
public class KichThuocResponse {

    private int id;
    private String maKichThuoc;
    private String tenKichThuoc;
    private int trangThai;

    public KichThuocResponse() {
    }

    public KichThuocResponse(int id, String maKichThuoc, String tenKichThuoc, int trangThai) {
        this.id = id;
        this.maKichThuoc = maKichThuoc;
        this.tenKichThuoc = tenKichThuoc;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
