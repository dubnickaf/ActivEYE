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
     * @throws IllegalArgumentException if recordDTO is null
     */
    void createRecord(RecordDTO recordDTO);

    /**
     * Updates RecordDTO
     * @param recordDTO RecordDTO to be updated
     * @throws IllegalArgumentException if recordDTO is null
     */
    void updateRecord(RecordDTO recordDTO);

    /**
     * Fetches RecordDTO by given ID
     * @param id ID of RecordDTO to be fetched
     * @throws IllegalArgumentException if id is null
     * @return RecordDTO with given ID
     */
    RecordDTO findById(Long id);

    /**
     * Deletes RecordDTO
     * @param recordDTO RecordDTO to be deleted
     * @throws IllegalArgumentException if recordDTO is null
     */
    void deleteRecord(RecordDTO recordDTO);

    /**
     * Gets all RecordDTO objects
     * @return List of all RecordDTO
     */
    List<RecordDTO> getAllRecords();
}
