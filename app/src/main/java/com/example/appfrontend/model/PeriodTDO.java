/* package com.example.appfrontend.model;

public class PeriodTDO {
} */

package com.example.appfrontend.model;

public class PeriodTDO {
    private int id_period;
    private String language;
    private String name_period;
    private int priority;
    private String enable;

    // Getters e Setters


    public int getId_period() {
        return id_period;
    }

    public void setId_period(int id_period) {
        this.id_period = id_period;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName_period() {
        return name_period;
    }

    public void setName_period(String name_period) {
        this.name_period = name_period;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}

