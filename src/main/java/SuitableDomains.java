public enum SuitableDomains {

    REDDIT("i.redd.it"),
    FLICKR("c1.staticflickr.com"),
    IMGUR("imgur.com");

    private final String domain;

    private SuitableDomains(final String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return domain;
    }

}
