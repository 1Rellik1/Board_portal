package board.games.first.game.service;

import board.games.first.game.dto.response.CardStatePlayerBalanceDTO;
import board.games.first.game.dto.response.RollDiceResultDTO;
import board.games.first.game.dto.response.SurrenderPlayerDTO;
import board.games.first.game.entity.CardState;
import board.games.first.game.entity.Message;
import board.games.first.game.entity.Player;
import board.games.first.game.entity.Session;
import board.games.first.game.entity.repo.MessageRepository;
import board.games.first.game.entity.repo.SessionRepository;
import board.games.first.game.enums.MoveStatus;
import board.games.first.game.MessageCreator;
import board.games.first.game.mapper.PlayerMapper;
import board.games.first.game.mapper.RollDicesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static board.games.first.game.params.EventParam.START_BONUS;
import static board.games.first.game.params.InitialGameValue.*;
import static board.games.first.game.params.PlayingFieldParam.FIELD_MIN_SIZE;
import static board.games.first.game.params.PlayingFieldParam.FIELD_SIZE;
import static board.games.first.game.params.ResultMessage.SURRENDER;
import static board.games.first.game.enums.MoveStatus.MIDDLE;
import static board.games.first.game.enums.MoveStatus.START;
import static board.games.first.game.enums.PlayerRole.USER;
import static board.games.first.game.enums.PlayerStatus.LOST;
import static board.games.first.game.enums.PlayerStatus.PLAYING;
import static board.games.first.game.enums.SessionState.IN_PROGRESS;

@Service
public class SessionWebSocketService {
    private final MessageRepository messageRepository;
    private final SessionRepository sessionRepository;
    private final ChatService chatService;
    private final SessionCommonService sessionCommonService;
    private final PlayerService playerService;
    private final CardStateService cardStateService;

    public SessionWebSocketService(MessageRepository messageDAO, SessionRepository sessionDAO,
                                   ChatService chatService, SessionCommonService sessionCommonService,
                                   PlayerService playerService, CardStateService cardStateService) {
        this.messageRepository = messageDAO;
        this.sessionRepository = sessionDAO;
        this.chatService = chatService;
        this.sessionCommonService = sessionCommonService;
        this.playerService = playerService;
        this.cardStateService = cardStateService;
    }

    @Transactional
    public Player addPlayerToSession(String sessionId, String playerName) {
        Session session = sessionCommonService.getSession(sessionId);
        playerService.savePlayer(sessionId, playerName, USER);
        Player player = playerService.getPlayer(sessionId, playerName);

        Message message = MessageCreator.createAddPlayerMessage(playerName);
        messageRepository.save(message);
        session.getPlayers().add(player);
        session.getMessages().add(message);

        return player;
    }


    @Transactional
    public void startGame(String sessionId, String nextPlayer) {
        Session session = sessionCommonService.getSession(sessionId);
        session.setCurrentPlayer(nextPlayer);
        session.setMoveStatus(START);
        session.setState(IN_PROGRESS);
        sessionRepository.saveAndFlush(session);
        Message message = MessageCreator.createStartGameMessage();
        messageRepository.save(message);
        session.getMessages().add(message);
    }

    @Transactional
    public String getNextPlayer(String sessionId, String previousPLayer) {
        List<Player> players = sessionCommonService.getSession(sessionId).getPlayers().stream()
                .sorted(Comparator.comparing(Player::getId)).toList();
        int count = players.size();

        ArrayDeque<Player> deque = new ArrayDeque<>();
        int idx = 0;

        for (int i = 0; i < count; i++) {
            Player player = players.get(i);

            if (previousPLayer.equals(player.getName())) {
                idx = i;
                break;
            }

            deque.addLast(player);
        }

        for (int i = count - 1; i > idx; i--) {
            deque.addFirst(players.get(i));
        }

        Player nextPlayer = null;

        while (!deque.isEmpty()) {
            Player player = deque.pop();

            if (player.getStatus() == PLAYING) {
                nextPlayer = player;
                break;
            }
        }

        String nextPlayerName = nextPlayer.getName();
        Session session = sessionCommonService.getSession(sessionId);
        session.setCurrentPlayer(nextPlayerName);
        session.setMoveStatus(START);
        sessionRepository.saveAndFlush(session);
        return nextPlayerName;
    }

    @Transactional(readOnly = true)
    public MoveStatus getCurrentMoveStatus(String sessionId) {
        return sessionCommonService.getSession(sessionId).getMoveStatus();
    }

    @Transactional
    public SurrenderPlayerDTO getSurrenderPlayer(String sessionId, String playerName) {
        playerService.updatePlayerStatus(LOST, sessionId, playerName);
        chatService.addCommonMessageToChatHistory(sessionId, playerName, SURRENDER);

        Session session = sessionCommonService.getSession(sessionId);
        List<CardState> cardStates =session.getCardStates()
                .stream()
                .filter(cs -> Objects.equals(cs.getOwnerName(), playerName))
                .collect(Collectors.toList());
        cardStates.forEach(cs -> {
            cs.setOwnerName(INITIAL_CURRENT_PLAYER_NAME);
            cs.setCurrentFine(INITIAL_CARD_FINE);
            cs.setLevel(INITIAL_CARD_LEVEL);
        });
        cardStateService.saveCardStates(cardStates);

        return PlayerMapper.surrenderPlayerToDTO(playerName, LOST, cardStates);
    }

    public RollDiceResultDTO movePlayer(Integer diceResult, String sessionId, String playerName) {
        Player player = playerService.getPlayer(sessionId, playerName);
        int position = player.getPosition();
        int potentialPos = player.getPosition() + diceResult;
        int newPosition;
        if (potentialPos < FIELD_MIN_SIZE) {
            newPosition = FIELD_SIZE + potentialPos;
        } else if (potentialPos > FIELD_SIZE) {
            newPosition = potentialPos - FIELD_SIZE;
        } else {
            newPosition = potentialPos;
        }
        playerService.updatePlayerPosition(diceResult, sessionId, playerName);

        Message updMessage = null;
        Long balance = null;
        if (newPosition > diceResult) {
            balance = player.getBalance() + START_BONUS;
            playerService.updatePlayerBalance(balance, sessionId, playerName);
            updMessage = MessageCreator.createStartBonusMessage(playerName);
        }
        Session session = sessionCommonService.getSession(sessionId);
        session.setMoveStatus(MIDDLE);
        sessionRepository.saveAndFlush(session);
        Message message = MessageCreator.createRollDicesMessage(playerName, diceResult);
        messageRepository.save(message);
        session.getMessages().add(message);

        if (updMessage != null) {
            messageRepository.save(updMessage);
            session.getMessages().add(updMessage);
        }

        return RollDicesMapper.rollResultTODTO(diceResult, playerName, diceResult, balance);
    }

    @Transactional
    public CardStatePlayerBalanceDTO acceptOffer(String sessionId, String playerName, String ownerName, Long money, List<String> cardIds) {
        Player player = playerService.getPlayer(sessionId, playerName);
        Long newBalance = player.getBalance() + money;
        playerService.updatePlayerBalance(newBalance, sessionId, playerName);

        List<CardState> cardStates = cardStateService.findByCardIds(cardIds);
        cardStates.forEach(cs -> cs.setOwnerName(ownerName));
        cardStateService.saveCardStates(cardStates);

        return PlayerMapper.changeBalanceCardStateToDTO(playerName, newBalance, cardStates);
    }
}
