package model;

public class CategoryUser {

    private int id;
    private String name;

    public CategoryUser() {
    }

    public CategoryUser(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getIdCategory() {
        return this.id;
    }

    public void setIdCategory(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.name;
    }

    public void setCategoryName(String name) {
        this.name = name;
    }

}
