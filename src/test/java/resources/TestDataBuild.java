package resources;

import POJO.AddPlace;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("123456789");
        place.setWebsite("https://rahulshettyacademy.com/");
        place.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shor park");
        myList.add("some test text");
        place.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);
        return place;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}
