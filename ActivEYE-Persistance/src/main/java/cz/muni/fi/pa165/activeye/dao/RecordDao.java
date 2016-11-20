package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.Record;

import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka [422600]
 */
public interface RecordDao {

    public List<Record> getAllRecords();

    public void createRecord(Record record);

    public Record getRecord(Long id);

    public void deleteRecord(Record record);

    public void updateRecord(Record record);

}
