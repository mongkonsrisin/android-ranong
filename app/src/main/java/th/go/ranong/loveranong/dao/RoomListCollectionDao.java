package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lapp on 2/3/2018 AD.
 */

public class RoomListCollectionDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<RoomListItemDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<RoomListItemDao> getData() {
        return data;
    }

    public void setData(List<RoomListItemDao> data) {
        this.data = data;
    }
}