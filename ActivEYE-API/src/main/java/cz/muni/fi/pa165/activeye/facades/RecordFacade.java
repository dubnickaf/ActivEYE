package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.dto.RecordDTO;

import java.util.List;

/**
 * @author spriadka
 */
public interface RecordFacade {
    /**
     * Creates RecordDTO
     * @param recordDTO Record to be created
     */
    void createRecord(RecordDTO recordDTO);

    /**
     * Updates RecordDTO
     * @param recordDTO RecordDTO to be updated
     */
    void updateRecord(RecordDTO recordDTO);

    /**
     * Fetches RecordDTO by given ID
     * @param id ID of RecordDTO to be fetched
     * @return RecordDTO with given ID
     */
    RecordDTO findById(Long id);

    /**
     * Deletes RecordDTO
     * @param recordDTO RecordDTO to be deleted
     */
    void deleteRecord(RecordDTO recordDTO);

    /**
     * Gets all RecordDTO objects
     * @return List of all RecordDTO
     */
    List<RecordDTO> getAllRecords();
}
