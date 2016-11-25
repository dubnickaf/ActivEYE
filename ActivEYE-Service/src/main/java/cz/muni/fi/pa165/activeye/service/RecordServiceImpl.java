package cz.muni.fi.pa165.activeye.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Record;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by spriadka on 11/22/16.
 */
@Service
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
