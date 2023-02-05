package game.info.service.exception.mapper;

import game.info.service.exception.data.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseErrorMapper {

    public static ResponseEntity errorToEntity(ApiError error) {
        return new ResponseEntity(error, error.getError());
    }
}
