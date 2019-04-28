package com.mmt.demo.Service;

import com.mmt.demo.Entity.MapTable;
import com.mmt.demo.Repository.MapTableRepository;
import com.mmt.demo.Response.DefaultResponse;
import com.mmt.demo.Response.InsertionResponse;
import com.mmt.demo.Utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Component
public class MapTableQueries {

    @Autowired
    MapTableRepository mapTableRepository;

    public DefaultResponse readFromMapTable(String key)
    {
        DefaultResponse defaultResponse = new DefaultResponse();

        Optional<MapTable> mapTable = mapTableRepository.findById(key);
        if(mapTable.isPresent())
        {
            MapTable mapTable1 = mapTable.get();
            int expiry = mapTable1.getExpiryTime();
            if(expiry == 0)
            {
                defaultResponse.setStatus(Constants.SUCCESS_CODE);
                defaultResponse.setGender(mapTable1.getGender());
            }
            else
            {
                Timestamp current = new Timestamp(new Date().getTime());
                Timestamp createdOn = mapTable1.getCreatedOn();
                int expirySec = mapTable1.getExpiryTime();

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(createdOn.getTime());
                cal.add(Calendar.SECOND, expirySec);
                Timestamp expiredOn = new Timestamp(cal.getTime().getTime());

                if(current.before(expiredOn))
                {
                    defaultResponse.setStatus(Constants.SUCCESS_CODE);
                    defaultResponse.setGender(mapTable1.getGender());
                }
                else
                {
                    defaultResponse.setStatus(Constants.EXPIRED);
                    defaultResponse.setGender(null);
                }
            }
        }
        else
        {
            defaultResponse.setStatus(Constants.SUCCESS_CODE);
            defaultResponse.setGender(null);
        }

        return defaultResponse;
    }

    public InsertionResponse putWithExpiry(String key,String value,int expiry)
    {
        InsertionResponse insertionResponse = new InsertionResponse();

        Optional<MapTable> mapTable = mapTableRepository.findById(key);
        if(mapTable.isPresent()) {
            MapTable mapTable2 = mapTable.get();
            mapTableRepository.delete(mapTable2);

        }

        MapTable mapTable1 = new MapTable();
        mapTable1.setName(key);
        mapTable1.setGender(value);
        mapTable1.setExpiryTime(expiry);
        mapTable1.setCreatedOn(new Timestamp(new Date().getTime()));

        mapTableRepository.save(mapTable1);

        insertionResponse.setStatus(Constants.SUCCESS_CODE);
        insertionResponse.setMessage(Constants.SUCCESS_MSG);

        return insertionResponse;

    }

    public InsertionResponse putWithoutExpiry(String key,String value)
    {
        InsertionResponse insertionResponse = new InsertionResponse();

        Optional<MapTable> mapTable = mapTableRepository.findById(key);
        if(mapTable.isPresent()) {
            MapTable mapTable2 = mapTable.get();
            mapTableRepository.delete(mapTable2);

        }

        MapTable mapTable1 = new MapTable();
        mapTable1.setName(key);
        mapTable1.setGender(value);
        mapTable1.setExpiryTime(0);
        mapTable1.setCreatedOn(new Timestamp(new Date().getTime()));

        mapTableRepository.save(mapTable1);

        insertionResponse.setStatus(Constants.SUCCESS_CODE);
        insertionResponse.setMessage(Constants.SUCCESS_MSG);

        return insertionResponse;

    }
}
