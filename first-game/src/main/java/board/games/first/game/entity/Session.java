package board.games.first.game.entity;

import board.games.first.game.enums.MoveStatus;
import board.games.first.game.enums.SessionState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static board.games.first.game.enums.SessionState.NEW;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "session")
public class Session {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "state", nullable = false)
    @Enumerated(STRING)
    private SessionState state = NEW;

    @Column(name = "current_player")
    private String currentPlayer;

    @Column(name = "move_status")
    @Enumerated(STRING)
    private MoveStatus moveStatus;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "players")
    private List<Player> players = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "card_states")
    private List<CardState> cardStates = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "messages")
    private List<Message> messages = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(MoveStatus moveStatus) {
        this.moveStatus = moveStatus;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<CardState> getCardStates() {
        return cardStates;
    }

    public void setCardStates(List<CardState> cardStates) {
        this.cardStates = cardStates;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
