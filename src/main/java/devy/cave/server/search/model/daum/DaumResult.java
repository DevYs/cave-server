package devy.cave.server.search.model.daum;

import java.util.List;

public class DaumResult {

    private int count;
    private List<Data> data;
    private Page page;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DaumResult{" +
                "count=" + count +
                ", data=" + data +
                ", page=" + page +
                '}';
    }
}
