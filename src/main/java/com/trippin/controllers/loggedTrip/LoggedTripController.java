package com.trippin.controllers.loggedTrip;


import com.trippin.entities.LoggedTrip;
import com.trippin.entities.User;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.LoggedTripSerializer;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.LoggedTripRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@RestController
public class LoggedTripController {

    @Autowired
    LoggedTripRepository loggedtrips;

    RootSerializer rootSerializer;
    LoggedTripSerializer LoggedTripSerializer;

    public LoggedTripController() {
        rootSerializer = new RootSerializer();
        LoggedTripSerializer = new LoggedTripSerializer();
    }

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (loggedtrips.count() == 0) {
            LoggedTrip loggedTrip = new LoggedTrip();
            loggedTrip.setTripName("Harold's hairy adventure.");
            loggedTrip.setLocation("Bermuda");
            loggedTrip.setDate("02-03-2016");
            loggedtrips.save(loggedTrip);
        }
    }

    //log a trip
    @RequestMapping(path = "/loggedtrips", method = RequestMethod.POST)
    public HashMap<String, Object> createLoggedTrip(@RequestBody RootParser<LoggedTrip> loggedTrip) throws Exception {
        LoggedTrip loggedTrip1 = loggedTrip.getData().getEntity();
        try {
            loggedtrips.save(loggedTrip1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootSerializer.serializeOne(
                "/loggedtrips/" + loggedTrip1.getId(),
                loggedTrip1,
                LoggedTripSerializer);
    }

}
