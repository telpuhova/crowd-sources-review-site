package models;

public class Special {
    private int id;
    private String name;
    private int year;
    private int comicId;
    private String country;
    private String language;
    private String description;

//    public Special(String name, int comicId) {
//        this.name = name;
//        this.comicId = comicId;
//    }

    public Special(String name) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Special)) return false;

        Special special = (Special) o;

        if (getYear() != special.getYear()) return false;
        if (getComicId() != special.getComicId()) return false;
        if (!getName().equals(special.getName())) return false;
        if (getCountry() != null ? !getCountry().equals(special.getCountry()) : special.getCountry() != null)
            return false;
        if (getLanguage() != null ? !getLanguage().equals(special.getLanguage()) : special.getLanguage() != null)
            return false;
        return getDescription() != null ? getDescription().equals(special.getDescription()) : special.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getYear();
        result = 31 * result + getComicId();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getLanguage() != null ? getLanguage().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
