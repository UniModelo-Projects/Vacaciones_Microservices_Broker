package com.exam.broker.handler;

import com.exam.broker.model.RetryJob;

public abstract class BaseHandler {
    protected BaseHandler next;

    public void setNext(BaseHandler next) {
        this.next = next;
    }

    public abstract void handle(RetryJob job) throws Exception;
}
