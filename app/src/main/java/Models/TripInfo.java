package Models;

import java.io.Serializable;

public class TripInfo implements Serializable
{
    private String UID;
    private String fromlocation;
    private String tolocation;
    private String startdate;
    private String enddate;
    private String guiderID;
    private String tripstatus;
    private String payment_details;
    private String guiderImage;
    private String userImage;
    private String notification_status;

    public TripInfo(String ID, String fromlocation,
                    String tolocation, String startlocation, String endlocation) {
        this.UID = ID;
        this.fromlocation = fromlocation;
        this.tolocation = tolocation;
        this.startdate = startlocation;
        this.enddate = endlocation;
    }

    public TripInfo(String UID_, String fromlocation, String tolocation, String startdate,
                    String enddate, String guiderID, String tripstatus, String payment/*,
                    String GuiderImage, String UserImage*/, String notification) {
        this.UID = UID_;
        this.fromlocation = fromlocation;
        this.tolocation = tolocation;
        this.startdate = startdate;
        this.enddate = enddate;
        this.guiderID = guiderID;
        this.tripstatus = tripstatus;
        this.payment_details = payment;
        //this.guiderImage = GuiderImage;
        //this.userImage = UserImage;
        this.notification_status = notification;
    }

    @Override
    public String toString() {
        return "TripInfo{" +
                "UID='" + UID + '\'' +
                ", fromlocation='" + fromlocation + '\'' +
                ", tolocation='" + tolocation + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", guiderID='" + guiderID + '\'' +
                ", tripstatus='" + tripstatus + '\'' +
                ", paymentdetails='" + payment_details + '\'' +
               // ", guiderImage='" + guiderImage + '\'' +
                //", userImage='" + userImage + '\'' +
                ", notification_status='" + notification_status + '\'' +
                '}';
    }

    public String getNotification_status() { return notification_status; }

    public void setNotification_status(String notification_status) { this.notification_status = notification_status; }

    public String getGuiderImage() { return guiderImage; }

    public void setGuiderImage(String guiderImage) { this.guiderImage = guiderImage; }

    public String getUserImage() { return userImage; }

    public void setUserImage(String userImage) { this.userImage = userImage; }

    public String getPayment_details() { return payment_details; }

    public void setPayment_details(String payment_details) { this.payment_details = payment_details; }

    public String getGuiderID() {
        return guiderID;
    }

    public void setGuiderID(String guiderID) {
        this.guiderID = guiderID;
    }

    public String getTripstatus() {
        return tripstatus;
    }

    public void setTripstatus(String tripstatus) {
        this.tripstatus = tripstatus;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID_) {
        this.UID = UID_;
    }

    public String getFromlocation() {
        return fromlocation;
    }

    public void setFromlocation(String fromlocation) {
        this.fromlocation = fromlocation;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startlocation) {
        this.startdate = startlocation;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String endlocation) {
        this.enddate = endlocation;
    }
}
