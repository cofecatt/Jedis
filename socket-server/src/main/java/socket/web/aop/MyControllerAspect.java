package socket.web.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Author: Cafecatt
 * @Date: 2022/2/22 7:56
 */
@Aspect
@Component
public class MyControllerAspect {

    private final static Logger logger = LoggerFactory.getLogger(MyControllerAspect.class);


    @Pointcut("@annotation(socket.web.anno.MyController)")
    public void myController() {}

    /**
     * 在切点之前织入
     */
    @Before("myController()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    /**
     * 在切点之后织入
     * @throws Throwable
     */
    @After("myController()")
    public void doAfter() throws Throwable {

    }

    /**
     * 环绕
     */
    @Around("myController()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开始时间
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        logger.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        // 结束后打个分隔线，方便查看
        logger.info("=========================================== End ===========================================");
        return result;
    }

}
