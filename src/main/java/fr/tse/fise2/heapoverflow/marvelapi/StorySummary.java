package fr.tse.fise2.heapoverflow.marvelapi;

public class StorySummary {
    private String resourceURI;

    private String name;

    private String type;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StorySummary [resourceURI = " + resourceURI + ", name = " + name + "]";
    }
}
