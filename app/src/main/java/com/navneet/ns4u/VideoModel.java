package com.navneet.ns4u;

import java.io.Serializable;

public class VideoModel implements Serializable {
    String name;
    String photourl;
    String videourl;

    public VideoModel() {
    }

    public VideoModel(String name, String photourl, String videourl) {
        this.name = name;
        this.photourl = photourl;
        this.videourl = videourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
