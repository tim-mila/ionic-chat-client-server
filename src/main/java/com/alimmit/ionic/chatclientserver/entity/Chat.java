package com.alimmit.ionic.chatclientserver.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(value = ChatEntityListener.class)
public class Chat extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 3961221518918886674L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date at;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String text;

    public Date getAt() {
        return at;
    }

    public Chat setAt(final Date at) {
        this.at = at;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Chat setRoom(final Room room) {
        this.room = room;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Chat setUsername(final String username) {
        this.username = username;
        return this;
    }

    public String getText() {
        return text;
    }

    public Chat setText(final String text) {
        this.text = text; return this;
    }

    @Override
    public String toString() {
        return "Chat{" + "at=" + at + ", room=" + room + ", username='" + username + '\'' + "} " + super.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final Chat chat = (Chat) o;

        if (at != null ? !at.equals(chat.at) : chat.at != null) return false;
        if (room != null ? !room.equals(chat.room) : chat.room != null) return false;
        if (username != null ? !username.equals(chat.username) : chat.username != null) return false;
        return text != null ? text.equals(chat.text) : chat.text == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (at != null ? at.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
