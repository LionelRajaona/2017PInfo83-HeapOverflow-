package fr.tse.fise2.heapoverflow.marvelapi;

public class EventSummary {
    private String resourceURI;

    private String name;

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

    @Override
    public String toString() {
        return "EventSummary [resourceURI = " + resourceURI + ", name = " + name + "]";
    }
}
