/*
 * @ (#) ComputationTask.java     1.0    3/10/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/10/2026
 * @version:    1.0
 */

import java.util.concurrent.Callable;

public class ComputationTask implements Callable<Long> {
        private String taskName;

    public ComputationTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public Long call() throws Exception {
        Long sum=0L;
        for(int i=1;i<1000;i++) {
            sum += i;
        Thread.sleep(10);
        }

        return sum;
    }
}
