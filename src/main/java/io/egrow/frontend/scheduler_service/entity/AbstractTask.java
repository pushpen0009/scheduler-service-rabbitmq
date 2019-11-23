package io.egrow.frontend.scheduler_service.entity;

import io.egrow.frontend.scheduler_service.controller.TaskHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * This class includes the self-registration of every task in the task handler.
 * It also includes an internal execution function which all tasks need to
 * implement.
 *
 * The normal execute function is implemented in here and allows logging of
 * the task before its actual execution and after the execution.
 */
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class AbstractTask implements Task {

    TaskHandler taskHandler;

    protected abstract void internalExecute();

    @Override
    public void execute() {
        this.internalExecute();
    }
}
