package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.entities.Record;

import java.util.List;

/**
 * Created by spriadka on 11/22/16.
 * @author spriadka
 */
public interface RecordService {
    /**
     * Creates Record
     * @param record to be created
     */
    void createRecord(Record record);

    /**
     * Updates Record
     * @param record to be updated
     */
    void updateRecord(Record record);

    /**
     * Gets Record with given ID
     * @param id ID of Record to be fetched
     * @return Record with given ID
     */
    Record findById(Long id);

    /**
     * Deletes Record
     * @param record Record to be deleted
     */
    void deleteRecord(Record record);

    /**
     * Fetches all records
     * @return list of all Records
     */
    List<Record> getAllRecords();
}
