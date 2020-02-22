package com.example.pokusaj3.Model;

public class Food {

    private String Name, Image,Description,Calories,MenuId;

    public Food() {

    }

    public Food(String name, String image, String description, String calories, String menuId) {
        Name = name;
        Image = image;
        Description = description;
        Calories = calories;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
