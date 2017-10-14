package com.alimmit.ionic.chatclientserver.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Room extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 8553972704081064721L;

    public static Room create(final String name) {
        return new Room(name);
    }

    public Room() { }

    @JsonCreator
    public Room(final String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public Room setName(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Room{" + "name='" + name + '\'' + "} " + super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final Room room = (Room) o;

        return name != null ? name.equals(room.name) : room.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
