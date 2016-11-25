package cz.muni.fi.pa165.activeye.facades;

import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.dto.RecordDTO;
import cz.muni.fi.pa165.activeye.mapping.BeanMappingService;
import cz.muni.fi.pa165.activeye.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 20.11.2016.
 * @author spriadka
 */
@Service
@Transactional
public class RecordFacadeImpl implements RecordFacade{

    @Inject
    private RecordService recordService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createRecord(RecordDTO recordDTO) {
        if (recordDTO == null){
            throw new IllegalArgumentException("Record to be created cannot be null");
        }
        Record record = beanMappingService.mapTo(recordDTO,Record.class);
        recordService.createRecord(record);
    }

    @Override
    public void updateRecord(RecordDTO recordDTO) {
        if (recordDTO == null){
            throw new IllegalArgumentException("Record to be created cannot be null");
        }
        Record record = beanMappingService.mapTo(recordDTO,Record.class);
        recordService.updateRecord(record);
    }

    @Override
    public RecordDTO findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Record to be created cannot be null");
        }
        Record record = recordService.findById(id);
        return (record == null) ? null : beanMappingService.mapTo(record,RecordDTO.class);
    }

    @Override
    public void deleteRecord(RecordDTO recordDTO) {
        if (recordDTO == null){
            throw new IllegalArgumentException("Record to be created cannot be null");
        }
        Record record = beanMappingService.mapTo(recordDTO,Record.class);
        recordService.deleteRecord(record);
    }

    @Override
    public List<RecordDTO> getAllRecords() {
        List<Record> allRecords = recordService.getAllRecords();
        return (allRecords == null) ? null : beanMappingService.mapTo(allRecords,RecordDTO.class);
    }
}
