package pl.android.buk.model.buks;

import com.google.gson.annotations.SerializedName;

public class BukDatum {
    @SerializedName("bookmaker_id")
    private Integer bookmakerId;
    @SerializedName("name")
    private String name;
    @SerializedName("img")
    private String img;

    public Integer getBookmakerId() {
        return bookmakerId;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }
}
