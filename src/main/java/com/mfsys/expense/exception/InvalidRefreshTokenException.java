package com.mfsys.expense.exception;

import com.mfsys.expense.constants.ErrorCodes;
import com.mfsys.expense.constants.ErrorMessages;

public class InvalidRefreshTokenException extends CustomException {

    public InvalidRefreshTokenException() {
        super(
                ErrorCodes.INVALID_REFRESH_TOKEN,
                ErrorMessages.INVALID_REFRESH_TOKEN
        );
    }
}
