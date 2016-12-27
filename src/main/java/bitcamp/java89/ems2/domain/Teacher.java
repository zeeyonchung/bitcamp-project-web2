package bitcamp.java89.ems2.domain;

import java.util.List;

public class Teacher extends Member {
  private static final long serialVersionUID = 1L;
  
  protected String homepage;
  protected String facabook;
  protected String twitter;
  protected List<Photo> photoList;
  
  
  
  public String getHomepage() {
    return homepage;
  }
  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }
  public String getFacabook() {
    return facabook;
  }
  public void setFacabook(String facabook) {
    this.facabook = facabook;
  }
  public String getTwitter() {
    return twitter;
  }
  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }
  public List<Photo> getPhotoList() {
    return photoList;
  }
  public void setPhotoList(List<Photo> photoList) {
    this.photoList = photoList;
  }
  
  
}
