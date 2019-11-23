package io.egrow.frontend.scheduler_service.controller.tasks;

import io.egrow.frontend.scheduler_service.controller.TaskHandler;
import io.egrow.frontend.scheduler_service.entity.AbstractTask;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExampleTask extends AbstractTask {

    public ExampleTask(TaskHandler taskHandler) {
        super(taskHandler);
    }

    @Override
    protected void internalExecute() {
        System.out.println("I am an example task");
    }
}
