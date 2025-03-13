package com.music.taskscheduler.service.workflow;

import com.music.taskscheduler.model.PublishRequestState;
import io.nflow.engine.workflow.curated.State;
import io.nflow.engine.workflow.definition.*;
import org.springframework.stereotype.Service;

@Service
public class PublishWorkflow extends WorkflowDefinition {

    public static final String TYPE = "PUBLISH";

    public static final String REQUEST_STATE = "REQUEST_STATE";

    public static final String RETRY_COUNTER = "RETRY_COUNTER";

    public static final State ERROR_STATE = new State("error", WorkflowStateType.end, "Error State");

    public static final State DONE = new State("done", WorkflowStateType.end, "Done State");

    public static final State PUBLISH_SONG_OR_ALBUM_STATE = new State("publish", WorkflowStateType.start, "Publish song or album");



    public PublishWorkflow() {
        super(TYPE, PUBLISH_SONG_OR_ALBUM_STATE, ERROR_STATE);
        permit(PUBLISH_SONG_OR_ALBUM_STATE, DONE);
    }

    public NextAction publish(StateExecution execution, @StateVar(value = REQUEST_STATE) PublishRequestState requestState, @StateVar(value = RETRY_COUNTER) Integer retryCounter){

        return NextAction.moveToState(DONE,"done publishing....");
    }

}

