package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kendo on 5/2/2561.
 */

public class MapAlltemDao {
    @SerializedName("poi_id")
    @Expose
    private int poiId;
    @SerializedName("poi_name")
    @Expose
    private String poiName;
    @SerializedName("poi_description")
    @Expose
    private String poiDescription;
    @SerializedName("poi_cat")
    @Expose
    private int poiCat;
    @SerializedName("poi_type")
    @Expose
    private int poiType;
    @SerializedName("poi_lat")
    @Expose
    private String poiLat;
    @SerializedName("poi_lng")
    @Expose
    private String poiLng;
    @SerializedName("poi_phone")
    @Expose
    private String poiPhone;
    @SerializedName("poi_website")
    @Expose
    private String poiWebsite;
    @SerializedName("poi_opentime")
    @Expose
    private String poiOpentime;
    @SerializedName("poi_time")
    @Expose
    private String poiTime;
    @SerializedName("poi_closetime")
    @Expose
    private String poiClosetime;
    @SerializedName("poi_nextstop")
    @Expose
    private int poiNextstop;
    @SerializedName("poi_photo")
    @Expose
    private String poiPhoto;
    @SerializedName("poi_openday")
    @Expose
    private String poiOpenday;
    @SerializedName("poi_mobile")
    @Expose
    private String poiMobile;
    @SerializedName("poi_email")
    @Expose
    private String poiEmail;
    @SerializedName("poi_facebook")
    @Expose
    private String poiFacebook;
    @SerializedName("poi_line")
    @Expose
    private String poiLine;
    @SerializedName("poi_pin")
    @Expose
    private String poiPin;

    public int getPoiId() {
        return poiId;
    }

    public void setPoiId(int poiId) {
        this.poiId = poiId;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiDescription() {
        return poiDescription;
    }

    public void setPoiDescription(String poiDescription) {
        this.poiDescription = poiDescription;
    }

    public int getPoiCat() {
        return poiCat;
    }

    public void setPoiCat(int poiCat) {
        this.poiCat = poiCat;
    }

    public int getPoiType() {
        return poiType;
    }

    public void setPoiType(int poiType) {
        this.poiType = poiType;
    }

    public String getPoiLat() {
        return poiLat;
    }

    public void setPoiLat(String poiLat) {
        this.poiLat = poiLat;
    }

    public String getPoiLng() {
        return poiLng;
    }

    public void setPoiLng(String poiLng) {
        this.poiLng = poiLng;
    }

    public String getPoiPhone() {
        return poiPhone;
    }

    public void setPoiPhone(String poiPhone) {
        this.poiPhone = poiPhone;
    }

    public String getPoiWebsite() {
        return poiWebsite;
    }

    public void setPoiWebsite(String poiWebsite) {
        this.poiWebsite = poiWebsite;
    }

    public String getPoiOpentime() {
        return poiOpentime;
    }

    public void setPoiOpentime(String poiOpentime) {
        this.poiOpentime = poiOpentime;
    }

    public String getPoiClosetime() {
        return poiClosetime;
    }

    public void setPoiClosetime(String poiClosetime) {
        this.poiClosetime = poiClosetime;
    }

    public int getPoiNextstop() {
        return poiNextstop;
    }

    public void setPoiNextstop(int poiNextstop) {
        this.poiNextstop = poiNextstop;
    }

    public String getPoiPhoto() {
        return poiPhoto;
    }

    public void setPoiPhoto(String poiPhoto) {
        this.poiPhoto = poiPhoto;
    }

    public String getPoiOpenday() {
        return poiOpenday;
    }

    public void setPoiOpenday(String poiOpenday) {
        this.poiOpenday = poiOpenday;
    }

    public String getPoiMobile() {
        return poiMobile;
    }

    public void setPoiMobile(String poiMobile) {
        this.poiMobile = poiMobile;
    }

    public String getPoiEmail() {
        return poiEmail;
    }

    public void setPoiEmail(String poiEmail) {
        this.poiEmail = poiEmail;
    }

    public String getPoiFacebook() {
        return poiFacebook;
    }

    public void setPoiFacebook(String poiFacebook) {
        this.poiFacebook = poiFacebook;
    }

    public String getPoiLine() {
        return poiLine;
    }

    public void setPoiLine(String poiLine) {
        this.poiLine = poiLine;
    }

    public String getPoiPin() {
        return poiPin;
    }

    public void setPoiPin(String poiPin) {
        this.poiPin = poiPin;
    }

    public String getPoiTime() {
        return poiTime;
    }

    public void setPoiTime(String poiTime) {
        this.poiTime = poiTime;
    }
}
