/* package com.example.appfrontend.database;

public class PeriodEntity {
} */
package com.example.appfrontend.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "periods")
public class PeriodEntity {

    @PrimaryKey
    private int id;

    private String name;
    private String startDate;
    private String endDate;

    // ✅ getter e setter
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

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

