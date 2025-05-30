package com.weykko.librarysystem.eventlistener;

import com.weykko.librarysystem.eventlistener.event.DatabaseChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
public class GlobalEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleDatabaseChangedEvent(DatabaseChangedEvent event) {
        log.info("Updated record in {} table with ID {}", event.table().toUpperCase(), event.id());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleDatabaseRollbackEvent(DatabaseChangedEvent event) {
        log.info("Rolled back changes for record in {} table with ID {}", event.table().toUpperCase(), event.id());
    }
}
