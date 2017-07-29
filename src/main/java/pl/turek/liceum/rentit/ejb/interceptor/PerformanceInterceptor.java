package pl.turek.liceum.rentit.ejb.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PerformanceInterceptor {

    @AroundInvoke
    public Object additionalInvokeForMethod(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder("Przetwarzanie metody biznesowej: ");
        sb.append(invocation.getTarget().getClass().getName()).append('.').append(invocation.getMethod().getName());
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            sb.append(" trwało: ").append(duration).append(" ms");
            Logger.getGlobal().log(Level.INFO, sb.toString());
        }
    }
}
