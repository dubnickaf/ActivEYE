package cz.muni.fi.pa165.activeye.dao;

import cz.muni.fi.pa165.activeye.entities.Record;

import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka [422600]
 */
public interface RecordDao {

    /**
     * Gets all Record entities from DB
     * @return List of all Record entity
     */
    List<Record> getAllRecords();

    /**
     * Creates Record entity in DB
     * @param record
     */
    void createRecord(Record record);

    /**
     * Fetches Record with given ID from DB
     * @param id ID of Record entity to be fetched
     * @return Record entity to be fetched
     */
    Record getRecord(Long id);

    /**
     * Deletes record entity from DB
     * @param record Record entity to be deleted
     */
    void deleteRecord(Record record);

    /**
     * Updates Record entity in DB
     * @param record Record entity to be updated
     */
    void updateRecord(Record record);

}
