package cz.muni.fi.pa165.activeye.service;

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
     * @throws IllegalArgumentException if record is null
     */
    void createRecord(Record record);

    /**
     * Updates Record
     * @param record to be updated
     * @throws IllegalArgumentException if record is null
     */
    void updateRecord(Record record);

    /**
     * Gets Record with given ID
     * @param id ID of Record to be fetched
     * @throws IllegalArgumentException if id is null
     * @return Record with given ID
     */
    Record findById(Long id);

    /**
     * Deletes Record
     * @param record Record to be deleted
     * @throws IllegalArgumentException if record is null
     */
    void deleteRecord(Record record);

    /**
     * Fetches all records
     * @return list of all Records
     */
    List<Record> getAllRecords();
}
