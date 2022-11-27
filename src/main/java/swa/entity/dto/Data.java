package swa.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<DataObject> data;

    public Data() {
    }

    public Data(DataObject data) {
        this.data = new ArrayList<>();
        this.data.add(data);
    }

    public Data(List<DataObject> data) {
        this.data = data;
    }

    public List<DataObject> getData() {
        return data;
    }

}
