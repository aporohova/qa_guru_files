package modal;

import java.util.List;

public class Dog {
    public String name;
    public String fullName;
    public Integer age;
    public String breed;
    public List<String> activities;
    public List<String> food;
    public Boolean isFriendly;
    public City city;

    public static class City {
        public String current;
        public String origin;

    }
}
