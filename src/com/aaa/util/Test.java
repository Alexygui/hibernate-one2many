package com.aaa.util;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aaa.entity.Grade;
import com.aaa.entity.Student;

/*
 * 单向一对多关系（班级-->学生）
 */
public class Test {
	public static void main(String[] args) {
		add();
	}
	
	//将学生添加到班级
	public static void add() {
		Grade grade = new Grade("Java一班", "Java软件开发一班");
		Student student1 = new Student("张三", "男");
		Student student2 = new Student("李小花", "女");
		
		//在学生表中添加对应的班级编号，在班级中添加学生编号，建立对应关系
		grade.getStudents().add(student1);
		grade.getStudents().add(student2);
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
		session.save(student1);
		session.save(student2);
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
}
