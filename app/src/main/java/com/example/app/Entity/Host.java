package com.example.app.Entity;

public class Host {
    private String hostId;
    private String hostName;
    private String address;
    private String dob;
    private String emai;
    private int phone;
    private int citizenID;
    private String image;
    public Host(){;}
    public Host(String hostId, String hostName, String address, String dob, String emai, int phone, int citizenID, String image) {
        this.hostId = hostId;
        this.hostName = hostName;
        this.address = address;
        this.dob = dob;
        this.emai = emai;
        this.phone = phone;
        this.citizenID = citizenID;
        this.image = image;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostId='" + hostId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", emai='" + emai + '\'' +
                ", phone=" + phone +
                ", citizenID=" + citizenID +
                ", image='" + image + '\'' +
                '}';
    }
}
