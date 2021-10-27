package com.huazie.jpa.concurrency;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.jpa.common.entity.Student;
import com.huazie.jpa.common.service.interfaces.IStudentSV;

import java.util.concurrent.ExecutorService;

/**
 * 测试 EntityManager 并发 persist
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class InsertTest {
    
    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(InsertTest.class);

    public static void testInsert(ExecutorService executorService, IStudentSV studentSV) {
        executorService.execute(new FleaInsertRunable0(studentSV));
        executorService.execute(new FleaInsertRunable1(studentSV));
        executorService.execute(new FleaInsertRunable2(studentSV));
    }

    static class FleaInsertRunable0 implements Runnable {

        IStudentSV studentSV;

        FleaInsertRunable0(IStudentSV studentSV) {
            this.studentSV = studentSV;
        }

        @Override
        public void run() {
            try {
                Student student = new Student();
                student.setStuName("张三5");
                student.setStuAge(18);
                student.setStuSex(1);
                student.setStuState(1);
                studentSV.save(student);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaInsertRunable1 implements Runnable {

        IStudentSV studentSV;

        FleaInsertRunable1(IStudentSV studentSV) {
            this.studentSV = studentSV;
        }

        @Override
        public void run() {
            try {
                Student student = new Student();
                student.setStuName("李四5");
                student.setStuAge(19);
                student.setStuSex(1);
                student.setStuState(1);
                studentSV.save(student);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }

    static class FleaInsertRunable2 implements Runnable {

        IStudentSV studentSV;

        FleaInsertRunable2(IStudentSV studentSV) {
            this.studentSV = studentSV;
        }

        @Override
        public void run() {
            try {
                Student student = new Student();
                student.setStuName("王二麻子5");
                student.setStuAge(20);
                student.setStuSex(1);
                student.setStuState(1);
                studentSV.save(student);
            } catch (CommonException e) {
                LOGGER.error("Exception = {}", e);
            }
        }
    }
}
