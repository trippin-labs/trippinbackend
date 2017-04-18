//package com.trippin.entities;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//public class UsersProfilesPhotos implements HasId {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    String id;
//
//    @ManyToOne
//    @Column
//    String photoUrl;
//
//    @Override
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//}
