package bish.flickrdemo.model;

/**
 * Created by BB045296 on 5/22/2016.
 */
public class ImageInfo {

    private String url;
    private String title;

    public ImageInfo(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*public ImageInfo(String farm, String id, String owner, String secret, String server, String title,String url) {
        this.farm = farm;
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.title = title;
        this.url = url;
    }

    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String url;
    private String title;

    public String getUrl(){  return url; }

    public void setUrl(String url){ this.url = url; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }



    @Override
    public String toString() {
        return "ImageInfo{" +
                "farm='" + farm + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", secret='" + secret + '\'' +
                ", server='" + server + '\'' +
                ", title='" + title + '\'' +
                '}';
    }*/
}
