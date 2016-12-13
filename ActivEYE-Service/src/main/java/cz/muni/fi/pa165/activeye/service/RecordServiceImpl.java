package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by spriadka on 11/22/16.
 */

/**
 * @author spriadka
 */
@Service
public class RecordServiceImpl implements RecordService {


    @Inject
    private RecordDao recordDao;

    @Override
    public void createRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record null");
        }
        record.setBurnedCalories(record.getActivity().getCaloriesRatio().multiply(record.getHoursSpent()));
        try {
            recordDao.createRecord(record);
        }
        catch (Exception e){
            throw new ActiveyeDataAccessException("Error occured when creating record",e);
        }
    }

    @Override
    public void updateRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record null");
        }
        record.setBurnedCalories(record.getActivity().getCaloriesRatio().multiply(record.getHoursSpent()));
        try {
            recordDao.updateRecord(record);
        }
        catch(Exception e){
            throw new ActiveyeDataAccessException("Error occured when updating record",e);
        }
    }

    @Override
    public Record findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        return recordDao.getRecord(id);
    }

    @Override
    public void deleteRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record cannot be null");
        }
        recordDao.deleteRecord(record);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordDao.getAllRecords();
    }
}
