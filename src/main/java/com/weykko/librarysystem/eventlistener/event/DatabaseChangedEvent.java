package com.weykko.librarysystem.eventlistener.event;

public record DatabaseChangedEvent(String table, Long id){
}
