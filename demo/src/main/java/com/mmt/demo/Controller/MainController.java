package com.mmt.demo.Controller;


import com.mmt.demo.Response.DefaultResponse;
import com.mmt.demo.Response.InsertionResponse;
import com.mmt.demo.Service.MapTableQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MapTableQueries mapTableQueries;

    @RequestMapping(path="/read", method = RequestMethod.GET)
    public DefaultResponse read(@RequestParam String key)
    {
        return mapTableQueries.readFromMapTable(key);
    }

    @RequestMapping(path="/withExpiry", method = RequestMethod.POST)
    public InsertionResponse insertWithExpiry(@RequestParam String key, @RequestParam String value, @RequestParam int expiry)
    {
        return mapTableQueries.putWithExpiry(key,value,expiry);
    }

    @RequestMapping(path="/withoutExpiry", method = RequestMethod.POST)
    public InsertionResponse insertWithoutExpiry(@RequestParam String key, @RequestParam String value)
    {
        return mapTableQueries.putWithoutExpiry(key,value);
    }


}
