package fr.tse.fise2.heapoverflow.marvelapi;

import java.util.Arrays;

public class Comic {

    private int id;

    private int digitalId;

    private String title;

    private Double issueNumber;

    private String variantDescription;

    private String description;

    private String modified;

    private String isbn;

    private String upc;

    private String diamondCode;

    private String ean;

    private String issn;

    private String format;

    private int pageCount;

    private TextObject[] textObjects;

    private String ressourceURI;

    private Url[] urls;

    private SeriesSummary series;

    private ComicSummary[] variants;

    private ComicSummary[] collections;

    private ComicSummary[] collectedIssues;

    private ComicDate[] dates;

    private ComicPrice[] prices;

    private Image thumbnail;

    private Image[] images;

    private CreatorList creators;

    private CharacterList characters;

    private StoryList stories;

    private EventList events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(int digitalId) {
        this.digitalId = digitalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public TextObject[] getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(TextObject[] textObjects) {
        this.textObjects = textObjects;
    }

    public String getRessourceURI() {
        return ressourceURI;
    }

    public void setRessourceURI(String ressourceURI) {
        this.ressourceURI = ressourceURI;
    }

    public Url[] getUrls() {
        return urls;
    }

    public void setUrls(Url[] urls) {
        this.urls = urls;
    }

    public SeriesSummary getSeries() {
        return series;
    }

    public void setSeries(SeriesSummary series) {
        this.series = series;
    }

    public ComicSummary[] getVariants() {
        return variants;
    }

    public void setVariants(ComicSummary[] variants) {
        this.variants = variants;
    }

    public ComicSummary[] getCollections() {
        return collections;
    }

    public void setCollections(ComicSummary[] collections) {
        this.collections = collections;
    }

    public ComicSummary[] getCollectedIssues() {
        return collectedIssues;
    }

    public void setCollectedIssues(ComicSummary[] collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    public ComicDate[] getDates() {
        return dates;
    }

    public void setDates(ComicDate[] dates) {
        this.dates = dates;
    }

    public ComicPrice[] getPrices() {
        return prices;
    }

    public void setPrices(ComicPrice[] prices) {
        this.prices = prices;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public CreatorList getCreators() {
        return creators;
    }

    public void setCreators(CreatorList creators) {
        this.creators = creators;
    }

    public CharacterList getCharacters() {
        return characters;
    }

    public void setCharacters(CharacterList characters) {
        this.characters = characters;
    }

    public StoryList getStories() {
        return stories;
    }

    public void setStories(StoryList stories) {
        this.stories = stories;
    }

    public EventList getEvents() {
        return events;
    }

    public void setEvents(EventList events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", digitalId=" + digitalId +
                ", title='" + title + '\'' +
                ", issueNumber=" + issueNumber +
                ", variantDescription='" + variantDescription + '\'' +
                ", description='" + description + '\'' +
                ", modified=" + modified +
                ", isbn='" + isbn + '\'' +
                ", upc='" + upc + '\'' +
                ", diamondCode='" + diamondCode + '\'' +
                ", ean='" + ean + '\'' +
                ", issn='" + issn + '\'' +
                ", format='" + format + '\'' +
                ", pageCount=" + pageCount +
                ", textObjects=" + Arrays.toString(textObjects) +
                ", ressourceURI='" + ressourceURI + '\'' +
                ", urls=" + Arrays.toString(urls) +
                ", series=" + series +
                ", variants=" + Arrays.toString(variants) +
                ", collections=" + Arrays.toString(collections) +
                ", collectedIssues=" + Arrays.toString(collectedIssues) +
                ", dates=" + Arrays.toString(dates) +
                ", prices=" + Arrays.toString(prices) +
                ", thumbnail=" + thumbnail +
                ", images=" + Arrays.toString(images) +
                ", creators=" + creators +
                ", characters=" + characters +
                ", stories=" + stories +
                ", events=" + events +
                '}';
    }
}
