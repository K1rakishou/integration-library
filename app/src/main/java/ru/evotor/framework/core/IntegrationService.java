package ru.evotor.framework.core;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import ru.evotor.framework.core.action.processor.ActionProcessor;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */
public class IntegrationService extends Service {

    private final IIntegrationManager.Stub binder = new IIntegrationManager.Stub() {
        @Override
        public void call(IIntegrationManagerResponse response, String action, Bundle bundle) throws RemoteException {
            ActionProcessor processor = processors.get(action);
            if (processor != null) {
                processor.process(action, response, bundle);
            }
        }
    };

    private ConcurrentHashMap<String, ActionProcessor> processors = new ConcurrentHashMap<>();

    @Override
    public IBinder onBind(Intent intent) {
        return binder.asBinder();
    }

    protected final void registerProcessor(String action, ActionProcessor processor) {
        Objects.requireNonNull(action);
        Objects.requireNonNull(processor);

        processors.put(action, processor);
    }
}