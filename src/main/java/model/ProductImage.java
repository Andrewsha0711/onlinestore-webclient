package model;

import java.io.Serializable;
import java.util.UUID;

public class ProductImage implements Serializable {
    private static final long serialVersionUID = -681415467006331721L;
    private UUID id;
    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
