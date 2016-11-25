package cz.muni.fi.pa165.activeye.dao.impl;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.entities.Record;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 24.10.2016.
 * @author spriadka
 */
@Transactional
@Repository
public class RecordDaoImpl implements RecordDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Record> getAllRecords() {

        return em.createQuery("SELECT r FROM Record r",Record.class).getResultList();
    }

    @Override
    public void createRecord(Record record) {
        em.persist(record);
    }

    @Override
    public Record getRecord(Long id) {
        if (id == null){
            throw new IllegalArgumentException("ID of record cannot be null");
        }
        try {
            return em.find(Record.class, id);
        }
        catch (NoResultException nre){
            return null;
        }
    }

    @Override
    public void deleteRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record cannot be null");
        }
        em.remove(em.contains(record) ? record : em.merge(record));
    }

    @Override
    public void updateRecord(Record record) {
        if (record == null){
            throw new IllegalArgumentException("Record cannot be null");
        }
        em.merge(record);
    }
}
