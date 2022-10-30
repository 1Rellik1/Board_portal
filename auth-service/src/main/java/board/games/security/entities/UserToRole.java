package board.games.security.entities;

public class UserToRole {

    /**
     * Таблица связи между бланками и маркерами бланков документа.
     */
    public static final String USER_2_ROLE_TABLE_NAME = "user_to_role";

    public static class ColumnName {

        public static final String U2RT_USER_ID = "user_id";

        public static final String U2RT_ROLE_ID = "role_id";
    }
}
