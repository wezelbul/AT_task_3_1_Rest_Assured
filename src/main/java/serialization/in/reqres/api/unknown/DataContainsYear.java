package serialization.in.reqres.api.unknown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataContainsYear {

    private Integer year;

    public DataContainsYear() {
        super();
    }

    public DataContainsYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
