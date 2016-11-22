package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Record;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by spriadka on 11/22/16.
 */
public class RecordServiceImpl implements RecordService {


    @Inject
    private RecordDao recordDao;

    @Override
    public void createRecord(Record record) {
        recordDao.createRecord(record);
    }

    @Override
    public void updateRecord(Record record) {
        recordDao.updateRecord(record);
    }

    @Override
    public Record findById(Long id) {
        return recordDao.getRecord(id);
    }

    @Override
    public void deleteRecord(Record record) {
        recordDao.deleteRecord(record);
    }

    @Override
    public List<Record> getAllRecords(){
        return recordDao.getAllRecords();
    }
}
