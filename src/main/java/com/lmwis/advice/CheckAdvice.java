package com.lmwis.advice;

import com.lmwis.error.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-03 18:42
 * @Version 1.0
 */
@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handleBindException(WebExchangeBindException e){


        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity handleCheckException(CheckException e){


        return new ResponseEntity<>(e.getFiledName()+":错误的值 "+e.getFiledValue()
                , HttpStatus.BAD_REQUEST);
    }

    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e->e.getField()+":"+e.getDefaultMessage())
                .reduce("",(s1, s2) ->s1+"\n"+s2 );
    }
}
