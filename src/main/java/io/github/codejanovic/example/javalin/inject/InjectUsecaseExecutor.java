package io.github.codejanovic.example.javalin.inject;


import org.jusecase.Usecase;
import org.jusecase.UsecaseExecutorException;
import org.jusecase.executors.AbstractUsecaseExecutor;
import org.jusecase.inject.Injector;

public class InjectUsecaseExecutor extends AbstractUsecaseExecutor {

    protected final Injector injector = Injector.getInstance();

    public void addUsecase(Class<? extends Usecase> usecaseClass) {
        try {
            super.addUsecase(getRequestClass(usecaseClass), usecaseClass.newInstance());
        } catch (Exception e) {
            throw new UsecaseExecutorException("Failed to initialize usecase " + usecaseClass, e);
        }
    }

    @Override
    protected Object resolveUsecase(Object usecase) {
        return usecase;
    }
}
