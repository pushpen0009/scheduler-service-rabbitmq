package io.egrow.frontend.scheduler_service.entity;

/**
 * Is the interface for a scheduled task. It has only one function which
 * executes the actual implementation of the task. When implementing the
 * task keep in mind that the task should be stateless.
 */
public interface Task {

    void execute();
}
