package com.aaa.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aaa.entity.Grade;
import com.aaa.entity.Student;
import com.aaa.util.HibernateUtil;

/*
 * 测试单向多对一的关联关系（学生-->班级）
 */
public class Test02 {
	public static void main(String[] args) {
		save();
	}
	
	//保存学生信息
	public static void save() {
		Grade grade = new Grade("Java一班", "Java开发软件一班");
		Student student1 = new Student("张三", "男");
		Student student2 = new Student("李小花", "女");
		//设置关联关系
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);
		student1.setGrade(grade);
		student2.setGrade(grade);
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		//grade.hbm.xml中添加级联的配置
		session.save(grade);
		//session.save(student1);
		//session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
}
