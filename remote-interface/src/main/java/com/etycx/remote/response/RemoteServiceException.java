package com.etycx.remote.response;

import lombok.Data;

/**
 * @author 武海升
 * @desc 远程服务异常
 * @date  2019-08-07
 */
@Data
public class RemoteServiceException extends RuntimeException  {

    private int error;

    private Object data;

    public RemoteServiceException() {
        super();
    }

    public RemoteServiceException(int error, String message) {
        super(message);
        this.error = error;
        this.data = false;
    }

}
