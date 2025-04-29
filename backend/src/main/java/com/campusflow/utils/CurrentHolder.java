package com.campusflow.utils;

// 用于存储当前线程的员工ID
public class CurrentHolder {
    // 用于存储当前线程的员工ID
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的员工ID
     * @param employeeId 员工ID
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取当前线程的员工ID
     * @return 员工ID
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 移除当前线程的员工ID
     */ 
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
