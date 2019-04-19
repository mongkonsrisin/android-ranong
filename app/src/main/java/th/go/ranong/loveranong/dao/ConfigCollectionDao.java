package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kendo on 3/8/2018.
 */

public class ConfigCollectionDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private ConfigDao data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ConfigDao getData() {
        return data;
    }

    public void setData(ConfigDao data) {
        this.data = data;
    }
}
