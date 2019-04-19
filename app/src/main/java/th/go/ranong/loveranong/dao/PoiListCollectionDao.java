package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kendo on 5/2/2561.
 */

public class PoiListCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<PoiListItemDao> data = null;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<PoiListItemDao> getData() {
        return data;
    }

    public void setData(List<PoiListItemDao> data) {
        this.data = data;
    }
}
