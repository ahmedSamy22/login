package com.example.login;

class General {
    String id, material,university,level,term,dr;



    General(){}

    public General(String id, String material, String university, String level, String term,String dr) {
        this.id = id;
        this.material = material;
        this.university = university;
        this.level = level;
        this.term = term;
        this.dr = dr;

    }

    public String getId() {
        return id;
    }

    public String getMaterial() {
        return material;
    }

    public String getUniversity() {
        return university;
    }

    public String getLevel() {
        return level;
    }

    public String getTerm() {
        return term;
    }

    public String getDr() {
        return dr;
    }
}
