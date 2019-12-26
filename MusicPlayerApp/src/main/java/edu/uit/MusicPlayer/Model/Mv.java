package edu.uit.MusicPlayer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mv implements Serializable {

@SerializedName("IdMv")
@Expose
private String idMv;
@SerializedName("TenMv")
@Expose
private String tenMv;
@SerializedName("HinhMv")
@Expose
private String hinhMv;
@SerializedName("LinkMv")
@Expose
private String linkMv;

public String getIdMv() {
return idMv;
}

public void setIdMv(String idMv) {
this.idMv = idMv;
}

public String getTenMv() {
return tenMv;
}

public void setTenMv(String tenMv) {
this.tenMv = tenMv;
}

public String getHinhMv() {
return hinhMv;
}

public void setHinhMv(String hinhMv) {
this.hinhMv = hinhMv;
}

public String getLinkMv() {
return linkMv;
}

public void setLinkMv(String linkMv) {
this.linkMv = linkMv;
}

}