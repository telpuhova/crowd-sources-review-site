package models;

public class Comic {
    private int id;
    private String name;
    private String dateOfBirth;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comic comic = (Comic) o;

        if (!name.equals(comic.name)) return false;
        return dateOfBirth != null ? dateOfBirth.equals(comic.dateOfBirth) : comic.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
