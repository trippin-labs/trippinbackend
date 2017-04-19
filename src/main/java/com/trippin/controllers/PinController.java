package com.trippin.controllers;

import com.amazonaws.services.s3.AmazonS3Client;
import com.trippin.serializers.RootSerializer;
import com.trippin.serializers.TripSerializer;
import com.trippin.services.PinRepository;
import com.trippin.services.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PinController {

    @Autowired
    PinRepository pins;

    @Autowired
    TripRepository trips;

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    @Autowired
    AmazonS3Client s3;

    RootSerializer rootSerializer;
    TripSerializer tripSerializer;




}
