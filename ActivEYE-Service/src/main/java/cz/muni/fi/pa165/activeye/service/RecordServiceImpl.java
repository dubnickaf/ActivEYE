package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.math.BigDecimal;
import java.time.DateTimeException;
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
        try {
            record.setBurnedCalories(record.getActivity().getCaloriesRatio().multiply(record.getHoursSpent()));

            if (record.getBurnedCalories().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Record cannot have negative value of burned calories");
            }
            recordDao.createRecord(record);
        }
        catch (IllegalArgumentException | PersistenceException e) {
            throw new ActiveyeDataAccessException("Error occured when creating record",e);
        }
        catch (NullPointerException e) {
            throw new ActiveyeDataAccessException("Record has no activity set",e);
        }
        catch (DateTimeException e) {
            throw new ActiveyeDataAccessException("Record for an Activity cannot end before it starts",e);
        }
    }

    @Override
    public void updateRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record null");
        }
        if(record.getId() == null) {
            throw new IllegalArgumentException("Record id cannot be null");
        }
        try {
            record.setBurnedCalories(record.getActivity().getCaloriesRatio().multiply(record.getHoursSpent()));

            if (record.getBurnedCalories().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Record cannot have negative value of burned calories");
            }
            recordDao.updateRecord(record);
        }
        catch(IllegalArgumentException | TransactionRequiredException e){
            throw new ActiveyeDataAccessException("Error occured when updating record",e);
        }
        catch (NullPointerException e) {
            throw new ActiveyeDataAccessException("Record has no activity set",e);
        }
        catch (DateTimeException e) {
            throw new ActiveyeDataAccessException("Record for an Activity cannot end before it starts",e);
        }
    }

    @Override
    public Record findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            return recordDao.getRecord(id);
        } catch (IllegalArgumentException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public void deleteRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record cannot be null");
        }
        try {
            recordDao.deleteRecord(record);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public List<Record> getAllRecords() {
        return recordDao.getAllRecords();
    }
}
