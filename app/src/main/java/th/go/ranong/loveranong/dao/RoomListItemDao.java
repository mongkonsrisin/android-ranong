package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lapp on 2/3/2018 AD.
 */

public class RoomListItemDao {
    @SerializedName("rm_id")
    @Expose
    private Integer rmId;
    @SerializedName("rm_poiid")
    @Expose
    private Integer rmPoiid;
    @SerializedName("rm_maxpeople")
    @Expose
    private Integer rmMaxpeople;
    @SerializedName("rm_price")
    @Expose
    private Integer rmPrice;
    @SerializedName("rm_size")
    @Expose
    private String rmSize;
    @SerializedName("rm_category")
    @Expose
    private Integer rmCategory;

    public Integer getRmId() {
        return rmId;
    }

    public void setRmId(Integer rmId) {
        this.rmId = rmId;
    }

    public Integer getRmPoiid() {
        return rmPoiid;
    }

    public void setRmPoiid(Integer rmPoiid) {
        this.rmPoiid = rmPoiid;
    }

    public Integer getRmMaxpeople() {
        return rmMaxpeople;
    }

    public void setRmMaxpeople(Integer rmMaxpeople) {
        this.rmMaxpeople = rmMaxpeople;
    }

    public Integer getRmPrice() {
        return rmPrice;
    }

    public void setRmPrice(Integer rmPrice) {
        this.rmPrice = rmPrice;
    }

    public String getRmSize() {
        return rmSize;
    }

    public void setRmSize(String rmSize) {
        this.rmSize = rmSize;
    }

    public Integer getRmCategory() {
        return rmCategory;
    }

    public void setRmCategory(Integer rmCategory) {
        this.rmCategory = rmCategory;
    }
}
