package com.mfsys.expense.exception;

import com.mfsys.expense.constants.ErrorCodes;
import com.mfsys.expense.constants.ErrorMessages;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(
                ErrorCodes.USER_NOT_FOUND,
                ErrorMessages.USER_NOT_FOUND
        );
    }
}
