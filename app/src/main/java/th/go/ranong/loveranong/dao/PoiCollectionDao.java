package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kendo on 5/2/2561.
 */

public class PoiCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private PoiListItemDao data = null;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PoiListItemDao getData() {
        return data;
    }

    public void setData(PoiListItemDao data) {
        this.data = data;
    }
}
