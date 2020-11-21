package com.TheAllen.Auth.Service.models;


import lombok.Builder;
import org.apache.tomcat.jni.Address;

import java.util.Date;
import java.util.Set;

@Builder
public class Profile {

    private String username;
    private String profilePictureUrl;
    private Date birthday;
    private Set<Address> addresses;

    public Profile() {}

    public Profile(String username, String profilePictureUrl, Date birthday, Set<Address> addresses) {
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.birthday = birthday;
        this.addresses = addresses;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
