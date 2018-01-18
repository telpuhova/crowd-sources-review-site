package models;

import java.util.List;

public class Comic {
    private int id;
    private String name;
    private List<Special> specials;

    public Comic(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comic comic = (Comic) o;

        if (!name.equals(comic.name)) return false;
        return specials != null ? specials.equals(comic.specials) : comic.specials == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (specials != null ? specials.hashCode() : 0);
        return result;
    }
}
