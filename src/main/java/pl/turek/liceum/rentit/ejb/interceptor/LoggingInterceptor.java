package pl.turek.liceum.rentit.ejb.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {

    @Resource
    private SessionContext sessionContext;

	
	@AroundInvoke
    public Object additionalInvokeForMethod(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder("Wywołanie metody biznesowej: ");
        sb.append(invocation.getTarget().getClass().getName()).append('.').append(invocation.getMethod().getName());
        sb.append(" z tożsamością użytkownika: " + sessionContext.getCallerPrincipal().getName());
        try {
            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" z parametrem typu:" + param.getClass().getName() + " o wartości" + param.toString());
                    } else {
                        sb.append(" z parametrem null");
                    }
                }
            }

            Object result = invocation.proceed();

            if (result != null) {
                sb.append(" zwrócono obiekt typu: " + result.getClass().getName() + " o wartości: " + result.toString());
            } else {
                sb.append(" zwrócono wartość null");
            }

            return result;
        } catch (Exception ex) {
            sb.append(" wystapil wyjatek typu: " + ex.getClass().getName());
            throw ex; //ponowne zgloszenie wyjatku
        } finally {
            Logger.getGlobal().log(Level.INFO, sb.toString());
        }
    }
}
