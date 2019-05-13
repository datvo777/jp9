package e.datvo_000.jp9shop.Model.Object;

import java.util.List;

/**
 * Created by datvo_000 on 06/03/2019.
 */

public class KhachHang  {
    String name;

    List<SoDiaChi>sdcList;

    public List<SoDiaChi> getSdcList() {
        return sdcList;
    }

    public void setSdcList(List<SoDiaChi> sdcList) {
        this.sdcList = sdcList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String email,phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
