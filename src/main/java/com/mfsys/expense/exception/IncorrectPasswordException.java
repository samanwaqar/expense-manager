package com.mfsys.expense.exception;

import com.mfsys.expense.constants.ErrorCodes;
import com.mfsys.expense.constants.ErrorMessages;

public class IncorrectPasswordException extends CustomException {

    public IncorrectPasswordException() {
        super(
                ErrorCodes.INCORRECT_PASSWORD,
                ErrorMessages.INCORRECT_PASSWORD
        );
    }
}
