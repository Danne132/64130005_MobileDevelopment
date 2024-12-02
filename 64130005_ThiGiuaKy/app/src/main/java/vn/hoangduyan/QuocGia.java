package vn.hoangduyan;

public class QuocGia {
    String name, pop;
    int imageResId;

    public QuocGia(String name, String pop, int imageResId) {
        this.name = name;
        this.pop = pop;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
