package com.fushin.easyrpc.logging.slf4j;

import com.fushin.easyrpc.core.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 丁成文
 * @date 2022/2/22
 */
class Slf4jLogger implements Logger {
    private final org.slf4j.Logger logger;

    public Slf4jLogger(Class type){
        logger = LoggerFactory.getLogger(type);
    }


    @Override
    public String getName() {
        return this.logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        this.logger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        this.logger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        this.logger.trace(format, arg1, arg1);
    }

    @Override
    public void trace(String format, Object... arguments) {
        this.logger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        this.logger.trace(msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        this.logger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        this.logger.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        this.logger.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        this.logger.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        this.logger.debug(msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        this.logger.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        this.logger.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        this.logger.info(format, arg1, arg1);
    }

    @Override
    public void info(String format, Object... arguments) {
        this.logger.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        this.logger.info(msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        this.logger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        this.logger.warn(format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        this.logger.warn(format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        this.logger.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        this.logger.warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        this.logger.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        this.logger.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        this.logger.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        this.logger.error(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        this.logger.error(msg, t);
    }
}
